/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epm.dao.impl;

import com.epm.dao.Sqls;
import com.epm.dao.UsersDAO;
import com.epm.dto.Users;
import com.epm.jdbc.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author
 */
public class UsersDAOImpl implements UsersDAO,Sqls {
    public Connection conn;
    public UsersDAOImpl() {
        try {
            ConnectionManager cm = new ConnectionManager();
            conn = cm.getConnection();
            if (conn != null ){
                System.out.println("Database Connection established properly with the application!!!");
            }
            else
            {
                System.out.println("Something went wrong on Database Connection creation.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //not sure how i can call cm.closeConnection() from here.
            //Basically when error occurs, connection should be called to close..otherwise on db side,it will stay open and if it hits the max limit, you cann't connect to db.
        }
    }
    
    public void closeConnection(){
        if(conn!= null ){
            try{
            conn.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public boolean addUser(Users user) throws Exception {
        //Check if user exists or not..If not then just add the user.
        boolean userAdded = false;
        int user_id = user.getUser_id();
        String name = user.getName();
        String location= user.getLocation();
        String password = user.getPassword();
        //++
        PreparedStatement ps ;
        try {
            System.out.println(ADDUSER);
            if ( userExists(user_id) == false )
            {
                System.out.println("User :" + user_id + " doesnot exists in db so lets insert the same.");
                ps  = conn.prepareStatement(ADDUSER);
                ps.setInt(1, user_id);
                ps.setString(2, password);
                ps.setString(3, name);
                ps.setString(4, location);
                int rowChanged = ps.executeUpdate();
                if ( rowChanged > 0 ){
                    userAdded = true ;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userAdded;
    }

    @Override
    public boolean login(int user_id, String password) {
        // Get the connection --it is already there.
        // Create a Statement -- take user name and password and validate.
        boolean bAuthenticaed = false;
        try {
            System.out.println(LOGINSQL);
            PreparedStatement ps = conn.prepareStatement(LOGINSQL);
            ps.setInt(1,user_id);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Application user validated successfully.");
                bAuthenticaed = true;
            } else {
                System.out.println("Something went wrong..Application user validation failed.");
                bAuthenticaed = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // Create a Query to check user exists or not
        // Use the execute****() method
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return bAuthenticaed;
    }

    
    
    
    @Override
    public boolean updateProfile(Users user) throws Exception {
        //Based on Users properties, update the db for the same user_id.
        boolean bProfUpdated = false;
        int user_id = user.getUser_id();
        String name = user.getName();
        String location= user.getLocation();
        PreparedStatement ps ;
        try {
            System.out.println(UPDATEPROFILE1);
            ps  = conn.prepareStatement(UPDATEPROFILE1);
            ps.setString(1, name);
            ps.setString(2, location);
            ps.setInt(3, user_id);
            int rowChanged = ps.executeUpdate();
            if ( rowChanged > 0 ){
                bProfUpdated = true ;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bProfUpdated ;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean userExists(int user_id) {
        boolean bUserExists = false;
        System.out.println(USEREXISTS);
        try{
            PreparedStatement ps = conn.prepareStatement(USEREXISTS);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                bUserExists = true;
                System.out.println("From UserExists ->" + user_id );
                
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return bUserExists;
        }
        return bUserExists;
    }
    //++++++++++++++
    
    @Override
    public boolean forgotPassword(int user_id) {
        boolean bPassChanged = false;
        // create default password and give it back.
        System.out.println(CHANGEPASSWORD + " ->" + DEFAULTPASSWORD);
        try {
            PreparedStatement ps = conn.prepareStatement(CHANGEPASSWORD);
            ps.setString(1, DEFAULTPASSWORD);
            ps.setInt(2, user_id);
            int rowChanged = ps.executeUpdate();
            if ( rowChanged > 0 ){
                bPassChanged = true ;
                //user.setPassword(DEFAULTPASSWORD); // important.
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            }
        return bPassChanged;    
    }

    @Override
    public Users viewProfile(int user_id) {
        //connect to db... get the results.. and bind back to users class... return the users object.
        // if things goes wrong, return null.
        Users user = new Users();
        try {
            System.out.println(VIEWPROFILE);
            PreparedStatement ps = conn.prepareStatement(VIEWPROFILE);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setUser_id(rs.getInt("user_id"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setLocation(rs.getString("location"));
                // System.out.println(rs.getString("password") + " ->" + rs.getString("name"));
            } else {
                System.out.println("User_id ->" + user_id + " doesnt exists in db.");
                user = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public boolean changePassword(int user_id, String newPassword) {
        //Grab the old password from Users class... 
        //compare with new one and if the are not same then change the password.
        boolean bPassChanged = false ;
        Users user = viewProfile(1000);
        String oldPassword= user.getPassword().toLowerCase();
        //System.out.println(oldPassword);
        if (oldPassword.equals(newPassword.toLowerCase())){
            System.out.println("please provide new password to change.");
            bPassChanged = false;
        }
        else{
            System.out.println(CHANGEPASSWORD);
            try {
                PreparedStatement ps = conn.prepareStatement(CHANGEPASSWORD);
                ps.setString(1, newPassword);
                ps.setInt(2, user_id);
                int rowChanged = ps.executeUpdate();
                if ( rowChanged > 0 ){
                    bPassChanged = true ;
                    user.setPassword(newPassword); // important.
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return bPassChanged ;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
