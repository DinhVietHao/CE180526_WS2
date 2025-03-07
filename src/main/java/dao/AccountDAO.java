/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Account;
import utils.DBContext;

/**
 *
 * @author DVHao
 */
public class AccountDAO extends DBContext{
    public AccountDAO() {
        super();
    }
    
    public String hashMD5(String pass){
        try {
            MessageDigest mes = MessageDigest.getInstance("MD5");
            byte[] mesMD5 = mes.digest(pass.getBytes());
            //[0x0a, 0x7a, 0x12, 0x09,...]
            StringBuilder str = new StringBuilder();
            for (byte b : mesMD5) {
                //0x0a
                String ch = String.format("%02x", b);
                //0a
                str.append(ch);
            }
            //str = 0a7a1209
            return str.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
    
    public Account verifyMD5(String user, String pass){
        Account acc = new Account();
        //acc.getId() = -1;
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, hashMD5(pass));
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                acc.setId(rs.getInt("id"));
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
                acc.setRole(rs.getInt("role"));
                return acc;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return acc;
    }
}
