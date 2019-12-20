/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.doubotis.banking.epsp.client.aisp.entities;

import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author doubo
 */
public class Account extends JSONObject {

    public Account(JSONObject jo) {
        for (String key : jo.keySet()) {
		put(key, jo.get(key));
	}
    }

    public void setResourceId(String resourceId) {
        put("resourceId", resourceId);
    }

    public String getResourceId() {
        return (String) get("resourceId");
    }
    
    public void setBic(String bic) {
        put("bicFi", bic);
    }
    
    public String getBic() {
        return (String) get("bicFi");
    }
    
    public void setName(String name) {
        put("name", name);
    }
    
    public String getName() {
        return (String) get("name");
    }
    
    public void setUsage(String usage) {
        put("usage", usage);
    }
    
    public String getUsage() {
        return (String) get("usage");
    }
    
    public String getCashAccountType() {
        return (String) get("cashAccountType");
    }
    
    public String getProduct() {
        return (String) get("product");
    }
    
    public void setCurrency(Currency currency) {
        put("currancy", currency.name());
    }
    
    public Currency getCurrency() {
        return Currency.valueOf((String) get("currency"));
    }
    
    public void setPsuStatus(String psuStatus) {
        put("psuStatus", psuStatus);
    }
    
    public String getPsuStatus() {
        return (String) get("psuStatus");
    }
    
    public void setAccountHolder(AccountHolder ah) {
        put("accountId", ah);
    }
    
    public AccountHolder getAccountHolder() {
        return new AccountHolder((JSONObject) get("accountId"));
    }

}
