/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 * This is a test to see if git works in exlipse.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {
//public class FacePamphlet extends ConsoleProgram 
//					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {

		//North interactors
		add(new JLabel("Name"), NORTH);
		tName = new JTextField(TEXT_FIELD_SIZE);
		add(tName, NORTH);
		bAdd = new JButton("Add");
		add(bAdd, NORTH);
		bDelete = new JButton("Delete");
		add(bDelete, NORTH);
		bLookup = new JButton("Lookup");
		add(bLookup, NORTH);
		
		//West interactors
		tStatus = new JTextField(TEXT_FIELD_SIZE);
		add(tStatus, WEST);
		bStatus = new JButton("Change Status");
		add(bStatus, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST); //create a space
		tPicture = new JTextField(TEXT_FIELD_SIZE);
		add(tPicture, WEST);
		bPicture = new JButton("Change Picture");
		add(bPicture, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST); //create a space
		tFriend = new JTextField(TEXT_FIELD_SIZE);
		add(tFriend, WEST);
		bFriend = new JButton("Add Friend");
		add(bFriend, WEST);
		
		addActionListeners();
		tStatus.addActionListener(this);
		tPicture.addActionListener(this);
		tFriend.addActionListener(this);
		
		pDatabase = new FacePamphletDatabase();
		
		canvas = new FacePamphletCanvas();
		add(canvas);
		
		currentProfile = null;
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bAdd){
			if (pDatabase.containsProfile(tName.getText())){
				canvas.showMessage(tName.getText() + " is already a valid profile");
			}
			else{
				pDatabase.addProfile(new FacePamphletProfile(tName.getText()));
				currentProfile = pDatabase.getProfile(tName.getText());
				canvas.showMessage("Add: " + currentProfile.toString());
			}
		}
		else if (e.getSource() == bDelete){
			if (pDatabase.containsProfile(tName.getText())){
				pDatabase.deleteProfile(tName.getText());
				currentProfile = null;
				canvas.showMessage("Delete: " + tName.getText());
			}
			else
				canvas.showMessage(tName.getText() + " is not a valid profile");
		}
		else if (e.getSource() == bLookup){
			if (pDatabase.containsProfile(tName.getText())){
				pDatabase.getProfile(tName.getText());
				currentProfile = pDatabase.getProfile(tName.getText());
				canvas.displayProfile(currentProfile);
				canvas.showMessage("Lookup: " + currentProfile.toString());
			}
			else
				canvas.showMessage(tName.getText() + " is not a valid profile");
				
		}
		else if (e.getSource() == tStatus || e.getSource() == bStatus){
			if(currentProfile == null)
				canvas.showMessage("Select a profile");
			else{
				currentProfile.setStatus(tStatus.getText());
				canvas.showMessage("Change Status: " + currentProfile.getStatus());
			}
		}
			else if (e.getSource() == tPicture || e.getSource() == bPicture){
			if(currentProfile == null)
				canvas.showMessage("Select a profile");
			else {
				GImage image = null;
				try {
					image = new GImage(tPicture.getText());
				}catch (ErrorException ex){
					canvas.showMessage("Could not find picture: " + tPicture.getText());
				}
				currentProfile.setImage(image);
			}
			}
		else if (e.getSource() == tFriend || e.getSource() == bFriend){
			if(currentProfile == null)
				canvas.showMessage("Select a profile");
			else if (pDatabase.containsProfile(tFriend.getText())){
				if(currentProfile.addFriend(tFriend.getText())){
					pDatabase.getProfile(tFriend.getText()).addFriend(currentProfile.getName());
					canvas.showMessage("Added friend: " + tFriend.getText());
				}	
				else{
					canvas.showMessage(tFriend.getText() + " is already a friend");
				}
			}
			else{
				canvas.showMessage(tFriend.getText() + " is not a valid profile");
			}
				
		}
	}
    	
/*    	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bAdd){
			if (pDatabase.containsProfile(tName.getText())){
				println(tName.getText() + " is already a valid profile");
			}
			else{
				pDatabase.addProfile(new FacePamphletProfile(tName.getText()));
				currentProfile = pDatabase.getProfile(tName.getText());
				println("Add: " + currentProfile.toString());
			}
		}
		else if (e.getSource() == bDelete){
			if (pDatabase.containsProfile(tName.getText())){
				pDatabase.deleteProfile(tName.getText());
				currentProfile = null;
				println("Delete: " + tName.getText());
			}
			else
				println(tName.getText() + " is not a valid profile");
		}
		else if (e.getSource() == bLookup){
			if (pDatabase.containsProfile(tName.getText())){
				pDatabase.getProfile(tName.getText());
				currentProfile = pDatabase.getProfile(tName.getText());
				println("Lookup: " + currentProfile.toString());
			}
			else
				println(tName.getText() + " is not a valid profile");
				
		}
		else if (e.getSource() == tStatus || e.getSource() == bStatus){
			if(currentProfile == null)
				println("Select a profile");
			else{
				currentProfile.setStatus(tStatus.getText());
				println("Change Status: " + currentProfile.getStatus());
			}
		}
 		else if (e.getSource() == tPicture || e.getSource() == bPicture){
			if(currentProfile == null)
				println("Select a profile");
			else {
				GImage image = null;
				try {
					image = new GImage(tPicture.getText());
				}catch (ErrorException ex){
					println("Could not find picture: " + tPicture.getText());
				}
				currentProfile.setImage(image);
			}
 		}
		else if (e.getSource() == tFriend || e.getSource() == bFriend){
			if(currentProfile == null)
				println("Select a profile");
			else if (pDatabase.containsProfile(tFriend.getText())){
				if(currentProfile.addFriend(tFriend.getText())){
					pDatabase.getProfile(tFriend.getText()).addFriend(currentProfile.getName());
					println("Added friend: " + tFriend.getText());
				}	
				else{
					println(tFriend.getText() + " is already a friend");
				}
			}
			else{
				println(tFriend.getText() + " is not a valid profile");
			}
				
		}
	}
*/
    private JTextField tName;
    private JButton bAdd, bDelete, bLookup;
    private JTextField tStatus, tPicture, tFriend;
    private JButton bStatus, bPicture, bFriend;
    private FacePamphletDatabase pDatabase;
    private FacePamphletProfile currentProfile;
    private FacePamphletCanvas canvas;
}
