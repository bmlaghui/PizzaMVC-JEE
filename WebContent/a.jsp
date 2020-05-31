<%@ page language="java"  pageEncoding="UTF-8"%>
<%@page import="pizza_package.DatabaseConnection" %>
<%@page import="pizza_package.Pizza" %>

 
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
        <html>
   <head>        
     <title>Pizza Management Application</title>
    
		</head>

        <body>

           	<form method="post" enctype="multipart/form-data"  action="uploadImage" >
                          
                     
               
                            <fieldset class="form-group">
    						   <img src="assets/images/notfound.png" id="imageToCahnge" width="100%" height="auto" />
     						</fieldset> 
					
                       		
	                        
	                            <input type="file"  id="imageSelected" class="form-control" name="image" accept=".jpg, .jpeg, .png" />
	                        
	                        <input type="submit" class="btn btn-success" value="Enregistrer" />
	                        
                        </form>
                                     <script src="assets/js/app-ajax.js"></script>                     
             
          
       
        </body>

        </html>