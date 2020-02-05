package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dbConnection.DatabaseConnection;
import model.Cache;
import util.CacheCRUD;

public class Main {

	public static void main(String[] args) {

		CacheCRUD cache = new CacheCRUD();
		DatabaseConnection databaseConnection = new DatabaseConnection();

		try {
//			setUp
//			startT		
			int localCapacity = cache.getCapacity();
			
			while (localCapacity > 0) {
				cache.createCache();
				Thread.sleep(4000); //sec
				localCapacity--;
			}
			//test for capacity
			cache.createCache();
			Thread.sleep(4000);
			
			cache.createCache();
			Thread.sleep(4000);
			
			cache.createCache();
			Thread.sleep(4000);
			
//			commitT
//			closeEntity
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		List<Cache> obj = new ArrayList<>();
		
		try {
			cache.readAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getCause();
		}

	}

}
