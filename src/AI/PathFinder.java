package AI;

import java.util.ArrayList;

import entity.Entity;
import main.GamePanel;

public class PathFinder {
	//gp to access variables ni game panel
	GamePanel gp;
	//declare node as 2d array
	Node node[][];
	//arraylists to store paths and data
	ArrayList<Node> openList = new ArrayList<>();
	public ArrayList<Node> pathList = new ArrayList<>();
	//declare node roles
	Node startNode, goalNode, currentNode;
	//for stopping the pathfind
	boolean goalReached = true;
	int tryNum = 0;
	int maxTry = 200;
	public PathFinder(GamePanel gp) {
		this.gp = gp;
		
		makeNodes();
	}
	
	public void makeNodes() {
		node = new Node[gp.maxWorldCol][gp.maxWorldRow];
		int col = 0; int row = 0;
		
		//loop the 2d node array
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
			node[col][row] = new Node(col, row);
			col++;
			
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}
	public void resetNodes() {
		int col = 0; int row = 0;
		//loop the 2dArray and reset everything
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
			node[col][row].open = false;
			node[col][row].checked = false;
			node[col][row].solid = false;
			
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
		//reset the lists, booleans, and counter;
		openList.clear();
		pathList.clear();
		goalReached = false;
		tryNum = 0;
	}
	public void setNodes(int startCol, int startRow, int goalCol, int goalRow, Entity ent) {
		resetNodes();
		
		//set start and goal node
		startNode = node[startCol][startRow];
		currentNode = startNode;
		goalNode = node[goalCol][goalRow];
		openList.add(currentNode);
		
		//scan the 2d array to set the solid nodes
		int col = 0; int row = 0;
		
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
			//get the tile posindex
			int tilePosIndex = gp.tManager.mapTileNum[gp.currentMap][col][row];
	  		//set solid nodes based on world map tiles
			if(gp.tManager.tile[tilePosIndex].collisionOn) {
				node[col][row].solid = true; 
			}
	 		
			//set solid nodes based on the postions of interactivetiles
			//loop the IT_manager array
			for(int i = 0; i < gp.IT_Manager[1].length; i++) {
				if(gp.IT_Manager[gp.currentMap][i] != null && gp.IT_Manager[gp.currentMap][i].destroyOn) {
					int ITCol = gp.IT_Manager[gp.currentMap][i].worldX/gp.tileSize;
					int ITRow = gp.IT_Manager[gp.currentMap][i].worldY/gp.tileSize;
					node[ITCol][ITRow].solid = true; 
				}
			}
			getCost(node[col][row]);
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}
	public void getCost(Node node) {
		//Get gCost (the distance from the startNode to the currentNode)
		int distanceX = Math.abs(node.col - startNode.col) ;
		int distanceY = Math.abs(node.row - startNode.row);
		node.gCost = distanceX + distanceY;
		
		//Get the hCost (the distance from  the currentNode to the goalNode)
		distanceX = Math.abs(node.col - goalNode.col);
		distanceY = Math.abs(node.row - goalNode.row);
		node.hCost = distanceX + distanceY;
		
		//Get the fcost (the distance between startNode and the goalNode, basically, the totalcost)
		node.fCost = node.hCost + node.gCost;
	}
	public boolean autoSearch() {
		boolean result = false;
		
		while(!goalReached && tryNum < 200) {
			int col = currentNode.col;
			int row = currentNode.row;
			
			currentNode.checked = true;
			openList.remove(currentNode);
			
			//open the nodes UP, DOWN, LEFT, RIGHT, use if to guarantee that the nodes arent in negative positions or larger than the screen
			if(row-1 >= 0) openNode(node[col][row-1]); //UP
			if(row+1 < gp.maxWorldRow) openNode(node[col][row+1]); //DOWN
			if(col-1 >= 0) openNode(node[col-1][row]); //LEFT 
			if(col+1 < gp.maxWorldCol) openNode(node[col+1][row]); //RIGHT
			
			//find the best path
			int bestNodeIndex = 0;
			int bestNodefCost = 77777;
			//open the openlist
			for(int i = 0; i < openList.size(); i++) {
				//find the best path
				if(openList.get(i).fCost < bestNodefCost) {
					bestNodeIndex = i;
					bestNodefCost = openList.get(i).fCost;
				}
				// if the fCost is equal, compare gCost instead
				else if(openList.get(bestNodeIndex).fCost == bestNodefCost) {
					if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
						bestNodeIndex = i;
					}
				}
				
				//end the loop if the openlist is empty
				if(openList.size() == 0) break; 
				
				//we set the currentNode to the bestNode in the open list, meaning we proceed to the next best node
				currentNode = openList.get(bestNodeIndex);
				if(currentNode == goalNode) {
					goalReached = true;
					result = true;
					trackPath();
				}
				
			}
			//findBestPath();
			tryNum++;
			
		}
		return result;
	}
	public void openNode(Node node) {
		if(!node.open && !node.checked && !node.solid) {
			node.open = true;
			node.parent = currentNode;
			openList.add(node);
		}
	}
	public void findBestPath() {
		
	}
	public void trackPath() {
		Node current = goalNode;
		
		while(current != startNode) {
			pathList.add(0, current);
			current = current.parent;
			
		}
	}
}
