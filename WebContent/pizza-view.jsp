<%@page import="pizza_package.DatabaseConnection" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="pizza_package.Pizza" %>
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
	<script src="assets/js/app-ajax.js"></script>
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
            <br>
            <% Pizza pizza= (Pizza) request.getAttribute("pizza");
            System.out.println("Nb Ing reçu: "+pizza.getIngredients().size());

            %>
            <div class="container col-md-6">
                <div class="card">
                    <div class="card-body">
                         <caption>
                            <h2>
                                    Détails d'une Pizza
                             </h2>
                        </caption>
<% if ( pizza != null)
                       {   
                    	%>
         <fieldset class="form-group">
       <img src="assets/images/<% out.print(pizza.getImage()); %>" id="imageAjax" width="100%" height="auto"/>
       </fieldset>
       
       <% } 
       if ( pizza == null)
                       {
                      %>
<fieldset class="form-group">
       <img src="assets/images/notfound.png" id="imageToCahnge" width="100%" height="auto"/>
       </fieldset>                                <%
                       }
					%>
                        <fieldset class="form-group">
                            <label>Pizza</label> <input type="text" value="<% if ( pizza != null) out.print(pizza.getDesignPizz()); %>" class="form-control" name="DesignPizz" required="required" disabled />
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Prix</label> <input type="text" value="<% if ( pizza != null) out.print(pizza.getPrice()); %> €" class="form-control" name="price" disabled />
                        </fieldset>
					    <fieldset class="form-group">
                            <label>Description</label> <input type="text" value="<% if ( pizza != null) out.print(pizza.getDescription()); %>" class="form-control" name="description" disabled/>
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Ingrédients</label>
                            <% List<Ingredient> ingredients = pizza.getIngredients();
                            if ( ingredients  != null){
                            	  for(Ingredient item : ingredients)
                            	    {
                            	%><fieldset class="form-group">
                             <input type="text" value="<%  out.print(item.getNomIngredient()); %>" class="form-control" name="description" disabled/>
                             </fieldset>
                             <% }} %>
                        </fieldset>
                        
                    </div>
                </div>
                  <!-- Footer -->
<footer class="page-footer font-small dark">

  <!-- Copyright -->
  <div class="footer-copyright text-center py-3">© 2020 Copyright 
    <a href="https://wWW.mlaghuibrahim.com/">Brahim MLAGHUI</a>
  </div>
  <!-- Copyright -->

</footer>
<!-- Footer -->
            </div>
          
       
        </body>

        </html>