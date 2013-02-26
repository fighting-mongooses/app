package com.fightingmongooses.fightingmongooses;

import java.util.Date;

public class Conference {
	public int id;
	public String name;
	public String description;
	public Date start;
	public Date end;
	
	public Conference(int _id, String _name, String _description, Date _start, Date _end){
		id = _id; name = _name; description = _description; start = _start; end = _end;
	}
	
	public String toString()
	{
		return "ID: " + id + ", Name: " + name + ", Description: " + description + ", Start: " + start + ", End: " + end;
	}
}