import java.awt.Color;

import javax.swing.*;

public class WithdrawFrame extends JFrame {
    public WithdrawFrame(UserAccount account, DashboardFrame dashboard) {
        setTitle("Withdraw");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setForeground(Color.WHITE);
        JTextField amountField = new JTextField(10);
        JButton withdrawButton = new JButton("Withdraw");

        withdrawButton.setBackground(new Color(0, 0, 0)); // BLACK
        withdrawButton.setForeground(Color.WHITE);

        withdrawButton.addActionListener(e -> {
            String input = amountField.getText().trim();
            try {
                double amount = Double.parseDouble(input);
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(null, "Enter a positive amount.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (account.withdraw(amount)) {
                    JOptionPane.showMessageDialog(null, "Withdrawal successful!");
                    dashboard.refreshBalance();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient balance.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 30, 30));
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(withdrawButton);

        add(panel);
        setVisible(true);
    }
}
