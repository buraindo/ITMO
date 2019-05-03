package ru.ifmo.rain.ibragimov.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.function.BiConsumer;

import static java.lang.String.format;

class TaskFactory {

    static Runnable getSendTask(final SocketAddress address, final String prefix, final int number, final int perThread, BiConsumer<DatagramSocket, DatagramPacket> retry) {
        return () -> {
            try (var socket = new DatagramSocket()) {
                socket.setSoTimeout(25);
                for (var i = 0; i < perThread; i++) {
                    var message = Util.getRequestMessage(prefix, number, i);
                    var bytes = message.getBytes(StandardCharsets.UTF_8);
                    var request = new DatagramPacket(bytes, bytes.length, address);
                    retry.accept(socket, request);
                }
            } catch (SocketException ignored) {
                System.err.println(format("Failed to send/receive data, address: %s.", address));
            }
        };
    }

    private static Runnable getRespondTask(DatagramSocket socket, DatagramPacket request) {
        return () -> {
            var message = "Hello, " + new String(request.getData(), request.getOffset(), request.getLength(), StandardCharsets.UTF_8);
            var bytes = message.getBytes(StandardCharsets.UTF_8);
            try {
                socket.send(new DatagramPacket(bytes, bytes.length, request.getSocketAddress()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    static Runnable getServerTask(DatagramSocket socket, ExecutorService receiver) {
        return () -> {
            try {
                while (!socket.isClosed()) {
                    var request = new DatagramPacket(new byte[socket.getReceiveBufferSize()], socket.getReceiveBufferSize());
                    socket.receive(request);
                    receiver.submit(TaskFactory.getRespondTask(socket, request));
                }
            } catch (RejectedExecutionException ignored) {
                System.err.println("All servers are busy now, please, wait for them to maintain your request.");
            } catch (SocketException ignored) {
                System.err.println(format("Unable to create a datagram socket with address %s.", socket.getInetAddress().getHostName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

}
