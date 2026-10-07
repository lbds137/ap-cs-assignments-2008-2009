import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/* SearchEngine.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-4.2-GiggleSearchEngine (due 04/13/09)
 * This class implements the core of a search engine as described in Java Methods A & AB, Skylight Publishing.
 */

public class SearchEngine {
	// Holds the name for the "url" (file name)
	private String myURL;
	// Holds the word index
	private Map<String, List<String>> myIndex;
	
	public SearchEngine(String url){
		myURL = url;
		myIndex = new HashMap<String, List<String>>(20000);
	}
	
	public String getURL(){
		return myURL;
	}
	public void add(String line){
		Set<String> words = parseWords(line);
		LinkedList<String> lines = new LinkedList<String>();
		
		// Loop through each word
		for(String word : words){
			// If the word is already in myIndex, simply add the current line to its list of lines
			if(myIndex.get(word) != null){
				myIndex.get(word).add(line);
			}
			// If the word is not already in myIndex, add the current line to lines, and
			// put the key / value pair (word / lines) into myIndex
			else{
				lines.add(line);
				myIndex.put(word, lines);
			}
			
			// Reset lines
			lines = new LinkedList<String>();
		}
		
	}
	public List<String> getHits(String word){
		return myIndex.get(word);
	}
	private Set<String> parseWords(String line){
		// Split the line into words
		String[] strAr = line.split("\\W+");
		
		TreeSet<String> strTreeSet = new TreeSet<String>();
		
		// Add non-empty words to strTreeSet
		for(String word : strAr){
			if(word != ""){
				strTreeSet.add(word.toLowerCase());
			}
		}
		
		return strTreeSet;
	}
}
