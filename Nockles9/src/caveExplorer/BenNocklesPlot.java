package caveExplorer;

public class BenNocklesPlot {

	private boolean revealed;
	private boolean containsTreasure;
	private int treasureValue;
	private int row;
	private int col;
	
	public BenNocklesPlot(int row, int col) {
		revealed = false;
		containsTreasure = false;
		treasureValue = 0;
		this.row = row;
		this.col = col;
	}
	
	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public void reveal(){
		revealed = true;
	}

	public boolean isRevealed() {
		return revealed;
	}

	public boolean containsTreasure() {
		return containsTreasure;
	}

	public void setContainsTreasure(boolean containsTreasure) {
		this.containsTreasure = containsTreasure;
	}

	public int getTreasureValue() {
		return treasureValue;
	}

	public void setTreasureValue(int treasureValue) {
		this.treasureValue = treasureValue;
	}

	
	
}
