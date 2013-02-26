package com.fightingmongooses.fightingmongooses;

public class ConEvent {
	public String name;
	public String date;
	public String place;
	public String duration;
	public String description;


	public ConEvent(String _name, String _date, String _place, String _duration, String _description){
		name = _name; description = _description; date = _date; place = _place; duration = _duration;
	}
	
	public String toString()
	{
		return "Name: " + name + ", Date: " + date + ", Place: " + place + 
			   ", Duration: " + duration + ", Description: " + description; 
	}
}
