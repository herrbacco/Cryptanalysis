import java.io.IOException;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
//For debugging and testing only
import java.io.FileWriter;
//For debugging and testing only
import java.io.PrintWriter;

/**
 *Title:  Cryptanalysis:  Driver object<p>
 *Author:  Julian Herrera  1316012<p>
 *School:  Cheyenne East High School<p>
 *Language:  Java<p>
 *Date:  April 1, 2003<p>
 *Purpose:  This object drives the Cryptanalysis project.  It requests the name of the file of the enciphered message.
 */

public class Driver
{
/**Constructs and initializes a Driver object and begins the decryption*/
	public Driver()
		throws IOException
	{
		System.out.println(toString());
		
		String filename = JOptionPane.showInputDialog("What is the name of the text file that contains the enciphered message?");
		
		//in the case that an invalid text file name is entered, this infinite loop catches the exception and asks the user for a new response
		for(;;)
		{
			try{
				
				Decipherer AlanTuring = new Decipherer(filename);
System.out.println("testing message...");
				AlanTuring.decipher();
				System.exit(0);
				
			}catch(FileNotFoundException error)
			{
				filename = JOptionPane.showInputDialog("File Not Found.  What is the name of the text file that contains the enciphered message?");
			}
		}
	}
	
/**A string representation of the Driver object and has a heading for the project
 *@return the introduction and instructions of this object as a String*/	
	public String toString()
	{
		String result = new String("");
		
		result += "Cryptanalysis\n\n";
		result += "by Julian Herrera\n";
		result += "at Cheyenne East High School, Cheyenne, WY\n";
		result += "using the Java language\n";
		result += "in April 2003\n";
		result += "for the purpose of decrypting a message that was enciphered with a monoalpabetic substitution cipher\n";
		
		return result;
	}
	
/**For debugging and testing purposes only*/	
	public static void main(String[] args)
		throws IOException
	{
 		PrintWriter test = new PrintWriter(new FileWriter("DriverTESTING.txt"));
 		
 		test.println("Driver TESTING");
 		
		test.println();
		test.println("start = new Driver()");
		Driver start = new Driver();
		
		test.println();
		test.println("start.toString() :" + start.toString());
		
		test.close();
	}	
}
