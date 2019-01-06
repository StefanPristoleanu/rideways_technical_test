package bookinggo.rides;

/**
 *
 * @author stefan
 */
public class CarPrice implements Comparable{
    private String carType;
    private int carPrice;
    private String carSupplier;

    public CarPrice(String carType, int carPrice, String carSupplier) {
        this.carType = carType;
        this.carPrice = carPrice;
        this.carSupplier = carSupplier;
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
    
    /**
     * @return the carSupplier
     */
    public String getCarSupplier() {
        return carSupplier;
    }

    /**
     * @param carSupplier the carSupplier to set
     */
    public void setCarSupplier(String carSupplier) {
        this.carSupplier = carSupplier;
    }
    
    @Override
    public String toString(){
        return carType + " - " + carPrice;
    }
    
    public String toStringWithSupplier(){
        return carType + " - " + carSupplier + " - " + carPrice;
    }

    @Override
    public int compareTo(Object other) {
        return ((CarPrice)other).getCarPrice() - this.getCarPrice();
    }
}
