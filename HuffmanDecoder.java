import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class HuffmanDecoder {
	private HashMap <String, Character> binaryCharacters  = new HashMap <String, Character>();
	private PrintWriter p;

	public HuffmanDecoder(String codeFile) throws FileNotFoundException {
		int i=0;
		Scanner scanner1 = new Scanner (new File (codeFile));
		 while (scanner1.hasNextLine()) {
			String line = scanner1.nextLine();
			if (line.equals("")) {
				
			} else {
				binaryCharacters.put(line, (char)(i));
			}
			i++;
		}
				 scanner1.close();
	}
				 public boolean isCode(String binary) {
					 return binaryCharacters.containsKey(binary);
				 }
				 public char decodeChar(String binary) {
					 return binaryCharacters.get(binary);

				 }
				 public void decodeLong(String encodedFile, String decodedFile) throws IOException {
						BufferedReader br = new BufferedReader (new FileReader (encodedFile));
						String current="";
						 p= new PrintWriter(decodedFile);
						 while (br.ready()) {
							char c = (char) br.read();
							current=current+c;
							if (isCode(current)) {
								p.print(decodeChar(current));
								current="";
							}
						}
						br.close(); 
						p.close();
				 }
				 public void decodeFile(String encodedFile) throws IllegalArgumentException, IOException {
					if (encodedFile.substring(encodedFile.length()-4).equals(".huf")==false) {	
					 throw new IllegalArgumentException();
					}
					BufferedReader br = new BufferedReader (new FileReader (encodedFile));
					char prevChar;
					char currentChar;
					prevChar=(char)br.read();
					 p= new PrintWriter("decodeIntermediate.txt");

					while (br.ready()) {
						currentChar=(char) br.read();
						if (br.ready()) {
							String binary=Integer.toBinaryString((int)prevChar);
							while (binary.length()<8) {
								binary = "0"+binary;
							}
							p.print(binary);
						}else {
							int cut=(int)currentChar;
							String binary=Integer.toBinaryString((int)prevChar);
							while (binary.length()<8) {
								binary = "0"+binary;
							}
							p.print(binary.substring(0, 8-cut));
						}
						prevChar=currentChar;
					}
					p.close();
					br.close();
					decodeLong("decodeIntermediate.txt",encodedFile.substring(0,encodedFile.length()-4));

				 }
	}
	
