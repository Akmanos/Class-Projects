/*
 * Copyright 2017 Marc Liberatore.
 * Modified 2018 David Wemhoener
 */

package log;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import com.opencsv.CSVReader;

public class LogParser {	
	/**
	 * Returns a list of SuspectEntries corresponding to the CSV data supplied by the given Reader.
	 * 
	 * The data contains one or more lines of the format:
	 * 
	 * Marc,413-545-3061,1234567890
	 * 
	 * representing a name, phone number, and passport number.
	 * 
	 * @param r an open Reader object
	 * @return a list of SuspectEntries
	 * @throws IOException
	 */
	private static CSVReader reader;
	private static String[] lines;
	
	public static List<SuspectEntry> parseLog(Reader r) throws IOException {
		List<SuspectEntry> entry = new ArrayList<SuspectEntry>();
		
		if(r == null) {
			throw new IOException();
		}
		
		reader = new CSVReader(r);
		
		while((lines = reader.readNext()) != null) {
			if(lines != null) {
					
				entry.add(new SuspectEntry(lines[0], lines[1], lines[2], lines[3]));
				
				if(lines.length >4) {
					for(int i=4; i<lines.length-3; i++) {
						entry.add(new SuspectEntry(lines[i], lines[i+1], lines[i+2], lines[i+3]));
					}
				}
			}	
			
		}		

		return entry;
	}
	/**
	 * Returns a sorted list of SuspectEntries whose passport numbers are common to all 
	 * of the supplied entryLists.
	 * 
	 * The list is sorted lexicographically by passport number, breaking ties by name 
	 * and then by phone number.
	 * 
	 * @param entryLists a list of lists of SuspectEntries
	 * @return a sorted list of SuspectEntries whose passport numbers are common to all 
	 * of the supplied entryLists
	 */
	public static List<SuspectEntry> findCommonEntries(List<List<SuspectEntry>> entryLists) {
		List<SuspectEntry> temp = new ArrayList<SuspectEntry>();
		Set<String> set = new TreeSet<String>();
		Set<String> set2= new TreeSet<String>();
		int m=0;
		
		//add passports to set
		for(int i=1; i<entryLists.size(); i++) {
			for(int j=0; j<entryLists.get(i).size(); j++) {
				set.add(entryLists.get(i).get(j).getPassPort());
			}
					
			while(m<1) {
				for(int b=0; b<entryLists.get(0).size(); b++) {
					set2.add(entryLists.get(0).get(b).getPassPort());
				}
				m++;
			}
			
			set2.retainAll(set);
		}
		
		for(int i=0; i<entryLists.size(); i++) {
			for(int j=0; j<entryLists.get(i).size(); j++) {
				if(set2.contains(entryLists.get(i).get(j).getPassPort())) {
					temp.add(entryLists.get(i).get(j));
				}
			}
		}
		
		return temp;
	}
	
}
