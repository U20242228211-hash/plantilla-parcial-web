<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Iniciar sesión</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
	<main class="container py-5">
		<div class="row justify-content-center">
			<div class="col-12 col-sm-10 col-md-6 col-lg-4">
				<div class="card shadow-sm">
					<div class="card-body p-4">
						<h1 class="h4 mb-4 text-center">Iniciar sesión</h1>

						<c:if test="${param.error != null}">
							<div class="alert alert-danger" role="alert">Usuario o contraseña incorrectos.</div>
						</c:if>
						<c:if test="${param.logout != null}">
							<div class="alert alert-success" role="alert">Sesión cerrada correctamente.</div>
						</c:if>

						<form action="${pageContext.request.contextPath}/procesar-login" method="post">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

							<div class="mb-3">
								<label for="username" class="form-label">Usuario</label>
								<input type="text" class="form-control" id="username" name="username" required autofocus>
							</div>

							<div class="mb-3">
								<label for="password" class="form-label">Contraseña</label>
								<input type="password" class="form-control" id="password" name="password" required>
							</div>

							<button type="submit" class="btn btn-primary w-100">Entrar</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</main>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
