package ru.ifmo.rain.ibragimov.udp;

import info.kgeorgiy.java.advanced.hello.HelloServer;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HelloUDPServer implements HelloServer {

    private DatagramSocket datagramSocket;
    private ExecutorService server;
    private ExecutorService receiver;

    @Override
    public void start(int port, int threadsNumber) {
        try {
            datagramSocket = new DatagramSocket(port);
            server = Executors.newSingleThreadExecutor();
            receiver = Executors.newFixedThreadPool(threadsNumber);
            server.submit(TaskFactory.getServerTask(datagramSocket, receiver));
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() {
        Util.close(server);
        Util.close(receiver);
        datagramSocket.close();
    }

    public static void main(String[] args) {
        Util.validateInput(args, true, 2);
        try {
            var port = Integer.parseInt(args[0]);
            var threadsNumber = Integer.parseInt(args[1]);
            new HelloUDPServer().start(port, threadsNumber);
        } catch (NumberFormatException nfe) {
            System.err.println("Port, threads number and requests per thread number must be integers!");
        }
    }
}
