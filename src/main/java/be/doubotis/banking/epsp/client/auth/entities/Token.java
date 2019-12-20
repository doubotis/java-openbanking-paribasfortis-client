/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.doubotis.banking.epsp.client.auth.entities;

import org.json.JSONObject;

/**
 *
 * @author doubo
 */
public class Token extends JSONObject {
    
    public Token(JSONObject jo) {
        for (String key : jo.keySet()) {
		put(key, jo.get(key));
	}
    }
    
    public Token(String token, String refreshToken) {
        put("access_token", token);
        put("refresh_token", refreshToken);
    }
    
    public String getToken() {
        return (String) get("access_token");
    }
    
    public String getRefreshToken() {
        return (String) get("refresh_token");
    }

    public void invalidate() {
        put("access_token", "");
    }
    
}
