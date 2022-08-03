package lk.ijse.gdse;

import java.io.*;
import java.net.Socket;

public class ClientApp {
    public static void main(String[] args)  {
            final int PORT=8000;
        Socket socket = null;
        try {
            socket = new Socket("localhost",PORT);

            DataOutputStream dataOutputStream= new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream=new DataInputStream(socket.getInputStream());
            InputStreamReader inputStreamReader=new InputStreamReader(System.in);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

            String message ="",reply="";

            while (!message.equals("finish")){
                reply = bufferedReader.readLine();
                dataOutputStream.writeUTF(reply);

                message = dataInputStream.readUTF();
                System.out.println(message);

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
