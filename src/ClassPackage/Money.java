package ClassPackage;

import UtilityPackage.DatabaseUtil;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Money extends DatabaseUtil {

    public double expense;
    public double income;
    public String date;
    public double balance;
    private static String currency;
    public int a;
    private User user;

    public Money() {

    }

    public Money(double income, char currency) {
        this.income = income;

        switch (currency) {
            case 's':
            case 'S':
                Money.currency = "SAR";
                break;
            case 'd':
            case 'D':
                Money.currency = "$";
                break;
            default:
                System.out.println("Wrong input please try agian");
                ;
        }

        balance = income;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBalance(double totalExpense) {
        this.balance = income - totalExpense;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setCurrency(String currency) {
        Money.currency = currency;
    }

    public String getCurrency() {
        return Money.currency;
    }

    void modIncome(double amount) {
        income = amount;
    }

    public String date() {
        LocalDate date = LocalDate.now();
        String text = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return text;
    }

    @Override
    public String toString() {
        return "---------------------------------------------\n"
                + "\tIncome = " + income + " " + Money.currency
                + "\n" + "\tBalance = " + balance + " " + Money.currency
                + "\n---------------------------------------------\n";
    }

    public int BasicDetailInsert(int regID) {

        ps = basicdetailQuery();
        try {
            ps.setInt(1, regID);
            ps.setDouble(2, income);
            ps.setDouble(3, balance);
            ps.setString(4, currency);
            a = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
           JOptionPane.showMessageDialog(null,"Error 0000","Money Manager",JOptionPane.ERROR_MESSAGE);

        }
        return a;
    }

    public Money BasicDetailSelect(int regID) {
        ps = basicdetailselectQuery();
        Money money = new Money();
        try {
            ps.setInt(1, regID);
            rs = ps.executeQuery();

            while (rs.next()) {
                income = Integer.parseInt(rs.getString("TotalIncome"));
                currency = rs.getString("CurrencyType");
                user=new User();
                user.setFullname(rs.getString("FullName"));
                user.setUsername(rs.getString("UserName"));
                money.balance = income;

                money.setCurrency(currency);
                money.setUser(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error 0001","Money Manager",JOptionPane.ERROR_MESSAGE);

        }
        return money;
    }

    public int BasicDetailUpdate(int regID) {
        ps = basicdetailsUpdateQuery();

        try {
            ps.setInt(1, (int) income);
            ps.setInt(2, regID);
            a = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
  JOptionPane.showMessageDialog(null,"Error 0002","Money Manager",JOptionPane.ERROR_MESSAGE);

        }
        return a;
    }

}
