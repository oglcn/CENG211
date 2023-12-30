package Data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO {

    public static ArrayList<Location> readLocations(String fileName) {
        ArrayList<Location> locations = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                if (parts.length > 0) {
                    // The first part is the country name
                    locations.add(new Country(parts[0]));

                    // The remaining parts are city names
                    for (int i = 1; i < parts.length; i++) {
                        locations.add(new City(parts[i]));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return locations;
    }
}
