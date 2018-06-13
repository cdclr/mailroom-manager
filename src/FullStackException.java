/**
 * @author Khalid Husain
 * Stony Brook ID: #109894542
 * Homework #3
 * Recitation: 03
 */
public class FullStackException extends Exception{
    private int stackNumber;
    public FullStackException(int stackNumber) {
        super("Stack "+stackNumber+" is full.");
        this.stackNumber = stackNumber;
    }
    public int getStackNumber() {
        return this.stackNumber;
    }
}
