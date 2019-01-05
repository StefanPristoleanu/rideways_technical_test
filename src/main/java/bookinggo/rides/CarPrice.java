package bookinggo.rides;

/**
 *
 * @author stefan
 */
public class CarPrice implements Comparable{
    private String carType;
    private int carPrice;

    public CarPrice(String carType, int carPrice) {
        this.carType = carType;
        this.carPrice = carPrice;
    }
    
    /**
     * @return the carType
     */
    public String getCarType() {
        return carType;
    }

    /**
     * @param carType the carType to set
     */
    public void setCarType(String carType) {
        this.carType = carType;
    }

    /**
     * @return the carPrice
     */
    public int getCarPrice() {
        return carPrice;
    }

    /**
     * @param carPrice the carPrice to set
     */
    public void setCarPrice(int carPrice) {
        this.carPrice = carPrice;
    }
    
    @Override
    public String toString(){
        return carType + " - " + carPrice;
    }

    @Override
    public int compareTo(Object other) {
        return ((CarPrice)other).getCarPrice() - this.getCarPrice();
    }
}
