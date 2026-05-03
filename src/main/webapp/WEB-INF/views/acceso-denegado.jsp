<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Acceso denegado</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
	<main class="container py-5">
		<div class="row justify-content-center">
			<div class="col-12 col-md-7 col-lg-5">
				<div class="card shadow-sm">
					<div class="card-body p-4 text-center">
						<h1 class="h4 mb-3">Acceso denegado</h1>
						<p class="text-muted">No tienes permisos para acceder a esta página.</p>
						<a class="btn btn-primary" href="${pageContext.request.contextPath}/">Volver al inicio</a>
					</div>
				</div>
			</div>
		</div>
	</main>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
