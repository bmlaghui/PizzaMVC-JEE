<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="pizza_package.Pizza" %>
<%@page import="pizza_package.Ingredient" %>
<%@page import="pizza_package.DatabaseConnection" %>
<%@page import="pizza_package.Pizza" %>

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
                        <a href="${pageContext.request.contextPath}/ControllerPizza?action=list&page=1" class="navbar-brand"> Pizza Management App </a>
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

      
                    <h3 class="text-center">Liste des Pizzas</h3>
                    
                    <% 
                    List<Pizza> listePizzas = (List<Pizza>) request.getAttribute("listpizza");
                    %>
                    <hr>
                    <div class="container text-left">

                        <a href="${pageContext.request.contextPath}/ControllerPizza?action=new" class="btn btn-success">Ajouter une pizza</a>
                    </div>
                    <br>
                    <%
            int pageNumber= Integer.parseInt(request.getParameter("page")); 
            int itemByPage=9;
            int max=pageNumber*itemByPage;
            int min=max-(itemByPage+1);
            int precedant=pageNumber-1;
            int suivant=pageNumber+1;
            int nbItems=listePizzas.size();
            int modulo=nbItems%itemByPage;
            int numberOfPages;
            int index=0;
            if(modulo == 0)  numberOfPages=nbItems/itemByPage;
            else numberOfPages=(nbItems/itemByPage)+1;
              for(Pizza item : listePizzas)
    {
    	if(listePizzas.indexOf(item) > min && listePizzas.indexOf(item) < max )
    	{
    		index++;
    		
    		%>
        <div class="col-md-4 col-sm-6 col-xs-10">
	      <div class="thumbnail" >
	          	        <a href="${pageContext.request.contextPath}/ControllerPizza?action=view&id=<%=item.getId()%>">
	          
	          <img src="assets/images/<%=item.getImage()%>" class="zoom" alt="Lights" style="width:100%; height:70%"></a>
	          <div class="caption" style="text-align: center;">
	           <p> <%=item.getPrice()%> €</b> </p>
	           <p> <a href="${pageContext.request.contextPath}/ControllerPizza?action=view&id=<%=item.getId()%>"><b><%=item.getDesignPizz()%></b><b> (<% out.println(item.getIngredients().size()); %></b> Ingrédients) </a></p>
	    	    <a class="btn btn-primary" href="${pageContext.request.contextPath}/ControllerPizza?action=edit&id=<%=item.getId()%>" role="button">Modifier</a>
	      	    <a class="btn btn btn-danger" href="${pageContext.request.contextPath}/ControllerPizza?action=delete&id=<%=item.getId()%>" role="button">Supprimer</a>
	    
	        </div>
	        
	        
	      </div>
	     <p style="border-bottom:3px solid black; position: relative; top: -20px;"></p>
	    </div>
<% 
    }
    	
                    }
%>
      
      </div>   
      
        <div class="row">
        <nav aria-label="navigation" class="align-self-center">
  <ul class="pagination">
    <li class="page-item <%if (precedant == 0) { %> disabled <% } %>">
      <a class="page-link" href="${pageContext.request.contextPath}/ControllerPizza?action=list&page=<% out.print(precedant); %>">Précédant</a>
    </li>
    <% for (int i=1; i<=numberOfPages; i++)
    {	
    %>
    
    
    <li class="page-item <%if (i == pageNumber) { %>active<% } %>">
      <a class="page-link" href="${pageContext.request.contextPath}/ControllerPizza?action=list&page=<% out.print(i); %>"><% out.print(i); %><span class="sr-only">(current)</span></a>
    </li>
    <% } %>
    <li class="page-item <%if (suivant > numberOfPages) { %> disabled <% } %>">
      <a class="page-link" href="${pageContext.request.contextPath}/ControllerPizza?action=list&page=<% out.print(suivant); %>">Suivant</a>
               &nbsp;&nbsp;&nbsp; Affichage de <%=index %> / <%=nbItems  %> enregistrements
    
    </li>
    
    
  </ul>
</nav>
      
        </div>
          </div>
</body>
</html> 