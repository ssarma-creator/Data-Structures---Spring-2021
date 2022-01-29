/**
 * Assignment 6 
 * Student Name: Sudarshana Sarma
 * This program finds anagram of given word and its hashcode
*/
import java.io.*;
import java.util.*;

public class Anagrams {
	/**
	 * primes: primes is an integer array of first 26 prime numbers letterTable:
	 * letterTable: stores each letter as key and corresponding prime number
	 * from the array of primes as value 
	 * anagramTable:anagramTable stores hashcode
	 * as key and words with same hashcode as list
	 */

	final Integer[] primes;
	Map<Character, Integer> letterTable;
	Map<Long, ArrayList<String>> anagramTable;

	/**
	 * Constructor to initialize the array and map
	 */
	public Anagrams() {
		anagramTable = new HashMap<Long, ArrayList<String>>();
		letterTable = new HashMap<Character, Integer>();
		primes = new Integer[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83,
				89, 97, 101 };
		buildLetterTable();
	}

	/**
	 * This method builds the hash table LetterTable
	 */
	private void buildLetterTable() {
		Character firstChar = 'a';
		for (int i = 0; i < primes.length; i++) {
			letterTable.put(firstChar, primes[i]);
			firstChar++;
		}
	}

	/**
	 * This method adds the word to the anagram table 
	 * In order to compute the hashcode of the string s, it calls myHashCode() method
	 * 
	 * @param s word to be added
	 */
	private void addWord(String s) {
		long hashCode = this.myHashCode(s);
		ArrayList<String> listOfWords = new ArrayList<String>();
		if (anagramTable.get(hashCode) == null) {
            listOfWords.add(s);
			anagramTable.put(hashCode, listOfWords);
		} else {
            listOfWords = anagramTable.get(hashCode);
			listOfWords.add(s);
			anagramTable.put(hashCode, listOfWords);
		}
	}

	/**
	 * This method given the string s, computes the hash code
	 * @param s The string for which the Hash code has to be computed
	 * @return the hash code of type long 
	 */
	private long myHashCode(String s) {
		long res = (long)1;

		for (char aChar : s.toCharArray()) {
			res *= (long)letterTable.get(aChar);
		}
		return res;
	}

	/**
	 * This method receives the name of a text file file containing words and builds
	 * hash table (anagramTable) It uses the addWord method
	 * @param s accepts the String that is to be added in anagramTable
	 * @throws IOException
	 */

	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null) {
			this.addWord(strLine);
		}
		br.close();
	}

	/**
	 * This method return the entries in the anagramTable that have the largest
	 * number of anagrams
	 * @return ArrayList of map having the largest number of Anagrams
	 */
	private ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries() {
		if (!anagramTable.isEmpty()) {
			long maxSize = 0;
			ArrayList<Map.Entry<Long, ArrayList<String>>> listOfWords = new ArrayList<>();
            for (Map.Entry<Long, ArrayList<String>> elem : anagramTable.entrySet()) {
				long presentSize = elem.getValue().size();
				if (presentSize > maxSize) {
					listOfWords.clear();
					listOfWords.add(elem);
					maxSize = presentSize;
				}
				if (presentSize == maxSize) {
					listOfWords.add(elem);
				}
			}
			return listOfWords;
		}
		return null;
	}

//Testing		
	public static void main(String args[]) {
		Anagrams a = new Anagrams();
		final long startTime = System.nanoTime();
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime / 1000000000);
		System.out.println("Time: " + seconds);

		
		System.out.println("Key of max anagrams: " + maxEntries.get(0).getKey());
		System.out.println("List of max anagrams: " + maxEntries.get(0).getValue());
		System.out.println("Length of the list of max anagrams: " + maxEntries.get(0).getValue().size());
	}

}
