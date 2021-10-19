package dao.Impl;

import dao.GenericDAO;
import mapper.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AbstractDAO<T> implements GenericDAO<T> {

    ResourceBundle resourceBundle = ResourceBundle.getBundle("db");

    public Connection getConnection(){
        try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://127.0.0.1:2906/newservlet";
                String user = "root";
                String password = "Nguyenvietket2906";
                return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }

    @Override
    public List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
        List<T> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            //set parameters
            setParameter(preparedStatement,parameters);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                results.add(rowMapper.mapRow(resultSet));
            }
            return results;
        }catch (SQLException e){
            return null;
        }finally {
            try {
                if(connection != null){
                    connection.close();
                }
                if (preparedStatement != null){
                    preparedStatement.close();
                }
                if(resultSet != null){
                    resultSet.close();
                }
            } catch (SQLException e) {
                return null;
            }
        }
    }

    @Override
    public void update(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            setParameter(preparedStatement,parameters);
            preparedStatement.executeUpdate();
            connection.commit();
        }catch (SQLException e){
            if(connection != null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }finally {
            try {
                if(connection != null){
                    connection.close();
                }
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Long insert(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Long id = null;
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setParameter(statement, parameters);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            connection.commit();
            return id;
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public int count(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            int count = 0;
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            //set parameters
            setParameter(preparedStatement,parameters);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                count = resultSet.getInt(1);
            }
            return count;
        }catch (SQLException e){
            return 0;
        }finally {
            try {
                if(connection != null){
                    connection.close();
                }
                if (preparedStatement != null){
                    preparedStatement.close();
                }
                if(resultSet != null){
                    resultSet.close();
                }
            } catch (SQLException e) {
                return 0;
            }
        }
    }

    private void setParameter(PreparedStatement preparedStatement, Object... parameters) {
        try {
            for (int i = 0; i < parameters.length; i++) {
                Object parameter = parameters[i];
                int index = i + 1;
                if(parameter instanceof Long){
                    preparedStatement.setLong(index, (long) parameter);
                }else if(parameter instanceof String){
                    preparedStatement.setString(index, (String) parameter);
                }else if(parameter instanceof Integer){
                    preparedStatement.setInt(index, (Integer) parameter);
                }else if(parameter instanceof Timestamp){
                    preparedStatement.setTimestamp(index, (Timestamp) parameter);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
