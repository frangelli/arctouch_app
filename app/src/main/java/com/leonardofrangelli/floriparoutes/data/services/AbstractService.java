package com.leonardofrangelli.floriparoutes.data.services;

import com.leonardofrangelli.floriparoutes.data.common.GenericHttpClient;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by frangelli on 9/8/14.
 */
public abstract class AbstractService {

    private GenericHttpClient client;
    private Map<String, String> headers;

    protected void initializeClient() {
        if(client == null) {
           this.client = GenericHttpClient.getInstance();
        }
    }

    protected void initializeHeaders() {
        if(headers == null) {
            this.headers = new HashMap<String, String>();
            this.headers.put("Content-Type", "application/json");
            this.headers.put("Authorization", "Basic V0tENE43WU1BMXVpTThWOkR0ZFR0ek1MUWxBMGhrMkMxWWk1cEx5VklsQVE2OA==");
            this.headers.put("X-AppGlu-Environment", "staging");
        }
    }

    protected String doPost(String url, String requestBody) {
        return client.sendRequestByPost(url,this.headers,requestBody);
    }

    protected String extractRowsFromJSON(String responseJSON) {
        return responseJSON.substring(responseJSON.indexOf("["),(responseJSON.lastIndexOf("]") + 1));
    }


}
