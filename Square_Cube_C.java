// Program to generate square and cube of given number using UDP server.
// Client side

import java.io.*;
import java.net.*;

class UDPClient {
    public static void main(String[] args) {
        try {
            String UDP_IP_ADDRESS = "127.0.0.1";
            int UDP_PORT_NO = 6789;

            DatagramSocket clientSocket = new DatagramSocket();

            // Input from the user
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter a number: ");
            String num = userInput.readLine();

            // Send the number to the server
            byte[] sendData = num.getBytes();
            InetAddress serverAddress = InetAddress.getByName(UDP_IP_ADDRESS);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, UDP_PORT_NO);
            clientSocket.send(sendPacket);

            // Receive square and cube from the server
            byte[] receiveData = new byte[1024];
            DatagramPacket squarePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(squarePacket);
            String square = new String(squarePacket.getData(), 0, squarePacket.getLength());

            DatagramPacket cubePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(cubePacket);
            String cube = new String(cubePacket.getData(), 0, cubePacket.getLength());

            // Print the results
            System.out.println("Square: " + square);
            System.out.println("Cube: " + cube);

            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

/*
OUTPUT
Enter a number: 12
Square: 144
Cube: 1728
*/