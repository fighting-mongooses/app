package com.fightingmongooses.fightingmongooses;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class UpdateDBActivity extends Activity {

	String appHost = "http://10.0.2.2:8000"; // This will always resolve to the
												// computer running the android
												// emulator

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_db);

		Database db = null;
		TextView t = (TextView) findViewById(R.id.test);
		
		try {			
			URL url = new URL(appHost + "/json_cons");
			InputStream is = url.openStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();

			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			
			JSONArray json_cons = new JSONArray(sb.toString());
			
			
			url = new URL(appHost + "/json_events");
			is = url.openStream();
			reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			sb = new StringBuilder();
			line = null;
			
			while((line = reader.readLine()) != null)
			{
				sb.append(line + "\n");
			}
			is.close();
			
			JSONArray json_events = new JSONArray(sb.toString());
			
			db = new Database(this);
			db.open();
			db.clear();

			for (int i = 0; i < json_cons.length(); i++) {
				JSONObject con = json_cons.getJSONObject(i);
				int id = con.getInt("pk");
				con = con.getJSONObject("fields");

				db.createConferenceEntry(id, con.getString("name"), con.getString("description"),
						con.getString("start_date"), con.getString("end_date"), 
						con.getString("website"), con.getString("guests"), con.getString("twitter"));
			}
			
			for (int i = 0; i < json_events.length(); i++) {
				JSONObject con = json_events.getJSONObject(i);
				int id = con.getInt("pk");
				con = con.getJSONObject("fields");

				db.createEventEntry(id, con.getString("name"), con.getString("time"), "PLACEHOLDER_PLACE",
						"PLACEHOLDER_DURATION", con.getString("description"), con.getInt("conference"));
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

			t.setText(test);
			

		} catch (Exception e) {
			e.printStackTrace();
			t.setText("Something went wrong (Check connection to django server)");
		}
		
		// Make sure we always close the db
		if(db != null)
			db.close();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_db, menu);
		return true;
	}

}
