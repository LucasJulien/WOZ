import java.util.HashMap;
import java.util.*;

/**
 * Write a description of class Inventaire here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Inventory
{
    // instance variables - replace the example below with your own
    private Object objectName;
    private int quantity;
    protected HashMap<String,Room> keysFounded; // the user collect keys

    /**
     * Constructor for objects of class Inventaire
     */
    public Inventory()
    {
        keysFounded = new HashMap<String,Room>();
    }

    /**
     * Import the object in the inventory of the user
     */
    public void setKeys(String name, Room theRoom){
        keysFounded.put(name, theRoom);
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int getQuantity()
    {
        // put your code here
        return quantity;
    }
    
    /**
     * @return the HashMap
     */
    public HashMap getKeys(){
        return keysFounded;
    }
}
