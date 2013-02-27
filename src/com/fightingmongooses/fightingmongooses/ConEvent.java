package com.fightingmongooses.fightingmongooses;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConEvent {
	public int id;
	public String name;
	public Date date;
	public String place;
	public String duration;
	public String description;


	public ConEvent(int _id, String _name, Date _date, String _place, String _duration, String _description){
		id = _id; name = _name; description = _description; date = _date; place = _place; duration = _duration;
	}
	
	@SuppressLint("SimpleDateFormat")
	public String toString()
	{
		SimpleDateFormat f = new SimpleDateFormat("MMM/dd/yy HH:mm");
		return "ID: " + id + ", Name: " + name + ", Date: " + f.format(date) + ", Place: " + place + 
			   ", Duration: " + duration + ", Description: " + description; 
	}
}
