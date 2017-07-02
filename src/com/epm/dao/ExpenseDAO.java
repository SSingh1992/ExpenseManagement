/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epm.dao;

import com.epm.dto.ExpenseType;
import java.util.List;

/**
 *
 * @author Rajnish
 */
public interface ExpenseDAO {
    public boolean saveExpenseType(ExpenseType expensetype);
    public ExpenseType findExpenseById(Integer expense_id);
    public List<ExpenseType> findAllExpenseTypes();
    public boolean deleteExpenseType(Integer expense_id);
    public boolean updateExpenseType(ExpenseType expensetype);      
}
