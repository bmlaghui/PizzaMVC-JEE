package pizza_package;

import java.io.Serializable;
import java.util.List;

public class Pizza  implements Serializable {

	private int id;
    private String DesignPizz;
    private float price;
    private String description;
    private String image;
    private List<Ingredient> ingredients;
   
    public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public Pizza( ) {
	 
	}

    public Pizza(int id,String designPizz, float price, String description, String image, List<Ingredient> ingredients) {
		super();
		this.id = id;
		this.DesignPizz = designPizz;
		this.price = price;
		this.description = description;
		this.image=image;
		this.ingredients=ingredients;

	}
    public Pizza(String designPizz, float price, String description, String image, List<Ingredient> ingredients) {
		super();
		this.DesignPizz = designPizz;
		this.price = price;
		this.description = description;
		this.image=image;
		this.ingredients=ingredients;

	}
    
	public Pizza(int id, String designPizz, float price, String description, String image) {
		super();
		this.id = id;
		this.DesignPizz = designPizz;
		this.price = price;
		this.description = description;
		this.image=image;

	}
	@Override
	public String toString() {
		return "Pizza [id=" + id + ", DesignPizz=" + DesignPizz + ", price=" + price + ", description=" + description + ", image=" + image + ", ingredients=" + ingredients + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDesignPizz() {
		return DesignPizz;
	}
	public void setDesignPizz(String designPizz) {
		DesignPizz = designPizz;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	

}
