# rideways_technical_test

# Setup

My project is built with Maven and can be loaded in any Java IDE (I have developed it in Netbeans).
Below I will put he console commands to compile and run the project.

```
 git clone https://github.com/StefanPristoleanu/rideways_technical_test.git
 cd rideways_technical_test
 mvn clean install
```

## Part 1 - Console application: 

### a) Console application to print the search results for Dave's Taxis
```  
  mvn install dependency:copy-dependencies 
  cd target/classes
  java -cp ".:../dependency/*" bookinggo.rides.RidesAppConsole 1 pickup=51.470020,-0.454295\&dropoff=51.00000,1.0000 2
```
 - output example:
Dave's sorted descending list: 
LUXURY - 895796
LUXURY_PEOPLE_CARRIER - 867688
STANDARD - 72568


__Note:__ the parameters for RidesAppConsole are:
  - first param is 1 or 2 based on part1.a or part1.b requests
  - second param are the pickup and dropoff coordinates as in the example
  - the third param is the requested number of passengers (is not used for part1.a)

### b) Console application to filter by number of passengers
```
    java -cp ".:../dependency/*" bookinggo.rides.RidesAppConsole 2 pickup=51.470020,-0.454295\&dropoff=51.00000,1.0000 4
```
 - output example:
Final descending list: 
LUXURY - JEFF - 97866
PEOPLE_CARRIER - JEFF - 103555
EXECUTIVE - DAVE - 126396
LUXURY_PEOPLE_CARRIER - DAVE - 584727
STANDARD - ERIC - 779779

--

## Part 2 - RESTful API

  Start server with command:
  - assuming that you followed the commands in the project root folder (with pom.xml)
  
```
 java -jar target/rides-0.0.1.jar 
```
  Open a browser at url:
  
  http://localhost:8080/api/dave-cars-descending?pickup=51.470020,-0.454295&dropoff=51.00000,1.0000
  
  Response example:
  > [{"carType":"STANDARD","carPrice":567855},{"carType":"EXECUTIVE","carPrice":464408}]
  
  http://localhost:8080/api/best-cars?pickup=51.470020,-0.454295&dropoff=51.00000,1.0000&passengersNo=5
  
  Response example:
  > [{"carType":"MINIBUS","carPrice":286980,"carSupplier":"ERIC"},{"carType":"LUXURY_PEOPLE_CARRIER","carPrice":710476,"carSupplier":"JEFF"},{"carType":"PEOPLE_CARRIER","carPrice":761418,"carSupplier":"ERIC"}]
