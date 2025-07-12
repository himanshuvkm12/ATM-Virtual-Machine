import java.awt.Color;

import javax.swing.*;

public class DepositFrame extends JFrame {
    public DepositFrame(UserAccount account, DashboardFrame dashboard) {
        setTitle("Deposit");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setForeground(Color.WHITE);
        JTextField amountField = new JTextField(10);
        JButton depositButton = new JButton("Deposit");

        depositButton.setBackground(new Color(0, 0, 0)); // BLACK
        depositButton.setForeground(Color.WHITE);

        depositButton.addActionListener(e -> {
            String input = amountField.getText().trim();
            try {
                double amount = Double.parseDouble(input);
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(null, "Enter a positive amount.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                account.deposit(amount);
                JOptionPane.showMessageDialog(null, "Deposit successful!");
                dashboard.refreshBalance();
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 30, 30));
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(depositButton);

        add(panel);
        setVisible(true);
    }
}
