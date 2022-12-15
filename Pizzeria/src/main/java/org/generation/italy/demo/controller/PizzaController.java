package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Ingrediente;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.pojo.Promozione;
import org.generation.italy.demo.serv.IngredienteService;
import org.generation.italy.demo.serv.PizzaService;
import org.generation.italy.demo.serv.PromozioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private PromozioneService promozioneService;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@GetMapping
	public String getIndex(Model model) {
		List<Pizza> pizze = pizzaService.findAll();
		model.addAttribute("pizze", pizze);
		return "index";
	}
	
	@GetMapping("/pizza/{id}")
	public String getPizza(@PathVariable("id") int id, Model model) {
		
		Optional<Pizza> optPizza = pizzaService.findPizzaById(id);
		
		if (optPizza.isEmpty()) {
			System.err.println("Pizza non disponibile con id: " + id);
		}
		
		Pizza pizza = optPizza.get();
		
		model.addAttribute("pizza", pizza);
		
		return "pizza";
	}
	
	@GetMapping("/pizza/create")
	public String createPizza(Model model) {
		List<Promozione> promozioni = promozioneService.findAll();
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		Pizza pizza = new Pizza();
		model.addAttribute("pizza", pizza);
		model.addAttribute("promozioni", promozioni);
		model.addAttribute("ingredienti", ingredienti);
		return "pizza-create";
	}
	@PostMapping("/pizza/create")
	public String storePizza(@Valid @ModelAttribute("pizza") Pizza pizza, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/pizza/create";
		}
		
		pizzaService.save(pizza);
		return "redirect:/";
	}
	
	@GetMapping("/pizza/update/{id}")
	public String editPizza(@PathVariable("id") int id, Model model) {

		Optional<Pizza> optPizza = pizzaService.findPizzaById(id);
		Pizza pizza = optPizza.get();
		
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		List<Promozione> promozioni = promozioneService.findAll();

		model.addAttribute("ingredienti", ingredienti);
		model.addAttribute("promozioni", promozioni);
		model.addAttribute("pizza", pizza);

		return "pizza-update";
	}
	
	@PostMapping("/pizza/store")
	public String updatePizza(@Valid @ModelAttribute("pizza") Pizza pizza, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/pizza/update/" + pizza.getId();
		}

		pizzaService.save(pizza);		

		return "redirect:/";
	}
	
	@GetMapping("/pizza/delete/{id}")
	public String deletePizza(@PathVariable("id") int id) {

		Optional<Pizza> optPizza = pizzaService.findPizzaById(id);
		Pizza pizza = optPizza.get();

		pizzaService.delete(pizza);

		return "redirect:/";
	}
	
	@GetMapping("/search")
	public String getSearchPizzaByName(Model model, 
			@RequestParam(name = "q", required = false) String query) {
		
//		List<Pizza> pizze = null;
//		if (query == null) {
//			
//			pizze = pizzaService.findAll();
//			
//		} else {
//			
//			pizze = pizzaService.findByName(query);
//		}
		List<Pizza> pizze = query == null 
							? pizzaService.findAll()
							: pizzaService.findByNome(query); 
		
		model.addAttribute("pizze", pizze);
		model.addAttribute("query", query);
		
		return "pizze-search";
	}
}
