package AI;

public class Node {
	Node parent;
	public int col, row;
	int gCost;
	int hCost;
	int fCost;
	boolean solid, open, checked;

	public Node(int col, int row) {
		this.col = col;
		this.row = row;
	}
}
