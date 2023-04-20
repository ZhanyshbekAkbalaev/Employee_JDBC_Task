package org.example.dao.impl;

import org.example.dao.EmployeeDao;
import org.example.model.Employee;
import org.example.model.Job;
import org.example.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDaoImpl implements EmployeeDao {
    private Connection connection;

    public EmployeeDaoImpl() {
        this.connection = Util.getConnection();
    }

    @Override
    public void createEmployee() {
        String sql = "create table if not exists employees(id serial primary key,first_name varchar,last_name varchar,age int ,email varchar,job_id int references jobs(id))";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Created Employee Table!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addEmployee(Employee employee) {
        String sql = "insert into employees(first_name,last_name ,age , email,job_id)values (?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setInt(3, employee.getAge());
            statement.setString(4, employee.getEmail());
            statement.setInt(5, employee.getJobId());
            statement.executeUpdate();
            System.out.println("Successfully saved!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropTable() {
        String sql = "drop table employees";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("dropped table employee");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void cleanTable() {
        String sql = "delete from employees";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("clean employee");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
        String sql = "update employees set first_name = ?," +
                "last_name = ? ,age = ?,email = ?,job_id = ? where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setInt(3, employee.getAge());
            statement.setString(4, employee.getEmail());
            statement.setLong(5, employee.getJobId());
            statement.setLong(6, id);
            statement.executeUpdate();
            System.out.println("updated employee!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> all = new ArrayList<>();
        String sql = "select * from employees";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                all.add(new Employee(resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getInt("job_id")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return all;
    }

    @Override
    public Employee findByEmail(String email) {
        Employee employee = new Employee();
        String sql = "select * from employees where email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                employee.setId(resultSet.getLong("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setAge(resultSet.getInt("age"));
                employee.setEmail(resultSet.getString("email"));
                employee.setJobId(resultSet.getInt("job_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employee;
    }
//
//
//
//
//

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        Map<Employee, Job> all = new HashMap<>();
        String sql = "select * from employees e join jobs j ON e.job_id = j.id where e.id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1,employeeId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                all.put(new Employee(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getInt(resultSet.getInt("job_id"))
                ),new Job(
                        resultSet.getLong("id"),
                        resultSet.getString("position"),
                        resultSet.getString("profession"),
                        resultSet.getString("description"),
                        resultSet.getInt("experience"))
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return all;
    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        List<Employee> all = new ArrayList<>();
        String sql = "select * from employees e join jobs j on j.id = e.job_id where j.position = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,position);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                all.add(new Employee(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getInt("job_id")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return all;
    }
}
