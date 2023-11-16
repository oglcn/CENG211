package Data;


public class Country extends Location {
    
    //#region Constructors

    /**
     * Constructor for Country class
     * @param name name of the country
     */
    public Country(String name) {
        super(name);
    }

    /**
     * Copy constructor for Country class
     * @param country Country object to be copied
     */
    public Country(Country country) {
        super(country);
    }


}
