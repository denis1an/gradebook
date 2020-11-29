package ru.andreev.gradebook.dao.impl;

import ru.andreev.gradebook.dao.IDao;
import ru.andreev.gradebook.db.Database;
import ru.andreev.gradebook.entity.Group;
import ru.andreev.gradebook.entity.Student;
import ru.andreev.gradebook.entity.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDao implements IDao<Student> {

    private final Connection connection;

    public StudentDao() {
        connection = Database.getConnection();
    }

    @Override
    public Optional<Student> findById(Integer id) {
        String SQL = "SELECT students.id, firstname, lastname, g.id, g.name FROM students " +
                "INNER JOIN groups g on g.id = students.\"groupId\" WHERE students.id = ?";
        Student student = null;
        try(PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setInt(1,id);

            statement.executeUpdate();
            try (ResultSet result = statement.executeQuery()){
                if(result.next()){
                    student = new Student();
                    student.setId(result.getInt("id"));
                    student.setFirstName(result.getString("firstname"));
                    student.setLastName(result.getString("lastname"));

                    Group group = new Group();
                    group.setId(result.getInt("g.id"));
                    group.setName("name");
                    student.setGroup(group);
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.ofNullable(student);
    }

    @Override
    public List<Student> findAll() {
        String SQL = "SELECT students.id, firstname, lastname FROM students";
        List<Student> students = null;
        try (Statement statement = connection.createStatement()){
            try (ResultSet resultSet = statement.executeQuery(SQL)){
                students = new ArrayList<>();
                while (resultSet.next()){
                    Student student = new Student();
                    student.setId(resultSet.getInt("id"));
                    student.setFirstName(resultSet.getString("firstname"));
                    student.setLastName(resultSet.getString("lastname"));
                    students.add(student);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public void save(Student student) {
        String SQL = "INSERT INTO students (firstname,lastname, \"groupId\") VALUES (?,?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setString(1,student.getFirstName());
            statement.setString(2,student.getLastName());
            statement.setInt(3,student.getGroup().getId());

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Student student) {
        String SQL = "UPDATE students SET firstname = ?, lastname = ?,  \"groupId\"= ? WHERE  id = ?";
        try (PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setInt(3, student.getGroup().getId());
            statement.setInt(4,student.getId());

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String SQL = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setInt(1, id);

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Student> findAllByGroupId(Integer groupId){
        String SQL = "SELECT students.id, firstname, lastname, \"groupId\", t.id, name, mark FROM students " +
                "LEFT JOIN tasks t ON students.id = t.\"studentId\" WHERE \"groupId\" = ? ORDER BY students.id";
        List<Student> students = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setInt(1,groupId);
            try (ResultSet resultSet = statement.executeQuery()){
                students = new ArrayList<>();
                boolean hadNext = resultSet.next();
                while (hadNext){
                    Student student = new Student();
                    student.setId(resultSet.getInt(1));
                    student.setFirstName(resultSet.getString(2));
                    student.setLastName(resultSet.getString(3));

                    Group group = new Group();
                    group.setId(resultSet.getInt(4));
                    student.setGroup(group);

                    List<Task> tasks = new ArrayList<>();
                    do{
                        Task task =  new Task();
                        task.setId(resultSet.getInt(5));
                        task.setName(resultSet.getString(6));
                        task.setMark(resultSet.getString(7));
                    }while ((hadNext = resultSet.next()) &&
                            student.getId() == resultSet.getInt(1));
                    student.setTasks(tasks);
                    students.add(student);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return students;
    }


}
