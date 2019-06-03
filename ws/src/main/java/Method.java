import java.util.List;

public class Method {


    private String name;

    private List<Param> inputParams;

    private Param outputParam;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Param> getInputParams() {
        return inputParams;
    }

    public void setInputParams(List<Param> inputParams) {
        this.inputParams = inputParams;
    }

    public Param getOutputParam() {
        return outputParam;
    }

    public void setOutputParam(Param outputParam) {
        this.outputParam = outputParam;
    }
}
