package attendance;

public class School implements Topic {

	private boolean inSchoolLoop;
	private String schoolResponse;
	
	public School(){
		
	}
	
	public void talk() {
		inSchoolLoop = true;
		while(inSchoolLoop){
			NocklesMain.print("Tell me about school.");
			schoolResponse = NocklesMain.getInput();
			if(schoolResponse.indexOf("stop")>= 0){
				inSchoolLoop =false;
				NocklesMain.chatbot.talkForever();
			}else{
				NocklesMain.print("That's my favorite "
						+ "part about school too.");
				if(NocklesMain.chatbot.getLike().isUserLikeStored()) {
					NocklesMain.print("If only school had more "+NocklesMain.chatbot.getLike().getThingsLiked()+", AM I RITE?");
				}
				
			}
			
		}
	}

	public boolean isTriggered(String userInput) {
//		String[] triggers = {"schhol","class","teacher"};
		//you could use a for loop
		//to iterate through an array
		if(NocklesMain.findKeyword(userInput, "school", 0) 
				>= 0 ){
			return true;
		}
		if(NocklesMain.findKeyword(userInput, "class", 0) 
				>= 0 ){
			return true;
		}
		return false;
	}
}























