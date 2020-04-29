package com.company;

import java.io.*;
import java.net.Socket;

class ClientThread extends Thread {
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket socket;
    final GameServer gm;

    public ClientThread(Socket socket, DataInputStream dis, DataOutputStream dos, GameServer gm)
    {
        this.socket = socket;
        this.dis = dis;
        this.dos = dos;
        this.gm = gm;
    }

    @Override
    public void run()
    {
        String received;
        while (true)
        {
            try {

                dos.writeUTF("You connected to the server. Type something...");

                received = dis.readUTF();

                if(received.equals("Exit"))
                {
                    System.out.println("Client " + this.socket + " sends exit...");
                    System.out.println("Closing this connection.");
                    dos.writeUTF("Server received the request ... ");
                    this.socket.close();
                    System.out.println("Connection closed");
                    break;
                } else if (received.equals("stop")) {
                    gm.isActive = false;
                    gm.serverSocket.close();
                } else{
                    System.out.println("Unknown command from the client " + this.socket);
                    dos.writeUTF("Server received the request ... ");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try
        {
            this.dis.close();
            this.dos.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
