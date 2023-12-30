package Data;

public class RadiationAbsorption extends ClimateMeasurement{

    //#region Constants
    // 5.0 <= unitAbsorptionValue <= 20.0
    private static final double MAX_UNIT_ABSORPTION = 20.0;
    private static final double MIN_UNIT_ABSORPTION = 5.0;

    //#endregion

    //#region Fields
    private double unitAbsorptionValue;

    private RadiationIntensity radiationIntensity;
    
    //#endregion

    //#region Constructors


    /**
     * Constructor for RadiationAbsorption class
     * @param unitAbsorptionValue radiation absorption value in percentage
     * @param year year of the measurement
     * @param month month of the measurement
     * @param radiationIntensity radiation intensity value
     */
    public RadiationAbsorption(double unitAbsorptionValue, int year, Month month, RadiationIntensity radiationIntensity) {
        super(year, month);
        this.unitAbsorptionValue = unitAbsorptionValue;
        this.radiationIntensity = radiationIntensity;
    }


    /**
     * Copy constructor for RadiationAbsorption class
     * @param radiationAbsorption RadiationAbsorption object to be copied
     */
    public RadiationAbsorption(RadiationAbsorption radiationAbsorption) {
        this(radiationAbsorption.unitAbsorptionValue, radiationAbsorption.year, radiationAbsorption.month, radiationAbsorption.radiationIntensity);
    }



    /**
     * Constructor for RadiationAbsorption class with random radiation absorption value
     * @param year year of the measurement
     * @param month month of the measurement
     */
    public RadiationAbsorption(int year, Month month) {
        // Pick random radiation absorption value and initialize instance
        // MIN_UNIT_ABSORPTION <= unitAbsorptionValue <= MAX_UNIT_ABSORPTION
        this((Math.random() * (MAX_UNIT_ABSORPTION - MIN_UNIT_ABSORPTION + 1)) + MIN_UNIT_ABSORPTION, year, month, getRandomRadiationIntensity());


    }

    /**
     * Helper that generates random radiation intensity value
     * @return random radiation intensity value
     */
    private static RadiationIntensity getRandomRadiationIntensity() {
        RadiationIntensity[] radiationIntensityValues = RadiationIntensity.values();
        int randomIndex = (int) (Math.random() * radiationIntensityValues.length);
        return radiationIntensityValues[randomIndex];
    }

    //#endregion

    //#region Getter methods.
    // Note: Setter methods are not needed, because the radiation absorption value is given in the constructor and it shall not be changed.

    /**
     * Getter method for unitAbsorptionValue field
     * @return unitAbsorptionValue field value
     */
    public double getUnitAbsorptionValue() {
        return unitAbsorptionValue;
    }

    /**
     * Getter method for radiationIntensity field
     * @return radiationIntensity field value
     */
    public RadiationIntensity getRadiationIntensity() {
        return radiationIntensity;
    }

    //#endregion

    //#region Overrides
    @Override
    public String toString() {
        String firstLine = super.toString();
        String secondLine = "Radiation Absorption: " + unitAbsorptionValue + "%";
        return firstLine + "\n" + secondLine;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true; // If the object is compared with itself then return true

        if (!super.equals(obj))
            return false; // If the super class is not equal then return false

        if (getClass() != obj.getClass()) {
            return false; // If the object is not of type RadiationAbsorption then return false
        }
        RadiationAbsorption other = (RadiationAbsorption) obj;
        if (Double.doubleToLongBits(unitAbsorptionValue) != Double.doubleToLongBits(other.unitAbsorptionValue)) {
            return false; // If the unitAbsorptionValue is not equal then return false
        }
        return true; // If all the checks are passed then return true

    }

    //#endregion
}
