package weatherdata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
/**
 * Provides methods to retrieve temperature data from a weather station file.    
 */
public class WeatherDataHandler {

	/**
	 * Test method
	 */
	public void test(String filePath) throws IOException {
		//Read all weather data
		List<String> fileData = Files.readAllLines(Paths.get(filePath));

		List<Measurement> measurements = new LinkedList<>();

		for (String s : fileData){
			String[] tmp = s.split(";");
			Measurement m = new Measurement(tmp[0], tmp[1], tmp[2], tmp[3]);
			measurements.add(m);
		}

		for (Measurement m : measurements){
			System.out.println(m);
		}

	}


	/**
	 * Load weather data from file.
	 * 
	 * @param filePath path to file with weather data
	 * @throws IOException if there is a problem while reading the file
	 */
	public void loadData(String filePath) throws IOException {
		//Read all weather data
		List<String> fileData = Files.readAllLines(Paths.get(filePath));

		List<Measurement> measurements = new LinkedList<>();

		for (String s : fileData){
			String[] tmp = s.split(";");
			Measurement m = new Measurement(tmp[0], tmp[1], tmp[2], tmp[3]);
		}
		/**
		 * TODO: Format data and put it in appropriate data structure.
		 */

	}

	/**
	 * Search for average temperature for all dates between the two dates (inclusive).
	 * Result is sorted by date.
	 * 
	 * @param dateFrom start date (YYYY-MM-DD) inclusive  
	 * @param dateTo end date (YYYY-MM-DD) inclusive
	 * @return average temperature for each date, sorted by date  
	 */
	public List<String> avarageTemperatures(LocalDate dateFrom, LocalDate dateTo) {
		/**
		 * TODO: Implement method.		
		 */
		return null;
	}
	/**
	 * Search for missing values between the two dates (inclusive) assuming there 
	 * should be 24 measurement values for each day (once every hour). Result is
	 * sorted by date.
	 * 
	 * @param dateFrom start date (YYYY-MM-DD) inclusive  
	 * @param dateTo end date (YYYY-MM-DD) inclusive
	 * @return dates with missing values together with number of missing values for each date, sorted by date
	 */
	public List<String> missingValues(LocalDate dateFrom, LocalDate dateTo) {
		/**
		 * TODO: Implement method.		
		 */
		return null;
	}
	/**
	 * Search for percentage of approved values between the two dates (inclusive).
	 * 
	 * @param dateFrom start date (YYYY-MM-DD) inclusive  
	 * @param dateTo end date (YYYY-MM-DD) inclusive
	 * @return period and percentage of approved values for the period  
	 */
	public List<String> approvedValues(LocalDate dateFrom, LocalDate dateTo) {
		/**
		 * TODO: Implement method.		
		 */
		return null;
	}	
}