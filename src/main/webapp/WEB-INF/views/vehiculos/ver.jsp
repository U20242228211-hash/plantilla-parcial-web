<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Detalle vehículo</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<main class="container py-4">
		<div class="row justify-content-center">
			<div class="col-12 col-lg-6">
				<div class="card">
					<div class="card-header">
						<h1 class="h4 mb-0">Detalle del vehículo</h1>
					</div>
					<div class="card-body">
						<dl class="row mb-0">
							<dt class="col-sm-4">Placa</dt>
							<dd class="col-sm-8">${vehiculo.placa}</dd>

							<dt class="col-sm-4">Hora entrada</dt>
							<dd class="col-sm-8">${vehiculo.horaEntradaFormato}</dd>

							<dt class="col-sm-4">Hora salida</dt>
							<dd class="col-sm-8">
								<c:choose>
									<c:when test="${vehiculo.horaSalida != null}">${vehiculo.horaSalidaFormato}</c:when>
									<c:otherwise>Sin salida</c:otherwise>
								</c:choose>
							</dd>

							<dt class="col-sm-4">Ubicación</dt>
							<dd class="col-sm-8">${vehiculo.ubicacion}</dd>

							<dt class="col-sm-4">Tipo</dt>
							<dd class="col-sm-8">${vehiculo.tipoVehiculo.nombre}</dd>
						</dl>
					</div>
					<div class="card-footer text-end">
						<a class="btn btn-primary" href="${pageContext.request.contextPath}/vehiculos">Volver al listado</a>
					</div>
				</div>
			</div>
		</div>
	</main>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
