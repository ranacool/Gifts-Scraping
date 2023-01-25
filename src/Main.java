import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.List;
import java.nio.file.Files;



public class Main {
	
    private static final String SAMPLE_CSV_FILE = "Cake.csv";


	public static void main(String[] args) throws IOException, InterruptedException {
		
		

		String rootURL = ("https://gifts.hamropatro.com/products/cake");

		description desc = new description(rootURL);
		link lin = new link(rootURL);
		name nam = new name(rootURL);
		nam.Scraper();
		
		
		//for name, image url, price
				List<String> links = lin.Get_Links();
				System.out.println("Link Sizeee" + links.size());
				List<String> des = desc.Get_Description(); 
				System.out.println("Description Size" + des.size());
				List<String> price = nam.all_price;
				System.out.println("Price Size" + price.size());
				List<String> name = nam.all_name;
				System.out.println("Name Size" + name.size());
				List<String> image_url = nam.all_image;
				System.out.println("Image Size" + image_url.size());
				
				try (
			            Writer writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE));
						

			            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
			                    .withHeader("Name", "Description", "Links", "Price", "Image_URL"));
			        ) {
			            for (int i = 0; i < name.size(); i++) {
			            	System.out.println(i);
			                csvPrinter.printRecord(name.get(i), des.get(i), links.get(i), price.get(i).replace("$", "") + " USD", image_url.get(i));
			            }
			            csvPrinter.flush();
			        }
	    }
			}


