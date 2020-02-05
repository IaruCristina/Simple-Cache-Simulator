package util;

import java.util.Comparator;

import model.Cache;

public class CacheDateComparator implements Comparator<Cache>{

	@Override
	public int compare(Cache c1, Cache c2) {
		return c1.getDate().compareTo(c2.getDate());
	}

}
