<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Plantilla Parcial Web</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<main class="container py-5">
		<div class="p-4 border rounded">
			<h1 class="h3 mb-3">Plantilla Parcial Web</h1>
			<p class="mb-0">Proyecto base Spring Boot con JSP, Bootstrap, JPA y MySQL.</p>
			<div class="d-flex gap-2 mt-4">
				<a class="btn btn-primary" href="${pageContext.request.contextPath}/vehiculos">Vehículos</a>
				<form action="${pageContext.request.contextPath}/logout" method="post">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
					<button type="submit" class="btn btn-outline-secondary">Cerrar sesión</button>
				</form>
			</div>
		</div>
	</main>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
