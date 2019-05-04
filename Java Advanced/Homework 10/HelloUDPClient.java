package ru.ifmo.rain.ibragimov.udp;

import info.kgeorgiy.java.advanced.hello.HelloClient;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.String.format;

public class HelloUDPClient implements HelloClient {

    private void retry(DatagramSocket socket, DatagramPacket request) {
        while (!socket.isClosed()) {
            try {
                socket.send(request);
                var response = new DatagramPacket(new byte[socket.getReceiveBufferSize()], socket.getReceiveBufferSize());
                socket.receive(response);
                var message = new String(response.getData(), response.getOffset(), response.getLength(), StandardCharsets.UTF_8);
                var expected = new String(request.getData());
                if (message.contains(expected)) {
                    return;
                }
            } catch (SocketTimeoutException ignored) {}
            catch (SocketException ignored) {
                System.err.println(format("Unable to create a datagram socket with address %s.", socket.getInetAddress().getHostName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void run(final SocketAddress address, final String prefix, final int threadsNumber, final int perThread, final ExecutorService sender) {
        for (var i = 0; i < threadsNumber; i++) {
            var task = TaskFactory.getSendTask(address, prefix, i, perThread, this::retry);
            sender.submit(task);
        }
        Util.close(sender);
    }

    @Override
    public void run(String addressStr, int port, String prefix, int threadsNumber, int perThread) {
        var address = new InetSocketAddress(addressStr, port);
        var sender = Executors.newFixedThreadPool(threadsNumber);
        run(address, prefix, threadsNumber, perThread, sender);
    }

    public static void main(String[] args) {
        Util.validateInput(args, false, 5);
        try {
            var address = args[0];
            var port = Integer.parseInt(args[1]);
            var prefix = args[2];
            var threadsNumber = Integer.parseInt(args[3]);
            var perThread = Integer.parseInt(args[4]);
            new HelloUDPClient().run(address, port, prefix, threadsNumber, perThread);
        } catch (NumberFormatException nfe) {
            System.err.println("Port, threads number and requests per thread number must be integers!");
        }
    }

}
