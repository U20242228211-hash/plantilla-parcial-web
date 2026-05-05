<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>${titulo}</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<main class="container py-4">
		<div class="row justify-content-center">
			<div class="col-12 col-lg-7">
				<h1 class="h3 mb-4">${titulo}</h1>

				<form action="${pageContext.request.contextPath}${accion}" method="post" class="card">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<div class="card-body">
						<div class="mb-3">
							<label for="placa" class="form-label">Placa</label>
							<input type="text" class="form-control" id="placa" name="placa" value="${vehiculo.placa}" maxlength="6" required>
						</div>

						<div class="row">
							<div class="col-md-6 mb-3">
								<label for="horaEntrada" class="form-label">Hora entrada</label>
								<input type="time" class="form-control" id="horaEntrada" name="horaEntrada" value="${vehiculo.horaEntradaFormato}" step="60" required>
							</div>
							<div class="col-md-6 mb-3">
								<label for="horaSalida" class="form-label">Hora salida</label>
								<input type="time" class="form-control" id="horaSalida" name="horaSalida" value="${vehiculo.horaSalidaFormato}" step="60">
							</div>
						</div>

						<div class="mb-3">
							<label for="ubicacion" class="form-label">Ubicación</label>
							<input type="text" class="form-control" id="ubicacion" name="ubicacion" value="${vehiculo.ubicacion}" required>
						</div>

						<div class="mb-3">
							<label for="tipoVehiculoId" class="form-label">Tipo de vehículo</label>
							<select class="form-select" id="tipoVehiculoId" name="tipoVehiculoId" required>
								<option value="">Seleccione...</option>
								<c:forEach var="tipo" items="${tiposVehiculo}">
									<c:choose>
										<c:when test="${vehiculo.tipoVehiculo != null && vehiculo.tipoVehiculo.id == tipo.id}">
											<option value="${tipo.id}" selected>${tipo.nombre}</option>
										</c:when>
										<c:otherwise>
											<option value="${tipo.id}">${tipo.nombre}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="card-footer d-flex justify-content-end gap-2">
						<a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/vehiculos">Cancelar</a>
						<button type="submit" class="btn btn-primary">Guardar</button>
					</div>
				</form>
			</div>
		</div>
	</main>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
