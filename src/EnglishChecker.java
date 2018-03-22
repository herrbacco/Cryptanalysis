//For debugging and testing only
import java.io.FileWriter;
//For debugging and testing only
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 *Title:  Cryptanalysis:  EnglishChecker object<p>
 *Author:  Julian Herrera  1316012<p>
 *School:  Cheyenne East High School<p>
 *Language:  Java<p>
 *Date:  April 1, 2003<p>
 *Purpose:  This object sections out the decryption attempt into words to be checked against the dictionary.  Its central method is the testMessage method that recursively tests the message.  
 */

public class EnglishChecker
{
/**Holds the Dictionary object to be checked against*/
	private Dictionary myDictionary;
/**Holds the message that last sectioned into words and found in the dictionary as a string*/
	private String myMessage = new String("");

/**Constructs and initializes an EnglishChecker object and creates a new Dictionary object*/
	public EnglishChecker()
		throws FileNotFoundException, IOException
	{
System.out.println("making dictionary...");
		myDictionary = new Dictionary();
	}

/**Tests the given message for containing English words
 *@param message the message to be tested as a string
 *@return boolean: true if the message contains words from the dictionary, false if the message does not contain words from the dictionary*/
	public boolean testMessage(String message, int level)
	{	
		if(message.length() == 0 || level > 7)
		{	
			return true;
		}
		
		int position = 0;
		
		String word = new String(String.valueOf(message.charAt(0)));
		
		while(word.length() <= myDictionary.getLongestWordLength(word) && position < message.length())
		{	
			if(myDictionary.wordExists(word) && testMessage(message.substring(position + 1), level + 1) == true)
			{	
				myMessage = message;
				return true;
			}
			
			++position;
			
			if(position < message.length())
			{	
				word += message.charAt(position);
			}
		}
		
		return false;
	}

/**Returns the dictionary used to check the message
 *@return the dictionary's default toString() function as a String*/
	public String toString()
	{
		return myDictionary.toString();
	}	

/**For debugging and testing purposes only*/
	public static void main(String[] args)
		throws FileNotFoundException, IOException
	{
 		/*PrintWriter test = new PrintWriter(new FileWriter("EnglishCheckerTESTING.txt"));
		
		test.println("EnglishChecker TESTING");
		
		test.println();
		test.println("myEC = new EnglishChecker()");
		EnglishChecker myEC = new EnglishChecker();
		
		test.println();
		test.println("myEC will be using the dictionary formed from mywords.txt");

		test.println();
		test.println("myEC.testMessage('') :" + myEC.testMessage(""));
		test.println("myEC.testMessage('i') :" + myEC.testMessage("i"));
		test.println("myEC.testMessage('isee') :" + myEC.testMessage("isee"));
		test.println("myEC.testMessage('indigokite') :" + myEC.testMessage("indigokite"));
		
		test.println("myEC.testMessage('ieatacatsiy') :" + myEC.testMessage("ieatacatsiy"));
		test.println("myEC.testMessage('300') :" + myEC.testMessage("300"));
		
		test.println();
		test.println("myEC.toString() :" + myEC.toString());
		
		test.close();*/
	}
}

