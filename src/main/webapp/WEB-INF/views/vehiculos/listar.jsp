<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Vehículos</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<c:set var="esAdministrador" value="${pageContext.request.isUserInRole('ADMINISTRADOR')}" />
	<c:set var="esAcomodador" value="${pageContext.request.isUserInRole('ACOMODADOR')}" />

	<main class="container py-4">
		<div class="d-flex flex-wrap justify-content-between align-items-center gap-2 mb-4">
			<h1 class="h3 mb-0">Vehículos</h1>
			<div class="d-flex gap-2">
				<a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/">Inicio</a>
				<c:if test="${esAdministrador}">
					<a class="btn btn-primary" href="${pageContext.request.contextPath}/vehiculos/nuevo">Nuevo</a>
				</c:if>
				<form action="${pageContext.request.contextPath}/logout" method="post">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<button type="submit" class="btn btn-outline-danger">Cerrar sesión</button>
				</form>
			</div>
		</div>

		<div class="table-responsive">
			<table class="table table-striped table-hover align-middle">
				<thead class="table-dark">
					<tr>
						<th>Placa</th>
						<th>Hora entrada</th>
						<th>Hora salida</th>
						<th>Ubicación</th>
						<th>Tipo</th>
						<th class="text-end">Acciones</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="vehiculo" items="${vehiculos}">
						<tr>
							<td>${vehiculo.placa}</td>
							<td>${vehiculo.horaEntradaFormato}</td>
							<td>
								<c:choose>
									<c:when test="${vehiculo.horaSalida != null}">${vehiculo.horaSalidaFormato}</c:when>
									<c:otherwise>Sin salida</c:otherwise>
								</c:choose>
							</td>
							<td>${vehiculo.ubicacion}</td>
							<td>${vehiculo.tipoVehiculo.nombre}</td>
							<td>
								<div class="d-flex justify-content-end gap-2">
									<a class="btn btn-sm btn-outline-primary" href="${pageContext.request.contextPath}/vehiculos/ver/${vehiculo.id}">Ver</a>
									<c:if test="${esAdministrador}">
										<a class="btn btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/vehiculos/editar/${vehiculo.id}">Editar</a>
										<a class="btn btn-sm btn-outline-danger" href="${pageContext.request.contextPath}/vehiculos/eliminar/${vehiculo.id}">Eliminar</a>
									</c:if>
									<c:if test="${esAdministrador || esAcomodador}">
										<a class="btn btn-sm btn-outline-success" href="${pageContext.request.contextPath}/vehiculos/ubicacion/${vehiculo.id}">Editar ubicación</a>
									</c:if>
								</div>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${empty vehiculos}">
						<tr>
							<td colspan="6" class="text-center text-muted py-4">No hay vehículos registrados.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
	</main>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
