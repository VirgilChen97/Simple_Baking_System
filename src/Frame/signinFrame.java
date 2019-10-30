/*
 * Created by JFormDesigner on Tue May 22 14:41:16 CST 2018
 */

package Frame;

import Entity.Account;
import control.AccountControl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * User interface for sign in
 * @author Yifeng Chen
 * @version 1.3
 */
public class signinFrame extends JDialog implements ActionListener {
    public signinFrame(JFrame owner, String title) {
        super(owner,title,true);
        this.owner = owner;
        initComponents();
        this.Signin.addActionListener(this);
        setVisible(true);
    }

    /**
     * Listener handle sign in operations
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String idstr = ID.getText();
        String pinstr = PIN.getText();
        String password = Password.getText();
        Account account = new AccountControl().login(idstr,pinstr,password);
        if(account == null) {
            JOptionPane.showMessageDialog(this,"Wrong User ID/PIN/Password","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(account.getSuspended()){
            int selection = JOptionPane.showConfirmDialog(this,"Your account is currently suspended\nDo you want to activate it?","Suspended Account",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
            if(selection == 0){
                account.setSuspended(false);
            }else return;
        }
        new mainFrame(account);
        this.setVisible(false);
        owner.setVisible(false);
    }

    /**
     * init all components in the frame
     */
    private void initComponents() {
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        ID = new JTextField();
        PIN = new JTextField();
        Password = new JTextField();
        Signin = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("ID");
        label1.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(label1);
        label1.setBounds(15, 15, 35, 25);

        //---- label2 ----
        label2.setText("PIN");
        label2.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(label2);
        label2.setBounds(15, 45, 35, 25);

        //---- label3 ----
        label3.setText("Password");
        label3.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(label3);
        label3.setBounds(15, 75, 80, 25);

        //---- ID ----
        ID.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(ID);
        ID.setBounds(115, 15, 105, ID.getPreferredSize().height);

        //---- PIN ----
        PIN.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(PIN);
        PIN.setBounds(115, 45, 105, 23);

        //---- Password ----
        Password.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPane.add(Password);
        Password.setBounds(115, 75, 105, 23);

        //---- Signin ----
        Signin.setText("Sign in");
        Signin.setFont(new Font("Arial", Font.PLAIN, 12));
        contentPane.add(Signin);
        Signin.setBounds(new Rectangle(new Point(80, 110), Signin.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(235, 145));
        pack();
        setLocationRelativeTo(getOwner());
    }
    JFrame owner;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField ID;
    private JTextField PIN;
    private JTextField Password;
    private JButton Signin;
}
