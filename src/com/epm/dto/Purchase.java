/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epm.dto;

import java.sql.Date;

/**
 *
 * @author Rajnish
 */
public class Purchase {
    public int user_id ; 
    public int purchase_id ;        // this column populates fromseq_purchase_id.nextval
    public int card_4_digit_info ; 
    public String card_type ;
    public String  bank_name ;
    public int cash_used ;  // if cash used 1/Yes then card_4_digit_info / card_type / bank_name is all null.  
    public float purchase_amount ;
    public String purchase_date ;
    public String purchase_desc ;
    public int expense_type_id ;      

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPurchase_id() {
        return purchase_id;
    }

    public void setPurchase_id(int purchase_id) {
        this.purchase_id = purchase_id;
    }

    public int getCard_4_digit_info() {
        return card_4_digit_info;
    }

    public void setCard_4_digit_info(int card_4_digit_info) {
        this.card_4_digit_info = card_4_digit_info;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public int getCash_used() {
        return cash_used;
    }

    public void setCash_used(int cash_used) {
        this.cash_used = cash_used;
    }

    public float getPurchase_amount() {
        return purchase_amount;
    }

    public void setPurchase_amount(float purchase_amount) {
        this.purchase_amount = purchase_amount;
    }

    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    public String getPurchase_desc() {
        return purchase_desc;
    }

    public void setPurchase_desc(String purchase_desc) {
        this.purchase_desc = purchase_desc;
    }

    public int getExpense_type_id() {
        return expense_type_id;
    }

    public void setExpense_type_id(int expense_type_id) {
        this.expense_type_id = expense_type_id;
    }


}
