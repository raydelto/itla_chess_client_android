package edu.itla.chess.androidclient.connection;

import java.io.IOException;
import java.net.Socket;

/**
 * This class creates a communication socket between android app and the java server
 * Created by JarlynManuel on 29/3/15.
 */
public class Connector {
    final String server = "localhost"; //declaring string variable, named server
    final int port = 2222; //declaring whole variable, named port
    private Socket socket = null; //declaring class Socket

    private void connect() throws IOException {
        socket = new Socket(server, port); //Opening socket and passing it by parameters the server and port
    }

    public Socket getSocketConnection() throws IOException {//Socket singleton
        if(socket == null){
            connect();
        }
        return socket;
    }

    public void closeConnection(){
        try {
            socket.close(); //closing Socket
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


