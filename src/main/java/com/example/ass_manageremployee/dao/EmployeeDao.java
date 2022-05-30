package com.example.ass_manageremployee.dao;

import com.example.ass_manageremployee.database.ConnectionUtil;
import com.example.ass_manageremployee.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    Connection connection = ConnectionUtil.getConnection();

    public List<Employee> getEmployeeByID(int id){
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employee where id = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Employee e = new Employee();
                e.setId(rs.getInt("id"));
                e.setName(rs.getString("name"));
                e.setEmail(rs.getString("email"));
                e.setAddress(rs.getString("address"));
                e.setPhone(rs.getString("phone"));
                list.add(e);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public List <Employee> getAllEmployees() {
        List< Employee > list = new ArrayList< >();
        String sql = "SELECT * FROM employee";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Employee e = new Employee();
                e.setId(rs.getInt("id"));
                e.setName(rs.getString("name"));
                e.setEmail(rs.getString("email"));
                e.setAddress(rs.getString("address"));
                e.setPhone(rs.getString("phone"));
                list.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String updateEmployee(Employee e){
        String sql = "UPDATE employee SET name = ?, email = ?, address = ?, phone = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, e.getName());
            ps.setString(2, e.getEmail());
            ps.setString(2, e.getAddress());
            ps.setString(2, e.getPhone());
            ps.setInt(3, e.getId());
            int isSuccess = ps.executeUpdate();
            if (isSuccess > 0){
                return "Success";
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return "Fail";
    }

    public String deleteEmployee(int id){
        String sql = "DELETE FROM employee WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            int isSuccess = ps.executeUpdate();
            if (isSuccess > 0){
                return "Success";
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return "Fail";
    }

    public String insertEmployee(Employee e){
        String sql = "INSERT INTO manager (name, email, address, phone) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, e.getName());
            ps.setString(2, e.getEmail());
            ps.setString(3, e.getAddress());
            ps.setString(4, e.getPhone());
            int isSuccess = ps.executeUpdate();
            if (isSuccess > 0){
                return "Success";
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return "Fail";
    }

}
