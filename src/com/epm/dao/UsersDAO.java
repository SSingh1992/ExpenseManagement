/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epm.dao;
import com.epm.dto.Users;
/**
 *
 * @author PRODEP_SOL
 */
public interface UsersDAO {
    public boolean login(int user_id,String password);
    public boolean addUser(Users user) throws Exception;
    public boolean updateProfile(Users user) throws Exception;
    public boolean changePassword(int user_id, String newPassword);
    public boolean forgotPassword(int user_id);
    public Users   viewProfile(int user_id);
    //++
    public void    closeConnection();  
}
