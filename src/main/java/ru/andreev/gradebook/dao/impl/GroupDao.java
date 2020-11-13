package ru.andreev.gradebook.dao.impl;

import ru.andreev.gradebook.dao.IDao;
import ru.andreev.gradebook.db.Database;
import ru.andreev.gradebook.entity.Group;
import ru.andreev.gradebook.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GroupDao implements IDao<Group> {

    @Override
    public Optional<Group> findById(Integer id) {
        String SQL = "SELECT groups.*, s.id, s.firstname, s.lastname FROM groups LEFT JOIN students s on groups.id = s.\"groupId\" WHERE groups.id = ?";
        Group group = null;
        try (Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL)){

            statement.setString(1,id.toString());

            try(ResultSet result = statement.executeQuery()){
               while (result.next()){
                   group = new Group();
                   group.setId(result.getInt("groups.id"));
                   group.setName(result.getString("name"));
                   List<Student> students = new ArrayList<>();
                   do{
                        Student student = new Student();
                        student.setId(result.getInt("s.id"));
                        student.setFirstName(result.getString("firstname"));
                        student.setLastName(result.getString("lastname"));
                        student.setGroup(group);

                        students.add(student);
                   }while (result.next());
                   group.setStudents(students);
               }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.ofNullable(group);
    }

    @Override
    public List<Group> findAll() {
        String SQL = "SELECT groups.*, s.id, s.firstname, s.lastname  FROM groups LEFT JOIN students s on groups.id = s.\"groupId\" ORDER BY groups.id";
        List<Group> groups = null;
        try(Connection connection = Database.getConnection();
            Statement statement = connection.createStatement()) {
            try (ResultSet result = statement.executeQuery(SQL)){
                boolean hadNext = result.next();
                groups = new ArrayList<>();
                while (hadNext){
                    Group group = new Group();
                    group.setId(result.getInt("id"));
                    group.setName(result.getString("name"));
                    List<Student> students = new ArrayList<>();
                    do{
                        Student student = new Student();
                        student.setId(result.getInt("s.id"));
                        student.setFirstName(result.getString("firstname"));
                        student.setLastName(result.getString("lastname"));
                        student.setGroup(group);

                        students.add(student);
                    }while ( (hadNext = result.next()) &&
                            group.getId() == result.getInt("id"));
                    group.setStudents(students);
                    groups.add(group);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  groups;
    }

    @Override
    public void save(Group group) {
        String SQL = "INSERT INTO groups (name) VALUES (?)";
        try(Connection connection  = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setString(1,group.getName());

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Group group) {
        String SQL = "UPDATE groups SET name = ? WHERE id = ?";
        try (Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setString(1,group.getName());
            statement.setInt(2, group.getId());

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String SQL = "DELETE FROM groups WHERE id = ?";
        try (Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setInt(1,id);

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
