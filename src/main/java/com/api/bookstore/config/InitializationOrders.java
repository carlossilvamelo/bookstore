package com.api.bookstore.config;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.api.bookstore.enums.Category;
import com.api.bookstore.models.Address;
import com.api.bookstore.models.Book;
import com.api.bookstore.models.BookOrder;
import com.api.bookstore.models.Credential;
import com.api.bookstore.models.User;
import com.api.bookstore.repository.BookOrderRepository;


@Configuration
public class InitializationOrders implements CommandLineRunner {

	
	@Autowired
	private BookOrderRepository bookOrderRepository;

	@Override
	public void run(String... args) throws Exception {
		Credential credential = new Credential("admin", "admin");
		User user = new User(credential, "Admin admin", "09006369470", "admin@admin.com",
				new Address("consectetur adipiscing elit", "sed semper urna hendrerit quis", 3000, 52110000,
						"suscipit sed venenatis est"));
		
		List<Book> bookList = generateBooks();
		
		BookOrder order = new BookOrder(user,
				new GregorianCalendar(2019,06,21,15,00),
				new GregorianCalendar(2019,07,21,15,00),
				bookList);
		bookOrderRepository.save(order);

	}

	public List<Book> generateBooks(){
		
		return Arrays.asList(
				//fantasy
		new Book("HARRY POTTER AND THE SORCERER’S STONE",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "J.K ROWLING", null, Category.FANTASY),
		new Book("A GAME OF THRONES BY GEORGE",
						"Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "R.R. MARTIN", null, Category.FANTASY),
		new Book("THE NAME OF THE WIND",
						"Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "PATRICK ROTHFUSS", null, Category.FANTASY),
		new Book("THE WAY OF KINGS",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "BRANDON SANDERSON", null, Category.FANTASY),
		// horror
		new Book("Angelfall",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "Susan Ee", null, Category.HORROR),
		new Book("The Immortal Rules",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "Julie Kagawa", null, Category.HORROR),
		new Book("Switched",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "Amanda Hocking", null, Category.HORROR),
		new Book("Born at Midnight",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "Hunter", null, Category.HORROR),
		// tech
		new Book("Hackers: Heroes of the Computer Revolution",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "Steven Levy", null, Category.TECHNOLOGY),
		new Book("Where Wizards Stay Up Late: The Origins of the Internet",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "Katie Hafner", null, Category.TECHNOLOGY),
		new Book("The Google Story: Inside the Hottest Business, Media, and Technology Success of Our Time",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "David Vise  ", null, Category.TECHNOLOGY),
		new Book("The Facebook Effect: The Inside Story of the Company That is Connecting the World",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "David Kirkpatrick", null, Category.TECHNOLOGY)
		
		);
	}

}
