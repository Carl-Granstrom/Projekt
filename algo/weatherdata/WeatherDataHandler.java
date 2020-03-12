package weatherdata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * Provides methods to retrieve temperature data from a weather station file.    
 */
public class WeatherDataHandler {

	public Map<LocalDateTime, Measurement> measurements;
	List<LocalTime> times = new ArrayList<>();


	/**
	 * Load weather data from file.
	 * 
	 * @param filePath path to file with weather data
	 * @throws IOException if there is a problem while reading the file
	 */
	public void loadData(String filePath) throws IOException {
		//Read all weather data
		List<String> fileData = Files.readAllLines(Paths.get(filePath));

		measurements = new HashMap<>();

		for (String s : fileData){
			String[] tmp = s.split(";");
			LocalDate date = LocalDate.parse(tmp[0]);
			LocalTime time = LocalTime.parse(tmp[1]);
			Measurement m = new Measurement(date, time, tmp[2], tmp[3]);
			LocalDateTime key = LocalDateTime.of(date, time);
			measurements.put(key, m);
		}

		//create a list of measurement times to check
		for (int i = 0; i < 24; i++) {
			times.add(LocalTime.of(i, 0));
		}
	}

	/**
	 * Search for average temperature for all dates between the two dates (inclusive).
	 * Result is sorted by date.
	 * 
	 * @param dateFrom start date (YYYY-MM-DD) inclusive  
	 * @param dateTo end date (YYYY-MM-DD) inclusive
	 * @return average temperature for each date, sorted by date  
	 */
	public List<String> averageTemperatures(LocalDate dateFrom, LocalDate dateTo) {
		List<String> returnStrings = new ArrayList<>();

		//create a list of dates to check
		List<LocalDate> dates = new ArrayList<>();

		//iterate through the dates and calculate average
		LocalDate currDate = dateFrom;
		while (!currDate.isAfter(dateTo)) {
			Float averageTemperature;
			Float totalTemperature = Float.valueOf(0);
			String dateString = currDate.toString();
			int numTimes = 0;
			for (LocalTime time : times) {
				LocalDateTime key = LocalDateTime.of(currDate, time);
				Measurement measurement = measurements.get(key);
				if (measurement != null) {
					totalTemperature += measurement.temperature;
					numTimes++;
				}

			}
			averageTemperature = totalTemperature / numTimes;
			returnStrings.add("Average temperature for " + dateString + ": " + averageTemperature);
			currDate = currDate.plusDays(1);
		}

		return returnStrings;
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
		List<String> returnStrings = new ArrayList<>();

		//create a list of dates to check
		List<LocalDate> dates = new ArrayList<>();
		LocalDate startDate = dateFrom;
		LocalDate endDate = dateTo;
		int numMissingValues = 0;

		//iterate through the dates and calculate average
		LocalDate currDate = startDate;
		while (!currDate.isAfter(endDate)) {
			String dateString = currDate.toString();

			for (LocalTime time : times) {
				LocalDateTime key = LocalDateTime.of(currDate, time);
				Measurement measurement = measurements.get(key);
				if (measurement == null) {
					numMissingValues++;
				}

			}
			currDate = currDate.plusDays(1);
		}
		returnStrings.add("Number of missing values in this period is " + numMissingValues);
		return returnStrings;
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