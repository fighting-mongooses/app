package com.fightingmongooses.fightingmongooses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class UpdateDBActivity extends Activity {
	
	String appHost = "http://10.0.2.2:8000"; // This will always resolve to the computer running the android emulator
	
	private void setText(int id, String text)
	{
		TextView t = (TextView)findViewById(id);
		t.setText(text);
	}
	
	String json = null;
	JSONArray rootArray = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_db);
		
		InputStream is = null;
		try {
			URL url = new URL(appHost + "/json_cons");
			is = url.openStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			
			String line = null;
            while ((line = reader.readLine()) != null) {
            	sb.append(line + "\n");
            }
            is.close();
            
            json = sb.toString();
            //setText(R.id.test, sb.toString());
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			rootArray = new JSONArray(json);
			
			Database db = new Database(this);
			db.open();
			
			String test = "";
			for(int i = 0; i < rootArray.length(); i++)
			{				
					JSONObject con = rootArray.getJSONObject(i);
					con = con.getJSONObject("fields");
					
					test += "Name: " + con.getString("name") + '\n'; 
					test += "Description: " + con.getString("description") + '\n';
					test += "Start Date: " + con.getString("start_date") + '\n';
					test += "End Date: " + con.getString("end_date") + "\n\n";
					
					db.createConferenceEntry(con.getString("name"), con.getString("description"),
							                 con.getString("start_date"), con.getString("end_date"));
				
			}
			
			//setText(R.id.test, test);
//
			//db.createConferenceEntry("test name", "test conf desc", "start", "end");
			//db.returnConference();
			//setText(R.id.test, db.returnConference()[0].toString());
			
			//db.createConferenceEntry("AAA", "BBB", "CCC", "DDD");
			
			test = "";
			Conference cons[] = db.returnConference();
			for(int i = 0; i < cons.length; i++){
				test += cons[i] + "\n";
				Log.i("ASD", cons[i].toString());
			}
			setText(R.id.test, test);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_db, menu);
		return true;
	}

}
