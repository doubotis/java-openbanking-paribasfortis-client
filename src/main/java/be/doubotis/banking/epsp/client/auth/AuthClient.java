/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.doubotis.banking.epsp.client.auth;

import be.doubotis.banking.epsp.client.APIException;
import be.doubotis.banking.epsp.client.EPSPAbstractClient;
import be.doubotis.banking.epsp.client.auth.entities.Token;
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
public class AuthClient extends EPSPAbstractClient {
    
    public AuthClient(String clientID, String clientSecret, String organizationID, String redirectUri) {
        super(clientID, clientSecret, organizationID, redirectUri);
    }
    
    protected APIException buildError(JSONObject jo)
    {
        int status = (Integer) jo.get("statusCode");
        String error = (String) jo.get("error");
        String message = (String) jo.get("message");
        return new APIException(status, error, message);
    }
    
    protected String getApiUri() {
        switch (getVersion()) {
            case SANDBOX:
                return "https://sandbox.auth.bnpparibasfortis.com/";    
            default:
                throw new IllegalStateException("Openbank version not supported");
        }
    }
    
    public Token getToken(String code) throws IOException {
        
        //Build url.
        String url = getApiUri() + "token";
        
        RequestBody requestBody = new MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("code", code)
            .addFormDataPart("grant_type", "authorization_code")
            .addFormDataPart("redirect_uri", mRedirectUri)
            .addFormDataPart("client_id", mClientID)
            .addFormDataPart("client_secret", mClientSecret)
            .addFormDataPart("scope", "aisp")
            .build();
        
        Request request = new Request.Builder()
            .addHeader("X-Openbank-Organization", mOrganizationID)
            .addHeader("X-Openbank-Stet-Version", mVersion.getCompleteName())
            .addHeader("Content-Type","multipart/form-data")
            .url(url)
            .post(requestBody)
            .build();
        
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        
        JSONObject jo = new JSONObject(result);
        
        // Find if there is an error.
        boolean hasError = jo.has("error");
        if (hasError) {
            APIException error = buildError(jo);
            /*if (error.toString().contains("Unauthorized")) {
                // Ask to the Auth client.
                refreshToken(mRefreshToken);
            }*/
            throw error;
        }
        
        
        Token token = new Token(jo);
        return token;
    }
    
}
