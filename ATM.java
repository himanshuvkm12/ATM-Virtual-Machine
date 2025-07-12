import javax.swing.UIManager;
import java.util.ArrayList;
import java.util.List;

public class ATM {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        List<UserAccount> accounts = new ArrayList<>();
        accounts.add(new UserAccount("123456", "1234", 5000.00));
        accounts.add(new UserAccount("654321", "4321", 3000.00));
        accounts.add(new UserAccount("111111", "1111", 10000.00));

        new LoginFrame(accounts);
    }
}
