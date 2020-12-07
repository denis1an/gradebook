package ru.andreev.gradebook.dao.impl;

import ru.andreev.gradebook.dao.IDao;
import ru.andreev.gradebook.db.Database;
import ru.andreev.gradebook.entity.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TaskDao implements IDao<Task> {

    private final Connection connection;

    public TaskDao() {
        connection = Database.getConnection();
    }

    @Override
    public Optional<Task> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public void save(Task task) {
        String SQL = "INSERT INTO tasks (name, mark, \"studentId\") values (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(SQL)){

            statement.setString(1,task.getName());
            statement.setString(2, task.getMark());
            statement.setInt(3,task.getStudent().getId());

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();;
        }
    }

    @Override
    public void update(Task task) {

    }

    @Override
    public void delete(Integer id) {

    }
}
