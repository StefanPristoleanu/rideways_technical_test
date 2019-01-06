package bookinggo.rides;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author stefan
 */
public class RidesAppConsoleTest {
    
    public RidesAppConsoleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of connect method, of class RidesAppConsole.
     */
    @Test
    public void testProcessRequest() {
        String[] args = null;
        Boolean result = RidesAppConsole.processRequest(args);
        System.out.println("connect: " + result);
        assertFalse(result);
        
        args = "1 pickup=51.470020,-0.454295&dropoff=51.00000,1.0000 5".split(" ");
        result = RidesAppConsole.processRequest(args);
        System.out.println("connect: " + result);
        assertTrue(result);
    }
    
    @Test
    public void testConnectURL() {
        String[] args = null;
        Boolean result = RidesAppConsole.processRequest(args);
        System.out.println("connect: " + result);
        assertFalse(result);
        
        args = "1 pickup=51.470020,-0.454295&dropoff=51.00000,1.0000 5".split(" ");
        result = RidesAppConsole.processRequest(args);
        System.out.println("connect: " + result);
        assertTrue(result);
    }

    /**
     * Test of searchResultForDave method, of class RidesApplication.
     */
    @Test
    @Ignore
    public void testSearchResultForDave() {
        System.out.println("searchResultForDave");
        HttpResponse<JsonNode> jsonResponse = null;
        List<CarPrice> carList = null;
        RidesAppConsole.searchResultForDave(jsonResponse, carList);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of searchResultWithPassengersNo method, of class RidesApplication.
     */
    @Test
    @Ignore
    public void testSearchResultWithPassengersNo() {
        System.out.println("searchResultWithPassengersNo");
        HttpResponse<JsonNode> jsonResponse = null;
        int passengersNo = 0;
        List<CarPrice> carList = null;
        RidesAppConsole.searchResultWithPassengersNo(jsonResponse, passengersNo, carList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sortCarsDesc method, of class RidesApplication.
     */
    @Test
    @Ignore
    public void testSortCarsDesc() {
        System.out.println("sortCarsDesc");
        List<CarPrice> carList = null;
        RidesAppConsole.sortCarsDesc(carList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of chooseBestCars method, of class RidesApplication.
     */
    @Test
    @Ignore
    public void testChooseBestCars() {
        System.out.println("chooseBestCars");
        List<CarPrice> carList = null;
        RidesAppConsole.chooseBestCars(carList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
