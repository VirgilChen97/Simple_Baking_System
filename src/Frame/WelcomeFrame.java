/*
 * Created by JFormDesigner on Mon May 21 21:46:37 CST 2018
 */

package Frame;

import control.AccountControl;
import control.Clear;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

/**
 * user interface when open the system
 * @author Yifeng Chen
 * @version 1.0
 */
public class WelcomeFrame extends JFrame implements ActionListener {
    public WelcomeFrame() {
        new AccountControl().loadData();
        initComponents();
        signup.addActionListener(this);
        signin.addActionListener(this);
        service.addActionListener(this);
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                new AccountControl().saveData();
            }
        });
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * inti all components in the frame
     */
    private void initComponents() {
        signin = new JButton();
        signup = new JButton();
        service = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();

        //======== this ========
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- signin ----
        signin.setText("Sign in");
        signin.setFont(new Font("Arial", Font.PLAIN, 12));
        contentPane.add(signin);
        signin.setBounds(15, 85, 200, 185);

        //---- signup ----
        signup.setText("Sign up");
        signup.setFont(new Font("Arial", Font.PLAIN, 12));
        contentPane.add(signup);
        signup.setBounds(225, 85, 85, 85);

        //---- service ----
        service.setText("Service Reserve");
        service.setFont(new Font("Arial", Font.PLAIN, 12));
        contentPane.add(service);
        service.setBounds(225, 180, 85, 90);

        //---- label1 ----
        label1.setText("Welcome !");
        label1.setFont(new Font("Arial", Font.BOLD, 14));
        contentPane.add(label1);
        label1.setBounds(15, 20, 110, 25);

        //---- label2 ----
        label2.setText("What would you like to do?");
        label2.setFont(new Font("Arial", Font.PLAIN, 12));
        label2.setForeground(Color.gray);
        contentPane.add(label2);
        label2.setBounds(15, 45, 205, 25);

        contentPane.setPreferredSize(new Dimension(325, 285));
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * listener handle the choose of user
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if(button == signin){
            new signinFrame(this,"Sign in");
        }else if(button == signup){
            new SignupUI(this,"Sign Up");
        }else if(button == service){
            new ReserveFrame(this,"Reserve");
        }
    }

    public static void main(String[] args) {
        new WelcomeFrame().setVisible(true);

        /* only for clear function testing */
        JFrame clear = new JFrame();
        JButton clearButton = new JButton("This button clears all funds\nJust for test");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clear.clear();
            }
        });
        clear.add(clearButton);
        clear.setSize(new Dimension(300,100));
        clear.setVisible(true);
    }
    private JButton signin;
    private JButton signup;
    private JButton service;
    private JLabel label1;
    private JLabel label2;
}
