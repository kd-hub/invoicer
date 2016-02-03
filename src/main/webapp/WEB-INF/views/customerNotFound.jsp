<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="content-wrapper">
			<div class="container">
				<section class="content">
					<div class="col-md-10 col-md-offset-1">
						<div class="callout callout-danger">
							<h4>Błąd</h4>
							<p>Brak klienta o podanym identyfikatorze:
								${invalidCustomerId}.</p>
						</div>
					</div>
				</section>
			</div>
		</div>
	</tiles:putAttribute>
	<tiles:putAttribute name="scripts">
		<div></div>
	</tiles:putAttribute>
</tiles:insertDefinition>