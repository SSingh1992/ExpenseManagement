/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expensemanagement;
import com.epm.dao.PurchaseDAO;
import com.epm.dao.UsersDAO;
import com.epm.dao.impl.UsersDAOImpl;
import com.epm.dto.Users;
import static com.epm.dao.Sqls.DEFAULTPASSWORD;
import com.epm.dao.impl.PurchaseDAOImpl;
import com.epm.dto.Purchase;
import com.epm.frontfaces.LoginForm;

/**
 *
 * @author PRODEP_SOL
 * ctrl + shft + I ->Import classes.
 */
public class ExpenseManagement {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //How to make java Swing application excutable using netbeans.       
        new LoginForm().setVisible(true);
    }
}
