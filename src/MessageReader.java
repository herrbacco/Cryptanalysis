import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
//For debugging and testing only
import java.io.FileWriter;
//For debugging and testing only
import java.io.PrintWriter;

/**
 *Title:  Cryptanalysis:  MessageReader object<p>
 *Author:  Julian Herrera  1316012<p>
 *School:  Cheyenne East High School<p>
 *Language:  Java<p>
 *Date:  April 1, 2003<p>
 *Purpose:  This object reads in a text file and buffers out all but the characters and ensures that they are all lowercase.  This prepares most any text file for decryption.  
 */

public class MessageReader
{
/**Holds the buffered message from text file as a string*/
	private String myString = "";

/**Constructs and initializes a MessageReader object and produces a string value of the text file found at the given filename
 *@param filename the name of the file to be read and buffered as a string*/
	public MessageReader(String filename)
		throws FileNotFoundException, IOException
	{
System.out.println('\n' + "reading file..." + filename);
		FileReader myFR = new FileReader(filename);
		
		int myChar = '\0';
		
		myChar = myFR.read();

		while(myChar != -1)
		{
			//removal of all unwanted symbols, buffering the message for deciphering
			if((char)myChar != ' ' && (char)myChar != '\t' && (char)myChar != ':' &&
				myChar != 10 && myChar != 13 && (char)myChar != ',' && (char)myChar != '=' &&
				(char)myChar != '.' && (char)myChar != '!' && (char)myChar != '?' && 
				(char)myChar != '-' && (char)myChar != '/' && (char)myChar != '(' &&  
				(char)myChar != ')' && (char)myChar != ';' && (char)myChar != '0' && 
				(char)myChar != '1' && (char)myChar != '2' && (char)myChar != '3' && 
				(char)myChar != '4' && (char)myChar != '5' && (char)myChar != '6' && 
				(char)myChar != '7' && (char)myChar != '8' && (char)myChar != '9' && 
				(char)myChar != '"' && (char)myChar != '+' && (char)myChar != '<' &&
				(char)myChar != '>' && (char)myChar != '|' && (char)myChar != '%' &&
				(char)myChar != '$' && (char)myChar != '&' && (char)myChar != '_' &&
				(char)myChar != '{' && (char)myChar != '}' && (char)myChar != '[' &&
				(char)myChar != ']' && (char)myChar != '·' && (char)myChar != '*' &&
				(char)myChar != '\\' && (char)myChar != '@')
			{
				myString += (char)myChar; 	
			}
			
			myChar = myFR.read();
		}
		
		myString = myString.toLowerCase();
		
		myFR.close();
	}

/**A string representation of the buffered message
 *@return the text file, buffered, as a string*/
	public String toString()
	{
		return myString;
	}	

/**For debugging and testing purposes only*/
	public static void main(String[] args)
		throws FileNotFoundException, IOException
	{
 		PrintWriter test = new PrintWriter(new FileWriter("MessageReaderTESTING.txt"));
		
		test.println("MessageReader TESTING");
		
		test.println();
		test.println("enciphered = new MessageReader('secretmsg.txt') :");
		MessageReader enciphered = new MessageReader("secretmsg.txt");
		
		test.println("plaintext = new MessageReader('plaintext.txt') :");
		MessageReader plaintext = new MessageReader("plaintext.txt");
		
		test.println();
		test.println("secretmsg.txt contains the following enciphered message : ");
		test.println("Qeb cxoo lk exq fk qeb f bxq xkxkx pbb qeb qeb ql pbb lk f zxq lklkff pbb pl fp bxq bxq bxq lk lk pl ofa ofa");
		
		test.println();
		test.println("plaintext.txt contains the following message : ");
		test.println("  Once upon a time, there lived three little pigs.  The first one had a house built of straw.  	The second was made of twigs, it was not very strong.  	The thrid was made of bricks, it was extremely strong.");

		test.println();
		test.println("enciphered.toString() : " + enciphered.toString());
		test.println("plaintext.toString() : " + plaintext.toString());

		test.close();
	}	
}

