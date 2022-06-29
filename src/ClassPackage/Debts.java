package ClassPackage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Debts extends Money
{
    public int debtNumber;
    private Debts debts;
    public int id;
    private ArrayList<Debts> al=new ArrayList<>();
    
    public Debts()
    {
        this.expense = 0.0;
        this.date = date();
        this.debtNumber = 0;
    }
    
    public Debts(double amount,int i)
    {
        this.expense = amount;
        this.date = date();
        this.debtNumber = i+1;
    }
    
    @Override
    public String toString()
    {
        return  "---------------------------------------------\n"
                +"\tDebt No."+this.debtNumber
                +"\n"
                +"\tDebt amount :" + this.expense+" "+ super.getCurrency() 
                +"\n" 
                +"\tDate : "+ this.date
                +"\n---------------------------------------------\n";
    }
    
    public int DebtsInsert(int regID){
         ps = debtsinsertquery();
        try {
            ps.setInt(1, (int) expense);
            ps.setString(2, date);
            ps.setInt(3, regID);
            a = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    
    public ArrayList<Debts> DebtsSelect(int regID) {

        ps = debtsselectquery();
        try {

            ps.setInt(1, regID);
            rs = ps.executeQuery();

            while (rs.next()) {
                debts = new Debts();
                debts.expense = Integer.parseInt(rs.getString("DebtsAmount"));
                debts.date = rs.getString("DebtsDate");
                debts.id=Integer.parseInt(rs.getString("DebtsID"));
                al.add(debts);
            }

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
    
    public int DebtsDelete(int debID) {

        ps = debtsdeletequery();
        try {

            ps.setInt(1, debID);

            a = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
}