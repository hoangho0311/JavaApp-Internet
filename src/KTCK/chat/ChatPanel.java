/*
 * Created by JFormDesigner on Sat Apr 09 13:42:37 ICT 2022
 */

package KTCK.chat;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import javax.swing.*;

/**
 * @author unknown
 */
public class ChatPanel extends JPanel {
    Socket socket = null;
    BufferedReader bf = null;
    DataOutputStream os = null;
    OutputThread t = null;
    String sender, receiver;
    public ChatPanel(Socket s, String sender, String receiver) {
        initComponents();
        socket=s;
        this.sender=sender;
        this.receiver=receiver;
        try {
            bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            os = new DataOutputStream(socket.getOutputStream());
            t= new OutputThread(socket,txtMessages,sender,receiver);
            t.start();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public JTextArea getTxtMessages(){
        return this.txtMessages;
    }

    private void btnSendMessage(ActionEvent e) {
        if(txtMessage.getText().trim().length()==0) return;
        try {
            os.writeBytes(txtMessage.getText());
            os.write(13);os.write(10);
            os.flush();
            this.txtMessages.append("\n"+sender+": "+txtMessage.getText());
            txtMessage.setText("");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    final File[] fileSend = new File[1];
    private void BTChoosefile(ActionEvent e) {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Choose a file to send");

        if(jFileChooser.showOpenDialog(null)==jFileChooser.APPROVE_OPTION){
            fileSend[0]=jFileChooser.getSelectedFile();
            txtMessage.setText(fileSend[0].getName());
        }
    }

    private void BTSendFile(ActionEvent e) {
        if (fileSend[0] == null) {

        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(fileSend[0].getAbsolutePath());
                Socket socket = null;
                socket = new Socket("localhost", 1234);
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                String filename = fileSend[0].getName();
                byte[] filenamebyte = filename.getBytes();

                byte[] filecontenBytes = new byte[(int) fileSend[0].length()];
                fileInputStream.read(filecontenBytes);

                dataOutputStream.writeInt(filenamebyte.length);
                dataOutputStream.write(filenamebyte);

                dataOutputStream.writeInt(filecontenBytes.length);
                dataOutputStream.write(filecontenBytes);

//                os.writeByte();
//                os.write(13);os.write(10);
//                os.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        txtMessage = new JTextField();
        btnSendMessage = new JButton();
        BTChoosefile = new JButton();
        BTSendFile = new JButton();
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        txtMessages = new JTextArea();

        //======== this ========
        setMaximumSize(new Dimension(740, 570));
        setPreferredSize(new Dimension(740, 570));
        setBackground(Color.white);
        setLayout(null);

        //---- txtMessage ----
        txtMessage.setBackground(new Color(255, 255, 51));
        add(txtMessage);
        txtMessage.setBounds(0, 425, 540, 85);

        //---- btnSendMessage ----
        btnSendMessage.setText("Send");
        btnSendMessage.addActionListener(e -> btnSendMessage(e));
        add(btnSendMessage);
        btnSendMessage.setBounds(545, 430, 100, 65);

        //---- BTChoosefile ----
        BTChoosefile.setIcon(new ImageIcon(getClass().getResource("/KTCK/img/filechoose.png")));
        BTChoosefile.setContentAreaFilled(false);
        BTChoosefile.setBorderPainted(false);
        BTChoosefile.addActionListener(e -> BTChoosefile(e));
        add(BTChoosefile);
        BTChoosefile.setBounds(new Rectangle(new Point(655, 430), BTChoosefile.getPreferredSize()));

        //---- BTSendFile ----
        BTSendFile.setText("Send File");
        BTSendFile.addActionListener(e -> BTSendFile(e));
        add(BTSendFile);
        BTSendFile.setBounds(new Rectangle(new Point(655, 465), BTSendFile.getPreferredSize()));

        //======== panel1 ========
        {
            panel1.setBackground(new Color(51, 255, 204));
            panel1.setLayout(null);

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
        add(panel1);
        panel1.setBounds(0, 345, 740, 55);

        //======== scrollPane1 ========
        {

            //---- txtMessages ----
            txtMessages.setBackground(Color.white);
            scrollPane1.setViewportView(txtMessages);
        }
        add(scrollPane1);
        scrollPane1.setBounds(0, 0, 740, 365);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < getComponentCount(); i++) {
                Rectangle bounds = getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            setMinimumSize(preferredSize);
            setPreferredSize(preferredSize);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTextField txtMessage;
    private JButton btnSendMessage;
    private JButton BTChoosefile;
    private JButton BTSendFile;
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTextArea txtMessages;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
