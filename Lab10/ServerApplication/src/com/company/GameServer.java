package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    ServerSocket serverSocket = null;
    boolean isActive = false;

    public GameServer() throws IOException {
        serverSocket = new ServerSocket(5056);
        isActive = true;

        while (isActive)
        {
            Socket socket = null;

            try
            {
                socket = serverSocket.accept();

                System.out.println("A new client is connected : " + socket);

                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                Thread t = new ClientThread(socket, dis, dos, this);
                t.start();

            }
            catch (Exception e){
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
                e.printStackTrace();
            }
        }
    }
}