package edu.gatech.omscs.dscars.dao;

import java.util.ArrayList;
import java.util.List;

public class TermDAO {	
	public List<String> getTerms() {
		List<String> terms=new ArrayList<String>();
		terms.add("Spring 2015");
		terms.add("Summer 2015");
		terms.add("Fall 2015");
		terms.add("Spring 2016");
		terms.add("Summer 2016");
		terms.add("Fall 2016");		
		return terms;
	}
}
