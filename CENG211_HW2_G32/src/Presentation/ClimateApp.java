package Presentation;

import java.util.ArrayList;
import java.util.Scanner;
import Data.*;
import Business.*;

public class ClimateApp {

    // File path
    private static final String FILE_PATH = "countries_and_cities.csv";

    // Year range for measurements
    // Made public for use throughout the project as configuration
    public static final int MIN_YEAR = 2020;
    public static final int MAX_YEAR = 2022;

    public static void main(String[] args) {

        ClimateRecord climateRecord = new ClimateRecord(FILE_PATH);

        // Ask user for input until they choose to exit
        while (true) {
            int choice = getMenuSelection();
            int year;
            City city;
            Country country;
            Month month;
            TempUnit tempUnit;
            WindSpeedUnit windSpeedUnit;

            switch (choice) {
                case 1:
                    // Calculate average temperature for a country according to temperature unit and
                    // year.
                    country = getCountrySelection(climateRecord);
                    tempUnit = getTemperatureUnitSelection();
                    year = getYearSelection();
                    // Get average temperature for the given country, temperature unit and year
                    double avgTemp = climateRecord.getAverageTemperature(country.getName(), year, tempUnit);

                    // Get tempUnit symbol
                    String tempUnitSymbol = getTempUnitSymbol(tempUnit);

                    System.out.println("Average temperature for " + country.getName() + " in " + year + " is "
                            + avgTemp + " " + tempUnitSymbol);
                    break;
                case 2:
                    // Calculate average temperature for a city according to temperature unit and
                    // year.
                    city = getCitySelection(climateRecord);
                    tempUnit = getTemperatureUnitSelection();
                    year = getYearSelection();
                    // Get average temperature for the given city, temperature unit and year
                    avgTemp = climateRecord.getAverageTemperature(city.getName(), year, tempUnit);

                    // Get tempUnit symbol
                    tempUnitSymbol = getTempUnitSymbol(tempUnit);

                    System.out.println("Average temperature for " + city.getName() + " in " + year + " is " + avgTemp
                            + " " + tempUnitSymbol);
                    break;
                case 3:
                    // Calculate average wind speed for a city according to speed unit and month,
                    // across all years.
                    city = getCitySelection(climateRecord);
                    windSpeedUnit = getWindSpeedUnitSelection();
                    month = getMonthSelection();
                    year = getYearSelection();

                    // Years to calculate average wind speed
                    ArrayList<Integer> years = new ArrayList<Integer>();
                    for (int i = MIN_YEAR; i <= MAX_YEAR; i++) {
                        years.add(i);
                    }

                    // For all years in the range, get average wind speed for the given city, wind
                    // speed unit and month
                    // Then, calculate the average of the averages
                    double avgWindSpeed = 0;
                    for (int year2 : years) {
                        avgWindSpeed += climateRecord.getAverageWindSpeed(city.getName(), month, year2, windSpeedUnit);
                    }
                    avgWindSpeed /= years.size();

                    // Get windSpeedUnit symbol
                    String windSpeedUnitSymbol = getWindSpeedUnitSymbol(windSpeedUnit);

                    System.out.println("Throughout all years, average wind speed for " + city.getName() + " in " + month
                            + " is " + avgWindSpeed + " " + windSpeedUnitSymbol);

                    break;

                case 4:
                    // Calculate average humidity of a city for every year.
                    city = getCitySelection(climateRecord);

                    // Years to calculate average humidity
                    years = new ArrayList<Integer>();
                    for (int i = MIN_YEAR; i <= MAX_YEAR; i++) {
                        years.add(i);
                    }

                    // For all years in the range, get average humidity for the given city and year
                    // Then, calculate the average of the averages
                    double avgHumidity = 0;

                    avgHumidity = climateRecord.getAverageHumidity(city.getName());

                    avgHumidity /= years.size();

                    System.out.println("Throughout all years, average humidity for " + city.getName() + " is "
                            + avgHumidity + "%");
                    break;

                case 5:
                    // Count how many times a year a specific radiation intensity value appears.
                    city = getCitySelection(climateRecord);
                    RadiationIntensity radiationIntensity = getRadiationIntensitySelection();

                    // Years to calculate radiation intensity
                    years = new ArrayList<Integer>();
                    for (int i = MIN_YEAR; i <= MAX_YEAR; i++) {
                        years.add(i);
                    }

                    // For all years in the range, get radiation intensity count for the given city
                    // and radiation intensity
                    // Then, calculate the sum of the counts
                    int radiationIntensityCount = 0;
                    for (int year2 : years) {
                        radiationIntensityCount += climateRecord.getRadiationIntensityCount(city.getName(), year2,
                                radiationIntensity);
                    }

                    System.out.println("Throughout all years, radiation intensity count for " + city.getName() + " is "
                            + radiationIntensityCount);
                    break;

                case 6:
                    // Calculate the “felt temperature” value for a specific month.
                    city = getCitySelection(climateRecord);
                    month = getMonthSelection();
                    year = getYearSelection();
                    tempUnit = getTemperatureUnitSelection();

                    // Get felt temperature for the given city, month and temperature unit
                    double feltTemperature = climateRecord.getFeltTemperature(city.getName(), month, year, tempUnit);

                    // Get tempUnit symbol
                    tempUnitSymbol = getTempUnitSymbol(tempUnit);

                    System.out.println("Felt temperature for " + city.getName() + " in " + month + " is "
                            + feltTemperature + " " + tempUnitSymbol);
                    break;

                case 7:
                    // Exit the application.
                	
                    System.exit(0);
                    break;
            }

        }

    }

    /**
     * Get Temperature Unit Symbol
     * 
     * @param tempUnit Temperature Unit
     * @return Temperature Unit Symbol
     */
    public static String getTempUnitSymbol(TempUnit tempUnit) {
        switch (tempUnit) {
            case CELCIUS:
                return "°C";
            case FAHRENHEIT:
                return "°F";
            case KELVIN:
                return "K";
            default:
                return "";
        }
    }

    /**
     * Get Wind Speed Unit Symbol
     * 
     * @return Wind Speed Unit Symbol
     */
    public static String getWindSpeedUnitSymbol(WindSpeedUnit windSpeedUnit) {
        switch (windSpeedUnit) {
            case METERS_PER_SECOND:
                return "m/s";
            case KILOMETERS_PER_HOUR:
                return "km/h";
            default:
                return "";
        }
    }

    /**
     * Displays the menu to the user and return the user answer
     * 
     * Menu:
     * 
     * [1] Calculate average temperature for a country according to temperature unit
     * and year.
     * [2] Calculate average temperature for a city according to temperature unit
     * and year.
     * [3] Calculate average wind speed for a city according to speed unit and year.
     * [4] Calculate average humidity of a city for every year.
     * [5] Count how many times a year a specific radiation intensity value appears.
     * [6] Calculate the “felt temperature” value for a specific month.
     * [7] Exit the application.
     * 
     */
    public static int getMenuSelection() {
        System.out.println("Menu:\n");
        System.out.println("[1] Calculate average temperature for a country according to temperature unit and year.");
        System.out.println("[2] Calculate average temperature for a city according to temperature unit and year.");
        System.out.println("[3] Calculate average wind speed for a city according to speed unit and year.");
        System.out.println("[4] Calculate average humidity of a city for every year.");
        System.out.println("[5] Count how many times a year a specific radiation intensity value appears.");
        System.out.println("[6] Calculate the felt temperature value for a specific month.");
        System.out.println("[7] Exit the application.");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        //
        return choice;
    }

    /**
     * Displays the temperature unit menu to the user and return the user answer
     * [1] Celsius [2] Fahrenheit [3] Kelvin
     */
    public static TempUnit getTemperatureUnitSelection() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Temperature Unit:");
        System.out.println("[1] Celsius [2] Fahrenheit [3] Kelvin");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        //
        switch (choice) {
            case 1:
                return TempUnit.CELCIUS;
            case 2:
                return TempUnit.FAHRENHEIT;
            case 3:
                return TempUnit.KELVIN;
            default:
                return TempUnit.CELCIUS;
        }

    }

    /**
     * Displays the wind speed unit menu to the user and return the user answer
     * [1] Meters Per Second [2] Kilometers Per Hour
     */
    public static WindSpeedUnit getWindSpeedUnitSelection() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wind Speed Unit:");
        System.out.println("[1] Meters Per Second [2] Kilometers Per Hour");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        //
        switch (choice) {
            case 1:
                return WindSpeedUnit.METERS_PER_SECOND;
            case 2:
                return WindSpeedUnit.KILOMETERS_PER_HOUR;
            default:
                return WindSpeedUnit.METERS_PER_SECOND;
        }
    }

    /**
     * Displays the month menu to the user and return the user answer
     * [1] January [2] February [3] March [4] April [5] May [6] June [7] July [8]
     * August [9] September [10] October [11] November [12] December
     */
    public static Month getMonthSelection() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Month:");
        System.out.println(
                "[1] January [2] February [3] March [4] April [5] May [6] June [7] July [8] August [9] September [10] October [11] November [12] December");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        //
        switch (choice) {
            case 1:
                return Month.JANUARY;
            case 2:
                return Month.FEBRUARY;
            case 3:
                return Month.MARCH;
            case 4:
                return Month.APRIL;
            case 5:
                return Month.MAY;
            case 6:
                return Month.JUNE;
            case 7:
                return Month.JULY;
            case 8:
                return Month.AUGUST;
            case 9:
                return Month.SEPTEMBER;
            case 10:
                return Month.OCTOBER;
            case 11:
                return Month.NOVEMBER;
            case 12:
                return Month.DECEMBER;
            default:
                // Throw exception
                throw new IllegalArgumentException("Invalid month number");

        }
    }

    /**
     * Displays the radiation intensity menu to the user and return the user answer
     * [1] Low [2] Medium [3] High
     */

    public static RadiationIntensity getRadiationIntensitySelection() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Radiation Intensity:");
        System.out.println("[1] Low [2] Medium [3] High");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        //
        switch (choice) {
            case 1:
                return RadiationIntensity.LOW;
            case 2:
                return RadiationIntensity.MEDIUM;
            case 3:
                return RadiationIntensity.HIGH;
            default:
                return RadiationIntensity.LOW;
        }
    }

    /**
     * Displays the city menu to the user and return the user answer
     * Example:
     * [1] Ankara [2] Istanbul [3] Izmir
     * Enter your choice as number: 1
     */
    public static City getCitySelection(ClimateRecord climateRecord) {

        // Get all city names
        ArrayList<String> cityNames = climateRecord.getCityNames();

        System.out.println("Cities:");

        // Display city names
        for (int i = 0; i < cityNames.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + cityNames.get(i));
        }

        // Get user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your choice as number: ");
        int choice = scanner.nextInt();
        //

        // Find the city with the given index
        return climateRecord.getCity(cityNames.get(choice - 1));
    }

    /**
     * Displays the country menu to the user and return the user answer
     * Example:
     * [1] Turkey [2] Germany [3] France
     * Enter your choice as number: 1
     */
    public static Country getCountrySelection(ClimateRecord climateRecord) {

        // Get all country names
        ArrayList<String> countryNames = climateRecord.getCountryNames();

        System.out.println("Countries:");

        // Display country names
        for (int i = 0; i < countryNames.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + countryNames.get(i));
        }

        // Get user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your choice as number: ");
        int choice = scanner.nextInt();
        

        // Find the country with the given index
        return climateRecord.getCountry(countryNames.get(choice - 1));
    }

    /**
     * Displays the year menu to the user and return the user answer
     * Example:
     * [1] 2020 [2] 2021 [3] 2022
     * Enter your choice as number: 1
     */
    public static int getYearSelection() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Year:");

        // Get year range from configuration
        ArrayList<Integer> years = new ArrayList<Integer>();
        for (int i = MIN_YEAR; i <= MAX_YEAR; i++) {
            years.add(i);
        }

        // Display years
        for (int i = 0; i < years.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + years.get(i));
        }

        // Get user input
        System.out.print("Enter your choice as number: ");
        int choice = scanner.nextInt();
        //

        // Find the year with the given index
        return years.get(choice - 1);
    }

}
