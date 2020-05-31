package pizza_package;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet; 

public class PizzaDAO {


	     
	     /**
	     * @throws Exception ******************************************/
	     
	/** Cette méthode récupére la liste de tout les pizzas et renvoie un objet de type List  **/ 

	       static public List<Pizza> listAllPizzas() throws Exception {
	       
	  	     List<Pizza> listPizza = new ArrayList<>();

	        String sql = "SELECT * FROM t_pizza";
	         
	       Connection conn = DatabaseConnection.initializeDatabase() ;
	       Statement statement = conn.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);
  
	        listPizza.clear();
	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String DesignPizz = resultSet.getString("DesignPizz");	         
	            float price = resultSet.getFloat("TarifPizz");
	            String description = resultSet.getString("description");	         
	            String image = resultSet.getString("image");
	           
	            List<Ingredient> ingredients = new ArrayList<Ingredient>();
	            String qr = "SELECT * FROM t_ingredient i,t_composer c where c.numIngredient=i.numIngredient and c.id= "+id;
		         		         
		        Statement st = conn.createStatement();
		        ResultSet rs = st.executeQuery(qr);
		        ingredients.clear();
		        
		        while (rs.next()) {
		            int numIngredient = rs.getInt("numIngredient");
		            String nomIngredient = rs.getString("nomIngredient");	         
		            Ingredient itemIngredient = new Ingredient(numIngredient, nomIngredient);
		            ingredients.add(itemIngredient);
		        }	
		        rs.close();
		        st.close();
	             Pizza itemPizza = new Pizza(id,DesignPizz, price,description,image,ingredients);
	             
			       
	            listPizza.add(itemPizza);

   
	        }
	         
	        resultSet.close();
	        statement.close();
	         
	       // itemPizza disconnect();

	       
	        
	        return listPizza;
	        
	    }
	       
	   	/** Cette méthode  renvoie un objet de type Pizza contenat la pizza dont l'id est passé en paramètre  **/ 
     
	       static public Pizza getPizzaById(int pizzaid) throws Exception {
	  	     List<Pizza> listPizza = new ArrayList<>();

	    	   Connection conn = DatabaseConnection.initializeDatabase() ;
		       Pizza onePizza= new Pizza();
		        
	    	   String query = "select * from t_pizza where id = " + pizzaid     ;
	           Statement stmt = conn.createStatement();
	           ResultSet res = stmt.executeQuery(query);
	           res.next();
	           
	            int id = res.getInt("id");
	            String DesignPizz = res.getString("DesignPizz");	         
	            float TarifPizz = res.getFloat("TarifPizz");
	            String description = res.getString("description");
	            String image = res.getString("image");
	             
	            List<Ingredient> ingredients=IngredientDAO.getIngredientsPizza(id);
	            if(ingredients != null)
	            {
	             onePizza = new Pizza(id,DesignPizz, TarifPizz,description,image,ingredients);
	            }
	            else 
	            {
		             onePizza = new Pizza(id,DesignPizz, TarifPizz,description,image);
		        }
	            listPizza.add(onePizza);
	            
	            res.close();
	            stmt.close();
	            conn.close();
	            return onePizza;
	           
	           
	       }
	       
		   	/** Cette méthode récupère un objet de type Pizza et l'insère dans la BDD **/ 
	       public static  void  AjouterPizza(Pizza pizza) throws Exception {
	    	   Connection conn = DatabaseConnection.initializeDatabase();
          
	           PreparedStatement ps = conn.prepareStatement("insert into t_pizza(DesignPizz,TarifPizz,description,image) values (?,?,?,?)") ;
	
	           ps.setString(1, pizza.getDesignPizz()) ;
	           ps.setFloat(2,  pizza.getPrice()) ; 
	           ps.setString(3, pizza.getDescription()) ;
	           ps.setString(4, pizza.getImage()) ;
	           ps.executeUpdate();
	           
	           Statement stmt = conn.createStatement();
	           ResultSet rs=stmt.executeQuery("select id from t_pizza");
	           if(rs.last()){
	           int numLastPizza=rs.getInt("id");
	           
	           for (Ingredient unIngredient : pizza.getIngredients()) {
	        	   PreparedStatement ps2 = conn.prepareStatement("insert into t_composer(id,numIngredient) values (?,?)") ;
	   	       	
		           ps2.setInt(1, numLastPizza) ;
		           ps2.setInt(2,  unIngredient.getNumIngredient()) ; 
		           ps2.executeUpdate();
	        	    }
	           }
	           
	           
	       }
	       
		   	/** Cette méthode supprime une pizza de la BDD dont l'id est passé en paramètre**/ 
	       public static void  SupprimerPizza(int numPizza) throws Exception {
	    	   Connection conn = DatabaseConnection.initializeDatabase();
	    	    PreparedStatement ps = conn.prepareStatement("delete from t_pizza where id=?") ;
	    	    ps.setInt(1, numPizza) ;
	    	    ps.executeUpdate();
	    	  }
	       
		   	/** Cette méthode récupère un objet de type Pizza et le modifie dans la BDD **/ 
	    	public static void  ModifierPizza(Pizza pizza) throws Exception {
		    	   Connection conn = DatabaseConnection.initializeDatabase();
	    	    PreparedStatement ps = conn.prepareStatement("update t_pizza set DesignPizz=?,TarifPizz=?,description=?,image=? where id=?") ;

	    	    ps.setString(1, pizza.getDesignPizz()) ;
	    	    ps.setFloat(2,  pizza.getPrice()) ; 
	    	    ps.setString(3, pizza.getDescription()) ;
	    	    ps.setString(4, pizza.getImage()) ;
	    	    ps.setInt(5, pizza.getId()) ;

	    	    ps.executeUpdate();
	    	    PreparedStatement prs = conn.prepareStatement("delete from t_composer where id=?") ;
	    	    prs.setInt(1, pizza.getId()) ;
	    	    prs.executeUpdate();

	    	    for (Ingredient unIngredient : pizza.getIngredients()) {
	    	 	   PreparedStatement ps2 = conn.prepareStatement("insert into t_composer(id,numIngredient) values (?,?)") ;
	    	       	
	    	        ps2.setInt(1, pizza.getId()) ;
	    	        ps2.setInt(2, unIngredient.getNumIngredient()) ; 
	    	        ps2.executeUpdate();
	    	 	    }
	    	}
}
