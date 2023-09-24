package edu.cscc;
//Calvin Gates, 10/17/2022, parsing JSON from webpage retrieved with url class

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.*;

public class Main {

	public static void main(String[] args) {
		final String theURLString = "http://api.open-notify.org/astros.json";
		
		try {
			URL url = new URL(theURLString);
			URLConnection connection = url.openConnection();
			try (BufferedReader brdr = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
				String s, json = "";
				while ((s = brdr.readLine()) != null) {
					json = json + s + " ";
				}
				//Establishes URL connection to space station API, reads through JSON and outputs it in unformatted string in next line
				System.out.println(json+"\n\n");
				
				//Creates JSON object based on string above, creates string array of people using parseJSON method from WeatherData lab, and lists people and their spacestation
				JSONObject jo = new JSONObject(json);
				String[] space = parseJSON(jo);
				System.out.println("There are "+space.length+" people in space");
				for(int i=0; i<space.length; i++) {
			         System.out.println(space[i]);
			    }
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//method I took from the WeatherData lab, parses the JSON and outputs a regular string array we can work with
	public static String[] parseJSON(JSONObject jo) {
		ArrayList<String> list = new ArrayList<>();
		JSONArray people = jo.getJSONArray("people");
		if (people.length() > 0) {
			for (int i=0; i<people.length(); ++i) {
				JSONObject person = people.getJSONObject(i);
				String craft = person.getString("craft");
				String name = person.getString("name");
				list.add(name+" is onboard: "+craft);
			}
		}
		return list.toArray(new String[1]);
	}
}

//TODO - Step 1 - read input from above URL and store in a String. Output the String to the console
// TODO - Step 2 - Using the org.json library, parse the JSON string to determine the number of people in orbit and then output that value
// TODO - Step 3 - Parse the JSON object and print the list of people in orbit and where they are.

		