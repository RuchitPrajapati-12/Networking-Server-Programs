// Program to communicate between server and client using TCP server.
// Client side

import java.io.*;
import java.net.*;

class TCPClient2 {
    public static void main(String[] args) {
        try {
            String HOST = "127.0.0.1";
            int PORT = 5000;

            // Create a socket and connect to the server
            Socket clientSocket = new Socket(HOST, PORT);

            // Receive data from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String data = in.readLine();
            System.out.println("Received from server: " + data);

            // Send a message to the server
            String message = "Hello, server!";
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(message);

            // Close the socket when communication is finished with the server
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

/*
OUTPUT
Received from server: Hello, client!
*/