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
						Stawki VAT <small>Dodaj nową stawkę VAT</small>
					</h1>
				</section>
				<section class="content">
					<div class="box box-info">
						<div class="box-header with-border">
							<h3 class="box-title">Nowa stawka VAT</h3>
						</div>
						<form:form modelAttribute="newVatRate" class="form-horizontal">
							<div class="box-body">
							<form:errors path="*" cssClass="alert alert-danger" element="div"/>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="vatRate">Stawka VAT</label>
									<div class=col-sm-2>
										<form:input id="vatRate" path="vatRate" type="text" class="form-control" />
										
									</div>
									<form:errors path="vatRate" cssClass="text-danger"/>
								</div>
							</div>
							<div class="box-footer">
							<a href="<c:url value='/vatrate/list'/>" class="btn btn-default">Anuluj</a>
							<button type="submit" class="btn btn-info pull-right">Dodaj</button>
							</div>
						</form:form>
					</div>
				</section>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>