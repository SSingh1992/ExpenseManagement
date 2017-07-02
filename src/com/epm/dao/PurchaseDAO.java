/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epm.dao;

import com.epm.dto.Purchase;
import java.util.List;

/**
 *
 * @author Rajnish
 */
public interface PurchaseDAO {
    public boolean savePurchase(Purchase purchase);
    public Purchase findPurchaseByID(Integer id);
    public List<Purchase> findAllPurchases(Integer id);
    public boolean deletePurchase(Integer id);
    public boolean updatePurchase(Purchase purchase);    
}
