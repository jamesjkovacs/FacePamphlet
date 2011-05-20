/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		// You fill this in
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		GLabel message = new GLabel(msg);
		message.setFont(MESSAGE_FONT);
		add(message, (getWidth() - message.getWidth()) / 2, getHeight() - BOTTOM_MESSAGE_MARGIN);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		GLabel name = new GLabel(profile.getName());
		name.setFont(PROFILE_NAME_FONT);
		name.setColor(Color.BLUE);
		add(name, LEFT_MARGIN, name.getHeight() + TOP_MARGIN);
		if(profile.getImage() == null){
			GRect noPicture = new GRect(IMAGE_WIDTH, IMAGE_HEIGHT);
			add(noPicture, LEFT_MARGIN, IMAGE_MARGIN);
			add(new GLabel("No Image"), LEFT_MARGIN, IMAGE_MARGIN);
		}
		else{
			profile.getImage().scale(IMAGE_WIDTH, IMAGE_HEIGHT);
			add(profile.getImage(), LEFT_MARGIN, IMAGE_MARGIN + name.getHeight() + TOP_MARGIN)  ;
		}
	}
	
}
