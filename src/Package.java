/**
 * @author Khalid Husain
 * Stony Brook ID: #109894542
 * Homework #3
 * Recitation: 03
 */
public class Package {
	private String recipient;
	private int arrivalDate = 0;
	private double weight;
	/**
	 * Overloaded constructor of the Package class.
	 * @param recipient
	 * 			The name of the package's recipient as a String.
	 * @param arrivalDate
	 * 			The date the package arrived as an int.
	 * @param weight
	 * 			The weight of the package as a double.
	 * <dt><b>Postconditions<b><dt>
	 * 			A Package object has been instantiated and its recipient, arrivalDate, and weight set to the recipient, arrivalDate, and weight parameters, respectively.
	 */
	public Package(String recipient, int arrivalDate, double weight) {
		this.recipient = recipient;
		this.arrivalDate = arrivalDate;
		this.weight = weight;
	}
	/**
	 * Changes the recipient of this Package to the specified String.
	 * @param recipient
	 * 			The new recipient of the Package as a String.
	 */
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	/**
	 * Changes the arrivalDate of this Package to the specified int.
	 * @param arrivalDate
	 * 			The new arrivalDate of this package as an int.
	 */
	public void setArrivalDate(int arrivalDate) {
	    this.arrivalDate = arrivalDate;
	}
	/**
	 * Changes the weight of this Package to the specified double.
	 * @param weight
	 * 			The new weight of this package as a double.
	 */
	public void setWeight(double weight) {
	    this.weight = weight;
	}
	/**
	 * Retrieves the recipient of a specific Package.
	 * @return
	 * 			The recipient of this Package as a String.
	 */
	public String getRecipient() {
	    return this.recipient;
	}
	/**
	 * Retrieves the arrival date of a specified Package.
	 * @return
	 * 			The arrivalDate of this Package as an int.
	 */
	public int getArrivalDate() {
	    return this.arrivalDate;
	}
	/**
	 * Retrieves the weight of a specified Package.
	 * @return
	 * 			The weight of this Package as a double.
	 */
	public double getWeight() {
	    return this.weight;
	}
	/**
	 * Creates a String with the recipient and arrivalDate of a specified Package.
	 * @return
	 * 			A text representation of this Package's intended recipient and arrivalDate.
	 */
	public String toString() {
	    return this.recipient+" "+this.arrivalDate;
	}
}

