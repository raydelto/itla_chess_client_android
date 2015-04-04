package edu.itla.chess.androidclient.network;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Manuel Inoa on 3/28/15.
 */

public class ServerClient {
    private Socket client;
    private PrintWriter printwriter;
    private BufferedReader reader;
    private static ServerClient instance;
    private MessageSender messageSender;
    private MessageReceiver messageReceiver;
    private boolean isConnected = false;

    public static ServerClient getInstance(String serverIp, String port) {
        if (instance == null) {
            instance = new ServerClient(serverIp, port);
        }
        return instance;
    }

    public static ServerClient getInstance() {
        if (instance == null) {
            instance = new ServerClient();
        }
        return instance;
    }

    private ServerClient() {
    }

    private ServerClient(String serverIp, String port) {
        Connector connector = new Connector();
        connector.execute(serverIp, port);
    }

    private class Connector extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            try {
                InetAddress serverAddr = InetAddress.getByName(params[0]);
                int port = Integer.parseInt(params[1]);

                client = new Socket(serverAddr, port);
                reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                printwriter = new PrintWriter(client.getOutputStream(), true);

                isConnected = true;
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class MessageSender extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... messages) {
            for (String message : messages) {
                printwriter.println(message);
            }
            printwriter.flush();

            return null;
        }

    }

    private class MessageReceiver extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String message = null;

            try {
                if (isConnected()) {
                    message = reader.readLine();
                    System.out.println(message);
                }
            } catch (IOException e) {
                System.err.println("Error mientras se leia el mensaje.");
                e.printStackTrace();
            }
            return message;
        }

    }

    public void sendMessage(String message) {
        if (messageSender != null) {
            if (messageSender.getStatus() == AsyncTask.Status.RUNNING) {
                messageSender.cancel(true);
            }
        }

        messageSender = new MessageSender();
        messageSender.execute(message);
    }

    public String getMessageFromServer() {
        String message = null;
        try {

            if (messageReceiver != null) {
                if (messageReceiver.getStatus() == AsyncTask.Status.RUNNING) {
                    messageReceiver.cancel(true);
                }
            }

            messageReceiver = new MessageReceiver();
            message = messageReceiver.execute().get();
            System.out.println("Aqui 4");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Aqui 5");
        System.out.println(message);
        return message;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void closeConnection() {
        try {
            this.client.close();
            this.printwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
