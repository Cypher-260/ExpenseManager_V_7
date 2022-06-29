package ClassPackage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Expenses extends Money {

    public String category;
    private ArrayList<Expenses> al = new ArrayList<>();

    public Expenses() {
        this.expense = 0.0;
        this.category = " ";
        this.date = date();
    }

    public Expenses(double amount, int choose) {
        this.expense = amount;

        switch (choose) {

            case 1:
                this.category = "bussiness";
                break;
            case 2:
                this.category = "education";
                break;
            case 3:
                this.category = "food";
                break;
            case 4:
                this.category = "medical";
                break;
            case 5:
                this.category = "fuel";
                break;
            case 6:
                this.category = "entertaiment";
                break;
            case 7:
                this.category = "home office";
                break;
            case 8:
                this.category = "clothing";
                break;
            default:
                this.category = "others";
                break;
        }

        this.date = date();
    }

    @Override
    public String toString() {
        return "---------------------------------------------\n"
                + "\tExpense amount :" + this.expense + " " + super.getCurrency()
                + "\n"
                + "\tCategory :" + this.category
                + "\n"
                + "\tDate : " + this.date
                + "\n---------------------------------------------\n";
    }

    public int ExpenseInsert(int regID) {

        ps = expenceaddquery();
        try {
            ps.setInt(1, (int) expense);
            ps.setString(2, category);
            ps.setString(3, date);
            ps.setInt(4, regID);
            a = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    public ArrayList<Expenses> ExpenseSelect(int regID) {

        ps = expenceselectquery();
        try {

            ps.setInt(1, regID);
            rs = ps.executeQuery();

            while (rs.next()) {
                Expenses e = new Expenses();
                e.expense = Integer.parseInt(rs.getString("Amount"));
                e.category = rs.getString("Category");
                e.date = rs.getString("EDate");
                
                al.add(e);

            }

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }

}
