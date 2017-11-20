

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class MainGameTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MainGameTest
{
    private MainGame theGame;
    /**
     * Default constructor for test class MainGameTest
     */
    public MainGameTest()
    {
        theGame = new MainGame();
        theGame.setVisible(false);
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void Test_getButtons()
    {
        assertNotEquals(null,theGame.getstop());
        assertNotEquals(null,theGame.getstart());
        assertNotEquals(null,theGame.getnextImg());
        assertNotEquals(null,theGame.getresearch());
        assertNotEquals(null,theGame.getpointCenteredB());
        assertNotEquals(null,theGame.getrightArrowB());
        assertNotEquals(null,theGame.getleftArrowB());
        assertNotEquals(null,theGame.getdownArrowB());
        assertNotEquals(null,theGame.getupArrowB());
    }
    
    @Test
    public void Test_getImageIcon()
    {
        assertEquals(null,theGame.geticonFirstFloor());
        assertEquals(null,theGame.getbeginning());
    }
    
    @Test
    public void Test_getkeysFounded()
    {
        assertEquals(null,theGame.getkeysFounded());
    }
}
