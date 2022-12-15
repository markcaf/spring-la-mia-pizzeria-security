package org.generation.italy.demo.pojo;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Promozione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	@NotNull(message = "La data di inizio deve essere inserita")
	private LocalDate dataInizio; 

	@Column
	@NotNull(message = "La data di fine deve essere inserita")
	private LocalDate dataFine; 

	@Column(unique = true)
	@NotNull(message = "Il titolo deve contenere qualcosa")
	private String titolo;
	
	@OneToMany(mappedBy = "promozione", cascade = CascadeType.REMOVE)
	private List<Pizza> pizze;
	
	public Promozione() { }
	public Promozione(LocalDate dataInizio, LocalDate dataFine, String titolo) {
		setDataInizio(dataInizio);
		setDataFine(dataFine);
		setTitolo(titolo);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}

	public LocalDate getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	public List<Pizza> getPizze() {
		return pizze;
	}
	public void setPizze(List<Pizza> pizze) {
		this.pizze = pizze;
	}
	
	@Override
	public String toString() {
		return getTitolo() + " (" + getDataInizio() + " / " + getDataFine() + ")";
	}
	
	
}
