//For debugging and testing only
import java.io.FileWriter;
//For debugging and testing only
import java.io.PrintWriter;
//For debugging and testing only
import java.io.IOException;

/**
 *Title:  Cryptanalysis:  LetterFrequency object<p>
 *Author:  Julian Herrera  1316012<p>
 *School:  Cheyenne East High School<p>
 *Language:  Java<p>
 *Date:  April 1, 2003<p>
 *Purpose:  This object holds two values: a letter value as a character and a frequency value as an integer.  This object simplifies the need for a letter linked with a frequency.  
 */

public class LetterFrequency
{
/**Holds the letter of this object as a character*/
	private char myLetter = '\0';
/**Holds the frequency of occurrences of the letter as an integer*/
	private int myFrequency = 0;

/**Constructs and initializes a LetterFrequency object with given letter and a frequency of 0
 *@param letter the letter that this object represents as a character*/
	public LetterFrequency(char letter)
	{
		this(letter, 0);
	}

/**Constructs and initializes a LetterFrequency object with given letter and given frequency
 *@param letter the letter that this object represents as a character
 *@param number the frequency of given letter as an integer*/
	public LetterFrequency(char letter, int frequency)
	{
		myLetter = letter;
		myFrequency = frequency;
	}

/**Increments the frequency of this object*/
	public void incrementFrequency()
	{
		++myFrequency;
	}

/**Gets the letter that this object represents
 *@return the letter of this object as a character*/
	public char getLetter()
	{
		return myLetter;
	}

/**Gets the frequency of the letter represented by this object
 *@return the frequency of the letter as an integer*/
	public int getFrequency()
	{
		return myFrequency;
	}

/**Compares this object's frequency with that of the given LetterFrequency's frequency
 *@param that the LetterFrequency object to which this is being compared to
 *@return integer: <p> <0 if this object's frequency is less than that object's frequency <p> >0 if this object's frequency is greater than that object's frequency <p> =0 if this object's frequency is equal to that object's frequency */
	public int compareTo(LetterFrequency that)
	{
		return (myFrequency - that.getFrequency());			
	}
	
/**A string representation of the LetterFrequency object
 *@return the letter that this object represents, and the frequency of this letter*/
	public String toString()
	{
		String result = new String("");
		
		result += "Letter: " + myLetter;
		result += "    Frequency: " + myFrequency;
		
		return result;
	}
	
/**For debugging and testing purposes only*/
	public static void main(String[] args)
		throws IOException
	{
 		PrintWriter test = new PrintWriter(new FileWriter("LetterFrequencyTESTING.txt"));
		
		test.println("LetterFrequency TESTING");
		
		test.println();
		test.println("charOnly = new LetterFrequency('b')");
		LetterFrequency charOnly = new LetterFrequency('b');
		
		test.println("charAndInt = new LetterFrequency('c', 5)");
		LetterFrequency charAndInt = new LetterFrequency('c', 5);
		
		test.println();
		test.println("charOnly.getLetter() :" + charOnly.getLetter());
		test.println("charAndInt.getLetter() :" + charAndInt.getLetter());
		
		test.println();
		test.println("charOnly.getFrequency() :" + charOnly.getFrequency());
		test.println("charAndInt.getFrequency() :" + charAndInt.getFrequency());
		
		test.println();
		charOnly.incrementFrequency();
		charAndInt.incrementFrequency();
		test.println("charOnly.incrementFrequency() :" + charOnly.getFrequency());
		test.println("charAndInt.incrementFrequency() :" + charAndInt.getFrequency());
		
		test.println();
		test.println("charOnly.compareTo(charAndInt) :" + charOnly.compareTo(charAndInt));
		test.println("charAndInt.compareTo(charOnly) :" + charAndInt.compareTo(charOnly));		

		test.println();
		test.println("charOnly.toString() :" + charOnly.toString());
		test.println("charAndInt.toString() :" + charAndInt.toString());

		test.close();
	}
}

