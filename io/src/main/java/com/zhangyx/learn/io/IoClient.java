package com.zhangyx.learn.io;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class IoClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        // TODO 创建多个线程，模拟多个客户端连接服务端
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 3333);
//                while (true) {
                    try {
                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
//                }
            } catch (IOException e) {
            }
        }).start();

//        currentRequest();

    }


    public static  void currentRequest() throws InterruptedException, IOException {
        int n = 1000;
        CountDownLatch latch = new CountDownLatch(1);
        for(int i = 0; i < n;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Socket socket = new Socket("127.0.0.1", 3333);
                        System.out.println(Thread.currentThread().getName()+"准备就绪");
                        latch.await();
                        System.out.println(Thread.currentThread().getName()+"开始执行");
                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            latch.countDown();
        }

    }

}
