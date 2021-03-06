package webservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class Webservices extends Thread {
	
	private final String key ="0011001101000111011100001111100100101000011000010110100001001"; 
	private RC4Cipher cipher;
	private final String WEBSERVICE = "http://covertstudios.ca/urbanWar/webservices/";
	private final String GET_HIGH_SCORE = "get-high-score";
	private final String GET_USER_HIGH_SCORE = "get-user-high-score";
	public Webservices(){
		super("Webservices");
		try {
			cipher= new RC4Cipher(key.getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private boolean init(){
		 try {
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		 
		return true;
	}
	public Object getTop(int levelIndex){
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(WEBSERVICE);
		try{
			List<NameValuePair> postData = new ArrayList<NameValuePair>(1);
			JSONObject jsonData = new JSONObject();
			jsonData.put("action", GET_HIGH_SCORE);
			jsonData.put("level", Integer.toString(levelIndex));
			postData.add(new BasicNameValuePair("postData", new String(cipher.encrypt(jsonData.toString().getBytes()))));
			//postData.add(new BasicNameValuePair("action", GET_HIGH_SCORE));
			//postData.add(new BasicNameValuePair("level", Integer.toString(levelIndex)));
			post.setEntity(new UrlEncodedFormEntity(postData));
			
			HttpResponse response = client.execute(post);
			
			StringBuilder responseString = inputStreamToString(response.getEntity().getContent());
			
			System.out.println(new String(cipher.decrypt(responseString.toString().getBytes())));
		}catch(ClientProtocolException e){
			
		}catch(IOException e){
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Object();
	}
	// Fast Implementation
	private StringBuilder inputStreamToString(InputStream is) {
	    String line = "";
	    StringBuilder total = new StringBuilder();
	    
	    // Wrap a BufferedReader around the InputStream
	    BufferedReader rd = new BufferedReader(new InputStreamReader(is));

	    // Read response until the end
	    try {
			while ((line = rd.readLine()) != null) { 
			    total.append(line); 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    // Return full string
	    return total;
	}

}
