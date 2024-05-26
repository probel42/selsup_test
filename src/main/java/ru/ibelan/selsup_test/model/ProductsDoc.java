package ru.ibelan.selsup_test.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Документ для ввода в оборот товаров.
 * TODO description (описание для полей)
 */
@Data
public class ProductsDoc {
	private ProductsDocDescription description;
	@JsonAlias("doc_id")
	private String docId;
	@JsonAlias("doc_status")
	private String docStatus;
	@JsonAlias("doc_type")
	private String docType;
	private boolean importRequest;
	@JsonAlias("owner_inn")
	private String ownerInn;
	@JsonAlias("participant_inn")
	private String participantInn;
	@JsonAlias("producer_inn")
	private String producerInn;
	@JsonAlias("production_date")
	private LocalDate productionDate;
	@JsonAlias("production_type")
	private String productionType;
	private List<Product> products;
	@JsonAlias("reg_date")
	private LocalDate regDate;
	@JsonAlias("reg_number")
	private String regNumber;
}
