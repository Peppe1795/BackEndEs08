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
				new Product(651651, "Giacca", "Boys", 450.10), new Product(665165, "Cravatta", "Boys", 125.40),
				new Product(665165, "Scarpe", "Boys", 300.40), new Product(656151, "Pannolini", "Baby", 12.50),
				new Product(651451, "Passeggino", "Baby", 500.12), new Product(515611, "Ballatoio", "Baby", 350.14)));

		System.out.println("--------- Lista libri --------");

		System.out.println();

		Predicate<Product> isMoreThan100 = p -> p.getPrice() > 100;
		Predicate<Product> isBooksCategory = p -> p.getCategory().equals("Books");
		List<Product> listaProdotti = lista.stream().filter(isMoreThan100.and(isBooksCategory)).toList();
		listaProdotti.forEach(s -> System.out.println("Id: " + s.getId() + " " + "nome libro: " + s.getName() + " "
				+ "categoria: " + s.getCategory() + " " + "prezzo: " + s.getPrice()));
		System.out.println();

		System.out.println("--------- Lista ordini Baby -----------");

		System.out.println();

		Predicate<Product> isBabyCategory = p -> p.getCategory().equals("Baby");
		List<Product> prodBaby = lista.stream().filter(isBabyCategory).toList();

		Customer cliente1 = new Customer(6511651, "Antonio", 1);
		Customer cliente2 = new Customer(6565111, "Luca", 2);
		Customer cliente3 = new Customer(6516151, "Francesco", 2);
		Customer cliente4 = new Customer(1611615, "Ambrogio", 2);

		List<Order> ordini = new ArrayList<>(Arrays.asList(
				new Order(6565156, "Pending", LocalDate.now().minusDays(2), LocalDate.now().plusDays(3), prodBaby,
						cliente1),
				new Order(6565156, "Completed", LocalDate.now().minusDays(4), LocalDate.now(), prodBaby, cliente2),
				new Order(6565156, "Completed", LocalDate.of(2021, 3, 25), LocalDate.now().plusDays(5), prodBaby,
						cliente3),
				new Order(6565156, "Completed", LocalDate.of(2021, 5, 15), LocalDate.now().plusDays(4), prodBaby,
						cliente4)));

		System.out.println(cliente1.toString());

		ordini.forEach(s -> System.out.println(
				"Id: " + s.getId() + ", " + "status: " + s.getStatus() + ", " + "Data ordine " + s.getOrderDate() + ", "
						+ "Data consegna: " + s.getDeliveryDate() + ", " + s.getProducts().toString()));

		System.out.println();

		System.out.println(cliente2.toString());
		ordini.forEach(
				s -> System.out.println("Id: " + s.getId() + ", " + "status: " + s.getStatus() + ", " + "Data ordine "
						+ s.getOrderDate() + ", " + "Data consegna: " + s.getDeliveryDate() + ", " + s.getProducts()));

		System.out.println();

		System.out.println("--------- Lista Boys --------");

		System.out.println();

		Predicate<Product> isBoysCategory = p -> p.getCategory().equals("Boys");
		List<Product> listaBoys = lista.stream().filter(isBoysCategory).toList();
		for (Product product : listaBoys) {
			double sconto = product.getPrice() - (product.getPrice() * 0.1);
			product.setPrice(sconto);
			System.out.println("Id: " + product.getId() + " " + "nome articolo: " + product.getName() + " "
					+ "categoria: " + product.getCategory() + " " + "prezzo: " + product.getPrice());

		}
		System.out.println();

		System.out.println("--------- Lista in base alla data --------");

		System.out.println();

		Predicate<Order> isTier2Customer = order -> order.getCustomer().getTier() == 2;
		Predicate<Order> isOrderDateBetween = order -> order.getOrderDate().isAfter(LocalDate.of(2021, 2, 1))
				&& order.getOrderDate().isBefore(LocalDate.of(2021, 4, 1));

		List<Order> filteredOrders = ordini.stream().filter(isTier2Customer.and(isOrderDateBetween)).toList();
		for (Order order : filteredOrders) {
			System.out.println("Id: " + order.getId() + ", " + "Status: " + order.getStatus() + ", " + "Data ordine: "
					+ order.getOrderDate() + ", " + "Data consegna: " + order.getDeliveryDate() + ", " + "Prodotti: "
					+ order.getProducts().toString());
		}
	}
}
