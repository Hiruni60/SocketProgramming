import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFormController {
    public TextArea txtFldArea;
    public TextField txtFld;
    public Button btnSend;

    final int PORT = 5000;
    ServerSocket serverSocket;
    Socket accept;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    String message ="";
    public void initialize(){
            new Thread(() ->{
                try{
               serverSocket = new ServerSocket(PORT);
               System.out.println("Server Started..");
               accept = serverSocket.accept();
               System.out.println("Client Connected.");

               dataOutputStream = new DataOutputStream(accept.getOutputStream());
               dataInputStream = new DataInputStream(accept.getInputStream());
               dataOutputStream = new DataOutputStream(accept.getOutputStream());

                while(!message.equals("exit")) {
                    message = dataInputStream.readUTF();
                    txtFldArea.appendText("\n Client : " + message);
                }
           } catch (IOException e) {
               e.printStackTrace();
           }

       }).start();
    }

    public void SendOnAction(ActionEvent actionEvent) throws IOException {
        dataOutputStream.writeUTF(txtFld.getText().trim());
        dataOutputStream.flush();
    }
}
