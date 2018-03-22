import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 *Title:  Cryptanalysis:  Decipherer object<p>
 *Author:  Julian Herrera  1316012<p>
 *School:  Cheyenne East High School<p>
 *Language:  Java<p>
 *Date:  April 1, 2003<p>
 *Purpose:  This object determines the most probable substitutions using known letter frequencies in coordination with letter frequencies from the enciphered message.  This decryption attempt is then sent to the EnglishChecker to determine if it has made the correct substitution.
 */

public class Decipherer
{
/**Holds the raw enciphered scrambled message as a string*/
	private String myScrambledMsg = "scrambled";
/**Holds the current decipher attempt as a string*/
	private String myDecipherAttempt = "";
/**Holds the final correct deciphered message as a string*/
	private String myDecipheredMsg = "not deciphered";
/**Holds 26 LetterFrequency objects that represent known letter frequencies for the English language*/
	private LetterFrequency[] actualLetterFreq = new LetterFrequency[26];
/**Holds 26 LetterFrequency objects that represent the frequencies from the raw scrambled enciphered message*/
	private LetterFrequency[] msgLetterFreq = new LetterFrequency[26];
	private MessageReader myMR;

/**Constructs and initializes a Decipherer object to decipher the message in the file at the given location
 *@param filename the name of the file in which the enciphered message is located*/
	public Decipherer(String filename)
		throws IOException, FileNotFoundException
	{
		myMR = new MessageReader(filename);
	}

/**Fills the actualLetterFreq array with the actual letter frequencies for the English language*/
	private void createActualLetterFreqArray()
	{
		actualLetterFreq[0] = new LetterFrequency('e', 1270);
		actualLetterFreq[1] = new LetterFrequency('t', 906);
		actualLetterFreq[2] = new LetterFrequency('a', 817);
		actualLetterFreq[3] = new LetterFrequency('o', 751);
		actualLetterFreq[4] = new LetterFrequency('i', 697);
		actualLetterFreq[5] = new LetterFrequency('n', 675);
		actualLetterFreq[6] = new LetterFrequency('s', 633);
		actualLetterFreq[7] = new LetterFrequency('h', 609);
		actualLetterFreq[8] = new LetterFrequency('r', 599);
		actualLetterFreq[9] = new LetterFrequency('d', 425);
		actualLetterFreq[10] = new LetterFrequency('l', 403);
		actualLetterFreq[11] = new LetterFrequency('c', 278);
		actualLetterFreq[12] = new LetterFrequency('u', 276);
		actualLetterFreq[13] = new LetterFrequency('m', 241);
		actualLetterFreq[14] = new LetterFrequency('w', 236);
		actualLetterFreq[15] = new LetterFrequency('f', 223);
		actualLetterFreq[16] = new LetterFrequency('g', 202);
		actualLetterFreq[17] = new LetterFrequency('y', 197);
		actualLetterFreq[18] = new LetterFrequency('p', 193);
		actualLetterFreq[19] = new LetterFrequency('b', 149);
		actualLetterFreq[20] = new LetterFrequency('v', 98);
		actualLetterFreq[21] = new LetterFrequency('k', 77);
		actualLetterFreq[22] = new LetterFrequency('j', 16);
		actualLetterFreq[23] = new LetterFrequency('x', 15);
		actualLetterFreq[24] = new LetterFrequency('q', 10);
		actualLetterFreq[25] = new LetterFrequency('z', 7);

	}

/**Deciphers and prints the message to a file*/
	public void decipher()
		throws FileNotFoundException, IOException
	{
		myScrambledMsg = myMR.toString();

		createActualLetterFreqArray();

		createMsgLetterFreqArray();

		boolean myDeciphered = false;
System.out.println("new englishchecker...");
		EnglishChecker myEC = new EnglishChecker();
System.out.println("testing...");
		for(long count = 1; count <= (10*9*8*7*6*5*4*3*2); ++count)
		{
			substituteLetters();

			if(myEC.testMessage(myDecipherAttempt,0) == true)
			{
				myDecipheredMsg = myDecipherAttempt;
				myDeciphered = true;
				appendToFile();
				System.out.println(count + " ");
			}

if(count % 100 == 0)
System.out.print(count + " ");

			nextProbableArrayConfig();
		}
	}

/**Fills the msgLetterFrequency array will letters and gives these letters a frequency count from the scrambled message*/
	private void createMsgLetterFreqArray()
	{
		//instantiate the 26 LetterFrequency objects in the array
		for(int i = 0; i < 26; ++i)
		{
			msgLetterFreq[i] = new LetterFrequency((char)(i + 'a'));
		}

		//for each letter in the message, increment the respective LetterFrequency object
		for(int location = 0; location < myScrambledMsg.length(); ++location)
		{
			msgLetterFreq[myScrambledMsg.charAt(location) - (int)('a')].incrementFrequency();
		}

		//sort LetterFrequency objects by Frequency
		for(int counter1 = 0; counter1 < 25; ++counter1)
		{
			for(int counter2 = counter1 + 1; counter2 < 26; ++counter2)
			{
				if(msgLetterFreq[counter1].compareTo(msgLetterFreq[counter2]) < 0)
				{
					LetterFrequency myTemp = msgLetterFreq[counter1];
					msgLetterFreq[counter1] = msgLetterFreq[counter2];
					msgLetterFreq[counter2] = myTemp;
				}
			}
		}

		//increment frequencies so that all LetterFrequency objects have unique Frequency
		for(int j = 25; j > 0; --j)
		{
			if(msgLetterFreq[j].compareTo(msgLetterFreq[j - 1]) == 0)
			{
				for(int k = j - 1; k >= 0; --k)
				{
					msgLetterFreq[k].incrementFrequency();
				}
			}
		}
	}

/**Substitutes the corresponding letters from the scrambled message into a decryption attempt string*/
	private void substituteLetters()
	{
		char specialChar = '*';
		char removedChar = '\0';
		char replacingChar = specialChar;
		int position = 0;
		int location = -1;

		myDecipherAttempt = myScrambledMsg;

		removedChar = msgLetterFreq[position].getLetter();

		for(int counter = 0; counter < 25; ++counter)
		{

			myDecipherAttempt = myDecipherAttempt.replace(removedChar, replacingChar);

			location = -1;

			do{
				++location;
			}while(removedChar != actualLetterFreq[location].getLetter());

			position = location;

			replacingChar = actualLetterFreq[position].getLetter();
			removedChar = msgLetterFreq[position].getLetter();
		}

		removedChar = specialChar;

		myDecipherAttempt = myDecipherAttempt.replace(removedChar, replacingChar);

	}

/**Replaces all occurences of the removedChar with the replacingChar
 *@param removedChar the letter to be replaced
 *@param replacingChar the letter to be replacing*/
	private void replaceLetters(char removedChar, char replacingChar)
	{
		String stringTemp = new String("");
		char charTemp = '\0';

		for(int position = 0; position < myDecipherAttempt.length(); ++position)
		{
			charTemp = myDecipherAttempt.charAt(position);

			if(charTemp == removedChar)
			{
				stringTemp += replacingChar;
			}
			else
			{
				stringTemp += charTemp;
			}
		}
	}

/**Shuffles the msgLetterFreq array into the next probable order*/
	private void nextProbableArrayConfig()
	{
		int endOfArray = 25;
		LetterFrequency myTemp;
		LetterFrequency[] myQueue = new LetterFrequency[900];
		int fullStartPosition = 0;
		int emptyEndPosition = 0;

		myTemp = msgLetterFreq[endOfArray];
		--endOfArray;

		do{
			myQueue[emptyEndPosition] = myTemp;
			++emptyEndPosition;
			myTemp = msgLetterFreq[endOfArray];
			--endOfArray;

		}while(myTemp.compareTo(myQueue[emptyEndPosition-1]) < 0);


		while(myQueue[fullStartPosition].compareTo(myTemp) >= 0)
		{
			myQueue[emptyEndPosition] = myQueue[fullStartPosition];
			++fullStartPosition;
			++emptyEndPosition;
		}

		msgLetterFreq[endOfArray+1] = myQueue[fullStartPosition];
		++fullStartPosition;
		++endOfArray;

		myQueue[emptyEndPosition] = myTemp;
		++emptyEndPosition;

		while(myQueue[fullStartPosition].compareTo(myTemp) < 0)
		{
			myQueue[emptyEndPosition] = myQueue[fullStartPosition];
			++emptyEndPosition;
			++fullStartPosition;
		}

		while(fullStartPosition < emptyEndPosition)
		{
			msgLetterFreq[endOfArray+1] = myQueue[fullStartPosition];
			++fullStartPosition;
			++endOfArray;
		}
	}

/**Prints the deciphered message to a file without designated file name*/
	public void appendToFile()
		throws IOException
	{
		appendToFile("deciphered.txt");
	}

/**Prints the deciphered message to a file with the given name
 *@param filename the name of the file to which the deciphered message will be printed to*/
	public void appendToFile(String filename)
		throws IOException
	{
		PrintWriter myPW = new PrintWriter(new FileWriter(filename, true));

		myPW.println(this);

		myPW.close();
	}

/**A string representation of the deciphered message*/
	public String toString()
	{
		return (myScrambledMsg + "    " + myDecipheredMsg);
	}

/**For debuging and testing purposes only*/
	public static void main(String[] args)
		throws FileNotFoundException, IOException
	{
		/*PrintWriter test = new PrintWriter(new FileWriter("DeciphererTESTING.txt"));

 		test.println("Decipherer TESTING");

 		test.println();
		test.println("myDecipherer = new Decipherer('secretmsg.txt')");
		Decipherer myDecipherer = new Decipherer("secretmsg.txt");

		test.println();
		test.println("secretmsg.txt contains the following enciphered message : ");
		test.println("Qeb cxoo lk exq fk qeb f bxq xk xk x pbb qeb qeb ql pbb lk f zxq lk lk f f pbb pl fp bxq bxq bxq lk lk pl ofa ofa");
		test.println("which means:");
		test.println("The fall on hat in the i eat an an a see the the to see on i cat on on i i see so is eat eat eat on on so rid rid");

		test.println();
		test.println("myDecipherer.toString() :" + myDecipherer.toString());

		test.println();
		test.println("myDecipherer.decipher()");
		myDecipherer.decipher();

		test.println();
		test.println("myDecipherer.toString() :" + myDecipherer.toString());

		test.close();*/
	}
}
