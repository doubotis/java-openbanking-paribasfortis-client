/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.doubotis.banking.epsp.client.aisp.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;

/**
 *
 * @author doubo
 */
public class Transaction extends JSONObject {
    
    public Transaction(JSONObject jo) {
        for (String key : jo.keySet()) {
		put(key, jo.get(key));
	}
    }
    
    public void setBookingDate(Date bookingDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String resultedString = sdf.format(bookingDate);
        put("bookingDate", resultedString);
    }
    
    public Date getBookingDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        Date resultedDate = null;
        try {
            resultedDate = sdf.parse((String) get("bookingDate"));
        } catch (ParseException pe) {}
        return  resultedDate;
    }
    
    public void setResourceId(String resourceId) {
        put("resourceId", resourceId);
    }
    
    public String getResourceId() {
        return (String) get("resourceId");
    }
    
    public void setStatus(String status) {
        put("status", status);
    }
    
    public String getStatus() {
        return (String) get("status");
    }
    
    public void setEntryReference(String entryReference) {
        put("entryReference", entryReference);
    }
    
    public String getEntryReference() {
        return (String) get("entryReference");
    }
    
    public void setTransactionAmount(Amount amount) {
        put("transactionAmount", amount);
    }
    
    public Amount getTransactionAmount() {
        return (Amount) get("transactionAmount");
    }
}
