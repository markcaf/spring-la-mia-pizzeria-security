package org.generation.italy.demo.serv;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Ingrediente;
import org.generation.italy.demo.repo.IngredienteRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class IngredienteService {
	
	@Autowired
	private IngredienteRepo ingredienteRepo;
	
	public void save (Ingrediente ingrediente) {
		ingredienteRepo.save(ingrediente);
	}
	
	public List<Ingrediente> findAll() {
		return ingredienteRepo.findAll();
	}
	
	public Ingrediente findIngredienteById(int id){
		return ingredienteRepo.findById(id).get();
	}
	
	public void deleteById(int id) {
		ingredienteRepo.deleteById(id);
	}
	
	@Transactional
	public List<Ingrediente> findAllWPizza() {
		List<Ingrediente> ingredienti = ingredienteRepo.findAll();

		for (Ingrediente ingrediente : ingredienti) {
			Hibernate.initialize(ingrediente.getPizze());
		}

		return ingredienti;
	}
}
