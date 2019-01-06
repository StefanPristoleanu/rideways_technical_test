package bookinggo.rides;

import java.util.HashMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author stefan
 */

@SpringBootApplication
@EnableAutoConfiguration
public class RidesAppAPI {

    private static final HashMap<String, Integer> carCapacityMap = new HashMap<String, Integer>();

    //args: pickup=51.470020,-0.454295&dropoff=51.00000,1.0000 5
    public static void main(String[] args) {
        SpringApplication.run(RidesAppAPI.class, args);
        //processRequest(args);
    }
}
