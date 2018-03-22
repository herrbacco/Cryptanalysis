import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
//For debugging and testing only
import java.io.FileWriter;
//For debugging and testing only
import java.io.PrintWriter;

/**
 *Title:  Cryptanalysis:  Dictionary object<p>
 *Author:  Julian Herrera  1316012<p>
 *School:  Cheyenne East High School<p>
 *Language:  Java<p>
 *Date:  April 1, 2003<p>
 *Purpose:  This object will house the 26 WordLists.  This object will read a text file and create corresponding WordLists.  This object will also be able to test whether or not a word exists in any of its WordLists.  
 */

public class Dictionary
{
/**Holds the 26 WordLists as an array*/	
	private WordList[] myDictionaryArray = new WordList [26];
/**Holds the name of the file from which this object was created as a string*/
	private String myFilename = new String("");	

/**Constructs and initializes a Dictionary object without specifying a file*/
	public Dictionary()
		throws FileNotFoundException, IOException
	{
		this("dictionary.txt");
	}

/**Constructs and initializes a Dictionary object from the given file
 *@param filename the name of the file from which this object is to be created as a string*/
	public Dictionary(String filename)
		throws FileNotFoundException, IOException
	{
		myFilename = filename;
		
		char myCharacter = 'a';
		String myWord = new String("");
		BufferedReader myReader = new BufferedReader(new FileReader(myFilename));
		
		myWord = myReader.readLine();
		
		//creates each WordList for the words in the given text file
		for(int counter = 0; counter < 26; ++counter)
		{
System.out.print((char)(counter + (int)'a'));
			int position = 0;
			String[] tempArray = new String[100];
			
			myCharacter = (char)myWord.charAt(0);
			tempArray[position] = myWord;
			
			myWord = myReader.readLine();
			
			while(myWord != null && myWord.charAt(0) == myCharacter)
			{
				++position;
				
				if(tempArray.length >= position)
				{
					tempArray = enlargeArray(tempArray);
				}
				
				tempArray[position] = myWord;
				
				myWord = myReader.readLine();
			}
			
			myDictionaryArray[counter] = new WordList(shrinkArray(tempArray));
		}
		
		myReader.close();
	}

/**Enlarges the array by enlarging its capacity by 1
 *@param smallArray the array to be enlarged as a String[]
 *@return the enlarged array as a String[]*/
	private String[] enlargeArray(String[] smallArray)
	{
		String[] tempArray = new String[(1 + smallArray.length)];
		
		for(int i = 0; i < smallArray.length; ++i)
		{
			tempArray[i] = smallArray[i];
		}
		
		return tempArray;
	}

/**Shrinks the array so that it is exactly the correct size
 *@param largeArray the array to be shrunken as a String[]
 *@return the shrunken array as a String[]*/
	private String[] shrinkArray(String[] largeArray)
	{
		int counter = 0;
		
		while(largeArray[counter] != null)
		{
			++counter;
		}
		
		String[] tempArray = new String[counter];
		
		for(int i = 0; i < tempArray.length; ++i)
		{
			tempArray[i] = largeArray[i];
		}

		return tempArray;
	}
	
/**Finds the longest word in the WordList that starts with the same letter as the given word
 *@param word the word that is being tested
 *@return the length of the longest word in the appropriate WordList*/
	public int getLongestWordLength(String word)
	{
		//checks to make sure the word is a word that starts with a lowercase letter, otherwise return 0
		if(word.charAt(0) >= 'a' && word.charAt(0) <= 'z')
		{
			char letter = word.charAt(0);
			
			return (myDictionaryArray[(int)letter - (int)'a'].getLongestWordLength());
		}
		
		return 0;
	}
	
/**Determines if the given word exists in the dictionary
 *@param word the word being tested
 *@return the result of the search as a boolean*/
	public boolean wordExists(String word)
	{
		//checks to make sure the word is a word that starts with a lowercase letter, otherwise return false
		if(word.charAt(0) >= 'a' && word.charAt(0) <= 'z')
		{
			char letter = word.charAt(0);
			
			return (myDictionaryArray[(int)letter - (int)'a'].wordExists(word));
		}
		
		return false;		
	}

/**A string representation of the file name from which it was made
 *@return the file name from which this object was formed as a string*/
	public String toString()
	{
		return myFilename;
	}

/**For debugging and testing purposes only*/
	public static void main(String[] args)
		throws FileNotFoundException, IOException
	{
 		PrintWriter test = new PrintWriter(new FileWriter("DictionaryTESTING.txt"));
 		
 		test.println("Dictionary TESTING");
 		
		test.println();
		test.println("noParam = new Dictionary()");
		Dictionary noParam = new Dictionary();
		
		test.println("fileParam = new Dictionary('mywords.txt')");
		Dictionary fileParam = new Dictionary("mywords.txt");

		test.println();
		test.println("mywords.txt contains select words in the form of a dictionary, i.e.");
		test.println("a");
		test.println("an");
		test.println("apple");
		test.println("banana");
		test.println("boy");
		test.println("can");
		test.println("cat");
		test.println("etc.");
		
		test.println();
		test.println("noParam.getLongestWordLength('aunt') :" + noParam.getLongestWordLength("aunt"));
		test.println("fileParam.getLongestWordLength('zoo') :" + fileParam.getLongestWordLength("but"));
		test.println("noParam.getLongestWordLength('rid') :" + noParam.getLongestWordLength("rid"));
		test.println("fileParam.getLongestWordLength('300') :" + fileParam.getLongestWordLength("300"));
		
		test.println();
		test.println("noParam.wordExists('apple') :" + noParam.wordExists("apple"));
		test.println("fileParam.wordExists('zoo') :" + fileParam.wordExists("zoo"));
		test.println("noParam.wordExists('rid') :" + noParam.wordExists("rid"));
		
		test.println("fileParam.wordExists('alabama') :" + fileParam.wordExists("alabama"));
		test.println("noParam.wordExists('zen') :" + noParam.wordExists("zen"));
		test.println("fileParam.wordExists('randolf') :" + fileParam.wordExists("randolf"));
		test.println("noParam.wordExists('300') :" + noParam.wordExists("300"));
		
		test.println();
		test.println("noParam.toString() :" + noParam.toString());
		test.println("fileParam.toString() :" + fileParam.toString());

		test.close();		
	}	
}
