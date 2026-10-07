/* BurgerJoint.java
 * Vladlena Costescu
 * AP Computer Science AB
 * Assignment-1.2-burgerJoint (due 9/22/08)
 * Welcome to Antonio's Pizza Palace! Please order what you would like.
 */

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Currency;
import java.util.Scanner;

public class BurgerJoint {
	public static void main(String[] args) {

		// Initialize scanner
		Scanner keyboard = new Scanner(System.in);

		// Initialize currency
		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
		Currency currency = Currency.getInstance("USD");
		nf.setCurrency(currency);

		// First set of options: type of pizza
		System.out.println("Welcome to Antonio's Pizza Palace! Please order what you would like.");
		System.out.println("\nChoose a type of pizza:");
		System.out.println("Press 1 for Mushroom Pizza");
		System.out.println("Press 2 for Pepperoni Pizza");
		System.out.println("Press 3 for Cheese Pizza");
		System.out.println("Press 4 for Vegetarian Pizza");
		String choice1str = keyboard.nextLine();
		int choice1 = checkIfInt(choice1str, "Please enter a number choice.");
		choice1 = checkIfCorrectNumber(choice1, "Please enter a number choice.");

		// Second set of options: size of pizza
		System.out.println("\nChoose a pizza size:");
		System.out.println("Press 1 for 6\" ($5.50)");
		System.out.println("Press 2 for 8\" ($7.25)");
		System.out.println("Press 3 for 10\" ($9.00)");
		System.out.println("Press 4 for 12\" ($10.75)");
		String choice2str = keyboard.nextLine();
		int choice2 = checkIfInt(choice2str, "Please enter a number choice.");
		choice2 = checkIfCorrectNumber(choice2, "Please enter a number choice.");

		// Third set of options: type of drink
		System.out.println("\nChoose a type of drink:");
		System.out.println("Press 1 for Lemonade ($2.15)");
		System.out.println("Press 2 for Iced Tea ($1.45)");
		System.out.println("Press 3 for Cranberry Juice ($2.25)");
		System.out.println("Press 4 for Water ($0.75)");
		String choice3str = keyboard.nextLine();
		int choice3 = checkIfInt(choice3str, "Please enter a number choice.");
		choice3 = checkIfCorrectNumber(choice3, "Please enter a number choice.");

		// Fourth set of options: size of drink
		System.out.println("\nChoose a drink size:");
		System.out.println("Press 1 for Small (add $0.00)");
		System.out.println("Press 2 for Medium (add $0.25)");
		System.out.println("Press 3 for Large (add $0.50)");
		System.out.println("Press 4 for Extra Large (add $0.75)");
		String choice4str = keyboard.nextLine();
		int choice4 = checkIfInt(choice4str, "Please enter a number choice.");
		choice4 = checkIfCorrectNumber(choice4, "Please enter a number choice.");

		// Fifth set of options: dessert type
		System.out.println("\nChoose a dessert:");
		System.out.println("Press 1 for Cannoli ($2.50)");
		System.out.println("Press 2 for Vanilla Ice Cream Cone ($3.25)");
		System.out.println("Press 3 for Strawberry Cheesecake Slice ($2.75)");
		System.out.println("Press 4 if you do not want a dessert");
		String choice5str = keyboard.nextLine();
		int choice5 = checkIfInt(choice5str, "Please enter a number choice.");
		choice5 = checkIfCorrectNumber(choice5, "Please enter a number choice.");

		// Handle the first set of options
		String pizzatype = null;
		switch(choice1)
		{
		case 1:
			pizzatype = "mushroom pizza";
			break;
		case 2:	
			pizzatype = "pepperoni pizza";
			break;
		case 3:
			pizzatype = "cheese pizza";
			break;
		case 4:
			pizzatype = "vegetarian pizza";
			break;
		default:
			break;
		}

		// Handle the second set of options
		int pizzasize = 0;
		double pizzasizecost = 0;
		switch(choice2)
		{
		case 1:
			pizzasize = 6;
			pizzasizecost = 5.50;
			break;
		case 2:	
			pizzasize = 8;
			pizzasizecost = 7.25;
			break;
		case 3:
			pizzasize = 10;
			pizzasizecost = 9.00;
			break;
		case 4:
			pizzasize = 12;
			pizzasizecost = 10.75;
			break;
		default:
			break;
		}

		// Handle the third set of options
		String drinktype = null;
		double drinktypecost = 0;
		switch(choice3)
		{
		case 1:
			drinktype = "lemonade";
			drinktypecost = 2.15;
			break;
		case 2:	
			drinktype = "iced tea";
			drinktypecost = 1.45;
			break;
		case 3:
			drinktype = "cranberry juice";
			drinktypecost = 2.25;
			break;
		case 4:
			drinktype = "water";
			drinktypecost = 0.75;
			break;
		default:
			break;
		}

		// Handle the fourth set of options
		String drinksize = null;
		double drinksizecost = 0;
		switch(choice4)
		{
		case 1:
			drinksize = "small";
			drinksizecost = 0.00;
			break;
		case 2:	
			drinksize = "medium";
			drinksizecost = 0.25;
			break;
		case 3:
			drinksize = "large";
			drinksizecost = 0.50;
			break;
		case 4:
			drinksize = "extra large";
			drinksizecost = 0.75;
			break;
		default:
			break;
		}

		// Handle the fifth set of options
		String desserttype = null;
		double dessertcost = 0;
		switch(choice5)
		{
		case 1:
			desserttype = "cannoli";
			dessertcost = 2.50;
			break;
		case 2:	
			desserttype = "vanilla ice cream cone";
			dessertcost = 3.25;
			break;
		case 3:
			desserttype = "slice of strawberry cheesecake";
			dessertcost = 2.75;
			break;
		case 4:
			desserttype = "no dessert";
			dessertcost = 0.00;
			break;
		default:
			break;
		}

		// Calculate the grand total
		double pretaxtotal;
		double taxadded;
		double taxtotal;

		pretaxtotal = pizzasizecost + drinktypecost + drinksizecost + dessertcost;
		taxadded = 0.065 * pretaxtotal;
		taxtotal = pretaxtotal + taxadded;

		// Truncate values to two decimal places and convert them to currency format
		pretaxtotal = Math.floor(pretaxtotal * 100)/100;
		taxadded = Math.floor(taxadded * 100)/100;
		taxtotal = Math.floor(taxtotal * 100)/100;

		String pretaxtotalstr = nf.format(pretaxtotal);
		String taxaddedstr = nf.format(taxadded);
		String taxtotalstr = nf.format(taxtotal);

		// Vowel before pizza?
		String aoranpizza = null;
		if(pizzasize == 8){
			aoranpizza = "an ";
		}
		else{
			aoranpizza = "a ";
		}

		// Vowel before drink?
		String aorandrink = null;
		if(drinksize == "extra large"){
			aorandrink = "an ";
		}
		else{
			aorandrink = "a ";
		}

		// Was a dessert chosen?
		String aornothingdessert = null;
		if(desserttype != "no dessert"){
			aornothingdessert = "a ";
		}
		else{
			aornothingdessert = "";
		}

		// Display results to user
		System.out.println("\nYou have ordered " + aoranpizza + pizzasize + "\" " + pizzatype + ", " + aorandrink + drinksize + " " + drinktype + ", and " + aornothingdessert + desserttype + ".");
		System.out.println("\nTotal: " + pretaxtotalstr);
		System.out.println("Tax: " + taxaddedstr);
		System.out.println("--------------");
		System.out.println("Grand Total: " + taxtotalstr);

		// Ask user how much he will pay
		System.out.println("\nPlease enter the cash amount you will pay.");
		String paymentstr = keyboard.nextLine();
		paymentstr = paymentstr.replace("$", "");
		paymentstr = paymentstr.replace(",", "");
		double payment = checkIfDouble(paymentstr, "Please enter a valid cash amount.");
		payment = Math.floor(payment * 100)/100;

		// Calculate change, if any, or prompt user that he didn't enter enough money
		double change = 0;
		String changestr = null;
		for(int i = 0; i < 2; i++){
			if(payment >= taxtotal){
				change = payment - taxtotal;
				changestr = nf.format(change);
				changestr = "Your change is: " + changestr + ".";
			}
			else{
				i = 0;
				String errorstr = "JUNK";
				payment = checkIfDouble(errorstr, "The amount you entered does not cover the cost of the meal. Please enter a valid cash amount.");
				payment = Math.floor(payment * 100)/100;
			}
		}

		// Display change amount, if any
		System.out.println("\n" + changestr);
		System.out.println("\nThank you for your order! Have a nice day!");
	}

	public static int checkIfInt(String inputstr, String displaytext) {

		// Initialize scanner
		Scanner keyboard = new Scanner(System.in);

		int inputint;

		// Loop to check if input is an integer using try-catch; every time an
		// error is thrown we reset the loop
		for (int i = 0; i < 2; i++) {
			try {
				inputint = Integer.parseInt(inputstr);
			} catch (NumberFormatException exception) {
				System.out.println(displaytext);
				i = 0;
				inputstr = keyboard.nextLine();
			}
		}

		// Once loop is over we know that the input is an integer, so we can
		// assign its value to our int
		inputint = Integer.parseInt(inputstr);

		return inputint;
	}
	public static double checkIfDouble(String inputstr, String displaytext){

		// Initialize scanner
		Scanner keyboard = new Scanner(System.in);

		double inputdouble;

		// Loop to check if input is a double using try-catch; every time an error is thrown we reset the loop
		for(int i = 0; i < 2; i++){
			try{
				inputdouble = Double.parseDouble(inputstr);
			}
			catch(NumberFormatException exception){
				System.out.println(displaytext);
				i = 0;
				inputstr = keyboard.nextLine();
				inputstr = inputstr.replace("$", "");
				inputstr = inputstr.replace(",", "");
			}
		}

		// Once loop is over we know that the input is a double, so we can assign its value to our double
		inputdouble = Double.parseDouble(inputstr);

		return inputdouble;
	}
	public static int checkIfCorrectNumber(Integer inputint, String displaytext){
		// Initialize scanner
		Scanner keyboard = new Scanner(System.in);

		String inputstr;

		// Loop to check if number is between 1 and 4, inclusive
		for(int i = 0; i < 2; i++){
			if(inputint != 1 && inputint != 2 && inputint != 3 && inputint != 4){
				System.out.println(displaytext);
				i = 0;
				inputstr = keyboard.nextLine();
				inputint = checkIfInt(inputstr, "Please enter a number choice.");
			}
			else{
				break;
			}
		}

		return inputint;
	}
}
