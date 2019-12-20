package be.doubotis.testing;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import be.doubotis.banking.epsp.client.aisp.AISPClient;
import be.doubotis.banking.epsp.client.aisp.entities.Account;
import be.doubotis.banking.epsp.client.aisp.entities.Balance;
import be.doubotis.banking.epsp.client.aisp.entities.Transaction;
import be.doubotis.banking.epsp.client.auth.entities.Token;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author doubo
 */
public class AISPClientTest {
    
    public static final String CLIENT_ID = "<client_id_here>";
    public static final String CLIENT_SECRET = "<client_secret_here>";
    public static final String CODE = "<code>";
    public static final String ORGANIZATION_ID = "<organization_id>";
    public static final String REDIRECT_URI = "<redirect_uri>";
    
    private AISPClient mClient;
    
    public AISPClientTest() {
        mClient = new AISPClient(CLIENT_ID, CLIENT_SECRET, ORGANIZATION_ID, REDIRECT_URI);
        mClient.setToken(new Token("02ef68d2-8e08-4198-85bc-85179824a138", ""));
    }
    
    @Test
    public void testAccounts() {
        
        try {
            List<Account> result = mClient.getAccounts();
            System.out.println(result);
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void testBalance() {
        
        try {
            List<Balance> result = mClient.getBalances("BE97825781680596EUR");
            System.out.println(result);
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void testTransactions() {
        
        try {
            List<Transaction> result = mClient.getTransactions("BE97825781680596EUR");
            System.out.println(result);
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }
}
