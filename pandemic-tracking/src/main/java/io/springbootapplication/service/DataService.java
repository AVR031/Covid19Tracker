package io.springbootapplication.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.springbootapplication.models.LocationBean;

@Service
public class DataService {
	
	//URL of the source file from where we are getting the data from 
	private static String VIRUS_DATA = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	private List<LocationBean> alldata =  new ArrayList<>();
	
	//Method to fetch the data inside the URL
	@PostConstruct
	@Scheduled(cron = "* * * 1 * *") //Cron expression to schedule update of the application daily
	public void fetchData() throws IOException, InterruptedException
	{
		//To allow user to see data when the data is populating into this list
		List<LocationBean> newList = new ArrayList<>();
		
		HttpClient client =  HttpClient.newHttpClient();
		
		//Build URI of the String
		HttpRequest request =  HttpRequest.newBuilder()
							   .uri(URI.create(VIRUS_DATA))
							   .build();
		//Fetch the response from the URL and convert it to String
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		//Creating string reader from httpResponse
		StringReader csvReader = new StringReader(httpResponse.body());
		
		//Reads CSV with header
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
		int i = 1;
		for (CSVRecord record : records) {
			LocationBean locationBean = new LocationBean();
			locationBean.setCounter(i++);
			locationBean.setState(record.get("Province/State"));
			locationBean.setCountry(record.get("Country/Region"));
			
			int latestCases = Integer.parseInt( record.get(record.size()-1) );
			int casesYesterday = Integer.parseInt( record.get(record.size()-2) );
			
			locationBean.setTotalCases(latestCases);
			locationBean.setChangeInCases(latestCases - casesYesterday);
			
			newList.add(locationBean);
		}
		this.alldata = newList;
	}

	public List<LocationBean> getAlldata() {
		return alldata;
	}
}