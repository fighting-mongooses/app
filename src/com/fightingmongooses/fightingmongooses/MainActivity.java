package com.fightingmongooses.fightingmongooses;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        createButtons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void createButtons(){
    	Database db = new Database(this);
        db.open();
        
        Conference cons[] = db.returnConference();
        
        db.close();
        
		for(int i = 0; i < cons.length; i++) 
			this.addButton(cons[i].name);	
    }
    
    public void addButton(String text)
    {
	    Button b = new Button(this);
		b.setText(text);
		LinearLayout ll = (LinearLayout)findViewById(R.id.main_layout);
		
		@SuppressWarnings("deprecation") // FILL_PARENT is deprecated since API level 8, but we're targeting 7
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		ll.addView(b, lp);
    }
    
    public void testClicked(View view)
    {
    	Intent intent = new Intent(this, Test_act.class);
    	startActivity(intent);

    }
    
    public void upDateDBClicked(View view)
    {
    	Intent intent = new Intent(this, UpdateDBActivity.class);
    	startActivity(intent);
    }    
}
