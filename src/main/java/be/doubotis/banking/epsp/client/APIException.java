/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.doubotis.banking.epsp.client;

import java.io.IOException;

/**
 *
 * @author doubo
 */
public class APIException extends IOException {
    
    private int mCode;
    private String mName;
    
    public APIException(int code, String name, String message)
    {
        super(message);
        mCode = code;
        mName = name;
    }

    @Override
    public String toString() {
        return mCode + " " + mName + " - " + getMessage();
    }
}
