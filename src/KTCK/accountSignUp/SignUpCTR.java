package KTCK.accountSignUp;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SignUpCTR {
    int port;
    String host;
    Socket mySocket;
    public SignUpCTR(){
        port = 1234;
        host = "localhost";
    }

    public void openSocket(){
        try {
            mySocket = new Socket(host,port);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendAccount(signupAccount s){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream());
            oos.writeObject(s);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getResult(){
        String res ="";
        try {
            ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
            res = (String)ois.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }


    public void closeConnect(){
        try {
            mySocket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
