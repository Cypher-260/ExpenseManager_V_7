package ClassPackage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Fixed_Expenses extends Money {

    public String type;
    public int number;
    public int id;
    private ArrayList<Fixed_Expenses> al = new ArrayList<>();
    private Fixed_Expenses fix;

    public Fixed_Expenses() {
        this.expense = 0.0;
        this.type = "Other";
        this.date = date();
        this.number = 0;
        this.id=0;
    }

    public Fixed_Expenses(double fixedExpenses, String type, int num) {
        this.expense = fixedExpenses;
        this.type = type;
        this.date = date();
        this.number = num + 1;
    }

    void modCommitment(double ammount) {
        this.expense = ammount;
    }

    @Override
    public String toString() {
        return "---------------------------------------------\n"
                + "\tCommitment No." + this.number
                + "\n"
                + "\tCommitment amount :" + this.expense + " " + super.getCurrency()
                + "\n"
                + "\tCommitment type :" + this.type
                + "\n"
                + "\tDate : " + this.date
                + "\n---------------------------------------------\n";
    }

    public int CommitmentInsert(int regID) {

        ps = commitmentdetailQuery();
        try {
            ps.setString(1, this.type);
            ps.setInt(2, (int) this.expense);
            ps.setInt(3, regID);
            ps.setString(4, date);

            a = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    public ArrayList<Fixed_Expenses> CommitmentSelect(int regID) {

        ps = commitmentselectquery();
        try {

            ps.setInt(1, regID);
            rs = ps.executeQuery();

            while (rs.next()) {
                Fixed_Expenses e = new Fixed_Expenses();
                e.expense = Integer.parseInt(rs.getString("CommitmentExpense"));
                e.type = rs.getString("CommitmentType");
                e.date = rs.getString("CommitmentDate");
                e.id=Integer.parseInt(rs.getString("CommitmentID"));

                al.add(e);

            }

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }

    public int CommitmentDelete(int comID) {

        ps = commitmentdeletequery();
        try {

            ps.setInt(1, comID);

            a = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    public Fixed_Expenses CommitmentSelectModify(int ID, int choose) {

        ps = commitmentselectquerymodify();
        try {

            ps.setInt(1, ID);
            ps.setInt(2, choose);
            rs = ps.executeQuery();

            if (rs.next()) {
                fix = new Fixed_Expenses();
                fix.expense = Integer.parseInt(rs.getString("CommitmentExpense"));
                fix.type = rs.getString("CommitmentType");
                fix.date = rs.getString("CommitmentDate");
                fix.id=Integer.parseInt(rs.getString("CommitmentID"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fix;
    }
    
    public int CommitmentUpdate(int comID) {

        ps = commitmentupdatequery();
        try {
            ps.setString(1, this.type);
            ps.setInt(2, (int) this.expense);
            ps.setString(3, date);
            ps.setInt(4, comID);

            a = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    

}
