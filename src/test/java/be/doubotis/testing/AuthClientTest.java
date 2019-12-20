package be.doubotis.testing;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import be.doubotis.banking.epsp.client.auth.AuthClient;
import be.doubotis.banking.epsp.client.auth.entities.Token;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author doubo
 */
public class AuthClientTest {
    
    public static final String CLIENT_ID = "<client_id_here>";
    public static final String CLIENT_SECRET = "<client_secret_here>";
    public static final String CODE = "<code>";
    public static final String ORGANIZATION_ID = "<organization_id_here>";
    public static final String REDIRECT_URI = "<redirect_uri>";
    
    private AuthClient mClient;
    
    
    public AuthClientTest() {
        mClient = new AuthClient(CLIENT_ID, CLIENT_SECRET, ORGANIZATION_ID, REDIRECT_URI);
    }

    @Test
    public void testAuth() {
        try {
            Token token = mClient.getToken(CODE);
            System.out.println(token);
        } catch (Exception e) {
            Assert.fail(e.toString());
        }
    }
}
