package bookinggo.rides;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RidesApplication {
    private static final HashMap<String, Integer> carCapacityMap = new HashMap<String, Integer>();
    //args: pickup=51.470020,-0.454295&dropoff=51.00000,1.0000 5
    public static void main(String[] args) {
        SpringApplication.run(RidesApplication.class, args);
        connect(args);
    }

    protected static boolean connect(String[] args) {
        if (args == null || args.length < 2) {
            System.out.println("No pickup and dropoff found. Exiting");
            return false;
        }
        System.out.println("App is running with args: " + Arrays.toString(args));
        List<CarPrice> carList = new ArrayList<>();
        carCapacityMap.put("STANDARD", 4);
        carCapacityMap.put("EXECUTIVE", 4);
        carCapacityMap.put("LUXURY", 4);
        carCapacityMap.put("PEOPLE_CARRIER", 6);
        carCapacityMap.put("LUXURY_PEOPLE_CARRIER", 6);
        carCapacityMap.put("MINIBUS", 16);

        System.out.println(args[1]);
        int passengersNo = Integer.valueOf(args[1]);

        try {
            //String daveAPI = "https://techtest.rideways.com/dave?pickup=51.470020,-0.454295&dropoff=51.00000,1.0000";
            String daveAPI = "https://techtest.rideways.com/dave";
            String ericAPI = "https://techtest.rideways.com/eric?pickup=51.470020,-0.454295&dropoff=51.00000,1.0000";
            String jeffAPI = "https://techtest.rideways.com/jeff?pickup=51.470020,-0.454295&dropoff=51.00000,1.0000";
            daveAPI += '?' + args[0];
            //todo if args = null
            HttpResponse<JsonNode> jsonResponse = Unirest.get(daveAPI)
                    .header("accept", "application/json").header("pickup", "51.470020,-0.454295")
                    .asJson();
            System.out.println("Dave response: " + jsonResponse.getBody().toString());
            //{"dropoff":"51.00000,1.0000","options":[{"price":633730,"car_type":"EXECUTIVE"},{"price":403789,"car_type":"LUXURY_PEOPLE_CARRIER"},{"price":531078,"car_type":"MINIBUS"}],"pickup":"51.470020,-0.454295","supplier_id":"DAVE"}

            //System.out.println(jsonResponse.getBody().getObject().getJSONArray("options").toString());
            /*for(int i = 0; i < jsonResponse.getBody().getObject().getJSONArray("options").length(); i++){
                JSONObject job = jsonResponse.getBody().getObject().getJSONArray("options").getJSONObject(i);
                CarPrice cp = new CarPrice(job.getString("car_type"), job.getInt("price"));
                if(hmap.get(job.getString("car_type")) >= passengersNo){
                    carList.add(cp);
                }
            }
            
            System.out.println(carList);*/
            //searchResultForDave(jsonResponse, peopleInCar, carList);
            searchResultWithPassengersNo(jsonResponse, passengersNo, carList);
            jsonResponse = Unirest.get(ericAPI)
                    .header("accept", "application/json").header("pickup", "51.470020,-0.454295")
                    .asJson();
            System.out.println("\nEric response: " + jsonResponse.getBody().toString());
            searchResultWithPassengersNo(jsonResponse, passengersNo, carList);

            jsonResponse = Unirest.get(jeffAPI)
                    .header("accept", "application/json").header("pickup", "51.470020,-0.454295")
                    .asJson();
            System.out.println("\nJeff response: " + jsonResponse.getBody().toString());
            searchResultWithPassengersNo(jsonResponse, passengersNo, carList);

            //sortCarsDesc(carList);
            chooseBestCars(carList);
            /*jsonResponse = Unirest.get(ericAPI)
                    .header("accept", "application/json").header("pickup", "51.470020,-0.454295")
                    .asJson();
            System.out.println(jsonResponse.getBody().toString());
            
            jsonResponse = Unirest.get(jeffAPI)
                    .header("accept", "application/json").header("pickup", "51.470020,-0.454295")
                    //.queryString("apiKey", "123")
                    //.field("pickup", "51.470020,-0.454295")
                    //.field("dropoff", "51.00000,1.0000")
                    .asJson();
            System.out.println(jsonResponse.getBody().toString());*/
            return true;
        } catch (UnirestException ex) {
            Logger.getLogger(RidesApplication.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    protected static void searchResultForDave(HttpResponse<JsonNode> jsonResponse, List<CarPrice> carList) {
        JSONArray daveResponse = jsonResponse.getBody().getObject().getJSONArray("options");
        for (int i = 0; i < daveResponse.length(); i++) {
            JSONObject job = daveResponse.getJSONObject(i);
            CarPrice cp = new CarPrice(job.getString("car_type"), job.getInt("price"));
            carList.add(cp);
        }
        System.out.println(carList);
    }

    protected static void searchResultWithPassengersNo(HttpResponse<JsonNode> jsonResponse, int passengersNo, List<CarPrice> carList) {
        JSONArray daveResponse = jsonResponse.getBody().getObject().getJSONArray("options");
        for (int i = 0; i < daveResponse.length(); i++) {
            JSONObject job = daveResponse.getJSONObject(i);
            CarPrice cp = new CarPrice(job.getString("car_type"), job.getInt("price"));
            if (carCapacityMap.get(job.getString("car_type")) >= passengersNo) {
                carList.add(cp);
            }
        }
        System.out.println(carList);
    }

    protected static void sortCarsDesc(List<CarPrice> carList) {
        Collections.sort(carList);
        System.out.println("Final descending list: " + carList);
    }

    protected static void chooseBestCars(List<CarPrice> carList) {
        Collections.sort(carList);
        List<CarPrice> bestCars = new ArrayList<>();
        bestCars.add(carList.get(0));
        for (int i = 1; i < carList.size() - 1; i++) {
            if(carList.get(i).getCarType() != carList.get(i+1).getCarType()){
                bestCars.add(carList.get(i));
            }
        }
        System.out.println("Final descending list: " + bestCars);
    }
}
