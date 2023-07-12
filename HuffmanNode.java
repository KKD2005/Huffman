
public class HuffmanNode implements Comparable <HuffmanNode>{
	private Character c;
	private Integer occurances;
	private HuffmanNode left;
	private HuffmanNode right;
	public HuffmanNode(Character c, Integer i) {
	    this.c=c;
	    occurances =i;
	    this.left = null;
		this.right = null;
	  }
	public Character getCharacter() {
		return c;
	}
	public void setCharacter(Character c) {
		this.c = c;
	}
	public Integer getOccurances() {
		return occurances;
	}
	public void setOccurances(Integer occurances) {
		this.occurances=occurances;
	}
	public HuffmanNode getLeft() {
		return left;
	}
	public void setLeft(HuffmanNode left) {
		this.left = left;
	}
	public HuffmanNode getRight() {
		return right;
	}
	public void setRight(HuffmanNode right) {
		this.right = right;
	}
	public boolean hasChildren() {
		return left != null || right != null;
	}
	public String toString() {
		return c.toString();
	}
	
	public int compareTo(HuffmanNode other) {
		// TODO Auto-generated method stub
		return occurances-(other).getOccurances();
	}
	
	
}
