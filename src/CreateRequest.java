import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import static org.junit.Assert.fail;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jerem
 */
public class CreateRequest {
    private static final String URL = "http://127.0.0.1:4567";
    
    private CreateRequest(){
        
    }

    protected static TestResponse get(String path) {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet request = new HttpGet(URL + path);
        StringBuilder result = new StringBuilder();
        return sendRequestAndReceiveResult(httpClient, request, result);

    }

    protected static TestResponse post(String path, List<NameValuePair> urlParameters) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost request = new HttpPost(URL + path);
        try {
            request.setEntity(new UrlEncodedFormEntity(urlParameters));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CreateRequest.class.getName()).log(Level.SEVERE, null, ex);
             return new TestResponse(500, ex.getMessage());
        }
        StringBuilder result = new StringBuilder();
        return sendRequestAndReceiveResult(httpClient, request, result);
    }
    
     protected static TestResponse put(String path, List<NameValuePair> urlParameters) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPut request = new HttpPut(URL + path);
        try {
            request.setEntity(new UrlEncodedFormEntity(urlParameters));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CreateRequest.class.getName()).log(Level.SEVERE, null, ex);
             return new TestResponse(500, ex.getMessage());
        }
        StringBuilder result = new StringBuilder();
        return sendRequestAndReceiveResult(httpClient, request, result);
    }
     
     protected static TestResponse delete(String path) {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpDelete request = new HttpDelete(URL + path);
        StringBuilder result = new StringBuilder();
        return sendRequestAndReceiveResult(httpClient, request, result);

    }

     private static TestResponse sendRequestAndReceiveResult(DefaultHttpClient httpClient, HttpRequestBase request, StringBuilder result) {
        HttpResponse response;
        try {
            response = httpClient.execute(request);
            if (readResult(response, result) == null) {
                return new TestResponse(500, "Error sendRequestAndReceiveResult");
            }
            return new TestResponse(response.getStatusLine().getStatusCode(), result.toString());

        } catch (IOException ex) {
            Logger.getLogger(CreateRequest.class.getName()).log(Level.SEVERE, null, ex);
            return new TestResponse(500, ex.getMessage());
        }
    }

    private static StringBuilder readResult(HttpResponse response, StringBuilder result) {
        try (final InputStreamReader stream = new InputStreamReader(response.getEntity().getContent());final BufferedReader rd = new BufferedReader(stream)) {
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return result;
        } catch (IOException ex) {
            Logger.getLogger(CreateRequest.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
