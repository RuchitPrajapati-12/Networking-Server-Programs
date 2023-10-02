// Program to transfer file from client on server using TCP server.
// Server side

import java.io.*;
import java.net.*;

class TCPServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000); // create server socket
            Socket socket = serverSocket.accept(); // accept client connection
            System.out.println("Connected to client.");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // input stream from client
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // output stream to client

            String inputLine;
            while ((inputLine = in.readLine()) != null) { // read input from client
                if (inputLine.equals("done")) { // if client sends "done", exit loop
                    break;
                }
                out.println(inputLine); // send input back to client
            }

            in.close(); // close input stream
            out.close(); // close output stream
            socket.close(); // close socket
            serverSocket.close(); // close server socket
            System.out.println("File transfer complete.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
