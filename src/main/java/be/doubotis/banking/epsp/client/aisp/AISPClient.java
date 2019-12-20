/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.doubotis.banking.epsp.client.aisp;

import be.doubotis.banking.epsp.client.APIException;
import be.doubotis.banking.epsp.client.EPSPAbstractClient;
import be.doubotis.banking.epsp.client.OpenbankVersion;
import be.doubotis.banking.epsp.client.aisp.entities.Account;
import be.doubotis.banking.epsp.client.aisp.entities.Balance;
import be.doubotis.banking.epsp.client.aisp.entities.Transaction;
import be.doubotis.banking.epsp.client.auth.AuthClient;
import be.doubotis.banking.epsp.client.auth.entities.Token;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author doubo
 */
public class AISPClient extends EPSPAbstractClient {

    protected Token mToken;

    public AISPClient(String clientID, String clientSecret, String organizationID, String redirectUri) {
        super(clientID, clientSecret, organizationID, redirectUri);
    }

    /**
     * Tokens methods
     */
    public void setToken(Token token) {
        mToken = token;
    }

    protected boolean hasReusableToken() {
        return (mToken != null && mToken.getToken().equals("") && !mToken.getRefreshToken().equals(""));
    }

    protected void refreshToken(String refreshToken) throws IOException {
        AuthClient authClient = new AuthClient(mClientID, mClientSecret, mOrganizationID, mRedirectUri);
        authClient.setVersion(getVersion());
        mToken = authClient.getToken(refreshToken);
    }

    protected void invalidateToken() {
        mToken.invalidate();
    }

    protected APIException buildError(JSONObject jo) {
        int status = (Integer) jo.get("status");
        String error = (String) jo.get("error");
        String message = (String) jo.get("message");
        return new APIException(status, error, message);
    }

    protected boolean manageBadTokenErrorIfPossible(APIException error) {

        if (error.toString().contains("Bad token")) {

            try {
                // Invalidate the token and try to refresh.
                invalidateToken();
                if (hasReusableToken()) {
                    refreshToken(mToken.getRefreshToken());
                    return true;
                }
                return false;
            } catch (IOException exc) {
                return false;
            }
        } else {
            return false;
        }
    }
    
    protected String getApiUri() {
        switch (getVersion()) {
            case SANDBOX:
                return "https://sandbox.api.bnpparibasfortis.com/";    
            default:
                throw new IllegalStateException("Openbank version not supported");
        }
    }

    /**
     * Retreive the list of accounts for the PSU.
     *
     * @throws java.io.IOException
     */
    public List<Account> getAccounts() throws IOException {

        if (hasReusableToken()) {
            // Firstly, ask the getToken method.
            refreshToken(mToken.getRefreshToken());
        }

        // Build url.
        String url = getApiUri() + "v1/accounts";

        // Build bearer token.
        String bearerToken = "Bearer " + mToken.getToken();

        Request request = new Request.Builder()
                .addHeader("Authorization", bearerToken)
                .addHeader("Signature", "alpha")
                .addHeader("X-Request-Id", "alpha")
                .addHeader("X-Openbank-Organization", mOrganizationID)
                .addHeader("X-Openbank-Stet-Version", mVersion.getCompleteName())
                .addHeader("Content-Type", "application/json")
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);

        // Retreive the accounts list.
        JSONObject jo = new JSONObject(result);

        // Find if there is an error.
        boolean hasError = jo.has("error");
        if (hasError) {
            APIException error = buildError(jo);
            boolean successfullyManaged = manageBadTokenErrorIfPossible(error);
            if (successfullyManaged) {
                return getAccounts();
            } else {
                throw error;
            }
        }

        JSONArray arr = jo.getJSONArray("accounts");

        // Convert to entities.
        ArrayList<Account> accounts = new ArrayList<>();
        arr.forEach((k) -> {
            Account account = new Account((JSONObject) k);
            accounts.add(account);
        });
        return accounts;

    }

    /**
     * Retreive the balance for the specified account.
     * @param accountResourceId Account resourceId
     * @throws java.io.IOException
     */
    public List<Balance> getBalances(String accountResourceId) throws IOException {
        
        if (hasReusableToken()) {
            // Firstly, ask the getToken method.
            refreshToken(mToken.getRefreshToken());
        }

        // Build url.
        String url = getApiUri() + "v1/accounts/" + accountResourceId + "/balances";

        // Build bearer token.
        String bearerToken = "Bearer " + mToken.getToken();

        Request request = new Request.Builder()
                .addHeader("Authorization", bearerToken)
                .addHeader("Signature", "alpha")
                .addHeader("X-Request-Id", "alpha")
                .addHeader("X-Openbank-Organization", mOrganizationID)
                .addHeader("X-Openbank-Stet-Version", mVersion.getCompleteName())
                .addHeader("Content-Type", "application/json")
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);

        // Retreive the accounts list.
        JSONObject jo = new JSONObject(result);

        // Find if there is an error.
        boolean hasError = jo.has("error");
        if (hasError) {
            APIException error = buildError(jo);
            boolean successfullyManaged = manageBadTokenErrorIfPossible(error);
            if (successfullyManaged) {
                return getBalances(accountResourceId);
            } else {
                throw error;
            }
        }

        JSONArray arr = jo.getJSONArray("balances");
        
        // Convert to balances.
        ArrayList<Balance> balances = new ArrayList<>();
        arr.forEach((k) -> {
            Balance balance = new Balance((JSONObject) k);
            balances.add(balance);
        });
        return balances;
    }
    
    /**
     * Retreive the transactions for the specified account.
     * @param accountResourceId Account resourceId
     * @throws java.io.IOException
     */
    public List<Transaction> getTransactions(String accountResourceId) throws IOException {
        
        if (hasReusableToken()) {
            // Firstly, ask the getToken method.
            refreshToken(mToken.getRefreshToken());
        }

        // Build url.
        String url = getApiUri() + "v1/accounts/" + accountResourceId + "/balances";

        // Build bearer token.
        String bearerToken = "Bearer " + mToken.getToken();

        Request request = new Request.Builder()
                .addHeader("Authorization", bearerToken)
                .addHeader("Signature", "alpha")
                .addHeader("X-Request-Id", "alpha")
                .addHeader("X-Openbank-Organization", mOrganizationID)
                .addHeader("X-Openbank-Stet-Version", mVersion.getCompleteName())
                .addHeader("Content-Type", "application/json")
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);

        // Retreive the accounts list.
        JSONObject jo = new JSONObject(result);

        // Find if there is an error.
        boolean hasError = jo.has("error");
        if (hasError) {
            APIException error = buildError(jo);
            boolean successfullyManaged = manageBadTokenErrorIfPossible(error);
            if (successfullyManaged) {
                return getTransactions(accountResourceId);
            } else {
                throw error;
            }
        }

        JSONArray arr = jo.getJSONArray("transactions");
        
        // Convert to transactions.
        ArrayList<Transaction> transactions = new ArrayList<>();
        arr.forEach((k) -> {
            Transaction transaction = new Transaction((JSONObject) k);
            transactions.add(transaction);
        });
        return transactions;
    }
    
}
