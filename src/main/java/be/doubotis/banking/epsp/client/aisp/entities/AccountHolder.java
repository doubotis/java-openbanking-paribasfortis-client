/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.doubotis.banking.epsp.client.aisp.entities;

import org.json.JSONObject;

/**
 *
 * @author doubo
 */
public class AccountHolder extends JSONObject {
    
    public AccountHolder(JSONObject jo) {
        for (String key : jo.keySet()) {
		put(key, jo.get(key));
	}
    }
    
    public void setIBAN(String iban) {
        put("iban", iban);
    }
    
    public String getIBAN() {
        return (String) get("iban");
    }
}
