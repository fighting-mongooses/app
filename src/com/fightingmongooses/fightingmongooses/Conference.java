package com.fightingmongooses.fightingmongooses;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conference {
	public int id;
	public String name;
	public String description;
	public Date start;
	public Date end;
	public String website;
	public String guests;
	public String twitter;
	
	public Conference(int _id, String _name, String _description, 
			Date _start, Date _end, String _website, String _guests, String _twitter){
		id = _id; name = _name; description = _description; 
		start = _start; end = _end; website = _website; guests = _guests; twitter = _twitter;
	}
	
	@SuppressLint("SimpleDateFormat")
	public String toString()
	{
		SimpleDateFormat f = new SimpleDateFormat("MMM/dd/yy HH:mm");
		return "ID: " + id + ", Name: " + name + ", Description: " + description + 
				", Start: " + f.format(start) + ", End: " + f.format(end) + 
				", Website: " + website + ", Guests: " + guests + ", Twitter: " + twitter;
	}
}