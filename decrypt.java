import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class decrypt 
{
	String FileName;

	//class constructor
	public decrypt (String FileName)
	{
		this.FileName=FileName;	
	}
	
	//the following three methods read the A, R, G, and B indicators of a given RGB index called p
	public static int Areader(int p)
	{
		return (p>>24)&(0xff);	
	}

	public static int Rreader(int p)
	{
		return (p>>16)&(0xff);	
	}

	public static int Greader(int p)
	{
		return (p>>8)&(0xff);	
	}

	public static int Breader(int p)
	{
		return (p)&(0xff);	
	}

	//translates from a given integer to a character
	public static char reverseTranslate(int str)
	{
		char nb=' ';
		switch (str)
		{
		case 1:		{nb='a';	break;}
		case 2:		{nb='a';	break;}

		case 3:		{nb='A';	break;}
		case 4:		{nb='A';	break;}

		case 5:		{nb='b';	break;}
		case 6:		{nb='b';	break;}

		case 7:		{nb='B'	;	break;}
		case 8:		{nb='B';	break;}


		case 9:		{nb='c'	;	break;}
		case 10:	{nb='c';	break;}

		case 11:	{nb='C'	;	break;}
		case 12:	{nb='C';	break;}

		case 13:	{nb='d';	break;}
		case 14:	{nb='d';	break;}

		case 15:	{nb='D'	;	break;}
		case 16:	{nb='D';	break;}


		case 17:	{nb='e'	;	break;}
		case 18:	{nb='e';	break;}

		case 19:	{nb='E'	;	break;}
		case 20:	{nb='E';	break;}


		case 21:	{nb='f'	;	break;}
		case 22:	{nb='f';	break;}

		case 23:	{nb='F'	;	break;}
		case 24:	{nb='F';	break;}

		case 25:	{nb='g'	;	break;}
		case 26:	{nb='g';	break;}

		case 27:	{nb='G' ;	break;}
		case 28:	{nb='G';	break;}

		case 29:	{nb='h'	;	break;}
		case 30:	{nb='h';	break;}

		case 31:	{nb='H'	;	break;}
		case 32:	{nb='H';	break;}

		case 33:	{nb='i'	;	break;}
		case 34:	{nb='i';	break;}

		case 35:	{nb='I'	;	break;}
		case 36:	{nb='A';	break;}

		case 37:	{nb='j'	;	break;}
		case 38:	{nb='j';	break;}

		case 39:	{nb='J'	;	break;}
		case 40:	{nb='J';	break;}

		case 41:	{nb='k'	;	break;}
		case 42:	{nb='k';	break;}

		case 43:	{nb='K'	;	break;}
		case 44:	{nb='K';	break;}

		case 45:	{nb='l'	;	break;}
		case 46:	{nb='l';	break;}

		case 47:	{nb='L'	;	break;}
		case 48:	{nb='L'	;	break;}

		case 49:	{nb='m'	;	break;}
		case 50:	{nb='m'	;	break;}

		case 51:	{nb='M'	;	break;}
		case 52:	{nb='M'	;	break;}

		case 53:	{nb='n'	;	break;}
		case 54:	{nb='n'	;	break;}

		case 55:	{nb='N'	;	break;}
		case 56:	{nb='N'	;	break;}

		case 57:	{nb='o'	;	break;}
		case 58:	{nb='o'	;	break;}

		case 59:	{nb='O'	;	break;}
		case 60:	{nb='O'	;	break;}

		case 61:	{nb='p'	;	break;}
		case 62:	{nb='p'	;	break;}

		case 63:	{nb='P'	;	break;}
		case 64:	{nb='P'	;	break;}

		case 65:	{nb='q'	;	break;}
		case 66:	{nb='q'	;	break;}

		case 67:	{nb='Q'	;	break;}
		case 68:	{nb='Q'	;	break;}

		case 69:	{nb='r'	;	break;}
		case 70:	{nb='r'	;	break;}

		case 71:	{nb='R'	;	break;}
		case 72:	{nb='R'	;	break;}

		case 73:	{nb='s'	;	break;}
		case 74:	{nb='s'	;	break;}

		case 75:	{nb='S'	;	break;}
		case 76:	{nb='S'	;	break;}

		case 77:	{nb='t'	;	break;}
		case 78:	{nb='t'	;	break;}

		case 79:	{nb='T'	;	break;}
		case 80:	{nb='T'	;	break;}

		case 81:	{nb='u'	;	break;}
		case 82:	{nb='u'	;	break;}

		case 83:	{nb='U'	;	break;}
		case 84:	{nb='U'	;	break;}

		case 85:	{nb='v'	;	break;}
		case 86:	{nb='v'	;	break;}

		case 87:	{nb='V'	;	break;}
		case 88:	{nb='V'	;	break;}

		case 89:	{nb='w'	;	break;}
		case 90:	{nb='w'	;	break;}

		case 91:	{nb='W'	;	break;}
		case 92:	{nb='W'	;	break;}

		case 93:	{nb='x'	;	break;}
		case 94:	{nb='x'	;	break;}

		case 95:	{nb='X'	;	break;}
		case 96:	{nb='X'	;	break;}

		case 97:	{nb='y'	;	break;}
		case 98:	{nb='y'	;	break;}

		case 99:	{nb='Y'	;	break;}
		case 100:	{nb='Y'	;	break;}

		case 101:	{nb='z'	;	break;}
		case 102:	{nb='z'	;	break;}

		case 103:	{nb='Z'	;	break;}
		case 104:	{nb='Z'	;	break;}

		case 105:	{nb=' '	;	break;}
		case 106:	{nb=' '	;	break;}
		case 107:	{nb=' '	;	break;}

		case 108:	{nb='!'	;	break;}
		case 109:	{nb='!'	;	break;}
		case 110:	{nb='!'	;	break;}

		case 111:	{nb='"'	;	break;}
		case 112:	{nb='"'	;	break;}
		case 113:	{nb='"'	;	break;}

		case 114:	{nb='#'	;	break;}
		case 115:	{nb='#'	;	break;}
		case 116:	{nb='#'	;	break;}

		case 117:	{nb='$'	;	break;}
		case 118:	{nb='$'	;	break;}
		case 119:	{nb='$'	;	break;}

		case 120:	{nb='%'	;	break;}
		case 121:	{nb='%'	;	break;}
		case 122:	{nb='%'	;	break;}

		case 123:	{nb='&'	;	break;}
		case 124:	{nb='&'	;	break;}
		case 125:	{nb='&'	;	break;}

		case 126:	{nb='\\';	break;}
		case 127:	{nb='\\';	break;}
		case 128:	{nb='\\';	break;}

		case 129:	{nb=')'	;	break;}
		case 130:	{nb=')'	;	break;}
		case 131:	{nb=')';		break;}

		case 132:	{nb='('	;	break;}
		case 133:	{nb='('	;	break;}
		case 134:	{nb='(';		break;}

		case 135:	{nb='*';		break;}
		case 136:	{nb='*'	;	break;}
		case 137:	{nb='*'	;	break;}

		case 138:	{nb='+';		break;}
		case 139:	{nb='+'	;	break;}
		case 140:	{nb='+'	;	break;}

		case 141:	{nb=',';		break;}
		case 142:	{nb=','	;	break;}
		case 143:	{nb=','	;	break;}

		case 144:	{nb='-';		break;}
		case 145:	{nb='-'	;	break;}
		case 146:	{nb='-'	;	break;}

		case 147:	{nb='.';		break;}
		case 148:	{nb='.'	;	break;}
		case 149:	{nb='.'	;	break;}

		case 150:	{nb='/';		break;}
		case 151:	{nb='/'	;	break;}
		case 152:	{nb='/'	;	break;}

		case 153:	{nb='0';		break;}
		case 154:	{nb='0'	;	break;}
		case 155:	{nb='0'	;	break;}

		case 156:	{nb='1';		break;}
		case 157:	{nb='1'	;	break;}
		case 158:	{nb='1'	;	break;}

		case 159:	{nb='2';		break;}
		case 160:	{nb='2'	;	break;}
		case 161:	{nb='2'	;	break;}

		case 162:	{nb='3';		break;}
		case 163:	{nb='3'	;	break;}
		case 164:	{nb='3'	;	break;}

		case 165:	{nb='4';		break;}
		case 166:	{nb='4'	;	break;}
		case 167:	{nb='4'	;	break;}

		case 168:	{nb='5';		break;}
		case 169:	{nb='5'	;	break;}
		case 170:	{nb='5'	;	break;}

		case 171:	{nb='6';		break;}
		case 172:	{nb='6'	;	break;}
		case 173:	{nb='6'	;	break;}

		case 174:	{nb='7';		break;}
		case 175:	{nb='7'	;	break;}
		case 176:	{nb='7'	;	break;}

		case 177:	{nb='8';		break;}
		case 178:	{nb='8'	;	break;}
		case 179:	{nb='8'	;	break;}

		case 180:	{nb='9';		break;}
		case 181:	{nb='9'	;	break;}
		case 182:	{nb='9'	;	break;}

		case 183:	{nb=':';		break;}
		case 184:	{nb=':'	;	break;}
		case 185:	{nb=':'	;	break;}

		case 186:	{nb=';';		break;}
		case 187:	{nb=';'	;	break;}
		case 188:	{nb=';'	;	break;}

		case 189:	{nb='<';		break;}
		case 190:	{nb='<'	;	break;}
		case 191:	{nb='<'	;	break;}

		case 192:	{nb='=';		break;}
		case 193:	{nb='='	;	break;}
		case 194:	{nb='='	;	break;}

		case 195:	{nb='>';		break;}
		case 196:	{nb='>'	;	break;}
		case 197:	{nb='>'	;	break;}

		case 198:	{nb='?';		break;}
		case 199:	{nb='?'	;	break;}
		case 200:	{nb='?'	;	break;}

		case 201:	{nb='@';		break;}
		case 202:	{nb='@'	;	break;}
		case 203:	{nb='@'	;	break;}

		case 204:	{nb='[';		break;}
		case 205:	{nb='['	;	break;}
		case 206:	{nb='['	;	break;}

		/*case 207:	{nb='\\';		break;}
		case 208:	{nb='\\'	;	break;}
		case 209:	{nb='\\'	;	break;}*/

		case 210:	{nb=']'	;	break;}
		case 211:	{nb=']';		break;}
		case 212:	{nb=']'	;	break;}

		case 213:	{nb='^';		break;}
		case 214:	{nb='^'	;	break;}
		case 215:	{nb='^'	;	break;}

		case 216:	{nb='_';		break;}
		case 217:	{nb='_'	;	break;}
		case 218:	{nb='_'	;	break;}

		case 219:	{nb='`';		break;}
		case 220:	{nb='`'	;	break;}
		case 221:	{nb='`'	;	break;}

		case 222:	{nb='{';		break;}
		case 223:	{nb='{'	;	break;}
		case 224:	{nb='{'	;	break;}

		case 225:	{nb='}';		break;}	
		case 226:	{nb='}'	;	break;}
		case 227:	{nb='}'	;	break;}

		case 228:	{nb='|';		break;}
		case 229:	{nb='|'	;	break;}
		case 230:	{nb='|'	;	break;}

		case 231:	{nb='~';		break;}
		case 232:	{nb='~'	;	break;}
		case 233:	{nb='~'	;	break;}

		case 234:	{nb='\'';		break;}
		case 235:	{nb='\''	;	break;}
		case 236:	{nb='\''	;	break;}

		case 249:	{nb='\n';		break;}
		case 250:	{nb='\n'	;	break;}
		case 251:	{nb='\n'	;	break;}

		}
		
		return nb;
	}
	
	//counts the number of characters inside a file
	public int count() throws IOException
	{
		Scanner fileScan = new Scanner(new File(FileName)),lineScan;
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

}
