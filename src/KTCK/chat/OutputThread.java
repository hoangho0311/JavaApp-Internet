package KTCK.chat;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class OutputThread extends Thread{
    Socket socket;
    JTextArea txt;
    BufferedReader bf;
    String sender, receriver;

    public OutputThread(Socket socket, JTextArea txt, String sender, String receriver) {
        super();
        this.socket = socket;
        this.txt = txt;
        this.sender = sender;
        this.receriver = receriver;
        try {
            bf= new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        while (true){
            try {
                if(socket!=null){
                    String msg="";
                    if((msg=bf.readLine())!=null && msg.length()>0){
                        txt.append("\n"+receriver+": "+msg);
                    }
                    sleep(1000);
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

}
