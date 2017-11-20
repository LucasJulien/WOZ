import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.*;
import java.util.Map.Entry;

/**
 * Write a description of class Listener here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Listener extends JFrame implements MouseListener
{
    private MainGame mg;

    /**
     * Constructor for objects of class Listener
     */
    public Listener(MainGame m)
    {
        mg = m;
    }

    //Méthode appelée lors du clic de la souris
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().equals(mg.getstart())){
            mg.img.setIcon(mg.geticonFirstFloor());
            mg.bottomPane.setVisible(true);
            mg.remove(mg.getstart());
            mg.add(mg.getstop(),BorderLayout.EAST);
            mg.pack();
            return;
        }
        else if(e.getSource().equals(mg.getstop())){
            mg.img.setIcon(mg.getbeginning());
            mg.bottomPane.setVisible(false);
            mg.remove(mg.getstop());
            mg.add(mg.getstart(),BorderLayout.EAST);
            mg.pack();
            return;
        } 
        else if(e.getSource().equals(mg.getresearch())){
            if(!(mg.currentRoom.getNameKey().equals(""))){
                mg.theUser.inventaire.getKeys().put(mg.currentRoom.getNameKey(), mg.currentRoom);//keysFounded.put(mg.currentRoom.getNameKey(), mg.currentRoom);
                mg.insertS("\nYou find a key !");
            };
        }

        if(e.getSource().equals(mg.getupArrowB())){
            mg.direction = "up";
        }
        else if(e.getSource().equals(mg.getdownArrowB())){
            mg.direction = "down";
        }
        else if(e.getSource().equals(mg.getleftArrowB())){
            mg.direction = "left";
        }
        else if(e.getSource().equals(mg.getrightArrowB())){
            mg.direction = "right";
        } 
        else if(e.getSource().equals(mg.getForwardArrowB())){
            mg.direction = "forward";
        }
        else if(e.getSource().equals(mg.getBackwardArrowB())){
            mg.direction = "backward";
        }else return;

        Room nextRoom = null;
        // Set the value of the nextRoom
        nextRoom = mg.getValue(nextRoom, mg.direction);
        // if the nextRoom is locked by a key, we control if the user
        // has the correct key to open it.
        if(nextRoom != null && nextRoom.getIsOpen() == false){
            Set<Entry<String, Room>> setEntryIcon = mg.theUser.inventaire.getKeys().entrySet();//keysFounded.entrySet();
            Iterator<Map.Entry<String, Room>> itEntryIcon = setEntryIcon.iterator();
            // Loop which defined the nextRoom 
            while(itEntryIcon.hasNext()){
                Map.Entry<String, Room> entryIcon = itEntryIcon.next();
                if(nextRoom.getNameKey().equals(entryIcon.getKey()))
                {
                    nextRoom.setIsOpen();
                }
            }
        }

        if(nextRoom != null && nextRoom.getIsOpen() == false){
            mg.insertS("\nYou don't have the key to enter!");
            return;
        }
        // Set the right image to the correct Room
        // If nameKey in class Room is different of "" so we need to control is the room is opened
        if(nextRoom != null){ 
            mg.getImage(nextRoom);
        }

        // Inform the user and change the currentRoom
        if (nextRoom == null) {
            mg.insertS("\nThere is no door!");
        }
        else {
            mg.currentRoom = nextRoom;
        }

        //Add the key to the frame if the research by the user has been done
        mg.displayObjects();
    }

    //Méthode appelée lors du survol de la souris
    public void mouseEntered(MouseEvent e) { }

    //Méthode appelée lorsque la souris sort de la zone du bouton
    public void mouseExited(MouseEvent e) { }

    //Méthode appelée lorsque l'on presse le bouton gauche de la souris
    public void mousePressed(MouseEvent e) { }

    //Méthode appelée lorsque l'on relâche le clic de souris
    public void mouseReleased(MouseEvent e) { } 
}
