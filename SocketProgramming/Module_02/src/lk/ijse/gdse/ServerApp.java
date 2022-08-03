package lk.ijse.gdse;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    public static void main(String[] args){
        //create server socket
        final int PORT=8000;
         // localhost=127.0.0.1
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);


        System.out.println("Server is running in port "+PORT);

        //request listening and change serverSocket into localSocket
        Socket localSocket = serverSocket.accept();
        System.out.println("Client accepted..!");

        DataOutputStream dataOutputStream= new DataOutputStream(localSocket.getOutputStream());
        DataInputStream dataInputStream=new DataInputStream(localSocket.getInputStream());
        InputStreamReader inputStreamReader=new InputStreamReader(System.in);
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

        //BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));

        String message ="",reply="";

        while (!message.equals("finish")){
            message = dataInputStream.readUTF();
            System.out.println(message);
            reply = bufferedReader.readLine();
            dataOutputStream.writeUTF(reply);
            dataOutputStream.flush();
        }

        dataInputStream.close();
        dataOutputStream.close();
        bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
