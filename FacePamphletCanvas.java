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
		message = new GLabel("");
		message.setFont(MESSAGE_FONT);
		add(message, (getWidth() - message.getWidth()) / 2, getHeight() - BOTTOM_MESSAGE_MARGIN);
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		message.setLabel(msg);
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
		name = new GLabel(profile.getName());
		name.setFont(PROFILE_NAME_FONT);
		name.setColor(Color.BLUE);
		add(name, LEFT_MARGIN, name.getHeight() + TOP_MARGIN);
		
		DisplayImage(profile, name);
		
		String stringStatus ;
		if(profile.getStatus() == "")
			stringStatus = "No Current Status";
		else
			stringStatus = profile.getName() + " is " + profile.getStatus();
			
		GLabel status = new GLabel(stringStatus);
		status.setFont(PROFILE_STATUS_FONT);
		add(status, LEFT_MARGIN, IMAGE_MARGIN + name.getHeight() + TOP_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN + status.getHeight());
	
		DisplayFriends(profile);
	}
	
	private void DisplayImage(FacePamphletProfile profile, GLabel name){
		if(profile.getImage() == null){
			GRect noPicture = new GRect(IMAGE_WIDTH, IMAGE_HEIGHT);
			add(noPicture, LEFT_MARGIN, IMAGE_MARGIN + name.getHeight() + TOP_MARGIN);
			GLabel noImage = new GLabel ("No Image");
			add(noImage, LEFT_MARGIN + (IMAGE_WIDTH - noImage.getWidth())/2, IMAGE_MARGIN + name.getHeight() + TOP_MARGIN + (IMAGE_HEIGHT/2));
		}
		else{
			GImage display = profile.getImage();
			display.scale(IMAGE_WIDTH, IMAGE_HEIGHT);
			display.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
			add(display, LEFT_MARGIN, IMAGE_MARGIN + name.getHeight() + TOP_MARGIN)  ;
		}
	}
	private void DisplayFriends(FacePamphletProfile profile){
		GLabel header = new GLabel("Friends:");
		header.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(header, (getWidth() - header.getWidth()) / 2, IMAGE_MARGIN + name.getHeight() + TOP_MARGIN);
		Iterator<String> friendList = profile.getFriends();
		double friendLocY = IMAGE_MARGIN + name.getHeight() + TOP_MARGIN;
		while(friendList.hasNext()){
			GLabel friend = new GLabel(friendList.next());
			friendLocY += friend.getHeight();
			add(friend, (getWidth() - header.getWidth()) / 2, friendLocY);
		}
	}
	private GLabel name; 
	GLabel message;
}
