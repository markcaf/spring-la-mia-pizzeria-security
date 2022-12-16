package org.generation.italy.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.generation.italy.demo.pojo.Drink;
import org.generation.italy.demo.pojo.Ingrediente;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.pojo.Promozione;
import org.generation.italy.demo.pojo.Role;
import org.generation.italy.demo.pojo.User;
import org.generation.italy.demo.serv.DrinkService;
import org.generation.italy.demo.serv.IngredienteService;
import org.generation.italy.demo.serv.PizzaService;
import org.generation.italy.demo.serv.PromozioneService;
import org.generation.italy.demo.serv.RoleService;
import org.generation.italy.demo.serv.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzeriaApplication implements CommandLineRunner {

	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private DrinkService drinkService;
	
	@Autowired
	private PromozioneService promozioneService;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	public static void main(String[] args) {
		SpringApplication.run(PizzeriaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//INSERIMENTO PROMOZIONI
		Promozione promo1 = new Promozione(LocalDate.parse("2022-12-01"), LocalDate.parse("2022-12-31"), "Sconto Dicembre");
		Promozione promo2 = new Promozione(LocalDate.parse("2023-01-01"), LocalDate.parse("2023-01-31"), "Promo Gennaio");

		promozioneService.save(promo1);
		promozioneService.save(promo2);
		
		//INSERIMENTO INGREDIENTI
		Ingrediente mozzarella = new Ingrediente("Mozzarella");
		Ingrediente olio = new Ingrediente("Olio");
		Ingrediente pomodoro = new Ingrediente("Pomodoro");
		Ingrediente aglio = new Ingrediente("Aglio");
		Ingrediente origano = new Ingrediente("Origano");
		Ingrediente salame = new Ingrediente("Salame piccante");
		
		List<Ingrediente> ingredientiMargherita = new ArrayList<>();
		ingredientiMargherita.add(mozzarella);
		ingredientiMargherita.add(olio);
		ingredientiMargherita.add(pomodoro);
		
		List<Ingrediente> ingredientiMarinara = new ArrayList<>();
		ingredientiMarinara.add(pomodoro);
		ingredientiMarinara.add(aglio);
		ingredientiMarinara.add(origano);
		
		List<Ingrediente> ingredientiDiavola = new ArrayList<>();
		ingredientiDiavola.add(mozzarella);
		ingredientiDiavola.add(pomodoro);
		ingredientiDiavola.add(salame);
		
		List<Ingrediente> ingredientiFritta = new ArrayList<>();
		ingredientiFritta.add(mozzarella);
		ingredientiFritta.add(pomodoro);
		
		ingredienteService.save(mozzarella);
		ingredienteService.save(olio);
		ingredienteService.save(pomodoro);
		ingredienteService.save(aglio);
		ingredienteService.save(origano);
		ingredienteService.save(salame);
		
		// INSERIMENTO PIZZE

		Pizza p1 = new Pizza("Margherita", "La classica pizza napoletana", 4, promo1, ingredientiMargherita);
		Pizza p2 = new Pizza("Marinara", "Leggera ed essenziale, la tradizione", 3, promo1, ingredientiMarinara);
		Pizza p3 = new Pizza("Diavola", "Un sapore piccante su un letto di pomodoro", 5, promo2, ingredientiDiavola);
		Pizza p4 = new Pizza("Pizza fritta", "Ripieno fritto tradizionale", 7, null, ingredientiFritta);

		pizzaService.save(p1);
		pizzaService.save(p2);
		pizzaService.save(p3);
		pizzaService.save(p4);
		
		// INSERIMENTO DRINK

		Drink d1 = new Drink("Coca Cola", "Bevanda gassata analcolica", 3);
		Drink d2 = new Drink("Vino", "Rosso o bianco della casa", 6);
		Drink d3 = new Drink("Sprite", "Bevanda gassata analcolica", 3);
		Drink d4 = new Drink("Birra", "Birra chiara artigianale", 7);

		drinkService.save(d1);
		drinkService.save(d2);
		drinkService.save(d3);
		drinkService.save(d4);
		
		
		// DELETE
//		promozioneService.deletePromozioneById(1);
//		pizzaService.deleteById(1);

		// LETTURA
		
//		List<Drink> drinks = drinkService.findAll();
//		System.out.println(drinks);
		
//		System.out.println("---------------------------");
//		List<Pizza> pizze = pizzaService.findAll();
//		for (Pizza pizza : pizze) {
//			System.err.println(pizza + "\n\t" + pizza.getPromozione());
//		}
//
//		System.out.println("---------------------------");
//		
//		List<Promozione> promozioni = promozioneService.findAllWPizza();
//
//		for (Promozione promozione : promozioni) {
//			System.err.println("\n" + promozione);
//			for (Pizza pizza : promozione.getPizze()) {
//				System.err.println("\t" + pizza);
//			}
//		}
		
//		System.err.println("------------------------------");
//		List<Pizza> pizze = pizzaService.findAllWIngredienti();
//		for (Pizza pizza : pizze) {
//			System.err.println(pizza + "\n\t" + pizza.getIngredienti());
//		}
//		
//		System.err.println("---------------------------");
//		List<Ingrediente> ingredienti = ingredienteService.findAllWPizza();
//		for (Ingrediente ingrediente : ingredienti) {
//			System.err.println("\n- " + ingrediente +  "\n\t\n" + ingrediente.getPizze());
//		}
		
		// AUTH
		
		Role admin = new Role("admin");
		Role user = new Role("user");

		roleService.save(admin);
		roleService.save(user);

		Set<Role> roles = new HashSet<>();
		roles.add(user);
		roles.add(admin);

		User user1 = new User("user", "user", user);
		User admin1 = new User("admin", "admin", admin);

		userService.save(user1);
		userService.save(admin1);

	}
}
