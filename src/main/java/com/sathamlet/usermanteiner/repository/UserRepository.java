package com.sathamlet.usermanteiner.repository;

import com.sathamlet.usermanteiner.model.User;
import com.sathamlet.usermanteiner.operations.ICrud;
import com.sathamlet.usermanteiner.tools.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements ICrud<User> {

    @Override
    public List<User> listing() {
        List<User> users = new ArrayList<>();
        try(Connection con = this.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios")){
            while(rs.next()){
                User u = this.createUser(rs);
                users.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public User getById(int id) {
        User u = null;
        try(PreparedStatement pstmt = this.getConnection()
                .prepareStatement("SELECT * FROM usuario WHERE id=?")){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) u = this.createUser(rs);
            return u;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error se retornara null");
            return u;
        }
    }

    @Override
    public boolean save(User t) {
        String sql = "";
        if(t.getId() == null) sql = "INSERT INTO usuarios " +
                                                        "(username, password, email) VALUES(?,?,?)";
        else sql = "UPDATE Producto SET username=?, password=?, email=? WHERE id=? ";
        try(Connection con = this.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, t.getUserName());
            pstmt.setString(2, t.getPassword());
            pstmt.setString(3, t.getEmail());
            if(t.getId() != null)
                pstmt.setInt(4, t.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try(PreparedStatement pstmt = this.getConnection()
                .prepareStatement("DELETE FROM usuarios WHERE id=?")){
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    private Connection getConnection() throws SQLException {
        return DBConnection.getConnection();
    }
    private User createUser(ResultSet rs) throws SQLException {
        User u = new User();
        u.setUserName(rs.getString("username"));
        u.setId(rs.getInt("id"));
        u.setPassword(rs.getString("password"));
        u.setEmail(rs.getString("email"));
        return u;
    }
}
