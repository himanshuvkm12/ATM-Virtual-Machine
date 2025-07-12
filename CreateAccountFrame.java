import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CreateAccountFrame extends JFrame {
    private JTextField accountField;
    private JPasswordField pinField;
    private JTextField balanceField;

    private List<UserAccount> accounts;

    public CreateAccountFrame(List<UserAccount> accounts) {
        this.accounts = accounts;

        setTitle("Create New Account");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // ✅ FIXED
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Create New Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = 1;
        JLabel accLabel = new JLabel("Account Number:");
        accLabel.setForeground(Color.WHITE);
        panel.add(accLabel, gbc);

        accountField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(accountField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setForeground(Color.WHITE);
        panel.add(pinLabel, gbc);

        pinField = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(pinField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        JLabel balLabel = new JLabel("Initial Balance:");
        balLabel.setForeground(Color.WHITE);
        panel.add(balLabel, gbc);

        balanceField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(balanceField, gbc);

        JButton createButton = new JButton("Create Account");
        createButton.setBackground(new Color(0, 0, 0)); // ✅ BLACK
        createButton.setForeground(Color.WHITE);
        createButton.setFocusPainted(false);
        createButton.addActionListener(e -> createAccount());

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        panel.add(createButton, gbc);

        add(panel);
        setVisible(true);
    }

    private void createAccount() {
        String accNum = accountField.getText().trim();
        String pin = new String(pinField.getPassword()).trim();
        String balanceText = balanceField.getText().trim();

        if (accNum.isEmpty() || pin.isEmpty() || balanceText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (UserAccount ua : accounts) {
            if (ua.getAccountNumber().equals(accNum)) {
                JOptionPane.showMessageDialog(this, "Account number already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        try {
            double balance = Double.parseDouble(balanceText);
            if (balance < 0) {
                JOptionPane.showMessageDialog(this, "Balance must be non-negative.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            accounts.add(new UserAccount(accNum, pin, balance));
            JOptionPane.showMessageDialog(this, "Account created successfully!");
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid balance.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
