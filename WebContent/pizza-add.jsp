<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="pizza_package.DatabaseConnection"%>
<%@page import="java.util.*"%>
<%@page import="pizza_package.Pizza"%>
<%@page import="pizza_package.PizzaDAO"%>
<%@page import="pizza_package.Ingredient"%>
<%@page import="pizza_package.IngredientDAO"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Pizza Management Application - Ajouter une pizza</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/style.css">
<script src="assets/js/app-ajax.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>

<body>

	<header> <nav class="navbar navbar-expand-md navbar-dark"
		style="background-color: tomato">
	<div>
		<a
			href="${pageContext.request.contextPath}/ControllerPizza?action=list&page=1"
			class="navbar-brand"> Pizza Management App </a>
	</div>

	<ul class="navbar-nav">
		<li><a
			href="${pageContext.request.contextPath}/ControllerPizza?action=list&page=1"
			class="nav-link">Pizzas</a></li>
		<li><a
			href="${pageContext.request.contextPath}/ControllerIngredient?action=list&page=1"
			class="nav-link">Ingrédients</a></li>
	</ul>
	</nav> </header>
	<br>

	<div class="container col-md-6">
		<div class="card">
			<div class="card-body">

				<caption>
					<h2>Ajouter Pizza</h2>
				</caption>

				<form method="post" action="${pageContext.request.contextPath}/ControllerPizza?action=insert">

					<fieldset class="form-group">
						<img src="assets/images/notfound.png" id="imageToChange"
							width="100%" height="auto" />
					</fieldset>

					<fieldset class="form-group">
						<label>Pizza</label> <input type="text" class="form-control"
							name="DesignPizz" required />
					</fieldset>

					<fieldset class="form-group">
						<label>Prix</label> <input type="text" class="form-control"
							name="price" required />
					</fieldset>

					<fieldset class="form-group">
						<label>Description</label> <input type="text" class="form-control"
							name="description" />
					</fieldset>
					<fieldset class="form-group">
						<label>Image</label> <input type="file" id="imageSelected"
							class="form-control" name="image" accept=".jpg, .jpeg, .png" />
					</fieldset>
					<fieldset class="form-group">
						<label>Ingrédients</label>
						<div class="row">

							<div class="col-md-9">

								<div class="form-check">
									<%
										for (Ingredient unIngredient : IngredientDAO.getAllIngredients()) {
									%>
									<input class="form-check-input" type="checkbox"
										name="ingredients"
										value="<%out.print(unIngredient.getNumIngredient());%>" />
									<label>&nbsp; &nbsp; <%
 	out.println(unIngredient.getNomIngredient());
 %></label>&nbsp;
									&nbsp; &nbsp;
									<%
										}
									%>


								</div>

							</div>

						</div>
					</fieldset>

					<input type="submit" class="btn btn-success" value="Enregistrer" />
				</form>
			</div>
		</div>
		<!-- Footer -->
		<footer class="page-footer font-small dark"> <!-- Copyright -->
		<div class="footer-copyright text-center py-3">
			© 2020 Copyright <a href="https://wWW.mlaghuibrahim.com/">Brahim
				MLAGHUI</a>
		</div>
		<!-- Copyright --> </footer>
		<script src="assets/js/app-ajax.js"></script>

		<!-- Footer -->
	</div>


</body>

</html>