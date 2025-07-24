package sockets;

import java.io.*;
import java.net.*;

public class SocketClient {
    private String serverAdress;
    private int serverPort;

    // socketClient constructor: serverAdress, serverPort
    public SocketClient(String serverAdress, int serverPort) {
        this.serverAdress = serverAdress;
        this.serverPort = serverPort;
    }

    // method to send a request to the server (socketServer)
    
    // requestData: is the data sent, not a method

    public String sendRequest(String requestData) {
        // creates a new socket conn to the server
        try (Socket socket = new Socket(serverAdress, serverPort);
            // writer: sends text to the server, true means it will send immediately
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // reader: reads text from the server (specifically using the method inputStream in the socket object)
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                System.out.println("[CLIENTE] enviando al servidor los siguientes datos: " + requestData);
                out.println(requestData); 

                String response = in.readLine();

                return response; // return the response from the server

        } catch (IOException e) {
            // this will catch any errors ocurred during the connection: timeout, connection refused, etc.
            e.printStackTrace();
            return "Error: " + e.getMessage();
            }
    }

}