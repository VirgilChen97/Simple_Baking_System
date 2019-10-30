/*
 * Created by JFormDesigner on Mon May 21 22:49:58 CST 2018
 */

package Frame;

import control.AccountControl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * User interface for service reservation
 * @author Yifeng Chen
 * @version 1.0
 */
public class ReserveFrame extends JDialog implements ActionListener {
    public ReserveFrame(JFrame owner,String title) {
        super(owner,title,true);
        initComponents();
        button1.addActionListener(this);
        setVisible(true);
    }

    /**
     * Listener for reserve button
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String idstr =id.getText();
        String pinstr = pin.getText();
        String datestr = date.getText();
        switch (new AccountControl().reserveControl(idstr,pinstr,datestr)){
            case 1:
                JOptionPane.showMessageDialog(this,"You have already reserved","Failed",JOptionPane.ERROR_MESSAGE);
                break;
            case 0:
                JOptionPane.showMessageDialog(this,"Reservation accepted","Success",JOptionPane.INFORMATION_MESSAGE);
                break;
            case -1:
                JOptionPane.showMessageDialog(this,"You don't have to reserve","Failed",JOptionPane.ERROR_MESSAGE);
                break;
            case -2:
                JOptionPane.showMessageDialog(this,"Wrong ID/PIN","Failed",JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * init all components in the frame
     */
    private void initComponents() {
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        panel2 = new JPanel();
        id = new JTextField();
        pin = new JTextField();
        date = new JTextField();
        button1 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setLayout(new GridLayout(0, 1, 10, 10));

            //---- label1 ----
            label1.setText("ID");
            label1.setFont(new Font("Arial", Font.PLAIN, 12));
            label1.setHorizontalAlignment(SwingConstants.LEFT);
            panel1.add(label1);

            //---- label2 ----
            label2.setText("PIN");
            label2.setFont(new Font("Arial", Font.PLAIN, 12));
            label2.setHorizontalAlignment(SwingConstants.LEFT);
            panel1.add(label2);

            //---- label3 ----
            label3.setText("DATE");
            label3.setFont(new Font("Arial", Font.PLAIN, 12));
            label3.setHorizontalAlignment(SwingConstants.LEFT);
            panel1.add(label3);
        }
        contentPane.add(panel1);
        panel1.setBounds(25, 10, 105, 70);

        //======== panel2 ========
        {
            panel2.setLayout(new GridLayout(0, 1, 10, 10));
            panel2.add(id);
            panel2.add(pin);
            panel2.add(date);
        }
        contentPane.add(panel2);
        panel2.setBounds(145, 10, 135, 70);

        //---- button1 ----
        button1.setText("Reserve");
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(115, 90), button1.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(310, 120));
        pack();
        setLocationRelativeTo(getOwner());
    }

    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JPanel panel2;
    private JTextField id;
    private JTextField pin;
    private JTextField date;
    private JButton button1;
}
