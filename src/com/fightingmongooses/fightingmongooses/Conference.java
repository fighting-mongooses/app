package com.fightingmongooses.fightingmongooses;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conference {
	private int id;
	private String name;
	private String description;
	private Date start;
	private Date end;
	private String website;
	private String guests;
	private String twitter;
	private String gmaps;
	
	public Conference(int _id, String _name, String _description, 
			Date _start, Date _end, String _website, String _guests, String _twitter, String _gmaps){
		id = _id; name = _name; description = _description; 
		start = _start; end = _end; website = _website; guests = _guests; twitter = _twitter; gmaps = _gmaps;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getStartString() {
		return formatDate(start);
	}

	public String getEndString() {
		return formatDate(end);
	}
	
	public Date getStart() {
		return start;
	}
	
	public Date getEnd() {
		return end;
	}

	public String getWebsite() {
		return website;
	}

	public String getGuests() {
		return guests;
	}

	public String getTwitter() {
		return twitter;
	}
	
	public String getGmaps() {
		return gmaps;
	}
	
	@SuppressLint("SimpleDateFormat")
	private String formatDate(Date d)
	{
		if(d == null)
			return "";
		SimpleDateFormat f = new SimpleDateFormat("dd/MMM/yyyy");
		return f.format(d);
	}
	
	public String toString()
	{
		return "ID: " + id + ", Name: " + name + ", Description: " + description + 
				", Start: " + formatDate(start) + ", End: " + formatDate(end) + 
				", Website: " + website + ", Guests: " + guests + ", Twitter: " + twitter + ", Gmaps: " + gmaps;
	}
}