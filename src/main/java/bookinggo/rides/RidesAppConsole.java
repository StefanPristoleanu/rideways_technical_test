package bookinggo.rides;

import bookinggo.rides.controllers.RestControllerAPI;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponseFactory;
import org.apache.http.HttpVersion;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.message.BasicStatusLine;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

public class RidesAppConsole {

    private static final HashMap<String, Integer> carCapacityMap = new HashMap<String, Integer>();

    //args: pickup=51.470020,-0.454295&dropoff=51.00000,1.0000 5
    public static void main(String[] args) {
        processRequest(args);
    }

    public static HttpResponse<JsonNode> connectURL(String[] args, String supplierName) throws UnirestException {
        String supplierAPI = "https://techtest.rideways.com/" + supplierName;
        supplierAPI += '?' + args[1];
        
        // first parameter is the connection timeout (the time it takes to connect to a server)
        //second parameter is  the socket timeout (the time it takes to receive data)
        //when socket timeout is 2000 more than 50% requests have timeout.
        Unirest.setTimeouts(2000, 4000);
        HttpResponse<JsonNode> jsonResponse;
        jsonResponse = Unirest.get(supplierAPI)
                .header("accept", "application/json").header("pickup", "51.470020,-0.454295")
                .asJson();
        System.out.println(supplierName + " response: " + jsonResponse.getBody().toString());
        return jsonResponse;
    }

    protected static boolean processRequest(String[] args) {
        if (args == null || args.length < 3) {
            System.out.println("No pickup and dropoff found. Exiting");
            return false;
        }
        System.out.println("App is running with args: " + Arrays.toString(args));
        List<CarPrice> carList = new ArrayList<>();

        //if the first argument is 1, we attempt step1 phase A: The response to Dave's Taxis in descending order
        System.out.println("PassengersNo: " + args[2]);
        int passengersNo = Integer.valueOf(args[2]);

        //String daveAPI = "https://techtest.rideways.com/dave?pickup=51.470020,-0.454295&dropoff=51.00000,1.0000";
        //String daveAPI = "https://techtest.rideways.com/dave";
        //String ericAPI = "https://techtest.rideways.com/eric?pickup=51.470020,-0.454295&dropoff=51.00000,1.0000";
        //String jeffAPI = "https://techtest.rideways.com/jeff?pickup=51.470020,-0.454295&dropoff=51.00000,1.0000";
        try {
            HttpResponse<JsonNode> jsonResponse = connectURL(args, "dave");
            if (args[0].equals("1")) {
                searchResultForDave(jsonResponse, carList);
                return true;
            }
            searchResultWithPassengersNo(jsonResponse, passengersNo, carList);
        } catch (UnirestException ex) {
            Logger.getLogger(RestControllerAPI.class.getName()).log(Level.SEVERE, null, ex);
            if (args[0].equals("1")) {
                return false;
            }
        }
        try {
            HttpResponse<JsonNode> jsonResponse = connectURL(args, "eric");
            searchResultWithPassengersNo(jsonResponse, passengersNo, carList);
        } catch (UnirestException ex) {
            Logger.getLogger(RestControllerAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            HttpResponse<JsonNode> jsonResponse = connectURL(args, "jeff");
            searchResultWithPassengersNo(jsonResponse, passengersNo, carList);
        } catch (UnirestException ex) {
            Logger.getLogger(RestControllerAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        chooseBestCars(carList);
        return true;
    }

    public static void searchResultForDave(HttpResponse<JsonNode> jsonResponse, List<CarPrice> carList) {
        //error cases received from suppliers, will abort method
        if (jsonResponse.getBody().getObject().has("error")) {
            return;
        }
        JSONArray daveResponse = jsonResponse.getBody().getObject().getJSONArray("options");
        for (int i = 0; i < daveResponse.length(); i++) {
            JSONObject job = daveResponse.getJSONObject(i);
            CarPrice cp = new CarPrice(job.getString("car_type"), job.getInt("price"), "Dave");
            carList.add(cp);
        }
        sortCarsDesc(carList);
        System.out.println("Dave's sorted descending list: ");
        for (int i = 0; i < carList.size(); i++) {
            System.out.println(carList.get(i).toString());
        }
    }

    public static void searchResultWithPassengersNo(HttpResponse<JsonNode> jsonResponse, int passengersNo, List<CarPrice> carList) {
        //error cases received from suppliers, will abort method
        if (jsonResponse.getBody().getObject().has("error")) {
            return;
        }
        if (carCapacityMap.isEmpty()) {
            carCapacityMap.put("STANDARD", 4);
            carCapacityMap.put("EXECUTIVE", 4);
            carCapacityMap.put("LUXURY", 4);
            carCapacityMap.put("PEOPLE_CARRIER", 6);
            carCapacityMap.put("LUXURY_PEOPLE_CARRIER", 6);
            carCapacityMap.put("MINIBUS", 16);
        }
        JSONArray daveCarsResponse = jsonResponse.getBody().getObject().getJSONArray("options");
        for (int i = 0; i < daveCarsResponse.length(); i++) {
            JSONObject job = daveCarsResponse.getJSONObject(i);
            CarPrice cp = new CarPrice(job.getString("car_type"), job.getInt("price"), jsonResponse.getBody().getObject().getString("supplier_id"));
            if (carCapacityMap.get(job.getString("car_type")) >= passengersNo) {
                carList.add(cp);
            }
        }
        System.out.println(carList);
        sortCarsDesc(carList);
    }

    protected static void sortCarsDesc(List<CarPrice> carList) {
        Collections.sort(carList);
        //System.out.println("Sorted list: " + carList);
    }

    public static List<CarPrice> chooseBestCars(List<CarPrice> carList) {
        List<CarPrice> bestCars = new ArrayList<>();
        bestCars.add(carList.get(carList.size() - 1));
        for (int i = carList.size() - 2; i >= 0; i--) {
            boolean foundCarType = false;
            for (int j = 0; j < bestCars.size(); j++) {
                if (bestCars.get(j).getCarType().equals(carList.get(i).getCarType())) {
                    foundCarType = true;
                }
            }
            if (!foundCarType) {
                bestCars.add(carList.get(i));
            }
        }
        System.out.println("Final descending list: ");
        for (int i = 0; i < bestCars.size(); i++) {
            System.out.println(bestCars.get(i).toStringWithSupplier());
        }
        return bestCars;
        //System.out.println("Final descending list: " + bestCars);
    }
}
