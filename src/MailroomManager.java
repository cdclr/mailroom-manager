/**
 * @author Khalid Husain
 * Stony Brook ID: #109894542
 * Homework #3
 * Recitation: 03
 */
import java.util.*;
public class MailroomManager {
	//Self Explanatory
    private static boolean programContinues = true;
    //6 Stacks to be utilized by subsequent methods corresponding to menu choices. One Stack is the floorStack with no size limit.
    private static PackageStack stackAG;
    private static PackageStack stackHJ;
    private static PackageStack stackKM;
    private static PackageStack stackNR;
    private static PackageStack stackSZ;
    private static PackageStack stackFloor;
    //The current day
    private static int currentDay = 0;
    /**
     * Delivers a package to the Mailroom and places it in the appropriate alphabetical PackageStack.
     * Prompts and accepts user input for the Package's recipient and weight.
     * Ensures user input is in the proper form; returns to the main method otherwise.
     * Assigns the arrivalDate of the package to the currentDay value aforementioned.
     * If the proper alphabetical PackageStack is full, the Package will be attempted to be added to the PackageStack directly above and below the original one, then two above and below.
     * 		-StackAG wraps around to stackSZ and vice versa for this checking process.
     */
    public static void deliverPackage() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the recipient name: ");
        String recipientName = input.next();
        char firstLetter = Character.toUpperCase(recipientName.charAt(0));
        if (firstLetter<'A' || firstLetter>'Z') {
            System.out.println("Please enter a name using only the English alphabet. \nTaking you back to the main menu.");
            return;
        }
        System.out.println("Please enter the weight (lbs): ");
        double packageWeight;
        if (input.hasNextDouble())
            packageWeight = input.nextDouble();
        else {
            System.out.println("Invalid weight; taking you back to the main menu.");
            return;
        }
        PackageStack targetStack = stackAG;
        Package deliveredPackage = new Package(recipientName, currentDay, packageWeight);
        if (firstLetter>='H' && firstLetter<='J')
            targetStack = stackHJ;
        if (firstLetter>='K' && firstLetter<='M')
            targetStack = stackKM;
        if (firstLetter>='N' && firstLetter<='R')
            targetStack = stackNR;
        if (firstLetter>='S' && firstLetter<='Z')
            targetStack = stackSZ;
        try {
            targetStack.push(deliveredPackage);
            System.out.println("A "+packageWeight+" lb package is awaiting pickup by "+recipientName+".");
        }
        //If the correct PackageStack is full, the method tries to place the Package into the PackageStack alphabetically above and below the original PackageStack.
        catch (FullStackException ex) {
        	PackageStack stacks[] = {stackAG, stackHJ, stackKM, stackNR, stackSZ};
        	int targetStackArrayLocation = targetStack.getStackNumber()-1;
        	//a = search range from full stack
        	String fullStacks = ""+targetStack.getStackNumber();
        	for (int i = targetStackArrayLocation, a = 1; a<stacks.length/2; a++) {
        		if ((i-a)<0) {
        			try {
        				stacks[stacks.length+(i-a)].push(deliveredPackage);
        				System.out.println("A "+packageWeight+" lb package is awaiting pickup by "+recipientName+". As stack(s) "+fullStacks+" was(were) full, it was placed in stack "+
        				stacks[stacks.length+(i-a)].getStackNumber());
        				return;
        			}
        			catch (FullStackException ex1) {
        				fullStacks+=""+stacks[stacks.length+(i-a)].getStackNumber()+", ";
        			}
        		}
        		if ((i+a)>stacks.length-1) {
        			try {
        				stacks[i+a-stacks.length].push(deliveredPackage);
        				System.out.println("A "+packageWeight+" lb package is awaiting pickup by "+recipientName+". As stack(s) "+fullStacks+" was(were) full, it was placed in stack "+
        				stacks[i+a-stacks.length].getStackNumber());
        				return;
        			}
        			catch (FullStackException ex2) {
        				fullStacks+=""+stacks[i+a-stacks.length].getStackNumber()+", ";
        			}
        		}
        		else {
        			try {
        				stacks[i-a].push(deliveredPackage);
        				System.out.println("A "+packageWeight+" lb package is awaiting pickup by "+recipientName+". As stack(s) "+fullStacks+" was(were) full, it was placed in stack "+
        				stacks[i-a].getStackNumber());
        				return;
        			}
        			catch (FullStackException ex3) {
        				fullStacks+=""+stacks[i-a].getStackNumber()+", ";
        				try {
        					stacks[i+a].push(deliveredPackage);
        					System.out.println("A "+packageWeight+" lb package is awaiting pickup by "+recipientName+". As stack(s) "+fullStacks+" was(were) full, it was placed in stack "+
        					stacks[i+a].getStackNumber());
        					return;
        				}
        				catch (FullStackException ex4) {
        					fullStacks+=""+stacks[i-a].getStackNumber()+", ";
        				}
        			}
        		}
        	}
        	try {
				stackFloor.push(deliveredPackage);
				System.out.println("All stacks were full, so "+recipientName+"'s "+packageWeight+" lb package is awaiting pickup in the Floor Stack.");
				return;
			} 
    		catch (FullStackException e) {
			}
        }
    }
    /**
     * Removes the most recent Package of a specified recipient from its PackageStack.
     * Prompts and accepts user input for the name of the recipient of the Package.
     * Ensures user input is in the proper form; returns to the main method otherwise.
     * Moves all the packages ahead of said Package in its PackageStack to the Floor Stack, then returns them to their PackageStack after the said Package is removed.
     */
    public static void getPackage() {
    	Scanner input = new Scanner(System.in);
    	System.out.println("Please enter the recipient name: ");
    	String recipient = input.next();
    	boolean recipientExists = false;
    	PackageStack targetStack = stackAG;
    	//Using the overloaded contains() method implemented in the PackageStack class
    	if (stackAG.contains(recipient))
    		recipientExists = true;
    	if (stackHJ.contains(recipient)) {
    		targetStack = stackHJ;
    		recipientExists = true;
    	}
    	if (stackKM.contains(recipient)) {
    		targetStack = stackKM;
    		recipientExists = true;
    	}
    	if (stackNR.contains(recipient)) {
    		targetStack = stackNR;
    		recipientExists = true;
    	}
    	if (stackSZ.contains(recipient)) {
    		targetStack = stackSZ;
    		recipientExists = true;
    	}
    	if (!recipientExists) {
    		System.out.println("There are no packages for a recipient with the name "+recipient+". Taking you back to the main menu.");
    		return;
    	}
    	for (int i = 0; i<targetStack.getCapacity(); i++) {
    		try { 
    			if (targetStack.peek().getRecipient().toLowerCase().equals(recipient.toLowerCase())) {
    				System.out.println("Move "+(i)+" packages from Stack "+targetStack.getStackNumber()+" to floor.\n");
    				printStacks();
    				Package packageGiven = targetStack.pop();
    				System.out.println("\nGive "+packageGiven.getRecipient()+" a "+packageGiven.getWeight()+" lb package delivered on day "+packageGiven.getArrivalDate()+".\n");
    				System.out.println("Return "+(i)+" packages to Stack "+targetStack.getStackNumber()+" from floor.\n");
    				while (i>0) {
    					targetStack.push(stackFloor.pop());
    					i--;
    				}
    				printStacks();
    				return;
    			}
    			else {
    				stackFloor.push(targetStack.pop());
    			}
    		}
    		catch (EmptyStackException ex) {
    			//This whole method is set up to have this exception not be thrown, because the recipient will be somewhere in the targetStack as per previous code.
    		}
    		catch (FullStackException ex1) {
    			//stackFloor can never throw a FullStackException with its push() method, but per assignment specifications there is only supposed to be one PackageStack class.
    		}
    	}
    }
    /**
     * Increments the currentDay marker by 1.
     */
    public static void makeItTomorrow() {
    	System.out.println("It is now day "+(++currentDay)+".");
    }
    //public static void makeItYesterday() {
    	//System.out.println("I wish I had this power in real life");
    //}
    /**
     * Prints all the PackageStacks, including the FloorStacks, with their Packages' recipientNames and arrivalDates.
     */
    public static void printStacks() {
        System.out.println("Current Packages: \n------------------------------------------- \nStack 1 (A-G): |"+stackAG.toString()
        +"\nStack 2 (H-J):|"+stackHJ.toString()+"\nStack 3 (K-M):|"+stackKM.toString()+"\nStack 4 (N-R):|"+stackNR.toString()
        +"\nStack 5 (S-Z):|"+stackSZ.toString()+"\nFloor:|"+stackFloor.toString());
    }
    /**
     * Moves the top Package of one PackageStack to another.
     * Prompts and accepts user input for the source and destination PackageStacks.
     * Ensures user input is in the proper form; returns to the main method otherwise.
     */
    public static void movePackage() {
    	Scanner input = new Scanner(System.in);
    	System.out.println("Please enter the source stack (enter 0 for floor): ");
    	int sourceStackNumber;
    	try {
    		sourceStackNumber = input.nextInt();
    	}
    	catch (InputMismatchException ex) {
    		System.out.println("Please enter an integer as the stack number");
    		return;
    	}
    	System.out.println("Please enter the destination stack");
    	int destinationStackNumber;
    	try {
    		destinationStackNumber = input.nextInt();
    	}
    	catch (InputMismatchException ex1) {
    		System.out.println("Please enter an integer as the stack number");
    		return;
    	}
    	PackageStack sourceStack;
    	switch (sourceStackNumber) {
    		case 0:
    			sourceStack = stackFloor;
    			break;
    		case 1:
    			sourceStack = stackAG;
    			break;
    		case 2:
    			sourceStack = stackHJ;
    			break;
    		case 3:
    			sourceStack = stackKM;
    			break;
    		case 4:
    			sourceStack = stackNR;
    			break;
    		case 5:
    			sourceStack = stackSZ;
    			break;
    		default:
    			System.out.println("Please enter a destination stack number between 0 and 5 (inclusive).");
    			return;
    	}
    	PackageStack destinationStack;
    	switch (destinationStackNumber) {
    		case 0:
    			destinationStack = stackFloor;
    			break;
    		case 1:
    			destinationStack = stackAG;
    			break;
    		case 2:
    			destinationStack = stackHJ;
    			break;
    		case 3:
    			destinationStack = stackKM;
    			break;
    		case 4:
    			destinationStack = stackNR;
    			break;
    		case 5:
    			destinationStack = stackSZ;
    			break;
    		default:
    			System.out.println("Please enter a destination stack number between 0 and 5 (inclusive).");
    			return;
    	}
    	try {
    		destinationStack.push(sourceStack.pop());
    	}
    	catch (EmptyStackException ex1) {
    		System.out.println("Stack "+sourceStack.getStackNumber()+" was empty.");
    		return;
    	}
    	catch (FullStackException ex2) {
    		System.out.println("Stack "+destinationStack.getStackNumber()+" was full.");
    		return;
    	}
    }
    /**
     * Moves all the Packages in the incorrect alphabetical PackageStack to the Floor Stack.
     */
    public static void unsortedPackagesToFloor() {
    	PackageStack targetStack = stackFloor;
    	char lowerBound = 'A';
		char upperBound = 'Z';
    	for (int i = 1; i<=5; i++) {
    		switch (i) {
    			case 1:
    				targetStack = stackAG;
    				lowerBound = 'A';
    				upperBound = 'G';
    				break;
    			case 2:
    				targetStack = stackHJ;
    				lowerBound = 'H';
    				upperBound = 'J';
    				break;
    			case 3:
    				targetStack = stackKM;
    				lowerBound = 'K';
    				upperBound = 'M';
    				break;
    			case 4:
    				targetStack = stackNR;
    				lowerBound = 'N';
    				upperBound = 'R';
    				break;
    			case 5:
    				targetStack = stackSZ;
    				lowerBound = 'S';
    				upperBound = 'Z';
    				break;
    		}
    		for (int k = 0; k<targetStack.size(); k++) {
    			if (!(Character.toUpperCase(targetStack.get(k).getRecipient().charAt(0))>=lowerBound && Character.toUpperCase(targetStack.get(k).getRecipient().charAt(0))<=upperBound)) {
    				try {
    					stackFloor.push(targetStack.remove(k));
    				}
    				catch (FullStackException ex) {
    					//stackFloor will never be full, but as per assignment specifications, there should only be one PackageClass
    				}
    			}
    		}
    	}
    	System.out.println("Packages in the wrong alphabetical stack were moved to the floor");
    	return;
    }
    /**
     * Lists all packages for a recipient.
     * Prompts and accepts user input for the name of the recipient.
     * Ensures user input is in the proper form; returns to the main method otherwise.
     */
    public static void listPackagesForUser() {
    	Scanner input = new Scanner(System.in);
    	System.out.println("Please enter the recipient name: ");
    	String recipientName = input.next();
    	int numPackages = 0;
    	String output = "";
    	PackageStack targetStack = stackFloor;
    	for (int i = 1; i<=6; i++) {
    		switch (i) {
    			case 1:
    				targetStack = stackAG;
    				break;
    			case 2:
    				targetStack = stackHJ;
    				break;
    			case 3:
    				targetStack = stackKM;
    				break;
    			case 4:
    				targetStack = stackNR;
    				break;
    			case 5:
    				targetStack = stackSZ;
    				break;
    			case 6:
    				targetStack = stackFloor;
    				break;  			
    		}
    		if (targetStack.contains(recipientName)) {
    			for (int k = 0; k<targetStack.size(); k++) {
    				if (targetStack.get(k).getRecipient().toLowerCase().equals(recipientName.toLowerCase())) {
    					Package temp = targetStack.get(k);
    					output+=("\nPackage "+ ++numPackages+ " is in Stack "+targetStack.getStackNumber()+", it was delivered on Day "+temp.getArrivalDate()+
    							", and weighs "+temp.getWeight()+" lbs." );
    				}
    			}
    		}
    	}
    	System.out.println(recipientName+" has "+numPackages+" packages waiting for him/her."+output);
    }
    /**
     * Removes all the Packages from the Floor Stack.
     */
    public static void emptyFloor() {
    	while (!stackFloor.isEmpty()) {
    		try {
    			stackFloor.pop();
    		}
    		catch (EmptyStackException ex) {
    		}
    	}
    	System.out.println("The floor stack has been emptied of all packages.");
		return;
    }
    /**
     * Ends the program.
     */
    public static void quitProgram() {
    	System.out.println("Application ended.");
    	programContinues = false;
    }
    public static void main(String[] args) {
    	//Initialize static variables
	    stackAG = new PackageStack(1, false);
        stackHJ = new PackageStack(2, false);
        stackKM = new PackageStack(3, false);
        stackNR = new PackageStack(4, false);
        stackSZ = new PackageStack(5, false);
        stackFloor = new PackageStack(6, true);
        //Switch statement to read user inptu and execute the corresponding menu option.
        while (programContinues) {
            Scanner input = new Scanner(System.in);
            System.out.println("Menu: \n\tD) Deliver a package \n\tG) Get someone's package \n\tT) Make it Tomorrow \n\tP) Print the stacks \n\tM) Move a package from one stack to another \n\tF) Find"+
             "packages in the wrong stack and move to floor \n\tL) List all packages awaiting a user \n\tE) Empty the floor \n\tQ) Quit \nPlease select an option: ");
            char selection = Character.toUpperCase(input.next().charAt(0));
            switch (selection) {
                case 'D':
                    deliverPackage();
                    break;
                case 'G':
                	getPackage();
                    break;
                case 'T':
                	makeItTomorrow();
                    break;
                case 'P':
                    printStacks();
                    break;
                case 'M':
                	movePackage();
                    break;
                case 'F':
                	unsortedPackagesToFloor();
                    break;
                case 'L':
                	listPackagesForUser();
                    break;
                case 'E':
                	emptyFloor();
                    break;
                case 'Q':
                	quitProgram();
                    break;
                default:
                    System.out.println("Invalid selection; please enter a valid menu choice");
                    break;
            }
        }
    }
}
