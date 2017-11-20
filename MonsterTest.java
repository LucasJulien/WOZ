

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class MonsterTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MonsterTest
{
    private Monster theMonster;
    
    /**
     * Default constructor for test class MonsterTest
     */
    public MonsterTest()
    {
        theMonster = new Monster();
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
    public void test_MonsterTest_getCanTalk()
    {
        assertEquals(true, theMonster.getCanTalk());
    }
    
    @Test
    public void test_MonsterTest_getCanTrock()
    {
        assertEquals(true, theMonster.getCanTrock());
    }
}

