package com.fightingmongooses.fightingmongooses;

import java.util.Date;

public class Conference {
	public String name;
	public String description;
	public Date start;
	public Date end;
	
	public Conference(String _name, String _description, Date _start, Date _end){
		name = _name; description = _description; start = _start; end = _end;
	}
	
	public String toString()
	{
		return "Name: " + name + ", Description: " + description + ", Start: " + start + ", End: " + end;
	}
}