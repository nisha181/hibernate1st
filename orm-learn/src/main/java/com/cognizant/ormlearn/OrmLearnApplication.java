package com.cognizant.ormlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

import java.util.List;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

@SpringBootApplication
public class OrmLearnApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
	private static CountryService countryService;
	
	private static void testGetAllCountries() {

		LOGGER.info("Start");

		List<Country> countries = countryService.getAllCountries();

		LOGGER.debug("countries={}", countries);

		LOGGER.info("End");

		}
	private static void getAllCountriesTest() throws CountryNotFoundException {

		LOGGER.info("Start");
		Country country = countryService.findCountryByCode("IN");

		LOGGER.debug("Country:{}", country);

		LOGGER.info("End");

		}
	private static void testAddCountry() {
		Country country=new Country("AA","ABC");
		countryService.addCountry(country);
		try {
			countryService.findCountryByCode("AA");
		} catch (CountryNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void testUpdateCountry() {
		countryService.updateCountry("AA","BCD");
	}
	
	private static void testDeleteCountry() {
		countryService.deleteCountry("AA");
	}

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);

		countryService = context.getBean(CountryService.class);
		testGetAllCountries();
		try {
			getAllCountriesTest();
		} catch (CountryNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testAddCountry();
		testUpdateCountry();
		testDeleteCountry();
	}

}
