package dao;

import model.UserModel;

public interface IUserDAO extends GenericDAO<UserModel> {
    UserModel findByUser(String userName, String password, Integer status);

    Long saveUser(String userName, String password,Integer status,Long roleId);
}