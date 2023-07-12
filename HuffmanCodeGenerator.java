import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Map.Entry;
public class HuffmanCodeGenerator {
	HuffmanNode root;
	HashMap <Character, String> codes;
	private PrintWriter p;
	HashMap <Character, Integer>charOccurances = new HashMap<Character, Integer>();
	PriorityQueue<HuffmanNode> nodes= new PriorityQueue<HuffmanNode>();
	public HuffmanCodeGenerator(String inputFile) throws IOException {
		BufferedReader br = new BufferedReader (new FileReader (inputFile));
		while (br.ready()) {
			char c = (char) br.read();
			if (charOccurances.containsKey(c)) {
				Integer prev = charOccurances.get(c);
				charOccurances.replace(c, prev+1);
			} else {
				charOccurances.put(c,1);
			}
		}
		br.close(); 
		createHuffmanTree();
		codes=createCodes(root);
	}
	public int getFrequency(char c) {
		if (charOccurances.containsKey(c)==false) {
			return 0;
		} 
		return charOccurances.get(c);
	}

	public void createHuffmanTree() {
		for (Character k:charOccurances.keySet()) {
			int occurances = charOccurances.get(k);
			HuffmanNode newNode = new HuffmanNode(k, occurances);
			nodes.add(newNode);
		}
		while (nodes.size()>1) {
			HuffmanNode node1 = nodes.poll();
			HuffmanNode node2 = nodes.poll();
			HuffmanNode newNode = new HuffmanNode(null, (node1.getOccurances()+ node2.getOccurances()));
			newNode.setLeft(node1);
			newNode.setRight (node2);
			nodes.add(newNode);
		}
		root = nodes.poll();
		
		
		

	}
	
	public HashMap <Character, String> createCodes(HuffmanNode node){
		if (node==null){
			return new HashMap <Character,String>();
		}else if (node.getLeft()==null) {
			HashMap <Character, String> created= new HashMap<Character, String>();
			created.put (node.getCharacter(),"");
			return created;
		} else {
			HashMap <Character, String> created= new HashMap  <Character, String>();
			HashMap <Character, String> first= createCodes(node.getLeft());
			HashMap <Character, String> second= createCodes(node.getRight());
			for (Character k:first.keySet()) {
				String code = first.get(k);
				created.put(k, "0"+code);
			}
			for (Character k:second.keySet()) {
				String code = second.get(k);
				created.put(k, "1"+code);
			}
			return created;
		}
	}
	public void makeCodeFile(String codeFile) throws FileNotFoundException {
		 p= new PrintWriter(codeFile);
		 for (int i=0; i<128; i++) {
			 if (codes.containsKey((char)i)==false) {
					p.print ("\n");
				} else {
					p.print(codes.get((char)i));
					p.print("\n");
				}
		 }
		 p.close();
	}
	public String getCode (char c) {
		return (String) codes.get(c);
	}
}
