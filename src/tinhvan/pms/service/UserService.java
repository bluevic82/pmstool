package tinhvan.pms.service;

import tinhvan.pms.model.User;

/**
 * @Purpose: class UserService
 * @author NguyenManhIT
 * @version 1.0
 * @Date 24/11/2017
 **/
public interface UserService {
    void save(User user);

    User findByUsername(String username);
    
}
