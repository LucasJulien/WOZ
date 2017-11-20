

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class InventoryTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class InventoryTest
{
    private Inventory theInventory;
    /**
     * Default constructor for test class InventoryTest
     */
    public InventoryTest()
    {
        theInventory = new Inventory();
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
    public void Test_getQuantity()
    {
        assertNotEquals(0,theInventory.getQuantity());
    }
}
