package service;

import mapper.UserMapper;
import model.UserModel;

import java.util.List;

public interface IUserService {
    UserModel findByUser(String userName, String password, Integer status);
    Long saveUser(String userName, String password,Integer status,Long roleId);
}
