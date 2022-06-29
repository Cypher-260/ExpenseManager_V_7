/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UtilityPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author
 */
public class DatabaseUtil extends JFrame {

    public Connection con;
    public PreparedStatement ps;
    public ResultSet rs;

    public DatabaseUtil() {
        connect();
    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/expensedatabase?serverTimezone=UTC", "root", "260260");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null,"Error 3066: can't connect to database","Money Manager",JOptionPane.ERROR_MESSAGE);
        }
    }

    protected PreparedStatement loginQuery() {
        try {
            ps = con.prepareStatement("SELECT * FROM `Registration` WHERE UserName=? AND RPassword=?");

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ps;
    }
    
    protected PreparedStatement registrationQuery() {
        try {
            ps = con.prepareStatement("INSERT INTO `registration`(`UserName`, `RPassword`, `EmailID`, `FullName`, `Contact`) VALUES (?,?,?,?,?)");

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ps;
    }

    protected PreparedStatement basicdetailQuery() {
        try {
            ps = con.prepareStatement("INSERT INTO `userdetails`(`RegistrationID`, `TotalIncome`, `Balance`, `CurrencyType`) VALUES (?,?,?,?)");

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ps;
    }

    protected PreparedStatement basicdetailselectQuery() {
        try {
            ps = con.prepareStatement("SELECT * FROM `userdetails` JOIN registration ON userdetails.RegistrationID=registration.RegistrationID WHERE userdetails.RegistrationID=?");

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ps;
    }

    protected PreparedStatement basicdetailsUpdateQuery() {
        try {
            ps = con.prepareStatement("UPDATE `userdetails` SET `TotalIncome`=? WHERE RegistrationID=?");

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ps;
    }

    protected PreparedStatement expenceaddquery() {
        try {
            ps = con.prepareStatement("INSERT INTO `expensedetail`(`Amount`, `Category`, `EDate`, `UserID`) VALUES (?,?,?,(SELECT userdetails.DetailID FROM userdetails WHERE userdetails.RegistrationID=?))");

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ps;
    }

    protected PreparedStatement expenceselectquery() {
        try {
            ps = con.prepareStatement("SELECT * FROM `expensedetail` JOIN userdetails ON expensedetail.UserID=userdetails.DetailID WHERE userdetails.RegistrationID=?");

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ps;
    }

    protected PreparedStatement commitmentdetailQuery() {
        try {
            ps = con.prepareStatement("INSERT INTO `CommitmentDetail`(`CommitmentType`, `CommitmentExpense`, `UserDetail`, `CommitmentDate`) VALUES (?,?,(SELECT userdetails.DetailID FROM userdetails WHERE userdetails.RegistrationID=?),?)");

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ps;
    }

    protected PreparedStatement commitmentselectquery() {
        try {
            ps = con.prepareStatement("SELECT * FROM `commitmentdetail` JOIN userdetails ON commitmentdetail.UserDetail=userdetails.DetailID WHERE userdetails.RegistrationID=?");

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ps;
    }
    
    protected PreparedStatement commitmentselectquerymodify() {
        try {
            ps = con.prepareStatement("SELECT * FROM `commitmentdetail` JOIN userdetails ON commitmentdetail.UserDetail=userdetails.DetailID WHERE userdetails.RegistrationID=? and CommitmentID =?");

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ps;
    }

    protected PreparedStatement commitmentdeletequery() {
        try {
            ps = con.prepareStatement("DELETE FROM `commitmentdetail` WHERE CommitmentID=?");

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ps;
    }

    protected PreparedStatement commitmentselectmodifyquery() {
        try {
            ps = con.prepareStatement("SELECT * FROM `commitmentdetail` WHERE CommitmentID=?");

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ps;
    }

    protected PreparedStatement commitmentupdatequery() {
        try {
            ps = con.prepareStatement("UPDATE `commitmentdetail` SET `CommitmentType`=?,`CommitmentExpense`=?,`CommitmentDate`=? WHERE CommitmentID=?");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ps;
    }

    protected PreparedStatement debtsinsertquery() {
        try {
            ps = con.prepareStatement("INSERT INTO `DebtsDetail`(`DebtsAmount`, `DebtsDate`, `UserID`) VALUES (?,?,(SELECT userdetails.DetailID FROM userdetails WHERE userdetails.RegistrationID=?))");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ps;
    }

    protected PreparedStatement debtsselectquery() {
        try {
            ps = con.prepareStatement("SELECT * FROM `DebtsDetail` JOIN userdetails ON debtsdetail.UserID=userdetails.DetailID WHERE userdetails.RegistrationID=?");

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ps;
    }
    
    protected PreparedStatement debtsdeletequery() {
        try {
            ps = con.prepareStatement("DELETE FROM `DebtsDetail` WHERE DebtsID=?");

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ps;
    }
}
