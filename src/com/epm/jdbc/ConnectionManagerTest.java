/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epm.jdbc;

//import com.epm.jdbc.ConnectionManager.giveConnection;
import com.epm.dao.UsersDAO;
import com.epm.dao.impl.UsersDAOImpl;
import java.sql.Connection;

/**
 *
 * @author Sanket
 */
public class ConnectionManagerTest {

    static UsersDAO udao = new UsersDAOImpl();
    public static void main(String args[]){
        try{
            
            ConnectionManager cn = new ConnectionManager();
            Connection connection = cn.getConnection();            
            if (connection != null){
                System.out.println("Connection established");
                
            }
            if(udao.login(1, "heli")){
    			System.out.println("We got from SQL");
    		}
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Wrong");
        }   
    }
    
}
