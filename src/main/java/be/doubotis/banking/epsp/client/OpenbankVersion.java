/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.doubotis.banking.epsp.client;

/**
 *
 * @author doubo
 */
public enum OpenbankVersion {
    SANDBOX("1.4.0.47.develop");
    
    private String mName;
    OpenbankVersion(String name) {
        mName = name;
    }
    
    public String getCompleteName() {
        return mName;
    }
}
