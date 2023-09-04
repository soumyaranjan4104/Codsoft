import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Create a class to represent the ATM machine
class ATM {
    private double accountBalance;

    public ATM(double initialBalance) {
        accountBalance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            accountBalance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && accountBalance >= amount) {
            accountBalance -= amount;
            return true;
        }
        return false;
    }

    public double checkBalance() {
        return accountBalance;
    }
}

// Create a class to represent the ATM GUI
public class ATMInterface2 {
    private static ATM atm;

    public static void main(String[] args) {
        atm = new ATM(1000.0); // Initial account balance

        // Create the main frame
        JFrame frame = new JFrame("ATM Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Set the background color to blue
        frame.getContentPane().setBackground(Color.BLUE);

        // Create a heading label
        JLabel headingLabel = new JLabel("WELCOME TO ATM MACHINE!");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Set font and size
        headingLabel.setHorizontalAlignment(JLabel.CENTER); // Center align the heading


        // Create components
//        JLabel balanceLabel = new JLabel("Balance: RS 0.0");
        JTextField amountField = new JTextField(10);
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton checkBalanceButton = new JButton("Check Balance"); // New button


        // Add components to the frame
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1)); // Use GridLayout for column-wise layout
        panel.setBackground(Color.BLUE); // Set the background color of the panel to blue
        panel.add(headingLabel); // Add the heading label
//        panel.add(balanceLabel);
        panel.add(amountField);
        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(checkBalanceButton); // Add the new button
        frame.add(panel);



        // Set up action listeners
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String amountStr = amountField.getText();
                try {
                    double amount = Double.parseDouble(amountStr);
                    if (amount > 0) {
                        atm.deposit(amount);
//                        balanceLabel.setText("Balance: Rs" + atm.checkBalance());
                        amountField.setText("");
                        JOptionPane.showMessageDialog(frame, "Deposit successful!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid deposit amount!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input!");
                }
            }
        });


        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String amountStr = amountField.getText();
                try {
                    double amount = Double.parseDouble(amountStr);
                    if (atm.withdraw(amount)) {
//                        balanceLabel.setText(String.format("Balance: Rs%.2f", atm.checkBalance()));
                        amountField.setText("");
                        JOptionPane.showMessageDialog(frame, "Withdrawal successful!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Insufficient funds or invalid input!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input!");
                }
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, String.format("Current Balance: Rs%.2f", atm.checkBalance()), "Balance", JOptionPane.INFORMATION_MESSAGE);
            }
        });


        // Display the frame
        frame.setVisible(true);
    }
}

