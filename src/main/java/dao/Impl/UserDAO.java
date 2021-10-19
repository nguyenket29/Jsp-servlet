package dao.Impl;

import dao.IUserDAO;
import mapper.NewMapper;
import mapper.UserMapper;
import model.UserModel;

import java.util.List;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {
    @Override
    public UserModel findByUser(String userName, String password, Integer status) {
        StringBuilder sql = new StringBuilder("SELECT * FROM user AS u");
        sql.append(" INNER JOIN role AS r ON r.id = u.roleid");
        sql.append(" WHERE username = ? AND password = ? AND status = ?");
        List<UserModel> users = query(sql.toString(), new UserMapper(), userName, password, status);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public Long saveUser(String userName, String password,Integer status,Long roleId) {
        StringBuilder sql = new StringBuilder("INSERT INTO user (username,password,status,roleid)");
        sql.append(" VALUES (?,?,?,?)");
        return insert(sql.toString(),userName,password,status,roleId);
    }
}
