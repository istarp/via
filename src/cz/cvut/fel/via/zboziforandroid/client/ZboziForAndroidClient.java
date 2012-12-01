package cz.cvut.fel.via.zboziforandroid.client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.net.Uri;

import com.google.gson.Gson;

import cz.cvut.fel.via.zboziforandroid.client.words.SaveWordResponse;
import cz.cvut.fel.via.zboziforandroid.client.words.WordResponse;

public class ZboziForAndroidClient {

	DefaultHttpClient httpClient;
	public final static String url = Utils.WORDS_API_URL;
	HttpContext localContex = new BasicHttpContext();

	public ZboziForAndroidClient() {
		httpClient = new DefaultHttpClient();
	}

	public WordResponse getWords(String email) {
		WordResponse wordResponse = null;

		try {

			Uri.Builder ub = Uri.parse(url).buildUpon();

			ub.path("/zboziczandroid/wordsmostsearchedlist");
			ub.appendQueryParameter("user", email);

			String url = ub.build().toString();
			System.out.println("Actual request:\n " + url + "\n");
			HttpGet httpGet = new HttpGet(url);

			httpGet.addHeader("Authorization", "Basic akE2Y0w6OXBEaWFwN2pyRWZB");
			httpGet.addHeader("Content-Type", "application/vnd.zboziczandroid.words+json");

			HttpResponse response = httpClient.execute(httpGet, localContex);
			if (response != null) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					Gson gson = new Gson();
					wordResponse = gson.fromJson(EntityUtils.toString(entity), WordResponse.class);
				}
			}
		} catch (ClientProtocolException e) {
			System.out.println(e.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return wordResponse;
	}

	public SaveWordResponse saveWord(String email, String word) {

		SaveWordResponse wordResponse = null;

		try {

			Uri.Builder ub = Uri.parse(url).buildUpon();

			ub.path("/zboziczandroid/word/");

			String url = ub.build().toString();
			System.out.println("Actual request:\n " + url + "\n");
			HttpPost httpPost = new HttpPost(url);

			httpPost.addHeader("Authorization", "Basic akE2Y0w6OXBEaWFwN2pyRWZB");
			httpPost.addHeader("Content-Type", "application/vnd.zboziczandroid.word+json");

			httpPost.setEntity(new StringEntity("{\"email\":\"" + email + "\",\"word\":\"" + word + "\"}"));

			HttpResponse response = httpClient.execute(httpPost, localContex);
			HttpEntity entity = response.getEntity();

			Gson gson = new Gson();
			wordResponse = gson.fromJson(EntityUtils.toString(entity), SaveWordResponse.class);

		} catch (ClientProtocolException e) {
			System.out.println(e.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return wordResponse;
	}

	public void deleteWords(String email) {

		try {

			Uri.Builder ub = Uri.parse(url).buildUpon();

			ub.path("/zboziczandroid/deletehistory");
			ub.appendQueryParameter("user", email);

			String url = ub.build().toString();
			System.out.println("Actual request:\n " + url + "\n");
			HttpDelete httpDelete = new HttpDelete(url);

			httpDelete.addHeader("Authorization", "Basic akE2Y0w6OXBEaWFwN2pyRWZB");
			httpDelete.addHeader("Content-Type", "application/vnd.zboziczandroid.words+json");

			HttpResponse response = httpClient.execute(httpDelete, localContex);
			HttpEntity entity = response.getEntity();

		} catch (ClientProtocolException e) {
			System.out.println(e.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}