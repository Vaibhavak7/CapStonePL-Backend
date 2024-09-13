package ideas.pl.pl_data;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

//    @Autowired
//    ProductRepository productRepository;


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

// 		TODO:	Insert
//		Product product = new Product(55, "Pen", "Statiory", 5.6f, 2);
//		Product demo = productRepository.save(product);
//		System.out.println("Product Saved.");
//		System.out.println(demo);

// 		TODO:	Update
//		Product product = new Product(1, "Pen", "Stationery", 15.75f, 4);
//		Product demo = productRepository.save(product);
//		System.out.println("Product Updated.");
//		System.out.println(demo);

// 		TODO:	View
//		List<Product> products = (List<Product>) productRepository.findAll();
//		for (Product product : products) {
//			System.out.println(product);
//		}

// 		TODO:	Delete
//		productRepository.deleteById(2);
//		System.out.println("Product Deleted.");

    }

}