<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@page import="pizza_package.DatabaseConnection" %>
<%@page import="pizza_package.Pizza" %>

 
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
                    </ul>
                </nav>
            </header>
            <br>
            <% Pizza pizza= (Pizza) request.getAttribute("pizza"); %>
            <div class="container col-md-6">
                <div class="card">
                    <div class="card-body">
                      
                        <caption>
                            <h2>
                                 <% 
                                 if ( pizza != null){ %> Modifier Pizza <%	} 
                     		     if ( pizza == null){ %> Ajouter Pizza <%	} 
								%>
                            </h2>
                        </caption>
 <% if ( pizza != null)
                       {   
                    	%>
                    	<form method="post"   action="${pageContext.request.contextPath}/ControllerPizza?action=update" >
							<input type="hidden" name="id" value="<% out.print(pizza.getId()); %>" />
                            <fieldset class="form-group">
		    			   		<img src="assets/images/<% out.print(pizza.getImage()); %>" id="imageAjax" width="100%" height="auto"/>
					      	</fieldset>
                      <%
                      	} 
                       if ( pizza == null)
                       {
                      %>
                      <form method="post" enctype="multipart/form-data"  action="updateImage" >
                          
                     
               
                            <fieldset class="form-group">
    						   <img src="assets/images/notfound.png" id="imageToChange" width="100%" height="auto" />
     						</fieldset> 
					
                       		
	                        
	                            <input type="file"  id="imageSelected" class="form-control" name="image" accept=".jpg, .jpeg, .png" />
	                        
	                        <input type="submit" class="btn btn-success" value="Upload" id="btnOk" />
	                        
                        </form>
                        
                    	
					<%
                       }
					%>
					
                       		 
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
                        <script src="assets/js/app-ajax.js"></script>                     

<!-- Footer -->
            </div>
          
       
        </body>

        </html>