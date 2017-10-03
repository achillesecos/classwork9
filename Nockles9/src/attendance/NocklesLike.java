package attendance;

public class NocklesLike implements Topic{

	private boolean inLikeLoop;
	private String likeResponse;
	private String thingsLiked;
	private boolean userLikeStored;
	
	
	public NocklesLike() {
		userLikeStored = false;
		
	}
	
	
	
	public String getThingsLiked() {
		return thingsLiked;
	}



	public boolean isUserLikeStored() {
		return userLikeStored;
	}



	public void talk() {
		NocklesMain.print("What are some things you like?");
		inLikeLoop = true;
		while(inLikeLoop){
			likeResponse = NocklesMain.getInput();
			int likePsn = NocklesMain.findKeyword(likeResponse, 
					"like", 
					0);
			if( likePsn >= 0 ){
				thingsLiked = 
						likeResponse.substring(likePsn+5);
				userLikeStored = true;
				NocklesMain.print("You are such an "
						+ "interesting"
						+ " person, because you like "+thingsLiked+".");
				if(NocklesMain.findKeyword(thingsLiked, 
						"school", 0) >= 0){
					inLikeLoop = false;
					NocklesMain.chatbot.getSchool().talk();
				}else{
					inLikeLoop = false;
					NocklesMain.chatbot.talkForever();	
				}
				
			}else{
				NocklesMain.print("I don't understand you.");
			}
		}
		
	}

	public boolean isTriggered(String userInput) {
		if(NocklesMain.findKeyword(userInput, "like", 0) 
				>= 0 ){
			return true;
		}
		return false;
	}
	
}









