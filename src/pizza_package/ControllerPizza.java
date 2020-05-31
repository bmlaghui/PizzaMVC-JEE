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
 * Servlet implementation class ControllerPizza
 */

@WebServlet("/ControllerPizza")
public class ControllerPizza extends HttpServlet {
	private static final long serialVersionUID = 1L;
	java.util.List<Pizza> listpizza ; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerPizza() {
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
            switch (action) {
            	/** Ce switch permet de rediriger l'action sur le controleur **/ 
                case "new": 
                	/** Cette option renvoie vers la méthode qui affiche le formulaire d'ajout **/ 
                    showNewForm(request, response);
                    break;
                case "delete":
                	/** Cette option supprime une pizza **/ 
                    deletepizza(request, response);
                    break;
                case "edit":
                	/** Cette option renvoie vers la méthode qui affiche le formulaire de modification **/ 
                    showEditForm(request, response);
                    break;
                case "view":
                	/** Cette option renvoie vers la méthode qui affiche les détails d'une pizza **/ 
                    showPizza(request, response);
                    break;
                default:
                	/** Cette option renvoie vers la méthode qui affiche la liste des pizzas **/ 
                    listpizza(request, response);
                    break;
            }
        } catch (SQLException ex) {
        	/** Cette fonction relève l'exception SQL **/ 
            throw new ServletException(ex);
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String action = request.getParameter("action");
		//System.out.print(action);
		try { 
        	/** Ce switch permet de rediriger l'action sur le controleur **/ 
            switch (action) {
        	/** Cette option renvoie vers la méthode qui insère une pizza dans la BDD **/ 
                case "insert":
                    insertpizza(request, response);
                    break;
                	/** Cette option renvoie vers la méthode qui modifie une pizza dans la BDD **/ 

                case "update":
                    updatepizza(request, response);
                    break;
            }
        } 
    	/** Cette méthode renvoie vers l'exception SQL  **/ 
		catch (SQLException ex) {
            throw new ServletException(ex);
        }
    	/** Cette méthode renvoie vers l'exception  **/ 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
				/** Cette méthode récupère la liste des pizzas depuis le model PizzaDAO  et la renvoie vers la vue pizza-list  **/ 
				private void listpizza(HttpServletRequest request, HttpServletResponse response)
			    throws Exception {
		 		List<Pizza> listpizza = PizzaDAO.listAllPizzas();
			        request.setAttribute("listpizza", listpizza);
		              
			        RequestDispatcher dispatcher = request.getRequestDispatcher("pizza-list.jsp");
			        dispatcher.forward(request, response);
			    }
				
				/** Cette méthode renvoie vers la vue de création d'une nouvelle pizza (pizza-add)  **/ 
			    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			    throws ServletException, IOException {
			        RequestDispatcher dispatcher = request.getRequestDispatcher("pizza-add.jsp");
			        dispatcher.forward(request, response);
			    }

				/** Cette méthode renvoie vers la vue de modification d'une  pizza (pizza-form)  **/ 			    
			    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			    throws Exception {
			    	 int id = Integer.parseInt(request.getParameter("id"));
				        Pizza existingpizza = PizzaDAO.getPizzaById(id);
				        RequestDispatcher dispatcher = request.getRequestDispatcher("pizza-form.jsp");
				        request.setAttribute("existingpizza", existingpizza);
				        dispatcher.forward(request, response);
				        System.out.println(response);
			    }
			    
				/** Cette méthode renvoie vers la vue d'affichage des détails  d'une  pizza (pizza-view)  **/ 
			    private void showPizza(HttpServletRequest request, HttpServletResponse response)
			    throws Exception {
			        int id = Integer.parseInt(request.getParameter("id"));
			        Pizza existingpizza = PizzaDAO.getPizzaById(id);
			        RequestDispatcher dispatcher = request.getRequestDispatcher("pizza-view.jsp");
			        request.setAttribute("pizza", existingpizza);
			        System.out.println("Nb Ing envoyé: "+ existingpizza.getIngredients().size());
			        dispatcher.forward(request, response);

			    }
			    
				/** Cette méthode récupére des donnés envoyés par la vue (pizza-add) et les envoie vers le model (fonction:AjouterPizza) pour les insérer dans la BDD  **/ 
			    private void insertpizza(HttpServletRequest request, HttpServletResponse response)
			    throws Exception {
			        String DesignPizz = request.getParameter("DesignPizz");
			        float price = Float.parseFloat(request.getParameter("price"));
			        String description = request.getParameter("description");
			        String image = request.getParameter("image");
			        String[] values =request.getParameterValues("ingredients");
			        List<Ingredient> ingredients=new ArrayList<Ingredient>();
			        int[] result = new int[values.length];
			        for (int i = 0; i < values.length; i++) {
			           result[i] = Integer.valueOf(values[i]);
			           Ingredient item=IngredientDAO.getIngredient(result[i]);
			           ingredients.add(item);
			        }
			        Pizza newpizza = new Pizza(DesignPizz,price,description,image,ingredients);
			        PizzaDAO.AjouterPizza(newpizza);
			        response.sendRedirect("ControllerPizza?action=list&page=1");
			    }
			    
				/** Cette méthode récupére des donnés envoyés par la vue (pizza-form) et les envoie vers le model (fonction:ModifierPizza) pour les modifier dans la BDD  **/ 
			    private void updatepizza(HttpServletRequest request, HttpServletResponse response)
			    throws Exception {
			        int id = Integer.parseInt(request.getParameter("id"));
			        String DesignPizz = request.getParameter("DesignPizz");
			        float price = Float.parseFloat(request.getParameter("price"));
			        String description = request.getParameter("description");
			        String image = request.getParameter("monImage");
			        String[] values =request.getParameterValues("ingredients");
			        List<Ingredient> ingredients=new ArrayList<Ingredient>();
			        int[] result = new int[values.length];
			        for (int i = 0; i < values.length; i++) {
			            result[i] = Integer.valueOf(values[i]);
			           Ingredient item=IngredientDAO.getIngredient(result[i]);
			           ingredients.add(item);
			        }
			        Pizza pizz = new Pizza(id,DesignPizz,price,description,image,ingredients);
			        PizzaDAO.ModifierPizza(pizz);
			        response.sendRedirect("ControllerPizza?action=list&page=1");

			    }
			    
				/** Cette méthode renvoie vers le model (fonction:SupprimerPizza) pour supprimer la pizza de la BDD  **/ 
			    private void deletepizza(HttpServletRequest request, HttpServletResponse response)
			    throws Exception {
			        int id = Integer.parseInt(request.getParameter("id"));
			        PizzaDAO.SupprimerPizza(id);
			        response.sendRedirect("ControllerPizza?action=list&page=1");

			    }
			  
}
