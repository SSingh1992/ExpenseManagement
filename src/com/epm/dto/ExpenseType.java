/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epm.dto;

/**
 *
 * @author Rajnish
 */
public class ExpenseType {

ExpenseType(){}    
int expense_id ;
String expense_type ;
String expense_subtype ;

    public int getExpense_id() {
        return expense_id;
    }

    public void setExpense_id(int expense_id) {
        this.expense_id = expense_id;
        //Sequence generates this data for oracle db case... for MY SQL, skip this ...or no need to use this.
        // select seq_expense.nextval from dual;
    }

    public String getExpense_type() {
        return expense_type;
    }

    public void setExpense_type(String expense_type) {
        this.expense_type = expense_type;
    }

    public String getExpense_subtype() {
        return expense_subtype;
    }

    public void setExpense_subtype(String expense_subtype) {
        this.expense_subtype = expense_subtype;
    }

}
