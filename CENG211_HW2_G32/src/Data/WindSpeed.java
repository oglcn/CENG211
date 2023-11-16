package Data;

public class WindSpeed extends ClimateMeasurement{
    
    // Constants
    // 0.0 <= metersPerSecond <= 113.2
    private static final double MAX_WIND_SPEED = 113.2;
    private static final double MIN_WIND_SPEED = 0.0;

    // Fields
    private double metersPerSecond;

    //#region Constructors

    /**
     * Constructor for WindSpeed class
     * @param metersPerSecond wind speed value in meters per second
     * @param year year of the measurement
     * @param month month of the measurement
     */
    public WindSpeed(double metersPerSecond, int year, Month month) {
        super(year, month);
        this.metersPerSecond = metersPerSecond;
    }

    /**
     * Copy constructor for WindSpeed class
     * @param windSpeed WindSpeed object to be copied
     */
    public WindSpeed(WindSpeed windSpeed) {
        this(windSpeed.metersPerSecond, windSpeed.year, windSpeed.month);
    }

    /**
     * Constructor for WindSpeed class with random wind speed value
     * @param year year of the measurement
     * @param month month of the measurement
     */
    public WindSpeed(int year, Month month) {
        // Pick random wind speed and initialize instance
        // MIN_WIND_SPEED <= windSpeed <= MAX_WIND_SPEED
        this((Math.random() * (MAX_WIND_SPEED - MIN_WIND_SPEED + 1)) + MIN_WIND_SPEED, year, month);
    }

    //#endregion

    //#region Getter methods.
    // Note: Setter methods are not needed, because the wind speed value is given in the constructor and it shall not be changed.

    /**
     * Getter method for wind speed measurement with given windSpeedUnit
     * @param windSpeedUnit wind speed unit. Can be METERS_PER_SECOND or KILOMETERS_PER_HOUR
     * @return wind speed measurement with given windSpeedUnit
     */
    public double getMeasurement(WindSpeedUnit windSpeedUnit) {
        switch (windSpeedUnit) {
            case METERS_PER_SECOND:
                return getMetersPerSecond();
            case KILOMETERS_PER_HOUR:
                return getKilometersPerHour();
            default:
                return 0.0;
        }
    }

    /**
     * Getter method for metersPerSecond field
     * @return metersPerSecond field value
     */
    public double getMetersPerSecond() {
        return metersPerSecond;
    }

    /**
     * Getter method for kilometersPerHour field
     * @return kilometersPerHour field value
     */
    public double getKilometersPerHour() {
        return metersPerSecond * 3.6;
    }

    //#endregion

    //#region Overrides
    @Override
    public String toString() {
        String firstLine = super.toString();
        String secondLine = "Wind Speed: " + metersPerSecond + " m/s";
        return firstLine + "\n" + secondLine;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) 
            return true; // If the object is compared with itself then return true

        if (!super.equals(obj)) 
            return false; // If the super class is not equal then return false

        if (getClass() != obj.getClass()) { 
            return false; // If the object is not of type WindSpeed then return false
        }
        WindSpeed other = (WindSpeed) obj;
        if (Double.doubleToLongBits(metersPerSecond) != Double.doubleToLongBits(other.metersPerSecond)) {
            return false; // If the metersPerSecond field is not equal then return false
        }
        return true; // If all fields are equal then return true
    }

    
    //#endregion
}
