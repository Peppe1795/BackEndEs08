package listaProdotti;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class App {

	public static void main(String[] args) {
		List<Product> lista = new ArrayList<>(Arrays.asList(
				new Product(546456, "Il signore degli anelli", "Books", 150.50),
				new Product(546456, "Angeli e demeoni", "Books", 25.50),
				new Product(546456, "Social media", "Books", 350.25),
				new Product(546456, "Il piccolo principe", "Books", 30.40),
				new Product(651651, "Samsung S22", "Elettronica", 1800.50),
				new Product(665165, "Pala", "Giardinaggio", 35.50), new Product(656151, "Pannolini", "Baby", 12.50),
				new Product(651451, "Passeggino", "Baby", 500.12), new Product(515611, "Ballatoio", "Baby", 350.14)));

		System.out.println("--------- Lista libri --------");
		Predicate<Product> isMoreThan100 = p -> p.getPrice() > 100;
		Predicate<Product> isBooksCategory = p -> p.getCategory().equals("Books");
		lista.stream().filter(isMoreThan100.and(isBooksCategory))
				.forEach(s -> System.out.println("Id: " + s.getId() + " " + "nome libro: " + s.getName() + " "
						+ "categoria: " + s.getCategory() + " " + "prezzo: " + s.getPrice()));

		System.out.println("--------- Lista ordini Baby -----------");
		Predicate<Product> isBabyCategory = p -> p.getCategory().equals("Baby");
		List<Product> prodBaby = lista.stream().filter(isBabyCategory).toList();

		Customer cliente1 = new Customer(6511651, "Antonio", 1);
		Customer cliente2 = new Customer(6565111, "Luca", 2);
		List<Order> ordini = new ArrayList<>(Arrays.asList(
				new Order(6565156, "Pending", LocalDate.now().minusDays(2), LocalDate.now().plusDays(3), prodBaby,
						cliente1),
				new Order(6565156, "Completed", LocalDate.now().minusDays(4), LocalDate.now(), prodBaby, cliente2)));
		System.out.println(cliente1.toString());
		ordini.forEach(s -> System.out.println(
				"Id: " + s.getId() + ", " + "status: " + s.getStatus() + ", " + "Data ordine " + s.getOrderDate() + ", "
						+ "Data consegna: " + s.getDeliveryDate() + ", " + s.getProducts().toString()));
		System.out.println(cliente2.toString());
		ordini.forEach(
				s -> System.out.println("Id: " + s.getId() + ", " + "status: " + s.getStatus() + ", " + "Data ordine "
						+ s.getOrderDate() + ", " + "Data consegna: " + s.getDeliveryDate() + ", " + s.getProducts()));

	}

}
