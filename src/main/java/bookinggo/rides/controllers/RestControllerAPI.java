package bookinggo.rides.controllers;

import bookinggo.rides.CarPrice;
import bookinggo.rides.RidesAppConsole;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author stefan
 */

@RestController
@RequestMapping("/api")
public class RestControllerAPI {
    
    // curl http://localhost:8080/api/dave-cars-descending?pickup=51.470020,-0.454295&dropoff=51.00000,1.0000
    @RequestMapping(value = "/dave-cars-descending", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<CarPrice> daveAPI(@RequestParam("pickup") String pickup, @RequestParam("dropoff") String dropoff) throws UnirestException {
        try {
            String[] args = new String[2];
            args[0] = "1";
            args[1] = "pickup=" + pickup + "&dropoff=" + dropoff; 
            HttpResponse<JsonNode> jsonResponse2 = RidesAppConsole.connectURL(args, "dave");
            List<CarPrice> carList = new ArrayList<>();
            RidesAppConsole.searchResultForDave(jsonResponse2, carList);
            return carList;
        } catch (JSONException ex) {
            List<CarPrice> carList2 = new ArrayList<>();
            Logger.getLogger(RestControllerAPI.class.getName()).log(Level.SEVERE, null, ex);
            return carList2;
        }
    }
    
    // curl http://localhost:8080/api/best-cars?pickup=51.470020,-0.454295&dropoff=51.00000,1.0000&5
    @RequestMapping(value = "/best-cars", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<CarPrice> bestCarsAPI(@RequestParam("pickup") String pickup, @RequestParam("dropoff") String dropoff, @RequestParam("passengersNo") String passengersNo) throws UnirestException {
        try {
            String[] args = new String[3];
            args[0] = "2";
            args[1] = "pickup=" + pickup + "&dropoff=" + dropoff; 
            args[2] = passengersNo;
            HttpResponse<JsonNode> jsonResponse = RidesAppConsole.connectURL(args, "dave");
            List<CarPrice> carList = new ArrayList<>();
            RidesAppConsole.searchResultWithPassengersNo(jsonResponse, Integer.valueOf(passengersNo), carList);
            
            jsonResponse = RidesAppConsole.connectURL(args, "eric");
            RidesAppConsole.searchResultWithPassengersNo(jsonResponse, Integer.valueOf(passengersNo), carList);
            
            jsonResponse = RidesAppConsole.connectURL(args, "jeff");
            RidesAppConsole.searchResultWithPassengersNo(jsonResponse, Integer.valueOf(passengersNo), carList);
            
            carList = RidesAppConsole.chooseBestCars(carList);
            return carList;
        } catch (JSONException ex) {
            List<CarPrice> carList2 = new ArrayList<>();
            Logger.getLogger(RestControllerAPI.class.getName()).log(Level.SEVERE, null, ex);
            return carList2;
        }
    }
}
