package bookinggo.rides.controllers;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author stefan
 */

@RestController
@RequestMapping("/api")
public class RestControllerAPI {
    
    // curl http://localhost:8080/api/dave-cars-descending
    @RequestMapping(value = "/dave-cars-descending", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> test1() {
        try {
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("responseCode", 0);
            jsonResponse.put("message", "OK Test1 - GET!");
            jsonResponse.put("timestamp", new Date() + "");
            return new ResponseEntity(jsonResponse.toString(), HttpStatus.OK);
        } catch (JSONException ex) {
            Logger.getLogger(RestControllerAPI.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity(ex.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
