/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epm.dao.impl;

import com.epm.dao.PurchaseDAO;
import com.epm.dao.Sqls;
import com.epm.dto.Purchase;
import com.epm.jdbc.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rajnish
 */
public class PurchaseDAOImpl implements PurchaseDAO, Sqls {

    public String dbused;
    public Connection conn;

    public PurchaseDAOImpl() {
        super();
        try {
            ConnectionManager cm = new ConnectionManager();
            conn = cm.getConnection();
            if (conn != null) {
                System.out.println("Database Connection is ok in PurchaseDaoImpl..");
            } else {
                System.out.println("Database Connection is NOT ok in PurchaseDaoImpl..");
            }
            dbused = cm.dbname;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean savePurchase(Purchase purchase) {
        String savePurchase;
        PreparedStatement ps;
        int rowChanged;
        boolean purchaseSaved = false;
        try {
            if (dbused.equalsIgnoreCase("oracle")) {
                //Need sequence no here.
                System.out.println(INSERT1_PURCHASING);
                ps = conn.prepareStatement(INSERT1_PURCHASING);
            } else {
                System.out.println(INSERT2_PURCHASING);
                ps = conn.prepareStatement(INSERT2_PURCHASING);
            }
            //purchase_id,
            //user_id ,card_4_digit_info ,card_type  , bank_name , cash_used ,purchase_amount,purchase_date ,purchase_desc ,expense_type_id
            ps.setInt(1, purchase.getUser_id());
            ps.setInt(2, purchase.getCard_4_digit_info());
            ps.setString(3, purchase.getCard_type());
            ps.setString(4, purchase.getBank_name());
            ps.setInt(5, purchase.getCash_used());
            ps.setFloat(6, purchase.getPurchase_amount());
            //ps.setDate(2, java.sql.Date.valueOf("2013-09-04");
            ps.setDate(7, java.sql.Date.valueOf(purchase.getPurchase_date()));
            ps.setString(8, purchase.getPurchase_desc());
            ps.setInt(9, purchase.getExpense_type_id());
            //++++++++++
            /*
            
            */
            //++++++++++
            rowChanged = ps.executeUpdate();
            if (rowChanged > 0) {
                purchaseSaved = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return purchaseSaved;
    }
    //+++++++++++++

    //%%%%%%%%%%%%%
    @Override
    public Purchase findPurchaseByID(Integer id) {
        PreparedStatement ps;
        Purchase purchase = null ;
        System.out.println(FIND_PURCHASE_BYID + " Param->" + id);
        try{
            purchase = new Purchase();
            ps = conn.prepareStatement(FIND_PURCHASE_BYID);
            ps.setInt(1,id);
            System.out.println("worked fine till here..");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                purchase.setPurchase_id(rs.getInt("purchase_id"));
                purchase.setUser_id(rs.getInt("user_id"));
                purchase.setCard_4_digit_info(rs.getInt("card_4_digit_info"));
                purchase.setCard_type(rs.getString("card_type"));
                purchase.setBank_name(rs.getString("bank_name"));
                purchase.setCash_used(rs.getInt("cash_used"));
                purchase.setPurchase_amount(rs.getFloat("purchase_amount"));
                purchase.setPurchase_date(rs.getString("purchase_date"));
                purchase.setPurchase_desc(rs.getString("purchase_desc"));
                purchase.setExpense_type_id(rs.getInt("expense_type_id"));
            }
            else{
                System.out.println("purchase_id ->" + id + " doesnt exists in db..or soemthing went wrong.");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return purchase;
    }
    //%%%%%%%%%%%%%
    
    @Override
    public List<Purchase> findAllPurchases(Integer id) {
        List<Purchase> listPurchases = new ArrayList<Purchase>();
        System.out.println(FIND_ALL_PURCHASES);
        PreparedStatement ps = null;        
        try{
            ps = conn.prepareStatement(FIND_ALL_PURCHASES);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Purchase purchase = new Purchase();
                //+++++++++
                purchase.setPurchase_id(rs.getInt("purchase_id"));
                purchase.setUser_id(rs.getInt("user_id"));
                purchase.setCard_4_digit_info(rs.getInt("card_4_digit_info"));
                purchase.setCard_type(rs.getString("card_type"));
                purchase.setBank_name(rs.getString("bank_name"));
                purchase.setCash_used(rs.getInt("cash_used"));
                purchase.setPurchase_amount(rs.getFloat("purchase_amount"));
                purchase.setPurchase_date(rs.getString("purchase_date"));
                purchase.setPurchase_desc(rs.getString("purchase_desc"));
                purchase.setExpense_type_id(rs.getInt("expense_type_id"));                
                listPurchases.add(purchase);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return listPurchases;
    }

    @Override
    public boolean deletePurchase(Integer id) {
        PreparedStatement ps;
        System.out.println( DELETE_PURCHASE + " Param->" + id);
        boolean bPurchaseDeleted = false;
        try{
        ps = conn.prepareStatement(DELETE_PURCHASE);
        ps.setInt(1,id);
        int rowChanged = ps.executeUpdate();
        if ( rowChanged > 0 ){
            bPurchaseDeleted = true ;
        }        
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return bPurchaseDeleted;
    }

    @Override
    public boolean updatePurchase(Purchase purchase) {

        PreparedStatement ps;
        System.out.println( UPDATE_PURCHASE );
        boolean bPurchaseUpdated = false;
        try{
            ps = conn.prepareStatement(UPDATE_PURCHASE);
            ps.setInt(1, purchase.getCard_4_digit_info());
            ps.setString(2, purchase.getCard_type());
            ps.setString(3, purchase.getBank_name());
            ps.setInt(4, purchase.getCash_used());
            ps.setFloat(5, purchase.getPurchase_amount());
            ps.setDate(6, java.sql.Date.valueOf(purchase.getPurchase_date()));
            ps.setString(7, purchase.getPurchase_desc());
            ps.setInt(8, purchase.getExpense_type_id());
            //where clause here
            ps.setInt(9,  purchase.getPurchase_id());
            ps.setInt(10, purchase.getUser_id());
            int rowChanged = ps.executeUpdate();
            if ( rowChanged > 0 ){
                bPurchaseUpdated = true ;
            }        
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return bPurchaseUpdated;
    }

}

