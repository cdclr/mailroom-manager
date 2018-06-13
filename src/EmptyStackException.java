/**
 * @author Khalid Husain
 * Stony Brook ID: #109894542
 * Homework #3
 * Recitation: 03
 */
public class EmptyStackException extends Exception{
    private int stackNumber;
    public EmptyStackException(int stackNumber) {
        super("Stack "+stackNumber+" is empty.");
        this.stackNumber = stackNumber;
    }
    public int getStackNumber() {
        return this.stackNumber;
    }
}
