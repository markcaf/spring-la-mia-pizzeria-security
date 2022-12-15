package org.generation.italy.demo.controller;

import java.util.List;

import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.pojo.Promozione;
import org.generation.italy.demo.serv.PizzaService;
import org.generation.italy.demo.serv.PromozioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/promozioni")
public class PromozioneController {
	
	@Autowired
	private PromozioneService promozioneService;
	
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping
	public String index(Model model) {
		List<Promozione> promozioni = promozioneService.findAllWPizza();
		model.addAttribute("promozioni", promozioni);
		return "promozioni";
	}
	
	@GetMapping("/promozione/create")
	public String createPromozione(Model model) {

		Promozione promozione = new Promozione();
		List<Pizza> pizze = pizzaService.findAll();
		model.addAttribute("promozione", promozione);
		model.addAttribute("pizze", pizze);

		return "promozione-create";
	}

	@PostMapping("/promozione/create")
	public String storePromozione(@Valid Promozione promozione) {
		List<Pizza> pizzeInPromo = promozione.getPizze();
		for (Pizza pizza : pizzeInPromo) {
			pizza.setPromozione(promozione);
		}
		promozioneService.save(promozione);

		return "redirect:/promozioni";
	}
}
