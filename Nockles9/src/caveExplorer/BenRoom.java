package caveExplorer;

public class BenRoom extends NPCRoom {

	public BenRoom(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}
	
	public String validKeys() {
		return "wdsaer";
	}
	
	public void printAllowedEntry() {
		CaveExplorer.print("You can only enter 'w', 'a', 's' or 'd' to move or"
				+ " you can type 'e' to interact.");
	}
	
	
	public void performAction(int direction) {
		if(direction == 5) {
			//do new action
		}else {
			super.performAction(direction);
		}
	}
	
	public String getContents() {
		return 
	}
	
	public String getDescription() {
		if(containsNPC() && !presentNPC.isActive()) {
			return super.getDescription() +"\n"+presentNPC.getInactiveDescription();
		}else {
			String npcDesc = "";
			if(presentNPC != null) {
				npcDesc = presentNPC.getActiveDescription();
			}
			return super.getDescription() + "\n"+npcDesc;
		}
	}
	

}
