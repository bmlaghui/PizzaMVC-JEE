package pizza_package;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class ControllerIngredient
 */

@WebServlet("/ControllerIngredient")
public class ControllerIngredient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	java.util.List<Ingredient> listingredient ; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerIngredient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		//System.out.print(action);
		try {          
			/** Ce switch permet de rediriger l'action sur le controleur **/ 
            switch (action) {
        		/** Cette option renvoie vers la méthode qui affiche le formulaire d'ajout **/ 
                case "new":
                    ShowNewForm(request, response);
                    break;
                /** Cette option supprime un ingrédient **/ 
                case "delete":
                    DeleteIngredient(request, response);
                    break;
                /** Cette option renvoie vers la méthode qui affiche le formulaire de modification **/ 
                case "edit":
                    ShowEditForm(request, response);
                    break;
                /** Cette option renvoie vers la méthode qui affiche les détails d'un ingrédient **/ 
                case "view":
                    ShowIngredient(request, response);
                    break;
                /** Cette option renvoie vers la méthode qui affiche la liste des ingrédients **/ 
                default:
                    ListIngredient(request, response);
                    break;
            }
        } 
    	/** Cette fonction relève l'exception SQL **/ 
		catch (SQLException ex) {
            throw new ServletException(ex);
        }
    	/** Cette fonction relève l'exception  **/ 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		//System.out.print(action);
		try { 
        	/** Ce switch permet de rediriger l'action sur le controleur **/ 
            switch (action) {
        		/** Cette option renvoie vers la méthode qui insère un ingrédient dans la BDD **/ 
                case "insert":
                    InsertIngredient(request, response);
                    break;
                /** Cette option renvoie vers la méthode qui modifie un ingrédient dans la BDD **/ 
                case "update":
                    UpdateIngredient(request, response);
                    break;
            }
        } /** Cette fonction relève l'exception SQL **/ 
		catch (SQLException ex) {
            throw new ServletException(ex);
        }
    	/** Cette fonction relève l'exception  **/ 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	}
	
	
				/** Cette méthode récupère la liste des pizzas depuis le model IngredientDAO  et la renvoie vers la vue ingredient-list  **/ 
				private void ListIngredient(HttpServletRequest request, HttpServletResponse response)
			    throws Exception {
		 		List<Ingredient> listingredient = IngredientDAO.getAllIngredients();
			        request.setAttribute("listingredient", listingredient);
		              
			        RequestDispatcher dispatcher = request.getRequestDispatcher("ingredient-list.jsp");
			        dispatcher.forward(request, response);
			    }
				
				/** Cette méthode renvoie vers la vue de création d'un nouveau ingrédient (ingredient-add)  **/ 
			    private void ShowNewForm(HttpServletRequest request, HttpServletResponse response)
			    throws ServletException, IOException {
			        RequestDispatcher dispatcher = request.getRequestDispatcher("ingredient-form.jsp");
			        dispatcher.forward(request, response);
			    }
				/** Cette méthode renvoie vers la vue de modification d'un ingrédient (ingredient-form)  **/ 			    

			    private void ShowEditForm(HttpServletRequest request, HttpServletResponse response)
			    throws Exception {
			    	 int id = Integer.parseInt(request.getParameter("id"));
				        Ingredient existingingredient = IngredientDAO.getIngredient(id);
				        RequestDispatcher dispatcher = request.getRequestDispatcher("ingredient-form.jsp");
				        request.setAttribute("ingredient", existingingredient);
				        dispatcher.forward(request, response);
			    }
			    
				/** Cette méthode renvoie vers la vue d'affichage des détails d'un ingrédient (ingredient-view)  **/ 
			    private void ShowIngredient(HttpServletRequest request, HttpServletResponse response)
			    throws Exception {
			        int id = Integer.parseInt(request.getParameter("id"));
			        Ingredient existingingredient = IngredientDAO.getIngredient(id);
			        RequestDispatcher dispatcher = request.getRequestDispatcher("ingredient-view.jsp");
			        request.setAttribute("ingredient", existingingredient);
			        dispatcher.forward(request, response);

			    }
			    
				/** Cette méthode récupére des donnés envoyés par la vue (ingredient-add) et les envoie vers le model (fonction:AjouterIngredient) pour les insérer dans la BDD  **/ 
			    private void InsertIngredient(HttpServletRequest request, HttpServletResponse response)
			    throws Exception {
			        String nomIngredient = request.getParameter("nomIngredient");
			        Ingredient newingredient = new Ingredient(nomIngredient);
			        IngredientDAO.AjouterIngredient(newingredient);
			        response.sendRedirect("ControllerIngredient?action=list&page=1");
			        
			         
			    }
			    
				/** Cette méthode récupére des donnés envoyés par la vue (ingredient-form) et les envoie vers le model (fonction:ModifierIngredient) pour les modifier dans la BDD  **/ 
			    private void UpdateIngredient(HttpServletRequest request, HttpServletResponse response)
			    throws Exception {
			        int id = Integer.parseInt(request.getParameter("id"));
			        String nomIngredient = request.getParameter("nomIngredient");
			       
			        Ingredient pizz = new Ingredient(id,nomIngredient);
			        IngredientDAO.ModifierIngredient(pizz);
			        response.sendRedirect("ControllerIngredient?action=list&page=1");
			    }
			    
				/** Cette méthode renvoie vers le model (fonction:SupprimerIngredient) pour supprimer l'ingrédient de la BDD  **/ 
			    private void DeleteIngredient(HttpServletRequest request, HttpServletResponse response)
			    throws Exception {
			        int id = Integer.parseInt(request.getParameter("id"));
			        IngredientDAO.SupprimerIngredient(id);
			        response.sendRedirect("ControllerIngredient?action=list&page=1");

			    }
			  
}
