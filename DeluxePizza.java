// ------------------------------------------------------- 
// Assignment 4
// Written by: Brandon Amyot 26990940
// For COMP 248 Section EE – Fall 2015
// --------------------------------------------------------
/*
 * Program description at the top of the driver class.
 */
public class DeluxePizza {

	// private instance variable for size of pizza and quantity of toppings
	private String pizzaSize;
	private int cheeseTopping, pepperoniTopping, mushroomTopping, veggieTopping;
	private boolean stuffedWithCheese;
	private static int numberOfPizzas;
	
	// default constructor
	public DeluxePizza()
	{
		pizzaSize = "";
		cheeseTopping = 0;
		pepperoniTopping = 0;
		mushroomTopping = 0;
		veggieTopping = 0;
		stuffedWithCheese = false;
	}
	
	// normal constructor
	public DeluxePizza(String pizzaSize, boolean isStuffed, int numOfCheese, int numOfPepperoni, int numOfMushroom, int veggieTopping)
	{
		this.pizzaSize = pizzaSize.toLowerCase();
		this.cheeseTopping = numOfCheese;
		this.pepperoniTopping = numOfPepperoni;
		this.mushroomTopping = numOfMushroom;
		this.veggieTopping = veggieTopping;
		this.stuffedWithCheese = isStuffed;
		
		++numberOfPizzas;
	}
	
	//copy constructor
	public DeluxePizza(DeluxePizza deluxe)
	{
		deluxe = new DeluxePizza();
		++numberOfPizzas;
	}
	
	// getters
	public String getPizzaSize() {return this.pizzaSize;}
	public int getCheeseTopping() {return this.cheeseTopping;}
	public int getPepperoniTopping() {return this.pepperoniTopping;}
	public int getMushroomTopping() {return this.mushroomTopping;}
	public int getVeggieTopping() {return this.veggieTopping;}
	public boolean getStuffedWithCheese() {return this.stuffedWithCheese;}
	public static int getNumberOfPizzas() {return numberOfPizzas;}
	
	// setters
	public void setPizzaSize(String size) {this.pizzaSize = size.toLowerCase();}
	public void setCheeseTopping(int cheese) {this.cheeseTopping = cheese;}
	public void setPepperoniTopping(int pep) {this.pepperoniTopping = pep;}
	public void setMushroomTopping(int mush) {this.mushroomTopping = mush;}
	public void setVeggieTopping(int veg) {this.veggieTopping = veg;}
	public void setStuffedWithCheese(boolean isStuffed) {this.stuffedWithCheese = isStuffed;}
	
	// to calculate cost of the pizza
	public double calcCost()
	{
		double totalCost = 0.00, costOfToppings = 0.00, costOfVeggies = 3.00;
		int numberOfToppings = cheeseTopping + pepperoniTopping + mushroomTopping;
		
		if (pizzaSize.equals("small"))
		{
			costOfToppings = 2;
			if(stuffedWithCheese)
			{
				totalCost = 12.00 + (numberOfToppings * costOfToppings) + (veggieTopping * costOfVeggies);
				return totalCost;
			}
			else {
				totalCost = 10.00 + (numberOfToppings * costOfToppings) + (veggieTopping * costOfVeggies);
				return totalCost;				
			}
		}
		else if (pizzaSize.equals("medium"))
		{
			costOfToppings = 2.25;
			if(stuffedWithCheese)
			{
				totalCost = 16.00 + (numberOfToppings * costOfToppings) + (veggieTopping * costOfVeggies);
				return totalCost;
			}
			else {
				totalCost = 12.00 + (numberOfToppings * costOfToppings) + (veggieTopping * costOfVeggies);
				return totalCost;				
			}
		}
		else if (pizzaSize.equals("large"))
		{
			costOfToppings = 2.50;
			if(stuffedWithCheese)
			{
				totalCost = 20.00 + (numberOfToppings * costOfToppings) + (veggieTopping * costOfVeggies);
				return totalCost;
			}
			else {
				totalCost = 14.00 + (numberOfToppings * costOfToppings) + (veggieTopping * costOfVeggies);
				return totalCost;				
			}
		}
		return totalCost;
	}
	
	// to turn the orders into strings and display size, toppins and cost. 
	@Override
	public String toString()
	{
		String order =  
				"\nPizza size: " + this.pizzaSize +
				"\nCheese filled dough: " + this.stuffedWithCheese +
				"\nNumber of cheese toppings: " + this.cheeseTopping +
				"\nNumber of pepperoni toppings: " + this.pepperoniTopping +
				"\nNumber of mushroom toppings: " + this.mushroomTopping +
				"\nNumber of veggie toppings: " + this.veggieTopping +
				"\nCost: " + calcCost() + "\n";
				
		
		return order;			
	}
	
	// equals method to check for equality of non-static parameters
	public boolean equals(DeluxePizza otherPizza)
	{
		boolean isEqual = pizzaSize.equals(otherPizza.pizzaSize)
				&& stuffedWithCheese == otherPizza.stuffedWithCheese
				&& cheeseTopping == otherPizza.cheeseTopping
				&& pepperoniTopping == otherPizza.pepperoniTopping 
				&& mushroomTopping == otherPizza.mushroomTopping
				&& veggieTopping == otherPizza.veggieTopping;
		return isEqual; 
	}

}
