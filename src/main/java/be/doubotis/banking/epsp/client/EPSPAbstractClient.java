/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.doubotis.banking.epsp.client;

import java.io.IOException;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

/**
 *
 * @author doubo
 */
public abstract class EPSPAbstractClient {
    
    protected String mClientID;
    protected String mClientSecret;
    protected String mOrganizationID;
    protected String mRedirectUri;
    protected OpenbankVersion mVersion;
    
    public EPSPAbstractClient(String clientID, String clientSecret, String organizationID, String redirectUri) {
        mClientID = clientID;
        mClientSecret = clientSecret;
        mOrganizationID = organizationID;
        mRedirectUri = redirectUri;
        mVersion = OpenbankVersion.SANDBOX;
    }
    
    public void setVersion(OpenbankVersion version) {
        mVersion = version;
    }
    
    public OpenbankVersion getVersion() {
        return mVersion;
    }
    
    
}
