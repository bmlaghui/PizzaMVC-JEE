package pizza_package;

public class Ingredient {
	int numIngredient;
	String nomIngredient;

public Ingredient(int numIngredient, String nomIngredient) {
		super();
		this.numIngredient = numIngredient;
		this.nomIngredient = nomIngredient;
		
	}
public Ingredient(String nomIngredient) {
	super();
	this.nomIngredient = nomIngredient;
	
}


public int getNumIngredient() {
	return numIngredient;
}
public void setNumIngredient(int numIngredient) {
	this.numIngredient = numIngredient;
}
public String getNomIngredient() {
	return nomIngredient;
}
public void setNomIngredient(String nomIngredient) {
	this.nomIngredient = nomIngredient;
}
@Override
public String toString() {
	return "Ingredient [id=" + numIngredient + ", nom=" + nomIngredient + "]";
}

}


	


