import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class HuffmanEncoder {
	String[]codes=new String[128];
	private PrintWriter p;

	 public HuffmanEncoder(String codeFile) throws FileNotFoundException {
			Scanner scanner1 = new Scanner (new File (codeFile));
			for (int i=0; i<128; i++) {
				codes[i]=scanner1.nextLine();
			}
			scanner1.close();
	 }
	 public String encodeChar(char input) {
		 return codes[((input))];
	 }
	 public void encodeLong(String inputFile, String outputFile) throws IOException {
		 BufferedReader br = new BufferedReader (new FileReader (inputFile));
		 p= new PrintWriter(outputFile);
			while (br.ready()) {
				char c = (char) br.read();
				p.print (codes[c]);
			}
			br.close(); 
			p.close();
	 }
	 public void encodeFile(String fileToCompress) throws IOException {
		 encodeLong (fileToCompress, "intermediate.txt");
		 BufferedReader br = new BufferedReader (new FileReader ("intermediate.txt"));
		 p= new PrintWriter(fileToCompress + ".huf");
		 boolean end = false;
		 String current="";
		 int padded=0;
		 while (br.ready()) {
			 for(int i=0; i<8; i++){
				 if (br.ready()) {
				char c = (char) br.read();
				current=current+c;
				 }
				 else {
					 current=current+"0";
					 padded++;
					 end=true;
				 }
			 }
			 p.print((char)Integer.parseInt(current, 2));
			 current="";
			}
		 p.print((char)(padded));
		 p.close();
		 br.close();
	 }
	}
