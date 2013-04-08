package com.fightingmongooses.fightingmongooses;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.TextView;

public class UpdateDBActivity extends Activity {

	String appHost = "http://10.0.2.2:8000"; // This will always resolve to the
												// computer running the android
												// emulator
	
	private class PissOffNetworkingInMainThreadException extends AsyncTask<Context, Void, String> {
		private JSONArray getJSON(String For) throws IOException, JSONException{
			URL url = new URL(appHost + "/json_" + For);
			InputStream is = url.openStream();
	
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
	
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			
			return new JSONArray(sb.toString());
		}
		
		@Override
		protected String doInBackground(Context...context){
			Database db = null;
			
			String text = null;
			
			try {						
				JSONArray json_cons = getJSON("cons");
				JSONArray json_events = getJSON("events");
				JSONArray json_maps = getJSON("maps");
				
				// oh my fucking god javaaa
				for(Context c : context)
					db = new Database(c);
				
				db.open();
				db.clear();

				for (int i = 0; i < json_cons.length(); i++) {
					JSONObject con = json_cons.getJSONObject(i);
					int id = con.getInt("pk");
					con = con.getJSONObject("fields");

					db.createConferenceEntry(id, con.getString("name"), con.getString("description"),
							con.getString("start_date"), con.getString("end_date"), 
							con.getString("website"), con.getString("guests"), con.getString("twitter"), con.getString("gmaps"));
				}
				
				for (int i = 0; i < json_events.length(); i++) {
					JSONObject con = json_events.getJSONObject(i);
					int id = con.getInt("pk");
					con = con.getJSONObject("fields");

					db.createEventEntry(id, con.getString("name"), con.getString("time"), con.getString("end_time"),
							con.getString("location"), con.getString("description"), con.getInt("conference"));
				}
				

				File dir = new File(Environment.getExternalStorageDirectory().getPath() + "/androkon/media");
				
				// Clear out old pictures
				try{
					File[] files = dir.listFiles();
					
					if(files != null){
						for(File f: files){
							if(f.isFile()){
								f.delete();
							}
						}
					}
				}
				catch (Exception e) {}
				
	            dir.mkdirs();
	            
				
				for (int i = 0; i < json_maps.length(); i++) {
					JSONObject con = json_maps.getJSONObject(i);
					int id = con.getInt("pk");
					con = con.getJSONObject("fields");
					
					String fileName = con.getString("picture");
					
					
					db.createMapEntry(id, fileName, con.getInt("con"));
					
					
					URL url = new URL(appHost + "/static/" + fileName);
					URLConnection ucon = url.openConnection();
					ucon.setReadTimeout(5000);
					ucon.setConnectTimeout(30000);
					
					InputStream is = ucon.getInputStream();
		            BufferedInputStream inStream = new BufferedInputStream(is, 1024 * 5);
		            
		            
		            FileOutputStream outStream = new FileOutputStream(
		            		Environment.getExternalStorageDirectory().getPath() + "/androkon/" + fileName);
		            byte[] buff = new byte[5 * 1024];

		            int len;
		            while ((len = inStream.read(buff)) != -1)
		            {
		                outStream.write(buff,0,len);
		            }
		            
		            outStream.flush();
		            outStream.close();
		            inStream.close();

				}

				
				// Display new cons to user
				String test = "Cons:\n";
				Conference cons[] = db.returnConference();
				for (int i = 0; i < cons.length; i++)
					test += cons[i] + "\n";

				test += "Events:\n";
				ConEvent events[] = db.returnEvents();
				for (int i = 0; i < events.length; i++)
					test += events[i] + "\n";

				test += "Maps\n";
				ConMap maps[] = db.returnMaps();
				for (int i = 0; i < maps.length; i++)
					test += maps[i] + "\n";
				
				text = test;
				

			} catch (Exception e) {
				e.printStackTrace();
				text = "Something went wrong (Check connection to django server)";
			}
			
			// Make sure we always close the db
			if(db != null)
				db.close();
			
			return text;
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_db);
		
		TextView t = (TextView) findViewById(R.id.test);
		
		// UGH
		try {
			t.setText(new PissOffNetworkingInMainThreadException().execute(this).get());
		} catch (Exception e) {
			t.setText("everything is broken");
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_db, menu);
		return true;
	}

}
