import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientFormController {
    public TextArea txtFldArea;
    public TextField txtFld;
    public Button btnSend;

    final int PORT = 5000;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    String message = "";

    public void initialize(){
        new Thread(() -> {
            try {
                socket = new Socket("localhost",PORT);

                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                while (!message.equals("exit")) {
                    message = dataInputStream.readUTF();
                    txtFldArea.appendText("\n Server : "+message);
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
