package service.Impl;

import dao.IUserDAO;
import dao.Impl.UserDAO;
import model.UserModel;
import service.IUserService;

import javax.inject.Inject;

public class UserService implements IUserService {
    @Inject
    private IUserDAO userDAO;

    @Override
    public UserModel findByUser(String userName, String password, Integer status) {
        return userDAO.findByUser(userName,password,status);
    }

    @Override
    public Long saveUser(String userName, String password,Integer status,Long roleId) {
        return userDAO.saveUser(userName,password,status,roleId);
    }
}
