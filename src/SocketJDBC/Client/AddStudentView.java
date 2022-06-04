/*
 * Created by JFormDesigner on Tue May 24 14:50:23 ICT 2022
 */

package SocketJDBC.Client;

import SocketJDBC.Student;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author haong ha
 */
public class AddStudentView extends JFrame {
    public AddStudentView() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addBTN(ActionEvent e) {
        btnAddClick();
    }

    private void resetBTN(ActionEvent e) {
        btnResetClick();
    }

    public void btnAddClick(){
        try {
            Student s = new Student();
            if(txtID.getText().length()!=0 && txtDob.getText().length()!=0 && txtYear.getText().length()!=0 && txtName.getText().length()!=0
                && txtAddress.getText().length()!=0) {
                s.setId(Integer.parseInt(txtID.getText()));
                s.setName(txtName.getText());
                s.setAddress(txtAddress.getText());
                s.setDob(txtDob.getText());
                s.setYear(txtYear.getText());
                ClientCTR ctr = new ClientCTR();
                ctr.openSocket();
                ctr.sendStudent(s);
                String res = ctr.getResult();

                if (res.equals("ok")){
                    JOptionPane.showMessageDialog(rootPane,"Success");
                }else
                    JOptionPane.showMessageDialog(rootPane,"Fail");
                ctr.closeConnect();
            }else {
                JOptionPane.showMessageDialog(rootPane,"Fail");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(rootPane,"Fail");
            e.printStackTrace();
        }
    }
    public void btnResetClick(){
        txtID.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtDob.setText("");
        txtYear.setText("");
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - haong ha
        label1 = new JLabel();
        txtID = new JTextField();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        txtName = new JTextField();
        txtDob = new JTextField();
        txtYear = new JTextField();
        txtAddress = new JTextField();
        addBTN = new JButton();
        resetBTN = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("ID");
        contentPane.add(label1);
        label1.setBounds(75, 15, 60, 20);
        contentPane.add(txtID);
        txtID.setBounds(190, 15, 115, 30);

        //---- label2 ----
        label2.setText("NAME");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(75, 55), label2.getPreferredSize()));

        //---- label3 ----
        label3.setText("DOB");
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(75, 85), label3.getPreferredSize()));

        //---- label4 ----
        label4.setText("yEAR");
        contentPane.add(label4);
        label4.setBounds(new Rectangle(new Point(90, 125), label4.getPreferredSize()));

        //---- label5 ----
        label5.setText("Address");
        contentPane.add(label5);
        label5.setBounds(new Rectangle(new Point(80, 165), label5.getPreferredSize()));
        contentPane.add(txtName);
        txtName.setBounds(190, 45, 115, 30);
        contentPane.add(txtDob);
        txtDob.setBounds(195, 85, 115, 30);
        contentPane.add(txtYear);
        txtYear.setBounds(195, 120, 115, 30);
        contentPane.add(txtAddress);
        txtAddress.setBounds(195, 160, 115, 30);

        //---- addBTN ----
        addBTN.setText("add");
        addBTN.addActionListener(e -> addBTN(e));
        contentPane.add(addBTN);
        addBTN.setBounds(new Rectangle(new Point(90, 215), addBTN.getPreferredSize()));

        //---- resetBTN ----
        resetBTN.setText("reset");
        resetBTN.addActionListener(e -> resetBTN(e));
        contentPane.add(resetBTN);
        resetBTN.setBounds(new Rectangle(new Point(220, 215), resetBTN.getPreferredSize()));

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

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - haong ha
    private JLabel label1;
    private JTextField txtID;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JTextField txtName;
    private JTextField txtDob;
    private JTextField txtYear;
    private JTextField txtAddress;
    private JButton addBTN;
    private JButton resetBTN;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
