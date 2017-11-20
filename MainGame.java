import java.util.HashMap;
import java.util.*;
import java.util.Map.Entry;
import java.util.Iterator;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.*;
import javax.swing.border.*;
import javax.swing.text.*;

/**
 * This class set going the game with the JFrame
 * 
 *
 * @author Grp7
 * @version Nov_Dec 2017
 */
public class MainGame extends JFrame
{
    protected JLabel img, informations, displayObjectsFound; // img -> display the current picture
        // display -> display the informations
        // displayObjectsFound -> display each object you get back 
    private ImageIcon Beginning, objectsIcon, iconFirstFloor; // beginning -> first picture to begin the game
        // objectsIcon -> picture for display each object
        // iconFirstFloor -> need to be declare here to return the icon
    private ImageIcon upArrow, downArrow, aheadArrow, behindArrow, leftArrow, rightArrow, pointCentered;
        // Each picture in the keyPad
    private JButton upArrowB, downArrowB, aheadArrowB, behindArrowB,leftArrowB, rightArrowB, pointCenteredB;
        // Each button associated to icons
    private JButton research ,nextImg, start, stop;
        // Most important buttons
    protected Personnages theUser; // The user
    protected Room currentRoom, control; // currentRoom -> room where the user is // control -> Room where the user wants to go
    protected String direction;
    private Listener theListener; // Call the class to listen the user interaction
    private Menu theMenu;
    protected JTextArea boxText; // Box where informations are displayed
    protected Document doc; // All the informations displayed to the user 
    protected Container bottomPane, objectsFound; // Container for the keypad and the objects found
    protected JScrollPane pa; // ScrollPane for the informations displayed to the user
    
    public static void main(){
        MainGame m = new MainGame();
    }

    /**
     * Constructor for objects of class Main
     */
    public MainGame()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit all
        this.setLayout(new BorderLayout()); // The FlowLayout class puts components in a row, sized at their preferred size
        this.setTitle("Φ SWAG STORY");
        String nameUser = JOptionPane.showInputDialog("What is your name ?");
        theUser = new Personnages(nameUser);
        theListener = new Listener(this);
        bottomPane = new Container();
        Container keyPad = new Container();
        Container keyPadUpDown = new Container();
        Container upOrDown = new Container();
        JPanel backgroundObjectsFound = new JPanel();
        backgroundObjectsFound.setBackground(new Color(243,243,243));
        objectsFound = new Container();

        // The display
        bottomPane.setLayout(new BorderLayout());
        keyPad.setLayout(new BorderLayout());
        keyPadUpDown.setLayout(new BorderLayout());
        upOrDown.setLayout(new BorderLayout());
        objectsFound.setLayout(new FlowLayout());
        informations = new JLabel("Bienvenue au Φ SWAG STORY",JLabel.CENTER);

        // Display AREA
        JEditorPane output = new JEditorPane();
        output.setEditable(false);
        doc = output.getDocument();
        insertS("Bonjour " + nameUser + " !\nBienvenue au Φ SWAG STORY\n");
        pa = new JScrollPane(output); // Scroll 
        pa.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        pa.setPreferredSize(new Dimension(0,50));
        
        createRooms();

        // The first display to begin
        Beginning = new ImageIcon("Ecran_accueil.png");

        // Button start & stop & research
        start =  new JButton("START");
        // start.setToolTipText("You want to begin ?");
        stop = new JButton("STOP");
        research = new JButton("Do the research !");

        // KeyPad_ Arrows
        upArrow = new ImageIcon("Arrows/arrowUp.gif");
        downArrow = new ImageIcon("Arrows/arrowDown.gif");
        leftArrow = new ImageIcon("Arrows/arrowWest.gif");
        rightArrow = new ImageIcon("Arrows/arrowEast.gif");
        pointCentered = new ImageIcon("Arrows/pointCentered.gif");
        aheadArrow = new ImageIcon("Arrows/arrowAhead.gif");
        behindArrow = new ImageIcon("Arrows/arrowBehind.gif");
        
        // Associated Buttons
        upArrowB = new JButton(upArrow);
        downArrowB = new JButton(downArrow);
        leftArrowB = new JButton(leftArrow);
        rightArrowB = new JButton(rightArrow);
        pointCenteredB = new JButton(pointCentered);
        aheadArrowB = new JButton(aheadArrow);
        behindArrowB = new JButton(behindArrow);
        
        // Buttons added to the container keyPad
        keyPad.add(aheadArrowB,BorderLayout.NORTH);
        keyPad.add(behindArrowB,BorderLayout.SOUTH);
        keyPad.add(leftArrowB,BorderLayout.WEST);
        keyPad.add(rightArrowB,BorderLayout.EAST);
        keyPad.add(pointCenteredB,BorderLayout.CENTER);
        upOrDown.add(upArrowB,BorderLayout.NORTH);
        upOrDown.add(downArrowB,BorderLayout.SOUTH);
        keyPadUpDown.add(keyPad,BorderLayout.WEST);
        keyPadUpDown.add(upOrDown,BorderLayout.CENTER);
        
        // Best display of these buttons
        upArrowB.setBackground(new Color(0,0,0,0));
        upArrowB.setContentAreaFilled(false);
        upArrowB.setBorderPainted(false);
        downArrowB.setBackground(new Color(0,0,0,0));
        downArrowB.setContentAreaFilled(false);
        downArrowB.setBorderPainted(false);
        leftArrowB.setBackground(new Color(0,0,0,0));
        leftArrowB.setContentAreaFilled(false);
        leftArrowB.setBorderPainted(false);
        rightArrowB.setBackground(new Color(0,0,0,0));
        rightArrowB.setContentAreaFilled(false);
        rightArrowB.setBorderPainted(false);
        pointCenteredB.setBorderPainted(false);
        pointCenteredB.setBackground(new Color(0,0,0,0));
        pointCenteredB.setContentAreaFilled(false);
        aheadArrowB.setBorderPainted(false);
        aheadArrowB.setBackground(new Color(0,0,0,0));
        aheadArrowB.setContentAreaFilled(false);
        aheadArrowB.setVerticalAlignment(SwingConstants.BOTTOM);
        behindArrowB.setBorderPainted(false);
        behindArrowB.setBackground(new Color(0,0,0,0));
        behindArrowB.setContentAreaFilled(false);
        behindArrowB.setVerticalAlignment(SwingConstants.TOP);
        
        //Bottom of the screen in the container bottomPane (under the picture)
        bottomPane.add(objectsFound, BorderLayout.NORTH);
        bottomPane.add(pa, BorderLayout.CENTER);
        bottomPane.add(keyPadUpDown, BorderLayout.WEST);
        bottomPane.add(research, BorderLayout.EAST);

        // The listener for all the buttons
        research.addMouseListener(theListener);
        start.addMouseListener(theListener);
        stop.addMouseListener(theListener);
        aheadArrowB.addMouseListener(theListener);
        behindArrowB.addMouseListener(theListener);
        leftArrowB.addMouseListener(theListener);
        rightArrowB.addMouseListener(theListener);
        upArrowB.addMouseListener(theListener);
        downArrowB.addMouseListener(theListener);
        
        // Current Image
        img = new JLabel();
        img.setIcon(Beginning);

        this.add(img,BorderLayout.CENTER);
        this.add(bottomPane,BorderLayout.SOUTH);
        bottomPane.setVisible(false);
        this.add(start,BorderLayout.EAST);

        this.pack();
        this.setVisible(true);

    }

    private void createRooms()
    {
        Room abyss, basement, butchery, cemetery, changingRoom, dungeon, firstFloor, gamingRoom, heliport, machineRoom, pgOffice, recyclingCenter, redRoom, secondFloor, thirdFloor, toilet, tortureRoom, trunkRoom;
        
        // JPanel_ All the pictures for the rooms 
        ImageIcon iconAbyss = new ImageIcon("rooms/abyss.jpg");
        ImageIcon iconBasement = new ImageIcon("rooms/basement.jpg");
        ImageIcon iconButchery = new ImageIcon("rooms/butchery.jpg");
        ImageIcon iconCemetery = new ImageIcon("rooms/cemetery.jpg");
        ImageIcon iconChangingRoom = new ImageIcon("rooms/changingRoom.jpg");
        ImageIcon iconDungeon = new ImageIcon("rooms/");
        iconFirstFloor = new ImageIcon("rooms/firstFloor.jpg");
        ImageIcon iconGamingRoom = new ImageIcon("rooms/gamingRoom.jpg");
        ImageIcon iconHeliport = new ImageIcon("rooms/heliport.jpg");
        ImageIcon iconMachineRoom = new ImageIcon("rooms/machineRoom.jpg");
        ImageIcon iconPgOffice = new ImageIcon("rooms/pgOffice.png");
        ImageIcon iconRecyclingCenter = new ImageIcon("rooms/recyclingCenter.jpg");
        ImageIcon iconRedRoom = new ImageIcon("rooms/redRoom.jpg");
        ImageIcon iconSecondFloor = new ImageIcon("rooms/secondFloor(1).jpg");
        ImageIcon iconThirdFloor = new ImageIcon("rooms/thirdFloor.jpg");
        ImageIcon iconToilet = new ImageIcon("rooms/toilet.jpg");
        ImageIcon iconTortureRoom = new ImageIcon("rooms/tortureRoom.jpg");
        ImageIcon iconTrunkRoom = new ImageIcon("rooms/trunkRoom.jpg");

        //create the rooms
        abyss = new Room(" You felt into the abyss");
        basement = new Room(" in the basement");
        butchery = new Room(" all is good in the pig" );
        cemetery = new Room(" Beautiful day to die" );      
        changingRoom = new Room(" it is going to sweat" );
        dungeon = new Room("Hello, get comfortable");
        firstFloor = new Room(" in the hallway of the dark house");
        gamingRoom = new Room(" in the Gaming Room");
        heliport = new Room("You win !! Congratulations");
        machineRoom = new Room(" Are you a machine" );
        pgOffice = new Room(" You do not have basics");
        recyclingCenter = new Room("I hope you sort out your waste");
        redRoom = new Room(" in the red Room ... where the blood flows");
        secondFloor = new Room(" near the death");
        thirdFloor = new Room(" near the death");
        toilet = new Room(" you are a little dirty");
        tortureRoom = new Room(" in the cellar");
        trunkRoom = new Room("do not touch the chests");
        

        // initialise room exits + key/Room + icon/Room
        // Room direction order (forward, backward, left, right, up, down)
        abyss.setExits(secondFloor,null,null,null,null,null);
        abyss.setExitsIcon(iconSecondFloor,secondFloor,null,null,null,null,null,null,null,null,null,null);
        
        basement.setExits(tortureRoom,null,null,null,firstFloor,null);
        basement.setExitsIcon(iconTortureRoom,tortureRoom,null,null,null,null,null,null,iconFirstFloor,firstFloor,null,null);
        
        butchery.setExits(null,null,null,firstFloor,null,null);
        butchery.setExitsIcon(null,null,null,null,null,null,iconFirstFloor,firstFloor,null,null,null,null);
        
        cemetery.setExits(null,null,secondFloor,null,recyclingCenter,null);
        cemetery.setExitsIcon(null,null,null,null,iconSecondFloor,secondFloor,null,null,iconRecyclingCenter,recyclingCenter,null,null);
        
        changingRoom.setExits(machineRoom,null,null,secondFloor,null,firstFloor);
        changingRoom.setExitsIcon(iconMachineRoom,machineRoom,null,null,null,null,iconSecondFloor,secondFloor,null,null,iconFirstFloor,firstFloor);
        
        dungeon.setExits(firstFloor,redRoom,null,null,null,null);
        dungeon.setExitsIcon(iconFirstFloor,firstFloor,iconRedRoom,redRoom,null,null,null,null,null,null,null,null);
        
        firstFloor.setExits(redRoom,null,butchery,gamingRoom,changingRoom,basement);
        firstFloor.setExitsIcon(iconRedRoom,redRoom,null,null,iconButchery,butchery,iconGamingRoom,gamingRoom,iconChangingRoom,changingRoom,iconBasement,basement);
        
        gamingRoom.setEntryByKey("Keys/key1.jpg",gamingRoom);
        gamingRoom.setExits(null,null,firstFloor,null,null,null);
        gamingRoom.setExitsIcon(null,null,null,null,iconFirstFloor,firstFloor,null,null,null,null,null,null);
        
        heliport.setExits(null,null,null,null,null,null);
        heliport.setExitsIcon(null,null,null,null,null,null,null,null,null,null,null,null);
        
        machineRoom.setExits(null,secondFloor,changingRoom,null,null,null);
        machineRoom.setExitsIcon(null,null,iconSecondFloor,secondFloor,iconChangingRoom,changingRoom,null,null,null,null,null,null);
        
        pgOffice.setExits(thirdFloor,null,null,trunkRoom,heliport,null);
        pgOffice.setExitsIcon(iconThirdFloor,thirdFloor,null,null,null,null,iconTrunkRoom,trunkRoom,iconHeliport,heliport,null,null);
        
        recyclingCenter.setExits(null,null,null,thirdFloor,null,cemetery);
        recyclingCenter.setExitsIcon(null,null,null,null,null,null,iconThirdFloor,thirdFloor,null,null,iconCemetery,cemetery);
        
        redRoom.setExits(dungeon,firstFloor,null,null,null,null);
        redRoom.setExitsIcon(iconDungeon,dungeon,iconFirstFloor,firstFloor,null,null,null,null,null,null,null,null);
        
        secondFloor.setExits(machineRoom,null,changingRoom,cemetery,null,null);
        secondFloor.setExitsIcon(iconMachineRoom,machineRoom,null,null,iconChangingRoom,changingRoom,iconCemetery,cemetery,null,null,null,null);
  
        thirdFloor.setExits(toilet,pgOffice,recyclingCenter,null,null,null);
        thirdFloor.setExitsIcon(iconToilet,toilet,iconPgOffice,pgOffice,iconRecyclingCenter,recyclingCenter,null,null,null,null,null,null);
        
        toilet.setExits(abyss,thirdFloor,null,null,null,null);
        toilet.setExitsIcon(iconAbyss,abyss,iconThirdFloor,thirdFloor,null,null,null,null,null,null,null,null);
        
        tortureRoom.setNameKey("Keys/key1.jpg");
        tortureRoom.setExits(null,basement,null,null,null,null);
        tortureRoom.setExitsIcon(null, null,iconBasement,basement,null,null,null,null,null,null,null,null);
       
        trunkRoom.setExits(null,pgOffice,null,null,null,null);
        trunkRoom.setExitsIcon(null,null,iconPgOffice,pgOffice,null,null,null,null,null,null,null,null);

        currentRoom = firstFloor;
    }

    /**
     * getValue()
     * Get the value of the next Room
     * @param next : It is the next Room where the user wants to go 
     * @param direc : this room is in this place (direction)
     */
    public Room getValue(Room next, String direc){
        Set<Entry<String, Room>> setEntry = currentRoom.getHashMap().entrySet();
        Iterator<Map.Entry<String, Room>> itEntry = setEntry.iterator();

        // Loop which defined the nextRoom 
        while(itEntry.hasNext()){
            Map.Entry<String, Room> entry = itEntry.next();
            if(direc.equals(entry.getKey()))
            {
                // HERE
                control = entry.getValue();
                // Control if the user has the key
                if(control.getCanEnterByKey(control)){
                    next = entry.getValue();
                    insertS("\nYou are entered with the key !");
                    break;
                } 
                // Control if the nextRoom is open or not by a key
                else if(!(control.getIsOpen())){
                    insertS("\nThis room is locked");
                    break;
                }
                // All is right, you can enter in the room
                else {
                    next = entry.getValue();
                    insertS("\n" + direc + " / You entered by chance");
                    break;
                }
                //HERE
                // Control if the user has to answer a question
                // else if(){
                // }

            }
        }
        return next;
    }

    /**
     * Change the image in the frame, according to the currentRoom
     * @param the currentRoom
     * @return the image
     */
    public JLabel getImage(Room next){
        Set<Entry<ImageIcon, Room>> setEntryIcon = currentRoom.getHashMapIcon().entrySet();
        Iterator<Map.Entry<ImageIcon, Room>> itEntryIcon = setEntryIcon.iterator();

        // Loop which defined the nextRoom 
        while(itEntryIcon.hasNext()){
            Map.Entry<ImageIcon, Room> entryIcon = itEntryIcon.next();
            if(next.equals(entryIcon.getValue()))
            {
                img.setIcon(entryIcon.getKey());
                break;
            }
        }
        return img;
    }

    /**
     * displayObjects()
     * Add to the frame all the objects founded
     */
    public void displayObjects(){
        objectsFound.removeAll();
        Set<Entry<String, Room>> setEntryIcon = theUser.inventaire.getKeys().entrySet();
        Iterator<Map.Entry<String, Room>> itEntryIcon = setEntryIcon.iterator();
        // Loop which defined the nextRoom 
        while(itEntryIcon.hasNext()){
            Map.Entry<String, Room> entryIcon = itEntryIcon.next();
                objectsIcon = new ImageIcon(entryIcon.getKey());
                displayObjectsFound = new JLabel();
                displayObjectsFound.setIcon(objectsIcon);
                objectsFound.add(displayObjectsFound);
        }
    }

    /** 
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            insertS("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;

        Set<Entry<String, Room>> setEntry = currentRoom.getHashMap().entrySet();
        //
        Iterator<Map.Entry<String, Room>> itEntry = setEntry.iterator();
        int k = 0; // k is a parameter in the while loop
        // Loop which defined the nextRoom 
        while(itEntry.hasNext()){
            Map.Entry<String, Room> entry = itEntry.next();
            if(direction.equals(entry.getKey()))
            {
                nextRoom = entry.getValue();
                break;
            }
        }

        if (nextRoom == null) {
            insertS("\nThere is no door!");
        }
        else {
            currentRoom = nextRoom;
        }
    }
    
    /**
     * Insert in the box, all the informations for the user
     */
    public void insertS(String s){
        try{
            doc.insertString(doc.getLength(),s ,null);
        } catch(BadLocationException exc) {
            exc.printStackTrace();
        }
    }
    
    // Send to the listener - Get methods for the ImageIcon
    public ImageIcon getbeginning(){
        return Beginning;
    }
    
    public ImageIcon geticonFirstFloor(){
        return iconFirstFloor;
    }
    
    // Send to the listener - Get methods for the buttons
    public JButton getupArrowB(){
        return upArrowB;
    }
    
    public JButton getdownArrowB(){
        return downArrowB;
    }
    
    public JButton getleftArrowB(){
        return leftArrowB;
    }
    
    public JButton getrightArrowB(){
        return rightArrowB;
    }
    
    public JButton getForwardArrowB(){
        return aheadArrowB;
    }
    
    public JButton getBackwardArrowB(){
        return behindArrowB;
    }
    
    public JButton getpointCenteredB(){
        return pointCenteredB;
    }
    
    public JButton getresearch(){
        return research;
    }
    
    public JButton getnextImg(){
        return nextImg;
    }
    
    public JButton getstart(){
        return start;
    }
    
    public JButton getstop(){
        return stop;
    }
    
    public HashMap getkeysFounded(){
        return theUser.inventaire.getKeys();
    }
    
}