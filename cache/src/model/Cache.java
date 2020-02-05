package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cache database table.
 * 
 */
@Entity
@NamedQuery(name="Cache.findAll", query="SELECT c FROM Cache c")
public class Cache implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idCache;

	private String date;

	private String information;

	public Cache() {
	}

	public int getIdCache() {
		return this.idCache;
	}

	public void setIdCache(int idCache) {
		this.idCache = idCache;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getInformation() {
		return this.information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	@Override
	public String toString() {
		return "Cache [idCache=" + idCache + ", date=" + date + ", information=" + information + "]";
	}

}