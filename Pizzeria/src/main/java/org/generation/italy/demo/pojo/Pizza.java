package org.generation.italy.demo.pojo;

import java.util.List;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@NotEmpty(message = "Il nome deve contenere qualcosa")
	@Column(name="nome")
	private String nome;
	
	@Lob
	@Nullable
	@Column(name="descrizione")
	private String descrizione;
	
	@NotNull(message = "Il prezzo non può essere vuoto")
	@Min(value=1, message = "Il prezzo deve essere maggiore di zero")
	private int prezzo;
	
	@ManyToOne
	@JoinColumn(name="promozione_id", nullable=true)
	private Promozione promozione;
	
	@ManyToMany
	private List<Ingrediente> ingredienti;
	
	public Pizza() { }
	public Pizza(String nome, String descrizione, int prezzo, Promozione promozione) {
		setNome(nome);
		setDescrizione(descrizione);
		setPrezzo(prezzo);
		setPromozione(promozione);
	}
	public Pizza(String nome, String descrizione, int prezzo, Promozione promozione, List<Ingrediente> ingredienti) {
		this(nome, descrizione, prezzo, promozione);
		setIngredienti(ingredienti);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}
	
	public Promozione getPromozione() {
		return promozione;
	}
	public void setPromozione(Promozione promozione) {
		this.promozione = promozione;
	}
	
	public List<Ingrediente> getIngredienti() {
		return ingredienti;
	}
	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}
	
	public void addIngredienti(Ingrediente ingrediente) {

		boolean finded = false;
		for (Ingrediente i : getIngredienti()) 
			if (i.getId() == ingrediente.getId())
				finded = true;

		if (!finded) 
			getIngredienti().add(ingrediente);
	}

	public void removeIngredienti(Ingrediente ingrediente) {
		getIngredienti().remove(ingrediente);
	}
	
	@Override
	public String toString() {
		return "ID: " + getId()
				+ "\nNome: " + getNome()
				+ "\nDescrizione: " + getDescrizione()
				+ "\nPrezzo: " + getPrezzo();
	}
}
