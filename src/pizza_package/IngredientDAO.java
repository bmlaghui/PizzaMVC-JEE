package pizza_package;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pizza_package.Ingredient;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet; 

public class IngredientDAO {

	/** Cette méthode récupére la liste de tout les ingrédients et renvoie un objet de type List  **/ 
    static public List<Ingredient> getAllIngredients() throws Exception {
    
     List<Ingredient> listIngredient = new ArrayList<>();
     	
     String sql = "SELECT * FROM t_ingredient";
      
     Connection conn = DatabaseConnection.initializeDatabase() ;
      
     Statement statement = conn.createStatement();
     ResultSet resultSet = statement.executeQuery(sql);
     listIngredient.clear();

     while (resultSet.next()) {
         int numIngredient = resultSet.getInt("numIngredient");
         String nomIngredient = resultSet.getString("nomIngredient");	         
         Ingredient itemIngredient = new Ingredient(numIngredient, nomIngredient);
         listIngredient.add(itemIngredient);
     }
     resultSet.close();
     statement.close();
     conn.close();
      
    // itemIngredient disconnect();
      
     return listIngredient;
 }
			/** Cette méthode récupére la liste de tout les ingrédient d'une pizza dont l'id est passé en paramètres  **/ 
	       static public List<Ingredient> getIngredientsPizza(int numPizza) throws Exception {
		       
	    	    List<Ingredient> listIngredient = new ArrayList<>();
	    	 	 
		        String sql = "SELECT * FROM t_ingredient i,t_composer c where c.numIngredient=i.numIngredient and c.id= "+numPizza;
		         
			       Connection conn = DatabaseConnection.initializeDatabase() ;
		         
		        Statement statement = conn.createStatement();
		        ResultSet resultSet = statement.executeQuery(sql);
		        listIngredient.clear();
		        
		        while (resultSet.next()) {
		            int numIngredient = resultSet.getInt("numIngredient");
		            String nomIngredient = resultSet.getString("nomIngredient");	         
		            Ingredient itemIngredient = new Ingredient(numIngredient, nomIngredient);
		            listIngredient.add(itemIngredient);
		        }
		         
		        resultSet.close();
		        statement.close();
		        conn.close();
		         
		       // itemIngredient disconnect();
		         
		        return listIngredient;
		    }
	       
			/** Cette méthode récupére les informations d'un ingrédient  dont l'id est passé en paramètres  **/ 
	        static public Ingredient getIngredient(int ingredient) throws Exception {
	    	   
		       Connection conn = DatabaseConnection.initializeDatabase() ;
		         
		        
	           String query = "select * from t_ingredient where numIngredient = " + ingredient  ;
	           Statement stmt = conn.createStatement();
	           
	           ResultSet res = stmt.executeQuery(query);
	           res.next();
	            int numIngredient = res.getInt("numIngredient");
	            String nomIngredient = res.getString("nomIngredient");	         
	             
	            
	            Ingredient oneIngredient = new Ingredient(numIngredient, nomIngredient);
	            
	            res.close();
	            stmt.close();
		        conn.close();
		        
	            return oneIngredient;
	           
	       }
	        
		   	/** Cette méthode récupère un objet de type Ingredient et l'insère dans la BDD **/ 
	       public static void  AjouterIngredient(Ingredient ingredient) throws Exception {
		       Connection conn = DatabaseConnection.initializeDatabase() ;
		         	           
	          
	           PreparedStatement ps = conn.prepareStatement("insert into t_ingredient(nomIngredient) values (?)") ;
	
	           ps.setString(1, ingredient.getNomIngredient()) ;
	           ps.executeUpdate();
	           
	       }
	       
		   	/** Cette méthode récupère un objet de type Ingredient et le modifie dans la BDD **/ 
	       public static void  ModifierIngredient(Ingredient ingredient) throws Exception {
	    	   Connection conn = DatabaseConnection.initializeDatabase();
    	    PreparedStatement ps = conn.prepareStatement("update t_ingredient set nomIngredient=? where numIngredient=?") ;

    	    ps.setString(1, ingredient.getNomIngredient()) ;
    	    ps.setInt(2, ingredient.getNumIngredient()) ;
    	    ps.executeUpdate();
    	    
    	   ps.close();
    	   conn.close();
    	}
		   	/** Cette méthode supprime un ingrédient de la BDD dont l'id est passé en paramètre**/ 
	       public static void  SupprimerIngredient(int numIngredient) throws Exception {
	    	   Connection conn = DatabaseConnection.initializeDatabase();
	    	    PreparedStatement ps = conn.prepareStatement("delete from t_ingredient where numIngredient=?") ;
	    	    ps.setInt(1, numIngredient) ;
	    	    ps.executeUpdate();
	    	    ps.close();
	    	    conn.close();
	    	  }

	
}
