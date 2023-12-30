package Data;

public class Humidity extends ClimateMeasurement {

    /**
     * Humidity value in percentage
     * 0.0 <= humidityPercentage <= 100.0
     */
    double humidityPercentage;

    //#region Constructors
    public Humidity(int year, Month month) {
        // Generate random humidityValue and initialize instance
        // 0.0 <= humidityValue <= 100.0
        this((Math.random() * 100.0), year, month);
    }

    public Humidity(double humidityPercentage, int year, Month month) {
        super(year, month);
        this.humidityPercentage = humidityPercentage;

    }

    public Humidity(Humidity measurement) {
        // Copy constructor
        this(measurement.humidityPercentage, measurement.year, measurement.month);
    }

    //#endregion

    //#region Getter methods
    // Note: Setter methods are not needed, because the humidity value is given in the constructor and it shall not be changed.
    public double getHumidityPercentage() {
        return humidityPercentage;
    }
    //#endregion

    //#region Overrides
    @Override
    public String toString() {
        String firstLine = super.toString();
        String secondLine = "Humidity: " + humidityPercentage + "%";
        return firstLine + "\n" + secondLine;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!super.equals(obj))
            return false;

        if (getClass() != obj.getClass()) {
            return false;
        }
        Humidity other = (Humidity) obj;
        if (Double.doubleToLongBits(humidityPercentage) != Double.doubleToLongBits(other.humidityPercentage)) {
            return false;
        }
        return true;
    }

    //#endregion

}
