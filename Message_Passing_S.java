// Program to communicate between server and client using TCP server.
// Server side 

import java.io.*;
import java.net.*;

class TCPServer2 {
    public static void main(String[] args) {
        try {
            String HOST = "127.0.0.1";
            int PORT = 5000;

            // Create a server socket and bind it to the IP address and port
            ServerSocket serverSocket = new ServerSocket(PORT, 50, InetAddress.getByName(HOST));

            System.out.println("Waiting for incoming connections on " + HOST + ":" + PORT);

            // Accept incoming connections and create a new socket for each one
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connection from " + clientSocket.getInetAddress() + " has been established.");

            // Send a message to the client
            String message = "Hello, client!";
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(message);

            // Receive data from the client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String data = in.readLine();
            System.out.println("Received from client: " + data);

            // Close the socket when communication is finished with the client
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

/*
OUTPUT
Waiting for incoming connections on 127.0.0.1:5000
Connection from /127.0.0.1 has been established.
Received from client: Hello, server!
*/