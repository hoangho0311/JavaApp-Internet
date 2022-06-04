package SocketJDBC.Client;

import SocketJDBC.Student;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientCTR {
    int port;
    String host;
    Socket mySocket;

    public ClientCTR(){
        host="localhost";
        port=8888;
    }

    public void openSocket(){
        try {
            mySocket = new Socket(host,port);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendStudent(Student s){
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
