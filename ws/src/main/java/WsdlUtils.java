import com.alibaba.fastjson.JSONObject;
import com.ibm.wsdl.PartImpl;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.wsdl.*;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;

public class WsdlUtils {


    public static void main(String[] args) throws WSDLException, ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse("/Users/zyx/Desktop/query.xml");
        WSDLFactory factory = WSDLFactory.newInstance();
        WSDLReader reader=factory.newWSDLReader();
        reader.setFeature("javax.wsdl.verbose", true);
        reader.setFeature("javax.wsdl.importDocuments", true);
        Definition def = reader.readWSDL(null,"/Users/zyx/Desktop/query.xml");
        Map bindings = def.getBindings();
        Iterator bindingIterator = bindings.values().iterator();
        JSONObject jsonObject = new JSONObject();
        List<Method> methods = new ArrayList<Method>();
        while(bindingIterator.hasNext()){
            Binding binding = (Binding) bindingIterator.next();
            List<Operation> operations = binding.getPortType().getOperations();
            for(Operation operation:operations){
                Method method = new Method();
                String methodName = operation.getName();
                method.setName(methodName);
                List<String> paramterOrdering = operation.getParameterOrdering();
                List<Param> inputParams = new ArrayList<Param>();
                Map<String, PartImpl> inputs = (Map<String,PartImpl>)operation.getInput().getMessage().getParts();
                //方法入参
                for(Map.Entry<String,PartImpl> entry:inputs.entrySet()){
                    String paramName = entry.getKey();
                    int paramOrder = paramterOrdering.indexOf(paramName);
                    String paramType = entry.getValue().getTypeName().getLocalPart();
                    Param param = new Param();
                    param.setName(paramName);
                    param.setType(paramType);
                    param.setOrder(String.valueOf(paramOrder));
                    inputParams.add(param);
                }
                method.setInputParams(inputParams);
                //方法出参
                Map<String, PartImpl> ouotputs = (Map<String,PartImpl>)operation.getOutput().getMessage().getParts();
                for(Map.Entry<String,PartImpl> entry:ouotputs.entrySet()){
                    Param param = new Param();
                    String paramName = entry.getKey();
                    String paramType = entry.getValue().getTypeName().getLocalPart();
                    param.setName(paramName);
                    param.setType(paramType);
                    param.setOrder("0");
                    method.setOutputParam(param);
                    break;
                }
                methods.add(method);
            }
            System.out.println(JSONObject.toJSONString(methods));
        }
    }




}
