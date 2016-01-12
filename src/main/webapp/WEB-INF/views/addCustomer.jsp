<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="content-wrapper">
			<div class="container">
				<section class="content-header">
					<h1>
						Klienci <small>Dodaj nowego klienta</small>
					</h1>
					<ol class="breadcrumb">
						<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
						<li><a href="#">Layout</a></li>
						<li class="active">Top Navigation</li>
					</ol>
				</section>
				<section class="content">
					<div class="box box-info">
						<div class="box-header with-border">
							<h3 class="box-title">Nowy klient</h3>
						</div>
						<form:form modelAttribute="newCustomer" class="form-horizontal">
							<div class="box-body">
							<form:errors path="*" cssClass="alert alert-danger" element="div"/>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="companyName">Nazwa firmy</label>
									<div class=col-sm-9>
										<form:input id="companyName" path="companyName" type="text" class="form-control" />
										<form:errors path="companyName" cssClass="text-danger"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="vatIdNumber">Numer NIP</label>
									<div class=col-sm-9>
										<form:input id="vatIdNumber" path="vatIdNumber" type="text" class="form-control" />
										<form:errors path="vatIdNumber" cssClass="text-danger"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="streetName">Ulica</label>
									<div class=col-sm-9>
										<form:input id="streetName" path="streetName" type="text" class="form-control" />
										<form:errors path="streetName" cssClass="text-danger"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="streetNumber">Numer domu</label>
									<div class=col-sm-9>
										<form:input id="streetNumber" path="streetNumber" type="text" class="form-control" />
										<form:errors path="streetNumber" cssClass="text-danger"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="apartmentNumber">Numer lokalu</label>
									<div class=col-sm-9>
										<form:input id="apartmentNumber" path="apartmentNumber" type="text" class="form-control" />
										<form:errors path="apartmentNumber" cssClass="text-danger"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="postalCode">Kod pocztowy</label>
									<div class=col-sm-9>
										<form:input id="postalCode" path="postalCode" type="text" class="form-control" />
										<form:errors path="postalCode" cssClass="text-danger"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="cityName">Miejscowość</label>
									<div class=col-sm-9>
										<form:input id="cityName" path="cityName" type="text" class="form-control" />
										<form:errors path="cityName" cssClass="text-danger"/>
									</div>
								</div>
							</div>
							<div class="box-footer">
							<a href="<c:url value='/customer'/>" class="btn btn-default">Anuluj</a>
							<button type="submit" class="btn btn-info pull-right">Dodaj</button>
							</div>
						</form:form>
					</div>
				</section>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>