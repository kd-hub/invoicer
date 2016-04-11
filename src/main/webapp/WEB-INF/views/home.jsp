<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="content-wrapper">
			<div class="container">
				<section class="content-header"></section>
				<section class="content">
					<div class="box box">
						<div class="box-header with-border">
							<h3 class="box-title">O aplikacji Invoicer</h3>
							<div class="box-tools pull-right">
								<button data-original-title="Remove" type="button"
									class="btn btn-box-tool" data-widget="remove"
									data-toggle="tooltip" title="">
									<i class="fa fa-times"></i>
								</button>
							</div>
						</div>
						<div class="box-body">
							<div>
								Aplikacja umożliwia wystawianie faktur w dowolnej firmie (w
								uproszczonym modelu biznesowym). Została napisana w Javie
								Enterprise Edition z wykorzystaniem frameworków:<br> Spring
								MVC, Hibernate i Twitter Bootstrap.
								<p></p>
							</div>
							<div class="alert alert-info alert-dismissible">
								<button type="button" class="close" data-dismiss="alert"
									aria-hidden="true">×</button>
								<h4>
									<i class="icon fa fa-warning"></i> Baza danych
								</h4>
								Możesz zresetować zawartość bazy danych i wczytać do niej
								przykładowe dane wybierając: <i>Ustawienia / <a
									href="<c:url value='/database' />">Baza danych</a></i> w górnym
								menu.
							</div>
							<h4>Funkcje aplikacji:</h4>
						</div>
						<div class="box-footer">
							<dl class="dl-horizontal list-unstyled">
								<dt>Klienci:</dt>
								<dd>
									<li>dodawanie, usuwanie, wyświetlanie i edycja danych
										klienta</li>
									<li>weryfikacja możliwości usunięcia danych klienta pod
										względem występowania w wystawionych fakturach</li>
								</dd>
								<dt>Produkty:</dt>
								<dd>
									<li>dodawanie, usuwanie, wyświetlanie i edycja produktów
										wraz ze stanami magazynowymi</li>
									<li>weryfikacja możliwości usunięcia danych produktu pod
										względem występowania w wystawionych fakturach</li>
									<li>weryfikacja możliwości dodania nowego produktu -
										występowanie w bazie co najmniej jednej stawki VAT</li>
								</dd>
								<dt>Fakury:</dt>
								<dd>
									<li>dodawanie i wyświetlanie faktur</li>
									<li>weryfikacja dostępności produktu (stan magazynu) i
										ceny sprzedaży (powyżej ceny zakupu) w procesie dodawania
										kolejnych pozycji na fakturze</li>
									<li>weryfikacja możliwości dodania nowej faktury -
										występowanie w bazie co najmniej jednej stawki VAT i jednego
										produktu</li>
								</dd>
								<dt>Stawki VAT:</dt>
								<dd>
									<li>dodawanie i usuwanie stawek VAT</li>
									<li>weryfikacja możliwości usunięcia stawki VAT pod
										względem występowania w produktach</li>
								</dd>
								<dt>Pozostałe:</dt>
								<dd>
									<li>kontrola poprawności danych wpisywanych w formularzach</li>
								</dd>
							</dl>
							<p></p>
							
						</div>
						<div class="box-footer">
						<h4>Diagram ERD:</h4>
							<p style="text-align: center">
								<img
									src="<c:url value="/resources/images/invoicer_erd.png"></c:url>"
									alt="invoicer_erd.png" />
							</p>
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