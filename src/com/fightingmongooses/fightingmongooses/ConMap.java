package com.fightingmongooses.fightingmongooses;

import android.os.Environment;

public class ConMap {
	private int id;
	private String path;
	private int con;
	
	
	public ConMap(int id, String path, int con) {
		this.id = id;
		this.path = path;
		this.con = con;
	}
	
	
	public int getId() {
		return id;
	}
	public String getPath() {
		return Environment.getExternalStorageDirectory().getPath() + "/androkon/" + path;
	}
	public int getCon() {
		return con;
	}



	public String toString() {
		return "id: " + id + ", path: " + getPath() + ", conference: " + con;
	}	
	
}
