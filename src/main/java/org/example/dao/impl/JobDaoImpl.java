package org.example.dao.impl;

import org.example.dao.JobDao;
import org.example.model.Job;
import org.example.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDaoImpl implements JobDao {
    private Connection connection;

    public JobDaoImpl() {
        this.connection = Util.getConnection();
    }

    @Override
    public void createJobTable() {
        String sql = "create table if not exists jobs(id serial primary key ,position varchar,profession varchar,description varchar,experience int)";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Created Job Table!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addJob(Job job) {
        String sql = "insert into jobs(position,profession ,description , experience)values (?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, job.getPosition());
            statement.setString(2, job.getProfession());
            statement.setString(3, job.getDescription());
            statement.setInt(4, job.getExperience());
            int i = statement.executeUpdate();
            System.out.println(i + " add Job.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Job getJobById(Long jobId) {
        Job job = new Job();
        String sql = "select * from jobs where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, jobId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("position"));
                job.setProfession(resultSet.getString("profession"));
                job.setDescription(resultSet.getString("description"));
                job.setExperience(resultSet.getInt("experience"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return job;
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        List<Job> job = new ArrayList<>();
         switch (ascOrDesc) {
             case "asc":
                 String sql = "select * from jobs order by experience";
                 try (PreparedStatement statement = connection.prepareStatement(sql)) {
                     ResultSet resultSet = statement.executeQuery();
                     while (resultSet.next()) {
                         job.add(new Job(
                                 resultSet.getLong("id"),
                                 resultSet.getString("position"),
                                 resultSet.getString("profession"),
                                 resultSet.getString("description"),
                                 resultSet.getInt("experience")
                         ));
                     }
                 } catch (SQLException e) {
                     System.out.println(e.getMessage());
                 }
                 break;
             case "desc":
                 String sqll = "select * from jobs order by experience DESC";
                 try (PreparedStatement statement = connection.prepareStatement(sqll)) {
                     ResultSet resultSet = statement.executeQuery();
                     while (resultSet.next()) {
                         job.add(new Job(
                                 resultSet.getLong("id"),
                                 resultSet.getString("position"),
                                 resultSet.getString("profession"),
                                 resultSet.getString("description"),
                                 resultSet.getInt("experience")
                         ));
                     }
                 } catch (SQLException e) {
                     System.out.println(e.getMessage());
                 }
                 break;
             default:
                 System.out.println("asc and dasc WRITE!!!");
         }
        return job;

    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        Job job = new Job();
        String sql = "select * from jobs where ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, employeeId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("position"));
                job.setProfession(resultSet.getString("profession"));
                job.setDescription(resultSet.getString("description"));
                job.setExperience(resultSet.getInt("experience"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return job;
    }

    @Override
    public void deleteDescriptionColumn() {
        String sql = "alter table jobs drop column description";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
