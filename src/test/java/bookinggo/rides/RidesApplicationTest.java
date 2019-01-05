/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author stefan
 */
public class RidesApplicationTest {
    
    public RidesApplicationTest() {
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
     * Test of connect method, of class RidesApplication.
     */
    @Test
    public void testConnect() {
        String[] args = null;
        Boolean result = RidesApplication.connect(args);
        System.out.println("connect: " + result);
        assertFalse(result);
        
        args = "pickup=51.470020,-0.454295&dropoff=51.00000,1.0000 5".split(" ");
        result = RidesApplication.connect(args);
        System.out.println("connect: " + result);
        assertTrue(result);
    }

    /**
     * Test of searchResultForDave method, of class RidesApplication.
     */
    @Test
    public void testSearchResultForDave() {
        System.out.println("searchResultForDave");
        HttpResponse<JsonNode> jsonResponse = null;
        List<CarPrice> carList = null;
        RidesApplication.searchResultForDave(jsonResponse, carList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchResultWithPassengersNo method, of class RidesApplication.
     */
    @Test
    public void testSearchResultWithPassengersNo() {
        System.out.println("searchResultWithPassengersNo");
        HttpResponse<JsonNode> jsonResponse = null;
        int passengersNo = 0;
        List<CarPrice> carList = null;
        RidesApplication.searchResultWithPassengersNo(jsonResponse, passengersNo, carList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sortCarsDesc method, of class RidesApplication.
     */
    @Test
    public void testSortCarsDesc() {
        System.out.println("sortCarsDesc");
        List<CarPrice> carList = null;
        RidesApplication.sortCarsDesc(carList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of chooseBestCars method, of class RidesApplication.
     */
    @Test
    public void testChooseBestCars() {
        System.out.println("chooseBestCars");
        List<CarPrice> carList = null;
        RidesApplication.chooseBestCars(carList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
