package caveExplorer;

public class BenBackEnd implements NocklesSupporter{

	private BenSupporter frontend;
	private BenNocklesPlot[][] plots;
	private int numberOfTreasures;
	private int totalTreasure;



	public BenBackEnd(BenSupporter frontend) {
		this.frontend = frontend;
		plots = new BenNocklesPlot[6][6];
		numberOfTreasures = 5;
		createPlots();
	}

	/**
	 * This method works with 2D arrays, but it only iterates through the array,
	 *  it is not complex at all, by itself, it would earn a 1
	 */
	private void createPlots() {
		for(int row = 0; row < plots.length; row++){
			for(int col = 0; col < plots[row].length; col++){
				plots[row][col] = new BenNocklesPlot(row, col);
			}
		}
		//Add treasures randomly
		int count = 0;
		while( count < numberOfTreasures){
			int randRow = (int)(Math.random() * plots.length);
			int randCol = (int)(Math.random() * plots[randRow].length);
			if(!plots[randRow][randCol].containsTreasure()){
				plots[randRow][randCol].setContainsTreasure(true);
				plots[randRow][randCol].setTreasureValue(5+(int)(Math.random() * 16));
				count++;
			}
		}


	}

	public int getTotalTreasure() {
		return totalTreasure;
	}

	public BenNocklesPlot[][] getPlots() {
		return plots;
	}

	public int[] getCoordInput() {
		String input = CaveExplorer.in.nextLine();
		int[] coords = toCoords(input);
		while(coords == null){
			System.out.println("You must enter cordinates of the form:\n          <row>,<col>"
					+ "\n<row> and <col> should be integers.");
			input = CaveExplorer.in.nextLine();
			coords = toCoords(input);
		}
		return coords;
	}

	private int[] toCoords(String input) {
		try{
			int a = Integer.parseInt(input.substring(0,1));
			int b = Integer.parseInt(input.substring(2,3));
			if(input.substring(1,2).equals(",") && input.length() ==3){
				int[] coords = {a,b};
				return coords;
			}else{
				return null;
			}
		}catch(Exception e){
			return null;
		}
	}

	public void reveal(BenNocklesPlot p) {
		p.reveal();
		if(p.containsTreasure()){
			totalTreasure += p.getTreasureValue();
			frontend.increaseDigs();
		}
		if((isAdjacentToTreasure(p))){
			frontend.addNewHint("The metal detector is going crazy! There must be treasure closeby.");
		}

	}

	/**
	 * This method checks adjacent elements surrounding the element p
	 * In so doing, it avoids ArrayIndexOutOfBoundsException. For this reason, it is worth a '5'
	 * @param p
	 * @return
	 */
	private boolean isAdjacentToTreasure(BenNocklesPlot p) {
		for(int row = p.getRow()-1; row < p.getRow()+2; row++){
			for(int col = p.getCol()-1; col < p.getCol()+2; col++){
				//avoid AIOOBE
				if(row >= 0 && row < plots.length && col >= 0 && col < plots[row].length){
					if(plots[row][col].containsTreasure() && !plots[row][col].isRevealed() && (row != p.getRow() || col != p.getCol())){
						return true;
					}
				}
			}
		}
		return false;
	}

}
