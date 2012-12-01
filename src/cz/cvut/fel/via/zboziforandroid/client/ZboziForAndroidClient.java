package cz.cvut.fel.via.zboziforandroid.client;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.net.Uri;

import com.google.gson.Gson;

import cz.cvut.fel.via.zboziforandroid.client.words.WordResponse;


public class ZboziForAndroidClient {

    DefaultHttpClient httpClient;
    public final static String url = "http://46.255.228.229:8080/zboziczandroid/wordsmostsearchedlist";
    HttpContext localContex = new BasicHttpContext();

    public ZboziForAndroidClient() {
        httpClient = new DefaultHttpClient();
    }

    public WordResponse getWords() {
        WordResponse wordResponse = null;
        
        try {
            
        	Uri.Builder ub = Uri.parse(url).buildUpon();
        	
//        	tohle ted asi neni treba.. zkuste pak otestovat
//        	ub.setPath("/zboziczandroid/wordsmostsearchedlist");
            ub.appendQueryParameter("user", "svobodnik.petr@gmail.com");
            

            String url = ub.build().toString();
            System.out.println("Actual request:\n " + url + "\n");
            HttpGet httpGet = new HttpGet(url);
            
            
            httpGet.addHeader("Authorization", "Basic akE2Y0w6OXBEaWFwN2pyRWZB");
            httpGet.addHeader("Content-Type", "application/vnd.zboziczandroid.words+json");
//            System.out.println(httpGet.toString());

            HttpResponse response = httpClient.execute(httpGet,localContex);
            HttpEntity entity = response.getEntity();
            
            Gson gson = new Gson();
            wordResponse = gson.fromJson(EntityUtils.toString(entity), WordResponse.class);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            System.out.println(e.toString());
        } catch (IOException e) {
        	// TODO Auto-generated catch block
            System.out.println(e.toString());
        }
        
        return wordResponse;

    }
}