import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DashboardFrame extends JFrame {
    private UserAccount account;
    private JLabel balanceLabel;
    private List<UserAccount> accounts;

    public DashboardFrame(UserAccount account, List<UserAccount> accounts) {
        this.account = account;
        this.accounts = accounts;

        setTitle("ATM Dashboard");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel welcomeLabel = new JLabel("Hello, " + account.getAccountNumber());
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(welcomeLabel, gbc);

        balanceLabel = new JLabel("Balance: $" + String.format("%.2f", account.getBalance()));
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        balanceLabel.setForeground(Color.WHITE);
        gbc.gridy = 1;
        panel.add(balanceLabel, gbc);

        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton logoutButton = new JButton("Logout");

        withdrawButton.setBackground(new Color(0, 0, 0)); // BLACK
        withdrawButton.setForeground(Color.WHITE);

        depositButton.setBackground(new Color(0, 0, 0)); // BLACK
        depositButton.setForeground(Color.WHITE);

        logoutButton.setBackground(new Color(0, 0, 0)); // BLACK
        logoutButton.setForeground(Color.WHITE);

        withdrawButton.addActionListener(e -> new WithdrawFrame(account, this));
        depositButton.addActionListener(e -> new DepositFrame(account, this));
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginFrame(accounts);
        });

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(withdrawButton, gbc);

        gbc.gridx = 1;
        panel.add(depositButton, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(logoutButton, gbc);

        add(panel);
        setVisible(true);
    }

    public void refreshBalance() {
        balanceLabel.setText("Balance: $" + String.format("%.2f", account.getBalance()));
    }
}
