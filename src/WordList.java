//For debugging and testing only
import java.io.FileWriter;
//For debugging and testing only
import java.io.PrintWriter;
//For debugging and testing only
import java.io.IOException;

/**
 *Title:  Cryptanalysis:  WordList object<p>
 *Author:  Julian Herrera  1316012<p>
 *School:  Cheyenne East High School<p>
 *Language:  Java<p>
 *Date:  April 1, 2003<p>
 *Purpose:  This object holds an array of words that all start with the same letter.  It also holds the length of the longest word in the array.  This object has a method that determines if a given word appears in the array of words
 */
public class WordList
{
/**Holds the letter that all of the words in the array begin with as a character*/
	private char myCharacter;
/**Holds the word list as an array of strings*/
	private String[] myWordList;
/**Holds the length of the longest word in the array as an integer*/
	private int myLongestWordLength = 0;
	
/**Constructs and initializes a WordList object as the given sorted array of words
 *@param arrayOfWords the array the the WordList object is supposed to represent as a String[]*/
 	public WordList(String[] arrayOfWords)
 	{
 		myWordList = arrayOfWords;
 		
 		myCharacter = myWordList[0].charAt(0);
 		
 		for(int counter = 0; counter < myWordList.length; ++counter)
 		{
 			if(myWordList[counter].length() > myLongestWordLength)
 			{
 				myLongestWordLength = myWordList[counter].length();
 			}
 		}
 		
 	}

/**Determines if the given word appears in the array of words
 *@param word the word being tested
 *@return boolean:<p>true if the word does exist <p>false if the word does not exist*/
 	public boolean wordExists(String word)	
 	{
 		boolean thisExists = false;
 		
 		int first = 0;
 		int last = (myWordList.length - 1);
 		int mid = (last / 2);
 		
 		do{

 			if(myWordList[mid].compareTo(word) == 0)
 			{
 				thisExists = true;
 			}
 			else if(myWordList[mid].compareTo(word) > 0)
 			{
 				last = (mid - 1);
 			}
 			else if(myWordList[mid].compareTo(word) < 0)
 			{
 				first = (mid + 1);
 			}
 			
 			mid = (first + last) / 2;
 		}while(thisExists == false && last >= first);
 		
 		return thisExists;
 	}

/**Gets the length of the longest word in the array of words
 *@return the length of the longest word in this WordList*/
 	public int getLongestWordLength()
 	{
 		return myLongestWordLength;
 	}

/**A string representation of the WordList object with the character that all words start with and the length of the longest word
 *@return the letter of this object and the length of the longest word as a string*/
 	public String toString()
 	{
 		String result = new String("");
 		
 		result += "letter: " + myCharacter;
 		result += "     longest word length: " + myLongestWordLength;
 		
 		return result;
 	}

/**For debugging and testing purposes only*/
	public static void main(String[] args)	
		throws IOException
 	{
 		PrintWriter test = new PrintWriter(new FileWriter("WordListTESTING.txt"));
 		
 		test.println("WordList TESTING");
 		
 		String[] even = new String[6];
 		String[] odd = new String[5];
 		odd[0] = even[0] = "baby";
 		odd[1] = even[1] = "banana";
 		odd[2] = even[2] = "better";
 		odd[3] = even[3] = "bite";
 		odd[4] = even[4] = "boy";
 		even[5] = "but";
 		
 		test.println();
		test.println("even array : evenWL     " + even[0] + "  " + even[1] + "  " + 
					even[2] + "  " + even[3] + "  " + even[4] + "  " + even[5]);

		test.println("odd array : oddWL     " + odd[0] + "  " + odd[1] + "  " + 
					odd[2] + "  " + odd[3] + "  " + odd[4]);

 		WordList evenWL = new WordList(even);
 		WordList oddWL = new WordList(odd);
 		
 		test.println();		
		test.println("evenWL.wordExists('baby') :" + evenWL.wordExists("baby"));
		test.println("evenWL.wordExists('but') :" + evenWL.wordExists("but"));
		test.println("evenWL.wordExists('better') :" + evenWL.wordExists("better"));
		test.println("evenWL.wordExists('baa') :" + evenWL.wordExists("baa"));
		test.println("evenWL.wordExists('by') :" + evenWL.wordExists("by"));
		test.println("evenWL.wordExists('bitter') :" + evenWL.wordExists("bitter"));
 
		test.println("oddWL.wordExists('baby') :" + oddWL.wordExists("baby"));
		test.println("oddWL.wordExists('boy') :" + oddWL.wordExists("boy"));
		test.println("oddWL.wordExists('better') :" + oddWL.wordExists("better"));
		test.println("oddWL.wordExists('baa') :" + oddWL.wordExists("baa"));
		test.println("oddWL.wordExists('by') :" + oddWL.wordExists("by"));
		test.println("oddWL.wordExists('bitter') :" + oddWL.wordExists("bitter"));

 		test.println();		
		test.println("evenWL.getLongestWordLength() :" + evenWL.getLongestWordLength());
		test.println("oddWL.getLongestWordLength() :" + oddWL.getLongestWordLength());
		
 		test.println();		
		test.println("evenWL.toString() : " + evenWL.toString());
		test.println("oddWL.toString() : " + oddWL.toString());
		
		test.close();
 	}
 }