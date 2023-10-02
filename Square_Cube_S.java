// Program to generate square and cube of given number UDP server.
// Server side

import java.io.*;
import java.net.*;

class UDPServer {
    public static void main(String[] args) {
        try {
            String UDP_IP_ADDRESS = "127.0.0.1";
            int UDP_PORT_NO = 6789;

            DatagramSocket serverSocket = new DatagramSocket(UDP_PORT_NO, InetAddress.getByName(UDP_IP_ADDRESS));
            byte[] receiveData = new byte[1024];

            System.out.println("UDP server up and listening...");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String data = new String(receivePacket.getData(), 0, receivePacket.getLength());
                int num = Integer.parseInt(data);

                int square = num * num;
                int cube = num * num * num;

                System.out.println("Received number: " + num);
                System.out.println("Square: " + square);
                System.out.println("Cube: " + cube);

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // Send square and cube results back to the client
                String squareStr = String.valueOf(square);
                String cubeStr = String.valueOf(cube);

                DatagramPacket squarePacket = new DatagramPacket(squareStr.getBytes(), squareStr.length(), clientAddress, clientPort);
                DatagramPacket cubePacket = new DatagramPacket(cubeStr.getBytes(), cubeStr.length(), clientAddress, clientPort);

                serverSocket.send(squarePacket);
                serverSocket.send(cubePacket);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

/*
OUTPUT
UDP server up and listening...
Received number: 12
Square: 144
Cube: 1728
*/