package com.zhangyx.learn.io;

import java.io.IOException;
import java.net.ServerSocket;

public class BioServer{

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9091);
        while(true){
            serverSocket.accept();

        }
    }
}
