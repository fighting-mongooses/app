package com.fightingmongooses.fightingmongooses;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConEvent {
	private int id;
	private String name;
	private Date time;
	private Date endTime;
	private String location;
	private String description;
	private int con;


	public ConEvent(int _id, String _name, Date _time, String _location,
			String _description, Date _endTime, int _con){
		id = _id; name = _name; description = _description; time = _time; 
		location = _location; endTime = _endTime; con = _con;
	}
	
	
	
	public int getId() {
		return id;
	}



	public String getName() {
		return name;
	}



	public Date getTime() {
		return time;
	}

	
	public String getTimeString() {
		return formatDate(time);
	}


	public Date getEndTime() {
		return endTime;
	}
	
	public String getEndTimeString() {
		return formatDate(endTime);
	}



	public String getLocation() {
		return location;
	}



	public String getDescription() {
		return description;
	}



	public int getCon() {
		return con;
	}



	@SuppressLint("SimpleDateFormat")
	private String formatDate(Date d)
	{
		if(d == null)
			return "";
		SimpleDateFormat f = new SimpleDateFormat("MMM/dd/yy HH:mm");
		return f.format(d);
	}
	
	public String toString()
	{
		return "ID: " + id + ", Name: " + name + ", Time: " + formatDate(time) + ", End Time: " + formatDate(endTime)
				+ ", Location: " + location + ", Description: " + description + ", Con: " + con; 
	}
}
