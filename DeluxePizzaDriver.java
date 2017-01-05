// ------------------------------------------------------- 
// Assignment 4
// Written by: Brandon Amyot 26990940
// For COMP 248 Section EE – Fall 2015
// --------------------------------------------------------
/* This program is designed to take and store the orders in a pizza restaurant.
 * When the program begins, it will prompt the user for the number of pizzas they can make during the day, 
 * which will initialize an array of the same size to store each object of type pizza.
 * The user will then be prompted with a main menu consisting of 5 choices.
 * The first option, when selected, will initialize a method to input new pizza orders.
 * It ask the user various questions, such as toppings and size, to create a new pizza.
 * The program will then store the pizza as an object in the array in the appropriate position.
 * Option two is to edit any pizza and store the corrections by replacing the old object in the array with the new one.
 * It will prompt the user with a menu of options so they don't have to retype in all the information and can change only what is needed.
 * Once they are done editing the pizza they will be asked if they want to edit another pizza so they won't have to type in their password again
 * to edit more than one pizza at once.
 * Option three will show all information for each pizza of a specific size chosen by the user.
 * Option four is designed so the user can see various statistics on the pizzas that have been created so far.
 * They will be prompted with yet another menu with choices to chose from to they can see the cheapest pizza, most expensive, average price, etc.
 * The last main menu option will end the program with a goodbye message.
 */

import java.util.Scanner;

public class DeluxePizzaDriver {
		
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		// array to hold todays pizzas
		DeluxePizza[] todaysPizza;
		
		// Welcome message
		System.out.println("----------------------------------------");
		System.out.println("\t Pop's Pizza Tracker");
		System.out.println("----------------------------------------");
		System.out.println();
		
		// initialize the array
		System.out.print("Enter the maximum number of pizzas you can make today from the ingrdients you have: ");
		todaysPizza = new DeluxePizza[keyboard.nextInt()];
		
		// run the program
		boolean loop = true;
		do {
			mainMenu();
			
			// take input for menu selection
			int menuChoice;
			menuChoice = keyboard.nextInt();
			while(menuChoice < 1 || menuChoice > 5)
			{
				System.out.print("Incorrect option, please enter your choice: ");
				menuChoice = keyboard.nextInt();
			}

			// return which method to run
			switch(menuChoice)
			{
			case 1: newPizza(todaysPizza); break;
			case 2: editPizza(todaysPizza); break;
			case 3:
				System.out.print("What size of pizza would you like to see information on? ");
				String whichSize = keyboard.next();
				whichSizeOfPizza(whichSize);
				pizzasOfSize(whichSize, todaysPizza); break;
			case 4: pizzaStats(todaysPizza); break;
			case 5: loop = false; break;
			}
		} while(loop);
		
		System.out.println("Thanks for using Pop's Pizza Tracker!"); 
		System.exit(0);
		keyboard.close();
	}
	
	// method for the menu
	public static void mainMenu() 
	{
		// display menu
		System.out.print("\nWhat would you like to do?" +
				"\n1. Enter a new pizza order (password required)" +
				"\n2. Change information of a specific order (password required)" +
				"\n3. Display details for all pizzas of a specific size (s/m/l)" +
				"\n4. Statistics of today's pizzas" +
				"\n5. Quit" +
				"\nPlease enter your choice: ");	
	}
	
	// method for inputing new pizzas
	public static void newPizza(DeluxePizza todaysPizza[]) {
		Scanner keyboard = new Scanner(System.in);

		if(!password())	// to return if password in incorrect
		{
			return;
		}
		// create new pizzas
		System.out.print("How many pizzas would you like to enter? ");
		int newPizzas;
		newPizzas = keyboard.nextInt();
		System.out.println();
		
		// checking how many empty spots are left
		// indexing where the next pizza will go
		int emptySpots = 0;
		int pizzaCounter = 0;
		for(DeluxePizza x:todaysPizza)
	    {
	        if(x == null)
	        {
	        	++emptySpots;	        	
	        }
	        else
	        {
	        	++pizzaCounter;
	        }
	    }
		// check if there's room to make new pizzas
		if (newPizzas > emptySpots)
		{
			System.out.println("Sorry, you only have enough ingredients to make " + emptySpots + " more pizzas");
			newPizzas = emptySpots; // modifying the number of Pizzas to make
		}
		// add the new pizzas to the array
		for(int k = 1; k <= newPizzas; ++k)
		{
			System.out.print("The size of pizza would you like to make (small/medium/large): ");
				String size = keyboard.next();
				// verify input
				while(!whichSizeOfPizza(size))
				{
					System.out.print("Oops, did your finger slip? Try again. ");
					size = keyboard.next().toLowerCase();
				}
			
			System.out.print("Cheese Stuffed Dough (yes/no): ");
				String stuffedAnswer = keyboard.next();
				checkYesOrNo(stuffedAnswer);

				boolean isStuffed;
				if(stuffedAnswer.equals("yes"))
				{
					isStuffed = true;
				}
				else
				{
					isStuffed = false;
				}
			System.out.print("Enter the number of cheese toppings: ");
				int cheese = keyboard.nextInt();
			
			System.out.print("Enter the number of pepperoni toppings: ");
				int pepperoni = keyboard.nextInt();
			
			System.out.print("Enter the number of mushroom toppings: ");
				int mushroom = keyboard.nextInt();
				
			System.out.print("Enter the number of veggie toppings: ");
				int veggie = keyboard.nextInt();
			
			todaysPizza[pizzaCounter] = new DeluxePizza(size, isStuffed, cheese, pepperoni, mushroom, veggie);
			System.out.println();
			++pizzaCounter;
		}
		return;
	}
	
	// method to edit the pizzas
	public static void editPizza(DeluxePizza todaysPizza[]) {
		Scanner keyboard = new Scanner(System.in);

		if(!password())	// to return if password in incorrect
		{
			return;
		}
		boolean repeatEdit = true;
		do {
			//ask which pizza to edit
			System.out.print("Which pizza would you like to edit? ");
			int pizzaIndex = keyboard.nextInt() - 1;
			// verifying input
			boolean correctValue;
			if(pizzaIndex > todaysPizza.length || pizzaIndex < 0)
			{
				correctValue = false;
				while(!correctValue)
				{
					System.out.print("Oops, did your finger slip? Please try again. ");
					pizzaIndex = keyboard.nextInt();
					if (pizzaIndex > todaysPizza.length || pizzaIndex < 0)
					{
						correctValue = true;
					}
				}
			}
			// if there is no pizza
			else if (todaysPizza[pizzaIndex] == null) 
			{
				System.out.println("Sorry, it seems there's no pizza to show");
				System.out.print("Do you want to edit a different pizza? (yes/no) If no, will return to main menu. ");
				String menuChoice = keyboard.next().toLowerCase();
				//verify input
				checkYesOrNo(menuChoice);

				if(menuChoice.equals("yes"))
				{
					repeatEdit = true;
				}
				else
				{
					return;
				}
			}
			else
			{
				boolean quit = false;
				do{
					System.out.println("Pizza #: " + (pizzaIndex + 1));
					System.out.print(todaysPizza[pizzaIndex]);
					System.out.println();
					// display menu
					System.out.print("\nWhat would you like to change?" +
							"\n1. Size" +
							"\n2. Cheese filled or not" +
							"\n3. Number of Cheese Toppings" +
							"\n4. Number of Pepperoni Toppings" +
							"\n5. Number of Mushroom Toppings" +
							"\n6. Number of Veggie Toppings" +
							"\n7. Quit" +
							"\nPlease enter your choice: ");
					// take input for menu selection
					int menuChoice;
					menuChoice = keyboard.nextInt();
					while(menuChoice < 1 || menuChoice > 7)
					{
						System.out.print("Incorrect option, please enter your choice: ");
						menuChoice = keyboard.nextInt();
					}
					switch(menuChoice)
					{
						// edit size
						case 1: 
							System.out.print("What size of Pizza would you like to change it to (small/medium/large): ");
							String size = keyboard.next();
							
							while(!whichSizeOfPizza(size))
							{
								System.out.println("Oops, did your finger slip?");
								System.out.print("What size of Pizza would you like to change it to (small/medium/large): ");
								size = keyboard.next();
							}
							
							todaysPizza[pizzaIndex].setPizzaSize(size);
							break;
						
						// edit cheese stuffed
						case 2:
							System.out.print("Cheese Stuffed Dough (yes/no): ");
								String stuffedAnswer = keyboard.next().toLowerCase();
								// verify input
								checkYesOrNo(stuffedAnswer);

								boolean isStuffed;
								if(stuffedAnswer.equals("yes"))
								{
									isStuffed = true;
								}
								else
								{
									isStuffed = false;
								}
								todaysPizza[pizzaIndex].setStuffedWithCheese(isStuffed);
							break;
						
						// edit cheese toppings
						case 3:
							System.out.print("Enter the number of cheese toppings: ");
								int cheese = keyboard.nextInt();
								todaysPizza[pizzaIndex].setCheeseTopping(cheese);
							break;
						
						// edit pepperoni toppings
						case 4:
							System.out.print("Enter the number of pepperoni toppings: ");
								int pepperoni = keyboard.nextInt();
								todaysPizza[pizzaIndex].setPepperoniTopping(pepperoni);
							break;
						
						// edit mushroom toppings
						case 5:
							System.out.print("Enter the number of mushroom toppings: ");
								int mushroom = keyboard.nextInt();
								todaysPizza[pizzaIndex].setMushroomTopping(mushroom);
							break;
					
						// edit veggie toppings
						case 6:
							System.out.print("Enter the number of veggie toppings: ");
								int veggie = keyboard.nextInt();
								todaysPizza[pizzaIndex].setVeggieTopping(veggie);
							break;
					
						// quit
						case 7:
							quit = true;
							break;
						}
					} while(!quit);
					System.out.print("Do you want to edit a different pizza? (yes/no) ");
					String editAnother = keyboard.next().toLowerCase();
					// verify input
					checkYesOrNo(editAnother);
	
					if(editAnother.equals("yes"))
					{
						repeatEdit = true;
					}
					else
					{
						repeatEdit = false;
					}
				}
		} while(repeatEdit);
		return;
	}

	// display options for menu choice 4
	public static void pizzaStats(DeluxePizza todaysPizza[]) {
		Scanner keyboard = new Scanner(System.in);

		boolean repeat = true;
		do{
			// display menu
			System.out.print("\nWhat information would you like to see?" +
					"\n1. Cost and details of cheapest pizza" +
					"\n2. Cost and details of most costly pizza" +
					"\n3. Number of pizzas sold today" +
					"\n4. Number of pizzas of a specific size" +
					"\n5. Average cost of pizzas" +
					"\n6. Quit" +
					"\nPlease enter your choice: ");
			// take input for menu selection
			int menuChoice;
			menuChoice = keyboard.nextInt();
			
			while(menuChoice < 1 || menuChoice > 6)
			{
				System.out.print("Incorrect option, please enter your choice: ");
				menuChoice = keyboard.nextInt();
			}
			// run desired method
			switch(menuChoice)
			{
			case 1: lowestPrice(todaysPizza); break;
			case 2: highestPrice(todaysPizza); break;
			case 3: 
				int totalPizzas = DeluxePizza.getNumberOfPizzas();
				System.out.println("You have sold " + totalPizzas + " pizzas today!");
				break;
			case 4: numberOfPizzasOfSize(todaysPizza); break;
			case 5: 
				double totalSales = 0.00;
				totalPizzas = DeluxePizza.getNumberOfPizzas();
				if (totalPizzas == 0)
				{
					System.out.println("You have not made any pizzas today!");
				}
				else
				{
					for(DeluxePizza x:todaysPizza)
					{
						if(x == null)
						{
							totalSales += 0.00;
						}
						else
						{
						totalSales += x.calcCost();
						}
					}
					double averageCost = totalSales / totalPizzas;
					System.out.println("The average cost per pizza is $" + averageCost + "!");
				}
				break;
			case 6:
				repeat = false;
			}
		}while(repeat);
		return;
	}
	
	// method for diplay pizzas of chosen size
	public static void pizzasOfSize(String whichSize, DeluxePizza todaysPizza[])
	{		
		int counter = 0;
		System.out.println("List of " + whichSize +" pizzas sold today:");
		System.out.println();
		for(int i = 0; i <= todaysPizza.length - 1; ++i)
		{
			if(todaysPizza[i] == null)
			{
				counter += 0;
			}
			else if (todaysPizza[i].getPizzaSize().equals(whichSize))
			{
				System.out.print("Pizza #: " + (i + 1));
				System.out.println(todaysPizza[i]);
				++counter;
			}
		}
		System.out.println("You made " + counter + " " + whichSize + " pizzas today!");
		return;
	}
	
	// method for displaying pizzas cheaper than a chosen price
	public static void cheaperThan(DeluxePizza todaysPizza[])
	{
		Scanner keyboard = new Scanner(System.in);

		System.out.print("Enter the price for which you wish to find all pizzas that are cheaper: ");
		double whichPrice = keyboard.nextDouble();
		
		for(int i = 0; i <= todaysPizza.length; ++i)
		{
			if(todaysPizza[i].calcCost() < whichPrice)
			{
				System.out.println("Pizza #: " + i + ", cost: $" + todaysPizza[i].calcCost());
			}
		}
		return;
	}
	
	// method for displaying the cheapest pizza
	public static void lowestPrice(DeluxePizza todaysPizza[])
	{
		boolean empty = true;
		for(DeluxePizza x:todaysPizza)
		{
			if(x != null)
				empty = false;
		}
		if(!empty){
		double cheapest = todaysPizza[0].calcCost();
		int index = 0;
		for(int i = 0; i <= todaysPizza.length - 1; ++i)
		{
			if(todaysPizza[i] == null)
			{
				cheapest += 0;
			}
			else if(todaysPizza[i].calcCost() < cheapest)
			{
				cheapest = todaysPizza[i].calcCost();
				index = i;
			}
		}
		System.out.println("The cheapest pizza is pizza # " + (index + 1) + " at $" + cheapest);
		return;
		}
		else
		{
			System.out.println("You haven't made any pizzas yet!");
			return;
		}
	}
	
	// method for displaying the highest pizza
		public static void highestPrice(DeluxePizza todaysPizza[])
		{
			boolean empty = true;
			for(DeluxePizza x:todaysPizza)
			{
				if(x != null)
					empty = false;
			}
			if(!empty){
			double expensive = 0;
			int index = 0;
			for(int i = 0; i <= todaysPizza.length - 1; ++i)
			{
				if(todaysPizza[i] == null)
				{
					expensive += 0;
				}
				else if(todaysPizza[i].calcCost() > expensive)
				{
					expensive = todaysPizza[i].calcCost();
					index = i;
				}
			}
			System.out.println("The most expensive pizza is pizza # " + (index + 1) + " at $" + expensive);
			return;
			}
			else
			{
				System.out.println("You haven't made any pizzas yet!");
				return;
			}
		}
		
	// method for diplay pizzas of chosen size
	public static void numberOfPizzasOfSize(DeluxePizza todaysPizza[])
	{
		Scanner keyboard = new Scanner(System.in);

		System.out.println("Please enter the specific size of pizza to be counted");
		String whichSize = keyboard.next();
		whichSizeOfPizza(whichSize);
		
		boolean empty = true;
		for(DeluxePizza x:todaysPizza)
		{
			if(x != null)
				empty = false;
		}
		if(!empty){
		int counter = 0;
		for(DeluxePizza x:todaysPizza)
		{
			if (x == null)
			{
				counter += 0;
			}
			else if (x.getPizzaSize().equals(whichSize))
			{
				++counter;
			}
		}
		System.out.println("You made " + counter + " " + whichSize + " pizza(s) today!");
		return;
		}
		else
		{
			System.out.println("You haven't made any pizzas yet!");
			return;
		}
	}

	// method for testing password
	public static boolean password() 
	{
		Scanner keyboard = new Scanner(System.in);

		boolean isCorrect = false;
		int tryCounter = 3;
		System.out.print("Please enter your password to continue: ");
		String myPassword = keyboard.nextLine().toLowerCase();
		for (int tries = 1; tries <= 3; ++tries)
		{
			if (myPassword.equals("deluxepizza"))
			{
				isCorrect = true;
				break;
			}
			else if(tries == 3)
			{
				System.out.println("It seems you're having trouble typing your password... returning to main menu.");
				return isCorrect;
			}
			else
			{
				--tryCounter;
				System.out.println("Oops, did your finger slip? Please try again. You have " + tryCounter + " tries remaining!");
				myPassword = keyboard.next().toLowerCase();
			}	
		}
		return isCorrect;
	}
	
	// method for taking and checking input for size of pizza
	public static boolean whichSizeOfPizza(String whichSize)
	{		
		boolean correctValue;
		if (whichSize.equalsIgnoreCase("small") || whichSize.equalsIgnoreCase("medium") || whichSize.equalsIgnoreCase("large"))
		{
			correctValue = true;
		}
		else
		{
			correctValue = false;
		}
		return correctValue;
	}
	
	// method for taking and checking input for yes/no questions
	public static String checkYesOrNo(String yesOrNo)
	{
		Scanner keyboard = new Scanner(System.in);

		boolean correctValue;
		if (yesOrNo.equalsIgnoreCase("yes") || yesOrNo.equalsIgnoreCase("no"))
		{
			correctValue = true;
		}
		else
		{
			correctValue = false;
			while(!correctValue)
			{
				System.out.print("Oops did your finger slip? Please try again. ");
				yesOrNo = keyboard.next().toLowerCase();
				if (yesOrNo.equalsIgnoreCase("yes") || yesOrNo.equalsIgnoreCase("no"))
				{
					correctValue = true;
				}
			}
		}
		return yesOrNo;
	}
	
}
