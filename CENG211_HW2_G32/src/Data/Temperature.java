package Data;

public class Temperature extends ClimateMeasurement {

    // Constants
    private static final int MAX_TEMPERATURE = 50;
    private static final int MIN_TEMPERATURE = -40;

    // Fields
    private int celciusMeasurement;

    //#region Constructors

    /**
     * Constructor for Temperature class
     * @param temperature temperature value in celcius
     * @param year year of the measurement
     * @param month month of the measurement
     */
    public Temperature(int temperature, int year, Month month) {
        super(year, month);
        this.celciusMeasurement = temperature;
    }

    /**
     * Copy constructor for Temperature class
     * @param temperature Temperature object to be copied
     */
    public Temperature(Temperature temperature) {
        this(temperature.celciusMeasurement, temperature.year, temperature.month);
    }

    /**
     * Constructor for Temperature class with random temperature value
     * @param year year of the measurement
     * @param month month of the measurement
     */
    public Temperature(int year, Month month) {
        // Pick random temperature and initialize instance
        // MIN_TEMPERATURE <= temperature <= MAX_TEMPERATURE
        this((int) (Math.random() * (MAX_TEMPERATURE - MIN_TEMPERATURE + 1)) + MIN_TEMPERATURE, year, month);
    }

    //#endregion
    
    //#region Getter methods. 
    // Note: Setter methods are not needed, because the temperature value is given in the constructor and it shall not be changed.

    /**
     * Getter method for temperature measurement with given tempUnit
     * @return temperature measurement with given tempUnit
     */
    public double getMeasurement(TempUnit tempUnit) {
        switch (tempUnit) {
            case CELCIUS:
                return getCelciusMeasurement();
            case FAHRENHEIT:
                return getFahrenheitMeasurement();
            case KELVIN:
                return getKelvinMeasurement();
            default:
                return 0;
        }
    }

    /**
     * Getter method for celciusMeasurement field
     * @return celciusMeasurement field value
     */
    public int getCelciusMeasurement() {
        return celciusMeasurement;
    }


    /**
     * Getter method for fahrenheitMeasurement field
     * @return fahrenheitMeasurement field value
     */
    public double getFahrenheitMeasurement() {
        return (celciusMeasurement * 9.0 / 5.0) + 32;
    }

    
    /**
     * Getter method for kelvinMeasurement field
     * @return kelvinMeasurement field value
     */
    public double getKelvinMeasurement() {
        return celciusMeasurement + 273.15;
    }


    //#endregion
    
    //#region Overrides

    /**
     * Returns a string representation of the Temperature object
     * @return string representation of the Temperature object
     */
    @Override
    public String toString() {
        String firstLine = super.toString();
        String secondLine = "Temperature: " + celciusMeasurement + "°C";
        return firstLine + "\n" + secondLine;
    }

    /**
     * Checks if the given object is equal to the Temperature object
     * @param o object to be compared
     * @return true if the given object is equal to the Temperature object, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Same object
        if (!super.equals(obj)) return false; // Ensure upper class values are equal (year, month for this case)
        if (obj == null || getClass() != obj.getClass()) return false;
        Temperature other = (Temperature) obj;
        return this.celciusMeasurement == other.celciusMeasurement;
    }
    //#endregion
}
