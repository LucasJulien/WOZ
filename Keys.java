import java.util.HashMap;
import java.util.*;
import java.util.Iterator;
import java.awt.*;
import javax.swing.*;

/**
 * Write a description of class Keys here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Keys
{
    // instance variables - replace the example below with your own
    private boolean canOpenDoor;
    private String nameIcon;

    /**
     * Constructor for objects of class Keys
     */
    public Keys()
    {
        canOpenDoor = false;
        nameIcon = "";
    }    

    /**
     * 
     */
    public void setNameIcon(String nameOfIcon){
        nameIcon = nameOfIcon;
    }

    /**
     * Define the value of the boolean CanOpenDoor
     * true : if this key can open a specific door
     */
    public void setCanOpenDoor()
    {
        canOpenDoor = true;
    }

    /**
     * getNameIcon()
     * @return the name of the icon (for a key)
     */
    public String getNameIcon(){
        return nameIcon;
    }

    /**
     * getCanOpenDoor()
     * @return true is the key is able to open a specific door
     */
    public boolean getCanOpenDoor()
    {
        // put your code here
        return canOpenDoor;
    }
}
