/*
 * Created by JFormDesigner on Sun Jan 30 20:24:01 ICT 2022
 */

package KTCK.Menu;

import KTCK.accountSignUp.ServerSendCTR;
import KTCK.accountSignUp.SignUpCTR;
import KTCK.accountSignUp.signupAccount;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.*;

/**
 * @author 21IT348 Hồ Việt Hoàng
 */
public class loadingScreen extends JFrame {
    public loadingScreen() {
        initComponents();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    String quyen, codeuser;



    private void userMouseClicked(MouseEvent e) {
       user.setText("");
    }

    private void passMouseClicked(MouseEvent e) {
        pass.setText("");
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    int cem = 1;

    public String returnPass(){
        String originalString = String.valueOf(passSUTXT.getPassword());
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        byte[] encodedhash = digest.digest(
                originalString.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedhash);
    }

    private void SIGNUPBTN(ActionEvent e) {
        try {
            signupAccount s = new signupAccount();
            if(nameSUTXT.getText().length()!=0 && userSUTXT.getText().length()!=0 && passSUTXT.getPassword().length!=0){
                s.setId(cem);
                cem++;
                s.setNameSU(nameSUTXT.getText());
                s.setUserSU(userSUTXT.getText());
                s.setPassSU(returnPass());
                SignUpCTR ctr = new SignUpCTR();
                ctr.openSocket();
                ctr.sendAccount(s);
                String res = ctr.getResult();

                if(res.equals("ok"))
                {
                    JOptionPane.showMessageDialog(rootPane,"Success");
                }else
                    JOptionPane.showMessageDialog(rootPane,"Fail");
                ctr.closeConnect();

            }else {
                JOptionPane.showMessageDialog(rootPane,"Fail");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    private void dangnhap(ActionEvent e) {

        quyen="user";
        codeuser = "HG001";
        String originalString = String.valueOf(pass.getPassword());
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        byte[] encodedhash = digest.digest(
                originalString.getBytes(StandardCharsets.UTF_8));


        try {
            signupAccount s = new signupAccount();
            if(user.getText().length()!=0 && pass.getPassword().length!=0){
                s.setId(1);
                s.setNameSU("");
                s.setUserSU(user.getText());
                s.setPassSU(bytesToHex(encodedhash));
                SignUpCTR ctr = new SignUpCTR();
                ctr.openSocket();
                ctr.sendAccount(s);
                String res = ctr.getResult();

                if(res.equals("ok"))
                {
                    Thread t1 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                for(int i=0;i<=100;i++){
                                    Thread.sleep(7);
                                    LoadingValue.setText(i+"%");
                                    if (i==10){
                                        label1.setText("Loading.");
                                    }
                                    if (i==20){
                                        label1.setText("Loading..");
                                    }
                                    if (i==50){
                                        label1.setText("Loading...");
                                    }
                                    if (i==70){
                                        label1.setText("Loading.");
                                    }
                                    if (i==100){
                                        label1.setText("Loading..");

                                        new MenuForm(quyen, codeuser);
                                        setVisible(false);
                                    }
                                    progressBar1.setValue(i);
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    });
                    t1.start();
                }else
                    error.setText("Incorrect username or password.");
                ctr.closeConnect();

            }else {
                JOptionPane.showMessageDialog(rootPane,"Fail");
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void EXIT(ActionEvent e) {

    }

    private void signupChange(ActionEvent e) {
        user.setVisible(false);
        pass.setVisible(false);
        button1.setVisible(false);

        nameSUTXT.setVisible(true);
        passSUTXT.setVisible(true);
        userSUTXT.setVisible(true);
        SIGNUPBTN.setVisible(true);
        label2.setVisible(false);
        signupChange.setVisible(false);
        button3.setVisible(true);
        label4.setVisible(true);
    }

    private void loginChange(ActionEvent e) {
        user.setVisible(true);
        pass.setVisible(true);
        button1.setVisible(true);

        nameSUTXT.setVisible(false);
        passSUTXT.setVisible(false);
        userSUTXT.setVisible(false);
        SIGNUPBTN.setVisible(false);
        label2.setVisible(true);
        signupChange.setVisible(true);
        button3.setVisible(false);
        label4.setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        button3 = new JButton();
        label4 = new JLabel();
        signupChange = new JButton();
        label2 = new JLabel();
        nameSUTXT = new JTextField();
        userSUTXT = new JTextField();
        passSUTXT = new JPasswordField();
        SIGNUPBTN = new JButton();
        progressBar1 = new JProgressBar();
        LoadingValue = new JLabel();
        label1 = new JLabel();
        error = new JLabel();
        button1 = new JButton();
        pass = new JPasswordField();
        button2 = new JButton();
        user = new JTextField();
        label3 = new JLabel();
        picture = new JLabel();

        //======== this ========
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/MN2/img/2037099.png")).getImage());
        setTitle("INTERNET");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setLayout(null);

            //---- button3 ----
            button3.setText("LOGIN");
            button3.setFont(new Font("Times New Roman", Font.BOLD, 16));
            button3.setBorderPainted(false);
            button3.setContentAreaFilled(false);
            button3.setForeground(new Color(204, 204, 255));
            button3.setVisible(false);
            button3.addActionListener(e -> loginChange(e));
            panel1.add(button3);
            button3.setBounds(730, 450, 125, button3.getPreferredSize().height);

            //---- label4 ----
            label4.setText("YOU HAD ACCOUNT");
            label4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            label4.setForeground(Color.black);
            label4.setVisible(false);
            panel1.add(label4);
            label4.setBounds(495, 450, 275, 30);

            //---- signupChange ----
            signupChange.setText("SIGN UP");
            signupChange.setBorderPainted(false);
            signupChange.setContentAreaFilled(false);
            signupChange.setFont(new Font("Times New Roman", Font.BOLD, 16));
            signupChange.setForeground(new Color(204, 204, 255));
            signupChange.addActionListener(e -> signupChange(e));
            panel1.add(signupChange);
            signupChange.setBounds(new Rectangle(new Point(720, 450), signupChange.getPreferredSize()));

            //---- label2 ----
            label2.setText("IF YOU DON'T HAVE ACCOUNT.");
            label2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            label2.setForeground(Color.black);
            panel1.add(label2);
            label2.setBounds(500, 450, 240, 30);

            //---- nameSUTXT ----
            nameSUTXT.setBackground(Color.white);
            nameSUTXT.setForeground(new Color(102, 102, 102));
            nameSUTXT.setText("NAME");
            nameSUTXT.setVisible(false);
            panel1.add(nameSUTXT);
            nameSUTXT.setBounds(480, 175, 425, 55);

            //---- userSUTXT ----
            userSUTXT.setBackground(Color.white);
            userSUTXT.setForeground(new Color(102, 102, 102));
            userSUTXT.setText("USERNAME");
            userSUTXT.setVisible(false);
            panel1.add(userSUTXT);
            userSUTXT.setBounds(480, 245, 425, 55);

            //---- passSUTXT ----
            passSUTXT.setBackground(Color.white);
            passSUTXT.setText("PASSWORD");
            passSUTXT.setVisible(false);
            panel1.add(passSUTXT);
            passSUTXT.setBounds(480, 315, 425, 55);

            //---- SIGNUPBTN ----
            SIGNUPBTN.setText("SIGNUP");
            SIGNUPBTN.setBackground(Color.white);
            SIGNUPBTN.setVisible(false);
            SIGNUPBTN.addActionListener(e -> {
			SIGNUPBTN(e);
		});
            panel1.add(SIGNUPBTN);
            SIGNUPBTN.setBounds(535, 385, 140, 40);
            panel1.add(progressBar1);
            progressBar1.setBounds(0, 655, 1350, 14);

            //---- LoadingValue ----
            LoadingValue.setText("0%");
            LoadingValue.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
            LoadingValue.setForeground(Color.white);
            panel1.add(LoadingValue);
            LoadingValue.setBounds(1295, 630, 65, 25);

            //---- label1 ----
            label1.setText("Loading....");
            label1.setForeground(Color.white);
            label1.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
            panel1.add(label1);
            label1.setBounds(5, 625, 155, 25);

            //---- error ----
            error.setFont(error.getFont().deriveFont(error.getFont().getStyle() | Font.BOLD, error.getFont().getSize() + 8f));
            error.setForeground(new Color(238, 8, 8));
            panel1.add(error);
            error.setBounds(480, 350, 410, 25);

            //---- button1 ----
            button1.setText("LOGIN");
            button1.setBackground(Color.white);
            button1.addActionListener(e -> dangnhap(e));
            panel1.add(button1);
            button1.setBounds(525, 385, 145, 40);

            //---- pass ----
            pass.setBackground(Color.white);
            pass.setText("PASSWORD");
            pass.setFont(pass.getFont().deriveFont(pass.getFont().getSize() + 6f));
            pass.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    passMouseClicked(e);
                }
            });
            panel1.add(pass);
            pass.setBounds(475, 285, 425, 60);

            //---- button2 ----
            button2.setText("CANCEL");
            button2.setBackground(Color.white);
            button2.addActionListener(e -> {
			EXIT(e);
		});
            panel1.add(button2);
            button2.setBounds(720, 385, 140, 40);

            //---- user ----
            user.setBackground(Color.white);
            user.setText("USERNAME");
            user.setFont(user.getFont().deriveFont(user.getFont().getSize() + 6f));
            user.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    userMouseClicked(e);
                }
            });
            panel1.add(user);
            user.setBounds(475, 210, 425, 55);

            //---- label3 ----
            label3.setText("INTERNET BANKING");
            label3.setIcon(null);
            label3.setForeground(Color.white);
            label3.setHorizontalAlignment(SwingConstants.CENTER);
            label3.setFont(new Font("JetBrains Mono Medium", Font.PLAIN, 48));
            panel1.add(label3);
            label3.setBounds(430, 110, 510, 50);

            //---- picture ----
            picture.setIcon(new ImageIcon(getClass().getResource("/MN2/img/479190-cool-color-backgrounds-1920x1200-hd-for-mobile.jpg")));
            panel1.add(picture);
            picture.setBounds(0, 0, 1350, 670);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel1);
        panel1.setBounds(0, 0, 1350, 670);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    public static void main(String[] args) {
        loadingScreen sp = new loadingScreen();
        sp.setVisible(true);

        
    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JButton button3;
    private JLabel label4;
    private JButton signupChange;
    private JLabel label2;
    private JTextField nameSUTXT;
    private JTextField userSUTXT;
    private JPasswordField passSUTXT;
    private JButton SIGNUPBTN;
    private JProgressBar progressBar1;
    private JLabel LoadingValue;
    private JLabel label1;
    private JLabel error;
    private JButton button1;
    private JPasswordField pass;
    private JButton button2;
    private JTextField user;
    private JLabel label3;
    private JLabel picture;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
