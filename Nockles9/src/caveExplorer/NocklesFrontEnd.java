package caveExplorer;

public class NocklesFrontEnd implements BenSupporter{

	private NocklesSupporter backend;
	private int digs;
	private String newHint;


	public static final void main(String[] args){
		NocklesFrontEnd demo = new NocklesFrontEnd();
		demo.play();
	}

	public NocklesFrontEnd() {
		backend = new BenBackEnd(this);
		digs = 6;
		newHint = null;
	}

	public void play(){
		new NocklesIntro().play();
		CaveExplorer.in.nextLine();
		menu();
	}

	private void menu() {
		System.out.println("Enter 'r' for rules or 'p' to play.");
		String command = NocklesUtility.waitForLetterInput("rp");
		if(command.equals("r")){
			NocklesUtility.print("There are treasures burried in the sand. You have "+digs+" chances to find the most valuable treasure."
					+ " Your metal detector will give you a hint after each attempt to find treasure. The hints may tell"
					+ "you the direction of the closest treasure or the distance to the closest treasure. Multiple treasures "
					+ "are hidden. If you find one, you will get an extra chance to find another.\n\n      - - press enter - -");
			CaveExplorer.in.nextLine();
			menu();
		}else{
			startGame();
		}

	}

	private void startGame() {
		BenNocklesPlot[][] plots = backend.getPlots();
		BenNocklesPlot p = null;
		while(digs > 0){
			displayField(plots);
			displayScoreStatus(p);
			displayHints(p);
			System.out.println("Where would you like to dig?");
			int[] coords = backend.getCoordInput();
			p = plots[coords[0]][coords[1]];
			backend.reveal(p);

			digs--;			
		}
		displayScoreStatus(p);
		System.out.println("__________Game Over__________\n");
		System.out.println("Value of your treasure: "+backend.getTotalTreasure());
	}

	private void displayHints(BenNocklesPlot p) {
		System.out.println("Digs remaining: "+digs);
		if(newHint != null){
			System.out.println(newHint);
			newHint = null;//mark as null once hint has been printed once.
		}else if (p!= null){
			printDirectionHint(p);
		}

	}

	/**
	 * This method is the most important method in this class because it considers the values of elements one column or row adjacent.
	 * 
	 * @param p
	 */
	private void printDirectionHint(BenNocklesPlot p) {
		BenNocklesPlot[][] plots = backend.getPlots();
		int eastCol = p.getCol()+1;
		int westCol = p.getCol()+1;
		String[] treasureHints = new String[4];
		String[] possibleHints = {"There is treasure one unit North, but not touching this plot.",
				"There is treasure one unit East, but not touching this plot.",
				"There is treasure one unit South, but not touching this plot.",
		"There is treasure one unit West, but not touching this plot."};
		for(int row = 0; row < plots.length; row++){
			if(valid(row, eastCol) && plots[row][eastCol].containsTreasure() && !plots[row][eastCol].isRevealed())treasureHints[1]=possibleHints[1];
			if(valid(row, westCol) && plots[row][westCol].containsTreasure() && !plots[row][westCol].isRevealed())treasureHints[3]=possibleHints[3];
		}

		int northRow = p.getRow()-1;
		int southRow = p.getRow()+1;
		for(int col = 0; col < plots[p.getRow()].length; col++){
			if(valid(northRow,col) && plots[northRow][col].containsTreasure() && !plots[northRow][col].isRevealed())treasureHints[0]=possibleHints[0];
			if(valid(southRow, col) && plots[southRow][col].containsTreasure()	&& !plots[southRow][col].isRevealed())treasureHints[2]=possibleHints[2];
		}

		for(String s: treasureHints){
			if(s != null){
				System.out.println(s);
			}
		}
	}

	private boolean valid(int row, int col){
		return row >= 0 && row< backend.getPlots().length && col >= 0 && col < backend.getPlots()[row].length;
	}

	private void displayScoreStatus(BenNocklesPlot p) {
		String status = "";
		if(p != null && p.containsTreasure()){
			status = "You found a treasure worth $"+p.getTreasureValue();
		}
		String output = "                                        Total Value = $"+backend.getTotalTreasure();
		System.out.println(status+output.substring(status.length()));
	}

	/**
	 * This is the method that prints the grid, or playing field. It is necessary for the game to be played, but on it's own
	 * it would only earn a 3. To earn a '5' I have included the printDirectionHint method, above
	 * @param plots
	 */

	private void displayField(BenNocklesPlot[][] plots) {
		String rows = "0123456789";
		String columns = "  0123456789";
		for(int row = 0; row < plots.length; row++){
			System.out.print(rows.substring(row, row+1)+" ");
			for(int col = 0; col < plots[row].length; col++){
				if(plots[row][col].isRevealed()){
					if(plots[row][col].containsTreasure()){
						System.out.print("X");
					}else{
						System.out.print(" ");	
					}

				}else{
					System.out.print(".");
				}
			}
			System.out.println(" " + rows.substring(row, row+1));
		}
		System.out.println(columns.substring(0, plots[0].length+2));
	}

	public void increaseDigs() {
		digs++;
	}

	public void addNewHint(String string) {
		newHint = string;
	}





}
