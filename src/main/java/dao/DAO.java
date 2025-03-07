/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.Category;
import utils.DBContext;
import java.sql.*;
import model.Product;

/**
 *
 * @author DVHao
 */
public class DAO extends DBContext {

    public DAO() {
        super();
    }

    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM category";
        try {
            PreparedStatement st = conn.prepareCall(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = new Category(rs.getInt("cat_id"),
                        rs.getString("cat_name"),
                        rs.getString("cat_description"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public Category getCategoryById(int id) {
        String sql = "SELECT * FROM category WHERE cat_id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Category c = new Category(rs.getInt("cat_id"),
                        rs.getString("cat_name"),
                        rs.getString("cat_description"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT pro_id, pro_name, pro_price, pro_quantity, pro_description, cat_id FROM product";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProId(rs.getInt("pro_id"));
                p.setProName(rs.getString("pro_name"));
                p.setProPrice(rs.getLong("pro_price"));
                p.setProQuantity(rs.getInt("pro_quantity"));
                p.setProDescription(rs.getString("pro_description"));
                Category c = getCategoryById(rs.getInt("cat_id"));
                p.setCat(c);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public Product getProductById(int id) {
        String sql = "SELECT * FROM product WHERE pro_id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Product p = new Product();
                p.setProId(rs.getInt("pro_id"));
                p.setProName(rs.getString("pro_name"));
                p.setProPrice(rs.getLong("pro_price"));
                p.setProQuantity(rs.getInt("pro_quantity"));
                p.setProDescription(rs.getString("pro_description"));
                Category c = getCategoryById(rs.getInt("cat_id"));
                p.setCat(c);
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public boolean insert(String name, long price, int quantity, String description, int catId) {
        String sqlGetMaxId = "SELECT MAX(pro_id) as MaxId FROM product";
        try {
            PreparedStatement psGetMaxId = conn.prepareStatement(sqlGetMaxId);
            ResultSet rsMaxId = psGetMaxId.executeQuery();
            if (rsMaxId.next()) {
                int nextId = rsMaxId.getInt("MaxId") + 1;
                String sql = "INSERT INTO product(pro_id, pro_name, pro_price, pro_quantity, pro_description, cat_id) "
                        + "VALUES(?,?,?,?,?,?)";
                try {
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setInt(1, nextId);
                    ps.setString(2, name);
                    ps.setLong(3, price);
                    ps.setInt(4, quantity);
                    ps.setString(5, description);
                    ps.setInt(6, catId);
                    int num = ps.executeUpdate();
                    return num > 0;
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean update(int proId, String name, long price, int quantity, String description, int catId) {
        String sql = "UPDATE product SET pro_name = ?, pro_price = ?, pro_quantity = ?, pro_description = ?, cat_id = ? WHERE pro_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setLong(2, price);
            ps.setInt(3, quantity);
            ps.setString(4, description);
            ps.setInt(5, catId);
            ps.setInt(6, proId);
            int num = ps.executeUpdate();
            return num > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean delete(int id) {
        String sql = "DELETE FROM product WHERE pro_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int num = ps.executeUpdate();
            return num > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
