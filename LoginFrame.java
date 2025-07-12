import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class LoginFrame extends JFrame {
    private JTextField accountField;
    private JPasswordField pinField;
    private JButton loginButton;
    private JButton createAccountButton;
    private List<UserAccount> accounts;

    public LoginFrame(List<UserAccount> accounts) {
        this.accounts = accounts;

        setTitle("ATM Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Welcome to ATM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
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

        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 0, 0)); // BLACK
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(new LoginAction());

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        createAccountButton = new JButton("Create Account");
        createAccountButton.setBackground(new Color(0, 0, 0)); // BLACK
        createAccountButton.setForeground(Color.WHITE);
        createAccountButton.setFocusPainted(false);
        createAccountButton.addActionListener(e -> new CreateAccountFrame(accounts));

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        panel.add(createAccountButton, gbc);

        add(panel);
        setVisible(true);
    }

    private class LoginAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String accInput = accountField.getText().trim();
            String pinInput = new String(pinField.getPassword()).trim();

            boolean found = false;
            for (UserAccount ua : accounts) {
                if (ua.getAccountNumber().equals(accInput) && ua.authenticate(pinInput)) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    dispose();
                    new DashboardFrame(ua, accounts);
                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(null, "Invalid credentials.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
