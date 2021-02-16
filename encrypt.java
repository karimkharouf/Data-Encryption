import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;

public class encrypt 
{
	private String FileName;
	
	//class constructor
	public encrypt(String FileName)
	{
		this.FileName=FileName;
	}

	//fake text  generator to "imitate encryption"
	public String FakeText (int length)
	{
		Random ran = new Random ();
		int index=0;
		String output="";

		for (int i = 0 ; i<length ; i++)
		{
			index=ran.nextInt(63)+64;	//random number between 64 and 126
			output+=(char)(index);
		}

		return output;
	}
	
	//read the line number in the file being read
	public String FileReader (int LineNumber) throws IOException
	{
		String output="";
		File source = new File (FileName);
		Scanner filescanner = new Scanner(source);
		for (int i = 0; i<LineNumber;i++)
			if (filescanner.hasNextLine())
				filescanner.nextLine();
		
		if (filescanner.hasNextLine())
			output=filescanner.nextLine();
		
		filescanner.close();
		return output;
	}
	
	//separates characters in string by "//"
	public static String stringReader(String string) 
	{
		String seperate="";
		for (int i = 0; i<string.length(); i++) 
		{
			char chari = string.charAt(i);
			seperate+=(Character.toString(chari) + "//"); 
		}
		
		return seperate;
	}
	
	//reads character number (index) inside a given string
	public char charReader (String string, int index)
	{
		char readChar=' ';
		Scanner scan = new Scanner (string);
		scan.useDelimiter("//");
		for(int i=0; i<index;i++)
			if (scan.hasNext())
				scan.next();
		
		if (scan.hasNext())
			readChar=scan.next().charAt(0);
		
		scan.close();
		return readChar;
	}
	
	//translates every given character to an integer
	public int translate(char x)
	{
		int nb=0;
		switch (x)
		{
		case'a':	{nb=2	;break;}
		case'A':	{nb=3	;break;}

		case'b':	{nb=6	;break;}
		case'B':	{nb=7	;break;}

		case'c':	{nb=10	;break;}
		case'C':	{nb=11	;break;}

		case'd':	{nb=14	;break;}
		case'D':	{nb=15	;break;}

		case'e':	{nb=18	;break;}
		case'E':	{nb=19	;break;}

		case'f':	{nb=22	;break;}
		case'F':	{nb=23	;break;}

		case'g':	{nb=26	;break;}
		case'G':	{nb=27	;break;}

		case'h':	{nb=30	;break;}
		case'H':	{nb=31	;break;}

		case'i':	{nb=34	;break;}
		case'I':	{nb=35	;break;}

		case'j':	{nb=38	;break;}
		case'J':	{nb=39	;break;}

		case'k':	{nb=42	;break;}
		case'K':	{nb=43	;break;}

		case'l':	{nb=46	;break;}
		case'L':	{nb=47	;break;}

		case'm':	{nb=50	;break;}
		case'M':	{nb=51	;break;}

		case'n':	{nb=54	;break;}
		case'N':	{nb=55	;break;}

		case'o':	{nb=58	;break;}
		case'O':	{nb=59	;break;}

		case'p':	{nb=62	;break;}
		case'P':	{nb=63	;break;}

		case'q':	{nb=66	;break;}
		case'Q':	{nb=67	;break;}

		case'r':	{nb=70	;break;}
		case'R':	{nb=71	;break;}

		case's':	{nb=74	;break;}
		case'S':	{nb=75	;break;}

		case't':	{nb=78	;break;}
		case'T':	{nb=79	;break;}

		case'u':	{nb=82	;break;}
		case'U':	{nb=83	;break;}

		case'v':	{nb=86	;break;}
		case'V':	{nb=87	;break;}

		case'w':	{nb=90	;break;}
		case'W':	{nb=91	;break;}

		case'x':	{nb=94	;break;}
		case'X':	{nb=95	;break;}

		case'y':	{nb=98	;break;}
		case'Y':	{nb=99	;break;}

		case'z':	{nb=102	;break;}
		case'Z':	{nb=103	;break;}


		case' ':	{nb=106	;break;}
		case'!':	{nb=109	;break;}
		case'"':	{nb=112	;break;}
		case'#':	{nb=115	;break;}
		case'$':	{nb=118	;break;}
		case'%':	{nb=121	;break;}
		case'&':	{nb=124	;break;}
		case'\\':	{nb=127	;break;}
		case')':	{nb=130	;break;}
		case'(':	{nb=133	;break;}
		case'*':	{nb=136	;break;}
		case'+':	{nb=139	;break;}
		case',':	{nb=142	;break;}
		case'-':	{nb=145	;break;}
		case'.':	{nb=148	;break;}
		case'/':	{nb=151	;break;}
		case'0':	{nb=154	;break;}
		case'1':	{nb=157	;break;}
		case'2':	{nb=160	;break;}
		case'3':	{nb=163	;break;}
		case'4':	{nb=166	;break;}
		case'5':	{nb=169	;break;}
		case'6':	{nb=172	;break;}
		case'7':	{nb=175	;break;}
		case'8':	{nb=178	;break;}
		case'9':	{nb=181	;break;}
		case':':	{nb=184	;break;}
		case';':	{nb=187	;break;}
		case'<':	{nb=190	;break;}
		case'=':	{nb=193	;break;}
		case'>':	{nb=196	;break;}
		case'?':	{nb=199	;break;}
		case'@':	{nb=202	;break;}
		case'[':	{nb=205	;break;}
		case']':	{nb=211	;break;}
		case'^':	{nb=214	;break;}
		case'_':	{nb=217	;break;}
		case'`':	{nb=220	;break;}
		case'{':	{nb=223	;break;}
		case'}':	{nb=226	;break;}
		case'|':	{nb=229	;break;}
		case'~':	{nb=232	;break;}
		case '\'':	{nb=235	;break;}
		}
		
		return nb;
	}

	// the next 4 methods extracts the integer specifying alpha, red, green, and blue from the given RGB index called p
	public  int Areader(int p)
	{
		return (p>>24)&(0xff);	
	}

	public int Rreader(int p)
	{
		return (p>>16)&(0xff);	
	}

	public int Greader(int p)
	{
		return (p>>8)&(0xff);	
	}

	public int Breader(int p)
	{
		return (p)&(0xff);	
	}
	
	//the next 3 methods set the R, G, B components of an RGB index
	public int Rsetter (int r)
	{
		return (255<<24) | (r<<16) | (255<<8) | 255;
	}

	public  int Gsetter (int g)
	{
		return (255<<24) | (255<<16) | (g<<8) | 255;
	}

	public  int Bsetter (int b)
	{
		return (255<<24) | (255<<16) | (255<<8) | b;
	}

	// counts the number of characters in the given file
	public int count() throws IOException
	{
		Scanner fileScan = new Scanner(new File(FileName)), lineScan;
		int x=0;
		String line="";

		while (fileScan.hasNext())
		{
			line = fileScan.nextLine();
			lineScan = new Scanner(line);
			lineScan.useDelimiter("");
			while (lineScan.hasNext())
			{
				x++;
				lineScan.next();
			}
		}
		
		fileScan.close();
		return x;
	}

	//generates the encryption code
	public static long EncryptionCodeGenerator ()
	{
		String encryptioncode="";
		long encryptionCode=0;

		String date=LocalDate.now()+"";
		String time=LocalTime.now()+"";

		Scanner scanDate = new Scanner(date);
		Scanner scanTime = new Scanner(time);

		scanDate.useDelimiter("-");
		scanTime.useDelimiter(":");

		while(scanDate.hasNext())
			encryptioncode+=scanDate.next();

		while(scanTime.hasNextInt())
			encryptioncode+=scanTime.next();

		scanDate.close();
		scanTime.close();
		encryptionCode=Long.parseLong(encryptioncode);
		return encryptionCode;
	}
	
	//counts the number of lines inside file to be read
	public int numberOfLines() throws IOException
	{
		int numberOfLines=0;
		Scanner fileScanner = new Scanner(new File(FileName));

		while (fileScanner.hasNextLine())
		{
			numberOfLines++;
			fileScanner.nextLine();
		}
		
		fileScanner.close();
		return numberOfLines;
	}
	
}
