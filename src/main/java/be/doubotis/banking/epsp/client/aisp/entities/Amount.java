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
public class Amount extends JSONObject {
    
    public Amount(JSONObject jo) {
        for (String key : jo.keySet()) {
		put(key, jo.get(key));
	}
    }
    
    public void setAmount(double amount) {
        put("amount", "" + amount);
    }
    
    public double getAmount() {
        return Double.parseDouble((String) get("amount"));
    }
    
    public void setCurrency(Currency currency) {
        put("currency", currency.name());
    }
    
    public Currency getCurrency() {
        return Currency.valueOf((String) get("currency"));
    }
    
}
