
package view;

import java.util.Comparator;

import app.COVID;

public class Sort implements Comparator<COVID>{
	public int compare(COVID a, COVID b)
	{
		
		return a.country.compareTo(b.country);
	}
}
