package KTCK.accountSignUp;



import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerSendCTR {
    private int port;
    private String host;
    private AccountManager dao;
    private ServerSocket myServer;
    private ArrayList<Socket> list;

    public ServerSendCTR() {
        port = 1234;
        host = "localhost";
        dao = new AccountManager();
        list = new ArrayList<>();
        openSocket();
        while (true) {
            try {
                Socket s = myServer.accept();
                list.add(s);

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
            signupAccount ss = receiveAccount(s);
            if(dao.checkAccount(ss)){
                sendResult("ok");
            } else{
                sendResult("failed");
            }
        } catch (Exception e) {
            sendResult("ok");
            e.printStackTrace();
        }
    }

    public void executeSignUp(Socket s){
        try {
            signupAccount ss = receiveAccount(s);
            if(dao.addAccount(ss)){
                sendResult("ok");
            } else{
                sendResult("failed");
            }
        } catch (Exception e) {
            sendResult("ok");
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

    public signupAccount receiveAccount(Socket s){
        signupAccount ss= null;
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            ss = (signupAccount) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ss;
    }


}
