package Data;

import java.util.ArrayList;


public class City extends Location {

    // #region Fields

    ArrayList<WindSpeed> windSpeedMeasurements;
    ArrayList<Humidity> humidityMeasurements;
    ArrayList<RadiationAbsorption> radiationAbsorptionMeasurements;

    // #endregion

    // #region Constructors

    /**
     * Constructor for City class
     * 
     * @param name name of the city
     */
    public City(String name) {
        super(name);
        windSpeedMeasurements = new ArrayList<WindSpeed>();
        humidityMeasurements = new ArrayList<Humidity>();
        radiationAbsorptionMeasurements = new ArrayList<RadiationAbsorption>();

        // Generate random measurements for each measurement type, for each month and
        // each year in the range
        for (int year = MIN_YEAR; year <= MAX_YEAR; year++) {
            for (Month month : Month.values()) {
                windSpeedMeasurements.add(new WindSpeed(year, month));
                humidityMeasurements.add(new Humidity(year, month));
                radiationAbsorptionMeasurements.add(new RadiationAbsorption(year, month));
            }
        }

    }

    /**
     * Copy constructor for City class
     * 
     * @param city City object to be copied
     */
    public City(City city) {
        super(city);
        windSpeedMeasurements = new ArrayList<WindSpeed>();
        humidityMeasurements = new ArrayList<Humidity>();
        radiationAbsorptionMeasurements = new ArrayList<RadiationAbsorption>();

        // Copy wind speed measurements
        for (WindSpeed windSpeed : city.windSpeedMeasurements) {
            windSpeedMeasurements.add(new WindSpeed(windSpeed));
        }

        // Copy humidity measurements
        for (Humidity humidity : city.humidityMeasurements) {
            humidityMeasurements.add(new Humidity(humidity));
        }

        // Copy radiation absorption measurements
        for (RadiationAbsorption radiationAbsorption : city.radiationAbsorptionMeasurements) {
            radiationAbsorptionMeasurements.add(new RadiationAbsorption(radiationAbsorption));
        }
    }

    // #endregion

    // #region Getter methods
    // Note: Setter methods are not defined; because by design, these fields should
    // not be modified after initialization.

    //#region Getter methods for measurements lists

    /**
     * Getter method for windSpeedMeasurements field
     * 
     * @return windSpeedMeasurements field value
     */
    public ArrayList<WindSpeed> getWindSpeedMeasurements() {
        return windSpeedMeasurements;
    }

    /**
     * Getter method for humidityMeasurements field
     * 
     * @return humidityMeasurements field value
     */
    public ArrayList<Humidity> getHumidityMeasurements() {
        return humidityMeasurements;
    }

    /**
     * Getter method for radiationAbsorptionMeasurements field
     * 
     * @return radiationAbsorptionMeasurements field value
     */
    public ArrayList<RadiationAbsorption> getRadiationAbsorptionMeasurements() {
        return radiationAbsorptionMeasurements;
    }
    //#endregion

    //#region Getter methods for specific years

    /**
     * Getter method for the wind speed measurements of a specific year
     * 
     * @param year year of the measurements
     * @return wind speed measurements of the year
     */
    public ArrayList<WindSpeed> getWindSpeedMeasurements(int year) {
        ArrayList<WindSpeed> measurements = new ArrayList<WindSpeed>();
        for (WindSpeed windSpeed : windSpeedMeasurements) {
            if (windSpeed.getYear() == year) {
                measurements.add(windSpeed);
            }
        }
        return measurements;
    }

    /**
     * Getter method for the humidity measurements of a specific year
     * 
     * @param year year of the measurements
     * @return humidity measurements of the year
     */
    public ArrayList<Humidity> getHumidityMeasurements(int year) {
        ArrayList<Humidity> measurements = new ArrayList<Humidity>();
        for (Humidity humidity : humidityMeasurements) {
            if (humidity.getYear() == year) {
                measurements.add(humidity);
            }
        }
        return measurements;
    }

    /**
     * Getter method for the radiation absorption measurements of a specific year
     * 
     * @param year year of the measurements
     * @return radiation absorption measurements of the year
     */
    public ArrayList<RadiationAbsorption> getRadiationAbsorptionMeasurements(int year) {
        ArrayList<RadiationAbsorption> measurements = new ArrayList<RadiationAbsorption>();
        for (RadiationAbsorption radiationAbsorption : radiationAbsorptionMeasurements) {
            if (radiationAbsorption.getYear() == year) {
                measurements.add(radiationAbsorption);
            }
        }
        return measurements;
    }
    //#endregion

    //#region Getter methods for specific measurements

    /**
     * Getter method for the wind speed measurement of a specific month and year
     * 
     * @param month month of the measurement
     * @param year  year of the measurement
     * @return wind speed of the month
     */
    public WindSpeed getWindSpeedMeasurement(Month month, int year) {
        for (WindSpeed windSpeed : windSpeedMeasurements) {
            if (windSpeed.getYear() == year && windSpeed.getMonth() == month) {
                return windSpeed;
            }
        }
        return null;
    }

    /**
     * Getter method for the humidity measurement of a specific month and year
     * 
     * @param month month of the measurement
     * @param year  year of the measurement
     * @return humidity of the month
     */
    public Humidity getHumidityMeasurement(Month month, int year) {
        for (Humidity humidity : humidityMeasurements) {
            if (humidity.getYear() == year && humidity.getMonth() == month) {
                return humidity;
            }
        }
        return null;
    }

    /**
     * Getter method for the radiation absorption measurement of a specific month
     * and year
     * 
     * @param month month of the measurement
     * @param year  year of the measurement
     * @return radiation absorption of the month
     */
    public RadiationAbsorption getRadiationAbsorptionMeasurement(Month month, int year) {
        for (RadiationAbsorption radiationAbsorption : radiationAbsorptionMeasurements) {
            if (radiationAbsorption.getYear() == year && radiationAbsorption.getMonth() == month) {
                return radiationAbsorption;
            }
        }
        return null;
    }

    //#endregion


    //#endregion
    
    //#region Overrides
    @Override
    public String toString() {
        return "City: " + name + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Same object
        if (!super.equals(obj)) return false; // Ensure upper class values are equal
        if (obj == null || getClass() != obj.getClass()) return false; // Ensure object is of type City

        City other = (City) obj;

        // Check if measurements are equal
        if (!windSpeedMeasurements.equals(other.windSpeedMeasurements)) return false;
        if (!humidityMeasurements.equals(other.humidityMeasurements)) return false;
        if (!radiationAbsorptionMeasurements.equals(other.radiationAbsorptionMeasurements)) return false;

        return true; // All fields are equal
    }
    //#endregion

}
