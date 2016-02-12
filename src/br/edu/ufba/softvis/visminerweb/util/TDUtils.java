package br.edu.ufba.softvis.visminerweb.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.edu.ufba.softvis.visminer.model.business.Tree;

public class TDUtils {
	
	public static List<Tree> sortTags(List<Tree> tags){
		Collections.sort(tags, new Comparator<Tree>(){
		     public int compare(Tree t1, Tree t2){
		         if(t1.getLastUpdate() == t2.getLastUpdate())
		             return 0;
		         return t1.getLastUpdate().compareTo(t2.getLastUpdate()) < 0 ? -1 : 1;
		     }
		});
		return tags;
	}

}
