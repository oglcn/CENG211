package Business;

import java.util.ArrayList;

import Data.*;

public class ClimateRecord {
    // #region Fields
    private ArrayList<Location> cities;
    private ArrayList<Location> countries;

    // #region Constructors

    /**
     * Constructor for ClimateRecord class
     * 
     * @param filePath path of the file that contains the City and Country data
     */
    public ClimateRecord(String filePath) {
        // Read locations from file
        ArrayList<Location> locations = FileIO.readLocations(filePath);

        // Initialize ArrayLists
        cities = new ArrayList<Location>();
        countries = new ArrayList<Location>();

        // Separate cities and countries
        for (Location location : locations) {
            if (location instanceof City) {
                cities.add(location);
            } else if (location instanceof Country) {
                countries.add(location);
            }
        }
    }

    // #endregion

    // #region Getter methods for fields

    /**
     * Get names of all cities
     * @return ArrayList of city names
     */
    public ArrayList<String> getCityNames() {
        ArrayList<String> cityNames = new ArrayList<String>();

        for (Location city : cities) {
            cityNames.add(city.getName());
        }

        return cityNames;
    }

    /**
     * Get names of all countries
     * @return ArrayList of country names
     */
    public ArrayList<String> getCountryNames() {
        ArrayList<String> countryNames = new ArrayList<String>();

        for (Location country : countries) {
            countryNames.add(country.getName());
        }

        return countryNames;
    }

    /**
     * Get city
     * @param cityName name of the city
     */
    public City getCity(String cityName) {
        // Get Location
        Location location = findLocationByName(cityName);

        // Check if location is found
        if (location == null) {
            // Throw exception
            throw new IllegalArgumentException("Location not found!");
        }

        // Check if location is city
        if (!(location instanceof City)) {
            // Throw exception
            throw new IllegalArgumentException("Location is not a city!");
        }

        return (City) location;
    }

    /**
     * Get country
     * @param countryName name of the country
     */
    public Country getCountry(String countryName) {
        // Get Location
        Location location = findLocationByName(countryName);

        // Check if location is found
        if (location == null) {
            // Throw exception
            throw new IllegalArgumentException("Location not found!");
        }

        // Check if location is country
        if (!(location instanceof Country)) {
            // Throw exception
            throw new IllegalArgumentException("Location is not a country!");
        }

        return (Country) location;
    }



    // #endregion

    // #region Getter methods for calculated values

    /* Queries:
     * 
     * 
     * 1. Calculate average temperature for a country depending on the selected
     * options. These
     * options are type of the measurement value (Celsius, Fahrenheit, Kelvin) and
     * year (2020,
     * 2021, 2022).
     * 
     * 2. Calculate average temperature for a city depending on the selected
     * options. These options
     * are type of the measurement value (Celsius, Fahrenheit, Kelvin) and year
     * (2020, 2021,
     * 2022).
     * 
     * 3. Calculate average wind speed for a city depending on the selected options.
     * These options are
     * the unit of speed and month of the year. For example, find Izmir’s average
     * wind speed in
     * km/h in March of the given 3 years (2020, 2021, 2022).
     * 
     * 4. Calculate average humidity of a city across the 3-year period. (no options
     * other than the city
     * name)
     * 
     * 5. Find out how many times throughout a certain year radiationIntensity has
     * been one of the
     * three possible enum values. The options are the year (2020, 2021, 2022) and a
     * specific
     * intensity value (low, medium, high). For example, find how many times the
     * intensity value
     * has become medium in 2022 for Berlin.
     * 
     * 6. Calculate the “felt temperature” value for a specific month of a specific
     * year using the
     * formula provided below. For example, calculate the felt temperature value of
     * July 2021 for
     * Paris.
     */

    /**
     * Find location by name
     * 
     * @param locationName name of the location
     * @return Location object
     */
    private Location findLocationByName(String locationName) {
        // Check Countries
        for (Location country : countries) {
            if (country.getName().equals(locationName)) {
                return country;
            }
        }

        // Check Cities
        for (Location city : cities) {
            if (city.getName().equals(locationName)) {
                return city;
            }
        }

        return null; // Location not found
    }

    /**
     * Getter method that calculates average temperature for a Location depending on
     * the selected options. These options are type of the measurement value
     * (Celsius, Fahrenheit, Kelvin) and year (2020, 2021, 2022).
     *
     * @param locationName name of the location
     * @param year         year of the measurement
     * @param tempUnit     unit of the temperature
     * @return average temperature of the location
     */
    public double getAverageTemperature(String locationName, int year, TempUnit tempUnit) {
        double averageTemperature = 0;

        // Get location
        Location location = findLocationByName(locationName);

        // Check if location is found
        if (location == null) {
            // Throw exception
            throw new IllegalArgumentException("Location not found!");
        }

        // Get temperature measurements for the location
        ArrayList<Temperature> temperatureMeasurements = location.getTemperatureMeasurements(year);

        // Filter measurements by year
        ArrayList<Temperature> filteredMeasurements = new ArrayList<Temperature>();
        for (Temperature temperature : temperatureMeasurements) {
            if (temperature.getYear() == year) {
                filteredMeasurements.add(temperature);
            }
        }

        double totalTemperature = 0;
        // Calculate average temperature
        for (Temperature temperature : filteredMeasurements) {
            totalTemperature += temperature.getMeasurement(tempUnit);
        }
        averageTemperature = totalTemperature / filteredMeasurements.size();

        return averageTemperature;

    }

    /**
     * Getter method that calculates average wind speed for a city depending on the
     * selected options. These options are the unit of speed and month of the year.
     * For example, find Izmir’s average wind speed in km/h in March of the given 3
     * years (2020, 2021, 2022).
     *
     * @param cityName      name of the city
     * @param month         month of the measurement
     * @param year          year of the measurement
     * @param windSpeedUnit unit of the speed
     * @return average wind speed of the city
     */
    public double getAverageWindSpeed(String cityName, Month month, int year, WindSpeedUnit windSpeedUnit) {
        double averageWindSpeed = 0;

        // Get city
        City city = (City) findLocationByName(cityName);

        // Check if city is found
        if (city == null) {
            // Throw exception
            throw new IllegalArgumentException("City not found!");
        }

        // Get wind speed measurements for the city
        ArrayList<WindSpeed> windSpeedMeasurements = city.getWindSpeedMeasurements(year);

        // Filter measurements by month
        ArrayList<WindSpeed> filteredMeasurements = new ArrayList<WindSpeed>();
        for (WindSpeed windSpeed : windSpeedMeasurements) {
            if (windSpeed.getMonth() == month) {
                filteredMeasurements.add(windSpeed);
            }
        }

        double totalWindSpeed = 0;
        // Calculate average wind speed
        for (WindSpeed windSpeed : filteredMeasurements) {
            totalWindSpeed += windSpeed.getMeasurement(windSpeedUnit);
        }
        averageWindSpeed = totalWindSpeed / filteredMeasurements.size();

        return averageWindSpeed;


}

    /**
     * Getter method that calculates average humidity of a city across the 3-year
     * period. (no options other than the city name)
     *
     * @param cityName name of the city
     * @return average humidity of the city
     */
    public double getAverageHumidity(String cityName) {
        double averageHumidity = 0;

        // Get city
        City city = (City) findLocationByName(cityName);

        // Check if city is found
        if (city == null) {
            // Throw exception
            throw new IllegalArgumentException("City not found!");
        }

        // Get humidity measurements for the city
        ArrayList<Humidity> humidityMeasurements = city.getHumidityMeasurements();

        double totalHumidity = 0;
        // Calculate average humidity
        for (Humidity humidity : humidityMeasurements) {
            totalHumidity += humidity.getHumidityPercentage();
        }
        averageHumidity = totalHumidity / humidityMeasurements.size();

        return averageHumidity;
    }

    /**
     * Getter method that finds out how many times throughout a certain year
     * radiationIntensity has been one of the three possible enum values. The
     * options are the year (2020, 2021, 2022) and a specific intensity value (low,
     * medium, high). For example, find how many times the intensity value has
     * become medium in 2022 for Berlin.
     *
     * @param cityName             name of the city
     * @param year                 year of the measurement
     * @param radiationIntensity   intensity of the radiation
     * @return number of times the intensity value has become "radiationIntensity" in "year" for
     *         "cityName"
     */
    public int getRadiationIntensityCount(String cityName, int year, RadiationIntensity radiationIntensity) {
        int count = 0;

        // Get city
        City city = (City) findLocationByName(cityName);

        // Check if city is found
        if (city == null) {
            // Throw exception
            throw new IllegalArgumentException("City not found!");
        }

        // Get radiation absorption measurements for the city
        ArrayList<RadiationAbsorption> radiationAbsorptionMeasurements = city.getRadiationAbsorptionMeasurements(year);

        // Filter measurements by radiation intensity
        ArrayList<RadiationAbsorption> filteredMeasurements = new ArrayList<RadiationAbsorption>();
        for (RadiationAbsorption radiationAbsorption : radiationAbsorptionMeasurements) {
            if (radiationAbsorption.getRadiationIntensity() == radiationIntensity) {
                filteredMeasurements.add(radiationAbsorption);
            }
        }

        count = filteredMeasurements.size();

        return count;

    }

    /**
     * Getter method that calculates the “felt temperature” value for a specific
     * month of a specific year using the formula provided below. For example,
     * calculate the felt temperature value of July 2021 for Paris.
     *
     * @param cityName name of the city
     * @param month    month of the measurement
     * @param year     year of the measurement
     * @param tempUnit unit of the temperature
     * @return felt temperature of the city
     * 
     * Felt Temperature Formula:
     * FT = CT + 0.3 * H – 0.7 * (RA / (WS + 10))
     * FT: Felt temperature (in Celsius)
     * CT: City temperature (in Celsius)
     * H: Humidity (in [0, 1] interval)
     * WS: Wind speed (in meter/second)
     * RA: Radiation absorption (use unitAbsorbtionValue attribute)
     */
    public double getFeltTemperature(String cityName, Month month, int year, TempUnit tempUnit) {

        // Get city
        City city = (City) findLocationByName(cityName);

        // Check if city is found
        if (city == null) {
            // Throw exception
            throw new IllegalArgumentException("City not found!");
        }

        // Get city temperature
        Temperature cityTemperature = city.getTemperatureMeasurement(month, year);

        // Get humidity
        Humidity humidity = city.getHumidityMeasurement(month, year);

        // Get wind speed
        WindSpeed windSpeed = city.getWindSpeedMeasurement(month, year);

        // Get radiation absorption
        RadiationAbsorption radiationAbsorption = city.getRadiationAbsorptionMeasurement(month, year);

    
        // Calculate felt temperature
        double feltTemperature = cityTemperature.getCelciusMeasurement() + 0.3 * humidity.getHumidityPercentage()
                - 0.7 * (radiationAbsorption.getUnitAbsorptionValue()
                        / (windSpeed.getMetersPerSecond() + 10));

        return feltTemperature;

    }

    // #endregion
}