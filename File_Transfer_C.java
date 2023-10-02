// Program to transfer file from client on server using TCP server.
// Client side

import java.io.*;
import java.net.*;

class TCPClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000); // create client socket and connect to server
            System.out.println("Connected to server.");

            BufferedReader in = new BufferedReader(new FileReader("input.txt")); // input stream from file
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // output stream to server

            String inputLine;
            while ((inputLine = in.readLine()) != null) { // read input from file
                out.println(inputLine); // send input to server
            }

            out.println("done"); // send "done" to server to signal end of file
            in.close(); // close input stream
            out.close(); // close output stream
            socket.close(); // close socket
            System.out.println("File transfer complete.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}