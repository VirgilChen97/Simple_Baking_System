/*
 * Created by JFormDesigner on Mon May 21 22:25:58 CST 2018
 */

package Frame;

import Entity.Account;
import control.AccountControl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * User interface for sign up
 * @author Yifeng Chen
 * @version 1.0
 */
public class SignupUI extends JDialog implements ActionListener {
    public SignupUI(JFrame owner, String title) {
        super(owner,title,true);
        this.owner = owner;
        initComponents();
        button1.addActionListener(this);
        this.setVisible(true);
    }

    /**
     * inti all components in the frame
     */
    private void initComponents() {
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        panel2 = new JPanel();
        type = new JComboBox<>();
        name = new JTextField();
        address = new JTextField();
        Birthday_year = new JTextField();
        birthday_month = new JTextField();
        birthday_day = new JTextField();
        password = new JPasswordField();
        passwordCheck = new JPasswordField();
        button1 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setLayout(new GridLayout(0, 1, 10, 10));

            //---- label1 ----
            label1.setText("Type");
            label1.setFont(new Font("Arial", Font.PLAIN, 12));
            label1.setHorizontalAlignment(SwingConstants.LEFT);
            panel1.add(label1);

            //---- label2 ----
            label2.setText("Name");
            label2.setFont(new Font("Arial", Font.PLAIN, 12));
            label2.setHorizontalAlignment(SwingConstants.LEFT);
            panel1.add(label2);

            //---- label3 ----
            label3.setText("Address");
            label3.setFont(new Font("Arial", Font.PLAIN, 12));
            label3.setHorizontalAlignment(SwingConstants.LEFT);
            panel1.add(label3);

            //---- label4 ----
            label4.setText("Birthday");
            label4.setFont(new Font("Arial", Font.PLAIN, 12));
            label4.setHorizontalAlignment(SwingConstants.LEFT);
            panel1.add(label4);

            //---- label5 ----
            label5.setFont(new Font("Arial", Font.PLAIN, 12));
            label5.setHorizontalAlignment(SwingConstants.LEFT);
            panel1.add(label5);

            //---- label6 ----
            label6.setFont(new Font("Arial", Font.PLAIN, 12));
            label6.setHorizontalAlignment(SwingConstants.LEFT);
            panel1.add(label6);

            //---- label7 ----
            label7.setText("Password");
            label7.setFont(new Font("Arial", Font.PLAIN, 12));
            label7.setHorizontalAlignment(SwingConstants.LEFT);
            panel1.add(label7);

            //---- label8 ----
            label8.setText("Password Check");
            label8.setFont(new Font("Arial", Font.PLAIN, 12));
            label8.setHorizontalAlignment(SwingConstants.LEFT);
            panel1.add(label8);
        }
        contentPane.add(panel1);
        panel1.setBounds(25, 10, 105, 215);

        //======== panel2 ========
        {
            panel2.setLayout(new GridLayout(0, 1, 10, 10));

            //---- type ----
            type.setModel(new DefaultComboBoxModel<>(new String[] {
                "Saver",
                "Junior",
                "Current"
            }));
            panel2.add(type);
            panel2.add(name);
            panel2.add(address);

            //---- Birthday_year ----
            Birthday_year.setToolTipText("Year");
            panel2.add(Birthday_year);

            //---- birthday_month ----
            birthday_month.setToolTipText("Month");
            panel2.add(birthday_month);

            //---- birthday_day ----
            birthday_day.setToolTipText("Day");
            panel2.add(birthday_day);
            panel2.add(password);
            panel2.add(passwordCheck);
        }
        contentPane.add(panel2);
        panel2.setBounds(145, 10, 135, 215);

        //---- button1 ----
        button1.setText("Sign up");
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(115, 240), button1.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(310, 270));
        pack();
        setLocationRelativeTo(getOwner());
    }

    /**
     * listener handles sign up operation
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String typestr = (String)type.getSelectedItem();
        String namestr = name.getText();
        String addressstr = address.getText();
        String yearstr = Birthday_year.getText();
        String monthstr = birthday_month.getText();
        String daystr = birthday_day.getText();
        String pwd = password.getText();
        String pwdConf = passwordCheck.getText();
        if (!pwd.equals(pwdConf)){
            JOptionPane.showMessageDialog(this, "Password Check failed", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Account account = new AccountControl().signup(typestr,namestr,addressstr,yearstr,monthstr,daystr,pwd);
        if(account == null){
            JOptionPane.showMessageDialog(this, "You are not under 16", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String ID = String.valueOf(account.getAccountNumber());
        String pin = String.valueOf(account.getPin());
        JOptionPane.showMessageDialog(this, "Success! Your ID: "+ID+" Your PIN: "+ pin,"Success", JOptionPane.INFORMATION_MESSAGE);
    }
    JFrame owner;
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JPanel panel2;
    private JComboBox<String> type;
    private JTextField name;
    private JTextField address;
    private JTextField Birthday_year;
    private JTextField birthday_month;
    private JTextField birthday_day;
    private JPasswordField password;
    private JPasswordField passwordCheck;
    private JButton button1;
}
