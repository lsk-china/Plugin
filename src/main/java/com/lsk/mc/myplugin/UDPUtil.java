package com.lsk.mc.myplugin;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.function.Consumer;

public class UDPUtil {
    private DatagramSocket serverSocket;
    private volatile boolean serverReceiveThreadRunFlag = true;
    private volatile Consumer<String> callback;

    private class ServerReceiveThread implements Runnable {
        @Override
        public void run() {
            byte[] buf = new byte[1024 * 64];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            while(serverReceiveThreadRunFlag == false) {
                try {
                    serverSocket.receive(packet);
                    int len = packet.getLength();
                    String data = new String(buf, 0, len);
                    if (callback != null) {
                        callback.accept(data);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public UDPUtil() {
        try {
            serverSocket = new DatagramSocket(11110);
            new Thread(new ServerReceiveThread()).start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void clean() {
        serverReceiveThreadRunFlag = false;
        for(int i = 0; i < 100; i++); // wait a while for server receive thread
        serverSocket.close();
    }

    public void sendTo(String ip, int host, String content) throws Exception {
        byte[] buf = content.getBytes("UTF-8");
        DatagramPacket packet = new DatagramPacket(buf, buf.length, InetAddress.getLocalHost(), 11111);
        serverSocket.send(packet);
    }

    public void onReceive(Consumer<String> callback) {
        this.callback = callback;
    }
}
