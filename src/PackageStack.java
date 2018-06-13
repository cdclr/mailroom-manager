/**
 * @author Khalid Husain
 * Stony Brook ID: #109894542
 * Homework #3
 * Recitation: 03
 */
import java.util.*;
public class PackageStack extends ArrayList<Package>{
    private final int CAPACITY = 7;
    private int stackNumber;
    private boolean isFloorStack;
    /**
     * Overloaded constructor of the PackageStack class.
     * @param stackNumber
     * 			The number of the PackageStack as an int.
     * @param isFloorStack
     * 			Whether or not the PackageStack is the Floor Stack as a boolean.
     * <dt><b>Postconditions<b><dt>
     * 			A PacakgeStack has been instantiated and its stackNumber and isFloorStack set to the stackNumber and isFloorStack parameters, respectively.
     */
    public PackageStack(int stackNumber, boolean isFloorStack) {
        super();
        this.stackNumber = stackNumber;
        this.isFloorStack = isFloorStack;
    }
    /**
     * Places a Package to the top of this PackageStack.
     * @param x
     * 			The Package to be placed on the top of this PackageStack.
     * @throws FullStackException
     * 			Indicates that the PackageStack the Package would be pushed to is full and is not the Floor Stack.
     */
    public void push(Package x) throws FullStackException{
        if (this.size()==CAPACITY && isFloorStack==false)
            throw new FullStackException(this.stackNumber);
        else
            this.add(x);
    }
    /**
     * Removes the top Package of this PackageStack.
     * @return
     * 			The Package that was removed from the top of this PackageStack.
     * @throws EmptyStackException
     * 			Indicates that the PackageStack is empty and has no Packages in it.
     */
    public Package pop() throws EmptyStackException{
        if (this.size()==0)
            throw new EmptyStackException(this.stackNumber);
        else
            return this.remove(this.size()-1);
    }
    /**
     * Retrieves the top Package of this PackageStack.
     * @return
     * 			The Package at the top of this PackageStack.
     * @throws EmptyStackException
     * 			Indicates that this PackageStack is empty and has no Packages in it.
     */
    public Package peek() throws EmptyStackException{
        if (this.size()==0)
            throw new EmptyStackException(this.stackNumber);
        else
            return this.get(this.size()-1);
    }
    /**
     * Checks if this PackageStack is full.
     * @return
     * 			True if this PackageStack is full, false otherwise.
     */
    public boolean isFull() {
        if (this.size()==CAPACITY && isFloorStack==false)
            return true;
        else
            return false;
    }
    /**
     * Checks if this PackageStack is empty.
     * @return
     * 			True if this PackageStack is empty, false otherwise.
     */
    public boolean isEmpty() {
        if (this.size()==0)
            return true;
        else
            return false;
    }
    /**
     * Gives the max number of Packages in this PackageSteak.
     * @return
     * 			The max number of Packages in this PackageStack as an int.
     */
    public int getCapacity() {
    	return this.CAPACITY;
    }
    /**
     * Gives the number of the PackageStack.
     * @return
     * 			The stackNumber of this PackageStack as an int.
     */
    public int getStackNumber() {
    	return this.stackNumber;
    }
    /**
     * Overloaded contains method from the Object class that checks if this PackageStack contains any Packages with a specified recipientName.
     * Used in Mailroom Manager.
     * @param s
     * 			The recipientName to check the PackageStack for.
     * @return
     * 			True if this PackageStack has any packages with the specified recipientName, false otherwise.
     */
    public boolean contains(String s) {
    	for (int i = 0; i<this.size(); i++)
    		if (this.get(i).getRecipient().toLowerCase().equals(s.toLowerCase()))
    			return true;
    	return false;
    }
    /**
     * Creates a String representation of all the Packages' recipientNames and arrivalDates in this Package Stack
     * @return
     * 			All Packages' recipientNames and arrivalDates in this PackageStack as a String.
     */
    public String toString() {
        if (this.size()==0)
            return "empty.";
        String output = "";
        for (int i = 0; i<this.size(); i++)
            output+=("["+this.get(i).toString()+"] ");
        return output;
    }
}
