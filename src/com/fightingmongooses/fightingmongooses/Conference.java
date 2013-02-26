package com.fightingmongooses.fightingmongooses;

public class Conference {
	public String name;
	public String description;
	public String start;
	public String end;
	
	public Conference(String _name, String _description, String _start, String _end){
		name = _name; description = _description; start = _start; end = _end;
	}
	
	public String toString()
	{
		return "Name: " + name + ", Description: " + description + ", Start: " + start + ", End: " + end;
	}
}