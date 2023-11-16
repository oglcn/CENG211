package Data;

import java.util.ArrayList;

import Presentation.ClimateApp;

public abstract class Location {

    // #region Constants

    // Year range for measurements
    // protected static final int MIN_YEAR = 2020;
    // protected static final int MAX_YEAR = 2022;
    // Constants moved to ClimateApp.java

    protected int MIN_YEAR;
    protected int MAX_YEAR;

    // #endregion

    // #region Fields

    String name;
    ArrayList<Temperature> temperatureMeasurements;


    // #endregion

    // #region Constructors

    /**
     * Constructor for Location class. Initializes the name field and creates empty
     * ArrayLists for measurements.
     * Then, generates random measurements for each measurement type to fill the
     * ArrayList
     * 
     * @param name name of the location
     */
    public Location(String name) {
        this.name = name;
        this.MIN_YEAR = ClimateApp.MIN_YEAR;
        this.MAX_YEAR = ClimateApp.MAX_YEAR;
        

        temperatureMeasurements = new ArrayList<Temperature>();

        // Generate random measurements for each measurement type, for each month and
        // each year in the range
        for (int year = MIN_YEAR; year <= MAX_YEAR; year++) {
            for (Month month : Month.values()) {
                temperatureMeasurements.add(new Temperature(year, month));
                
            }
        }

    }

    /**
     * Copy constructor for Location class
     * 
     * @param location Location object to be copied
     */
    public Location(Location location) {
        this.name = location.name;

        temperatureMeasurements = new ArrayList<Temperature>();


        // Copy temperature measurements
        for (Temperature temperature : location.temperatureMeasurements) {
            temperatureMeasurements.add(new Temperature(temperature));
        }



    }

    // #endregion

    // #region Getter methods
    // Note: Setter methods are not defined; because by design, these fields should
    // not be modified after initialization.

    /**
     * Getter method for name field
     * 
     * @return name field value
     */
    public String getName() {
        return name;
    }

    // Getter methods for measurements lists

    /**
     * Getter method for temperatureMeasurements field
     * 
     * @return temperatureMeasurements field value
     */
    public ArrayList<Temperature> getTemperatureMeasurements() {
        return temperatureMeasurements;
    }

    

    // Getter methods for specific years

    /**
     * Getter method for the temperature measurements of a specific year
     * 
     * @param year year of the measurements
     * @return temperature measurements of the year
     */
    public ArrayList<Temperature> getTemperatureMeasurements(int year) {
        ArrayList<Temperature> measurements = new ArrayList<Temperature>();
        for (Temperature temperature : temperatureMeasurements) {
            if (temperature.getYear() == year) {
                measurements.add(temperature);
            }
        }
        return measurements;
    }



    // Getter methods for specific measurements

    /**
     * Getter method for the temperature measurement of a specific month and year
     * 
     * @param month month of the measurement
     * @param year  year of the measurement
     * @return temperature of the month
     */
    public Temperature getTemperatureMeasurement(Month month, int year) {
        for (Temperature temperature : temperatureMeasurements) {
            if (temperature.getYear() == year && temperature.getMonth() == month) {
                return temperature;
            }
        }
        return null;
    }

    

    // #endregion

    // #region Overrides

    @Override
    public String toString() {
        return "Location: " + name + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true; // If the object is compared with itself then return true

        if (!(obj instanceof Location))
            return false; // If the object is not a Location object then return false

        Location other = (Location) obj;
        if (!name.equals(other.name))
            return false; // If the name is not equal then return false

        // If the measurements are not equal then return false
        if (!temperatureMeasurements.equals(other.temperatureMeasurements))
            return false;
        
        return true; // If all the fields are equal then return true
    }

}
