package com.farmerswife.cirkus.codetest.entities;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import org.json.JSONObject;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Blog {
	
	@Id
	private long id;
	
	private String title;
	private Map<Long, Entry> entries = new HashMap<>();

	public Blog() {}
	
	public Blog(long id, String title) {
		this.id = id;
		this.title = title;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@XmlTransient
	public Map<Long, Entry> getEntries() {
		return entries;
	}

	public void setEntries(Map<Long, Entry> entries) {
		this.entries = entries;
	}
	
//	public JSONObject toJSON() {
//		try {
//			JSONObject jsonobj = new JSONObject();
//			jsonobj.put("id", this.id);
//			jsonobj.put(title, this.title);
//			return jsonobj;
//		}catch(Exception e) {
//			return null;
//		}
//	}
}
