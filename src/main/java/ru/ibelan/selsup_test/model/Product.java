package ru.ibelan.selsup_test.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.time.LocalDate;

/**
 * Вводимый в оборот товар.
 * TODO description (описание для полей)
 */
@Data
public class Product {
	@JsonAlias("certificate_document")
	private String certificateDocument;
	@JsonAlias("certificate_document_date")
	private LocalDate certificateDocumentDate;
	@JsonAlias("certificate_document_number")
	private String certificateDocumentNumber;
	@JsonAlias("owner_inn")
	private String ownerInn;
	@JsonAlias("producer_inn")
	private String producerInn;
	@JsonAlias("production_date")
	private LocalDate productionDate;
	@JsonAlias("tnved_code")
	private String tnvedCode;
	@JsonAlias("uit_code")
	private String uitCode;
	@JsonAlias("uitu_code")
	private String uituCode;
}
