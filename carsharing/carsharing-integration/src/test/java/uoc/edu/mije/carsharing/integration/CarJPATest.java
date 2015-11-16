package uoc.edu.mije.carsharing.integration;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CarJPATest extends TestCase{

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CarJPATest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( CarJPATest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

}
