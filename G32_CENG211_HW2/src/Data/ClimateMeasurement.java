package Data;

/**
 * Abstract class for climate measurements
 */
public class ClimateMeasurement {

	// Fields
	int year;
	Month month;

	//#region Constructors

	public ClimateMeasurement(int year, Month month) {
		this.year = year;
		this.month = month;
	}

	public ClimateMeasurement(ClimateMeasurement measurement) {
		// Copy constructor
		this(measurement.year, measurement.month);
	}

	//#endregion

	//#region Getter methods
	// Note: Setter methods are not needed, because the year and month values are given in the constructor and they shall not be changed.

	/**
	 * Getter method for year field
	 * @return year field value
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Getter method for month field
	 * @return month field value
	 */
	public Month getMonth() {
		return month;
	}

	//#endregion

	//#region Overrides

	@Override
	public String toString() {
		return "Year: " + year + ", Month: " + month;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true; // If the object is compared with itself then return true

		if (!(obj instanceof ClimateMeasurement))
			return false; // If the object is not a ClimateMeasurement object then return false

		ClimateMeasurement other = (ClimateMeasurement) obj;
		if (month != other.month)
			return false; // If the month is not equal then return false

		if (year != other.year)
			return false; // If the year is not equal then return false

		return true; // If all the fields are equal then return true
	}

}
