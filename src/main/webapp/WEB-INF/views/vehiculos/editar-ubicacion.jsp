<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Editar ubicación</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<main class="container py-4">
		<div class="row justify-content-center">
			<div class="col-12 col-lg-6">
				<h1 class="h3 mb-4">Editar ubicación</h1>

				<div class="card mb-3">
					<div class="card-body">
						<p class="mb-1"><strong>Placa:</strong> ${vehiculo.placa}</p>
						<p class="mb-1"><strong>Hora entrada:</strong> ${vehiculo.horaEntrada}</p>
						<p class="mb-1">
							<strong>Hora salida:</strong>
							<c:choose>
								<c:when test="${vehiculo.horaSalida != null}">${vehiculo.horaSalida}</c:when>
								<c:otherwise>Sin salida</c:otherwise>
							</c:choose>
						</p>
						<p class="mb-0"><strong>Tipo:</strong> ${vehiculo.tipoVehiculo.nombre}</p>
					</div>
				</div>

				<form action="${pageContext.request.contextPath}/vehiculos/ubicacion/${vehiculo.id}" method="post" class="card">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<div class="card-body">
						<div class="mb-3">
							<label for="ubicacion" class="form-label">Ubicación</label>
							<input type="text" class="form-control" id="ubicacion" name="ubicacion" value="${vehiculo.ubicacion}" required>
						</div>
					</div>
					<div class="card-footer d-flex justify-content-end gap-2">
						<a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/vehiculos">Cancelar</a>
						<button type="submit" class="btn btn-primary">Actualizar ubicación</button>
					</div>
				</form>
			</div>
		</div>
	</main>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
