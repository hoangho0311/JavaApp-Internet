package SocketJDBC.Server;

import SocketJDBC.Student;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerCTR {
    private int port;
    private String host;
    private ServerDAO dao;
    private ServerSocket myServer;
    private ArrayList<Socket> list;

    public ServerCTR() {
        port = 8888;
        host = "localhost";
        dao = new ServerDAO();
        list = new ArrayList<>();
        openSocket();
        while (true) {
            try {
                Socket s = myServer.accept();
                list.add(s);
                execute(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void sendResult(String res){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(list.get(list.size()-1).getOutputStream());
            oos.writeObject(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void execute(Socket s){
        try {
            Student ss = receiveStudent(s);
            if(dao.addStudent(ss)){
                sendResult("ok");
                new ServerView().showMess("Success!");
            } else{
                sendResult("failed");
                new ServerView().showMess("Failed!");
            }
        } catch (Exception e) {
            sendResult("ok");
            new ServerView().showMess("Success!");
            e.printStackTrace();
        }
    }

    public void openSocket(){
        try {
            myServer = new ServerSocket(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Student receiveStudent(Socket s){
        Student ss= null;
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            ss = (Student)ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ss;
    }
}
