package util;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.persistence.TypedQuery;

import dbConnection.DatabaseConnection;
import model.Cache;

public class CacheCRUD {
	
	private DatabaseConnection databaseConnection = new DatabaseConnection();

	private int capacity;

	public CacheCRUD() {
		super();
		Scanner sc = new Scanner(System.in);
		System.out.println("Select your capacity for your Cache: ");
		this.capacity = sc.nextInt();
	}


	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public void createCache() throws Exception {
	
		
		List<Cache> unsortedList = readAll();

		if (unsortedList==null || unsortedList.size() < capacity) {
			
			databaseConnection.startTransaction();
			
			Cache cache = new Cache();
			cache.setInformation("Enough Capacity");
		
			cache.setDate(LocalTime.now().getSecond()+""); //de mentionat 
			
			System.out.println( "Information Added! Cache UNDER capacity");
		
			databaseConnection.saveCache(cache);
			databaseConnection.commitTransaction();
			
		} else {
			databaseConnection.startTransaction();
			
			Collections.sort(unsortedList, new CacheDateComparator());
			// sau bag info de la tastatura
		
			unsortedList.get(0).setDate(LocalTime.now().getSecond()+"");
			unsortedList.get(0).setInformation("Not Enough Capacity");
			System.out.println( "Information Added! Cache OVER capacity");
			
			
			databaseConnection.saveCache(unsortedList.get(0));
			databaseConnection.commitTransaction();
			
		}
	

	}
	
/*
	public List<Cache> getAll() {
		TypedQuery<Cache> query = databaseConnection.getEntityManager().createQuery("SELECT c FROM Cache c",
				Cache.class);
		System.out.println(query.getResultList().size() + " SIZE");
		return query.getResultList();
	}
*/
	
	public Cache searchCache(Cache a) {
		try {
//			databaseConnection.setUp();
			@SuppressWarnings({ "static-access", "unchecked" })
			List<Cache> results = databaseConnection.entityManager
					.createNativeQuery("Select * from cache.cache", Cache.class).getResultList();
//			databaseConnection.closeEntityManager();
			for (Cache cache : results) {
				if (cache.getIdCache() == a.getIdCache())
					return cache;
			}
			Cache cache = new Cache();
			cache.setIdCache(0);
			return cache;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Cache cache = new Cache();
		cache.setIdCache(0);
		return cache;
	}

	public void readCache(int idCache) {
		try {
			
			Cache a = new Cache();
			a.setIdCache(idCache);
			a = searchCache(a);
			System.out.println(
					"Cache: " + a.getIdCache() + " have information: " + a.getInformation() + ", at: " + a.getDate());
//				databaseConnection.closeEntityManager();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Cache> readAll() throws Exception {
	databaseConnection.setUp();
		@SuppressWarnings("unchecked")
		List<Cache> results = databaseConnection.getEntityManager()
				.createNativeQuery("Select * from cache.cache", Cache.class).getResultList();
		if(results==null)
			System.out.println("results = null");
//			databaseConnection.closeEntityManager();
		for (Cache cache : results) {
			readCache(cache.getIdCache());
		}
			return results;
	}

}
