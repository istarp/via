package cz.cvut.fel.via.zboziforandroid.client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import cz.cvut.fel.via.zboziforandroid.client.items.ItemsResponse;
import cz.cvut.fel.via.zboziforandroid.client.product.ProductResponse;
import cz.cvut.fel.via.zboziforandroid.client.products.ProductsResponse;

import android.net.Uri;


public class ViaClientHttp {

	DefaultHttpClient httpClient;
    public final static String url = "http://api.zbozi.sbeta.cz";
    HttpContext localContex = new BasicHttpContext();

    public ViaClientHttp() {
        httpClient = new DefaultHttpClient();
        httpClient.getCredentialsProvider().setCredentials(
                new AuthScope("api.zbozi.sbeta.cz", 80),
                new UsernamePasswordCredentials("cvut", "pqvDd-wek2D"));
    }
    
    
    public ProductResponse getProduct(int id) {
        ProductResponse p = null;
        try {
        	 Uri.Builder ub = Uri.parse(url).buildUpon();
             ub.path("1/product");
             
             //add parameters
            ub.appendQueryParameter("productId", Integer.toString(id));
            
            //create url
            String url = ub.build().toString();
            System.out.println("Actual request:\n " + url + "\n");
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet,localContex);
            HttpEntity entity = response.getEntity();
            Gson gson = new Gson();
            p = gson.fromJson(EntityUtils.toString(entity), ProductResponse.class);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            System.out.println(e.toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e.toString());
        }

        return p;
    }
    
    public ProductsResponse getProducts(String query, int offset, int limit, String criterion, String direction,
            double minPrice, double maxPrice) {
        ProductsResponse productsResponse = null;
        try {
        	
            Uri.Builder ub = Uri.parse(url).buildUpon();
            ub.path("1/products");

            
            //add parameters
            ub.appendQueryParameter("query", query);
            ub.appendQueryParameter("offset", Integer.toString(offset));
            if (limit != -1) {
                ub.appendQueryParameter("limit", Integer.toString(limit));
            }
            if (!criterion.equals("")) {
                ub.appendQueryParameter("criterion", criterion);
            }
            ub.appendQueryParameter("direction", direction);
            ub.appendQueryParameter("minPrice", Double.toString(minPrice));
            ub.appendQueryParameter("maxPrice", Double.toString(maxPrice));
            
            //create url
            String url = ub.build().toString();
            System.out.println("Actual request:\n " + url + "\n");
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet,localContex);
            HttpEntity entity = response.getEntity();
            Gson gson = new Gson();
            productsResponse = gson.fromJson(EntityUtils.toString(entity), ProductsResponse.class);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            System.out.println(e.toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e.toString());
        }
        return productsResponse;
    }
    
    /**
     * @param   offset Index prvni vracene polozky, indexovano od 0.
     * @param   limit Maximální poèet vrácených položek.   
     * @param   regionId ID regionu, vybira se z Enum ERegions.
     * @param   aggregateRegions Agreguj regiony.
     * @param   paymentType Podporovany zpusob platby. "" znamena bez omezeni.
     * @param   aggregatePaymentTypes Agreguj typy plateb.
     * @param   maxStockAvailabality Maximální skladová dostupnost položek v hodinách. -1 znamená bez omezení.
     * @param   aggregateAvailabilities Agreguj dostupnosti.
     * @param   atStoreOnly Netusim, jak to sakra funguje...
     */
    public ItemsResponse getItems(int productId, int offset, int limit, String regionId, boolean aggregateRegions,
            String paymentType, boolean aggregatePaymentTypes,
            int maxStockAvailabality, boolean aggregateAvailabilities, boolean atStoreOnly) {
        ItemsResponse itemResponse = null;
        try {
        	Uri.Builder ub = Uri.parse(url).buildUpon();
            ub.path("1/product/items");

            //add parameters
            ub.appendQueryParameter("productId", Integer.toString(productId));
            ub.appendQueryParameter("offset", Integer.toString(offset));
            if (limit != -1) {
                ub.appendQueryParameter("limit", Integer.toString(limit));
            }

            if (aggregateRegions) {
                ub.appendQueryParameter("aggregateRegions", "true");
                ub.appendQueryParameter("regionId", regionId);
            }
            if (aggregatePaymentTypes) {
                ub.appendQueryParameter("aggregatePaymentTypes", "true");
                ub.appendQueryParameter("paymentType", paymentType);
            }

            if (aggregateAvailabilities) {
                ub.appendQueryParameter("aggregateAvailabilities", "true");
                ub.appendQueryParameter("maxStockAvailability", Integer.toString(maxStockAvailabality));
            }
            
            if(atStoreOnly)
            	ub.appendQueryParameter("atStoreOnly", Boolean.valueOf(atStoreOnly).toString());
            
            
            //create url
            String url = ub.build().toString();
            System.out.println("Actual request:\n " + url + "\n");
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet,localContex);
            HttpEntity entity = response.getEntity();
//            System.out.println(EntityUtils.toString(entity));
            Gson gson = new Gson();
            itemResponse = gson.fromJson(EntityUtils.toString(entity), ItemsResponse.class);


        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            System.out.println(e.toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e.toString());
        }

        return itemResponse;

    }

}
