package Main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class IMDB {
	private String movieName;
	final String myKey = "k_avuw7pm0"; // kalani's key
	//final String myKey = "k_yz15r0pk"; // thomas's key
	public IMDB() {
		// dummy constructor
	}
	public IMDB(String mN) {
		movieName = mN;
	}
	public String getID(String mN) {
		HttpURLConnection connection = null;
		String movieName = mN;
		try
		  {
//TO GET THE IMDB ID
			  URL Newurl = new URL("https://imdb-api.com/en/API/SearchAll/" +myKey + "/" +movieName);
			   connection = (HttpURLConnection)Newurl.openConnection();
			   connection.setRequestMethod("GET");
			   connection.setDoInput(true);
			   
			   InputStream stream1 = connection.getInputStream();
			   BufferedReader reader1 = new BufferedReader(new InputStreamReader(stream1));
			   StringBuilder responce1 = new StringBuilder();
			   String line1=null;
			   while((line1=reader1.readLine())!=null)
			   {
			    responce1.append(line1);
			    responce1.append("\r");
			   }
			   reader1.close();
			   String result1 = responce1.toString();
	//CHOOSES TOP RESULT OF THE SEARCH -- if you want to make more specific search movie or show instead of search all
			   JSONObject jsonObj = new JSONObject(result1);
			   JSONArray JsonArray = jsonObj.getJSONArray("results");
			   JSONObject data = JsonArray.getJSONObject(0);
			   String id1 =data.getString("id");
			   return id1;
		  }
	catch (Exception e) {
			  e.printStackTrace();
			  return "Movie/Show does not have a trailer";
		  }
	}
	public String getLink(String IMDBid) {
		HttpURLConnection connection = null;
		  String id1 = IMDBid; 
		   URL url;
		try {
			url = new URL("https://imdb-api.com/en/API/YouTubeTrailer/"+myKey+ "/" + id1);
			connection = (HttpURLConnection)url.openConnection();
			   connection.setRequestMethod("GET");
			   connection.setDoInput(true);
			   
			   InputStream stream = connection.getInputStream();
			   BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			   StringBuilder responce = new StringBuilder();
			   String line=null;
			   while((line=reader.readLine())!=null)
			   {
			    responce.append(line);
			    responce.append("\r");
			   }
			   reader.close();
			   String result = responce.toString();
			   
			   JSONObject object = new JSONObject(result);
			 
			   String title = object.getString("title");
			   String videoURL = object.getString("videoUrl");
			   

			   if (videoURL.equals(null)) {
				   videoURL = "Movie/Show exists but there is no trailer";
			   }
			   return ("Movie/Show title: "+ title + "\nMovie/Show link: "+ videoURL);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ("Movie/Show exists but there is no trailer");
		} 
	}
}