<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="pizza_package.Ingredient" %>
<%@page import="pizza_package.Ingredient" %>
<%@page import="pizza_package.DatabaseConnection" %>
<%@page import="pizza_package.Ingredient" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
   <head>        
     <title>Pizza Management Application</title>
     <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	 <meta charset="utf-8">
	 <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/style.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
		</head>
<body>
<header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="${pageContext.request.contextPath}/ControllerIngredient?action=list&page=1" class="navbar-brand"> Pizza Management App </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="${pageContext.request.contextPath}/ControllerPizza?action=list&page=1" class="nav-link">Pizzas</a></li>
                        <li><a href="${pageContext.request.contextPath}/ControllerIngredient?action=list&page=1" class="nav-link">Ingrédients</a></li>
                    </ul>
                </nav>
            </header>
            <div class="container">
<div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

      
                    <h3 class="text-center">Liste des Ingredients</h3>
                    
                    <% 
                    List<Ingredient> listeIngredients = (List<Ingredient>) request.getAttribute("listingredient");

                    %>
                    <hr>
                    <div class="container text-left">

                        <a href="${pageContext.request.contextPath}/ControllerIngredient?action=new" class="btn btn-success">Ajouter un ingredient</a>
                    </div>
                    <br>
                    <table class="table">
  <thead class="thead-light">
    <tr>
      <th scope="col">#</th>
      <th scope="col">Nom</th>
      <th scope="col" colspan="2">Opérations</th>
    </tr>
  </thead>
  <tbody>
                    <%
            int pageNumber= Integer.parseInt(request.getParameter("page")); 
            int itemByPage=9;
            int max=pageNumber*itemByPage;
            int min=max-(itemByPage+1);
            int precedant=pageNumber-1;
            int suivant=pageNumber+1;
            int nbItems=listeIngredients.size();
            int modulo=nbItems%itemByPage;
            int numberOfPages;
            int index=0;
            if(modulo == 0)  numberOfPages=nbItems/itemByPage;
            else numberOfPages=(nbItems/itemByPage)+1;
              for(Ingredient item : listeIngredients)
    {
    	if(listeIngredients.indexOf(item) > min && listeIngredients.indexOf(item) < max )
    	{
    		index++;
    		
    		%>
    		<tr>
      <th scope="row"><a href="${pageContext.request.contextPath}/ControllerIngredient?action=view&id=<%=item.getNumIngredient()%>"><b><%=item.getNumIngredient()%></b><b>  </a></th>
      <td><a href="${pageContext.request.contextPath}/ControllerIngredient?action=view&id=<%=item.getNumIngredient()%>"><b><%=item.getNomIngredient()%></b><b>  </a></td>
      <td><a class="btn btn-primary" href="${pageContext.request.contextPath}/ControllerIngredient?action=edit&id=<%=item.getNumIngredient()%>" role="button">Modifier</a></td>
      <td>	      	    <a class="btn btn btn-danger" href="${pageContext.request.contextPath}/ControllerIngredient?action=delete&id=<%=item.getNumIngredient()%>" role="button">Supprimer</a>
      </td>
    </tr>   

<% 
    }
    	
                    }
%>
   
  </tbody>
</table>
      
      </div>   
      
        <div class="row">
        <nav aria-label="navigation" class="align-self-center">
  <ul class="pagination">
    <li class="page-item <%if (precedant == 0) { %> disabled <% } %>">
      <a class="page-link" href="${pageContext.request.contextPath}/ControllerIngredient?action=list&page=<% out.print(precedant); %>">Précédant</a>
    </li>
    <% for (int i=1; i<=numberOfPages; i++)
    {	
    %>
    
    
    <li class="page-item <%if (i == pageNumber) { %>active<% } %>">
      <a class="page-link" href="${pageContext.request.contextPath}/ControllerIngredient?action=list&page=<% out.print(i); %>"><% out.print(i); %><span class="sr-only">(current)</span></a>
    </li>
    <% } %>
    <li class="page-item <%if (suivant > numberOfPages) { %> disabled <% } %>">
      <a class="page-link" href="${pageContext.request.contextPath}/ControllerIngredient?action=list&page=<% out.print(suivant); %>">Suivant</a>
               &nbsp;&nbsp;&nbsp; Affichage de <%=index %> / <%=nbItems  %> enregistrements
    
    </li>
    
    
  </ul>
</nav>
      
        </div>
          </div>
</body>
</html> 