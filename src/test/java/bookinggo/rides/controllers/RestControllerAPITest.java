package bookinggo.rides.controllers;

import bookinggo.rides.CarPrice;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author stefan
 */
public class RestControllerAPITest {
    
    public RestControllerAPITest() {
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
     * Test of daveAPI method, of class RestControllerAPI.
     */
    @Test
    public void testDaveAPI() {
        String pickup = "51.470020,-0.454295";
        String dropoff = "51.00000,1.0000";
        RestControllerAPI instance = new RestControllerAPI();
        ResponseEntity result = instance.daveAPI(pickup, dropoff);
        System.out.println("daveAPI - response: " + result.toString());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        
    }

    /**
     * Test of bestCarsAPI method, of class RestControllerAPI.
     */
    @Test
    public void testBestCarsAPI() {
        String pickup = "51.470020,-0.454295";
        String dropoff = "51.00000,1.0000";
        String passengersNo = "4";
        RestControllerAPI instance = new RestControllerAPI();
        ResponseEntity<List<CarPrice>> result = instance.bestCarsAPI(pickup, dropoff, passengersNo);
        System.out.println("bestCarsAPI - response: " + result.toString());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    
}
