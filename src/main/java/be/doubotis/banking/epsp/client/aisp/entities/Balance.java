/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.doubotis.banking.epsp.client.aisp.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author doubo
 */
public class Balance extends JSONObject {
    
    public Balance(JSONObject jo) {
        for (String key : jo.keySet()) {
		put(key, jo.get(key));
	}
    }
    
    public void setBalanceType(BalanceType balanceType) {
        put("balanceType", balanceType.name());
    }
    
    public BalanceType getBalanceType() {
        return BalanceType.valueOf((String) get("balanceType"));
    }
    
    public void setName(String name) {
        put("name", name);
    }
    
    public String getName() {
        return (String) get("name");
    }
    
    public void setReferenceDate(Date referenceDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String resultedString = sdf.format(referenceDate);
        put("referenceDate", resultedString);
    }
    
    public Date getReferenceDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        Date resultedDate = null;
        try {
            resultedDate = sdf.parse((String) get("referenceDate"));
        } catch (ParseException pe) {}
        return  resultedDate;
    }
    
    public void setBalanceAmount(Amount amount) {
        put("balanceAmount", amount);
    }
    
    public Amount getBalanceAmount() {
        return (Amount) get("balanceAmount");
    }
    
}
