package com.leonardofrangelli.floriparoutes.data.common;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by frangelli on 9/8/14.
 */
public class GenericHttpClient {

    private static GenericHttpClient instance;
    private static HttpClient client;

    public static GenericHttpClient getInstance() {
        if (instance == null) {
           instance = new GenericHttpClient();
        }
        return instance;
    }

    public String sendRequestByGet(String url) {
        HttpGet get = new HttpGet(url);
        client = new DefaultHttpClient();
        return executeRequestAndGetResponseBody(get);
    }

    public String sendRequestByPost(String url) {
        HttpPost post = new HttpPost(url);
        client = new DefaultHttpClient();
        return executeRequestAndGetResponseBody(post);
    }

    public String sendRequestByPost(String url, Map<String,String> headers, String requestContent) {
        HttpPost post = new HttpPost(url);

        setRequestHeaders(post,headers);
        client = new DefaultHttpClient();
        setRequestContentBody(post, requestContent);

        return executeRequestAndGetResponseBody(post);
    }

    private void setRequestHeaders(HttpEntityEnclosingRequest request, Map<String, String> headers) {
        for (String key : headers.keySet()) {
            request.addHeader(key,headers.get(key));
        }
    }


    private void setRequestContentBody(HttpEntityEnclosingRequest request, String requestContent) {
        try {
            StringEntity requestBody;
            requestBody = new StringEntity(requestContent);
            request.setEntity(requestBody);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private String executeRequestAndGetResponseBody(HttpUriRequest request) {
        try {
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            final String responseString = EntityUtils.toString(entity, "UTF-8");

            return responseString;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
