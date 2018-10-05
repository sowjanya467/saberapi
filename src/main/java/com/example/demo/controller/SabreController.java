package com.example.demo.controller;
/*************************************************************************************************************
*
* purpose:Spring program to access Sabre APIs and get the list of cheap flights to a certain destination.
* 
* @author sowjanya467
* @version 1.0
* @since 05-10-18
*
**************************************************************************************************/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/saber")
public class SabreController {

	private final String url = "https://api-crt.cert.havail.sabre.com/v2/auth/token";

	private static final String Basic_PARAMS = "grant_type=client_credentials&username=ss&password=ss";

	/**
	 * purpose: method to get token
	 * @return token
	 * @throws IOException
	 */
	@RequestMapping(value = "/getToken", method = RequestMethod.GET)
	public String getToken() throws IOException {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setRequestProperty("Authorization",
				"Basic VmpFNk5uVndibUl4Wkc4NVkzZ3hjRFJrTVRwUVZVSk1TVU02UlZoVTpTVEJMTTBwMWRtdz0=");
		// con.setRequestProperty("Accept", "application/x-www-form-urlencoded");

		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(Basic_PARAMS.getBytes());
		os.flush();
		os.close();

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			System.out.println(response.toString());
			return response.toString();
		} else {
			System.out.println("POST request is not working");

		}
		return "unsuccessfull";
	}

	/**
	 * purpose:method to get low cost fare flights
	 * @param token
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getflights", method = RequestMethod.GET)
	public String getCheaperFlights(@RequestParam String token) throws IOException {
		String urlget = "https://api-crt.cert.havail.sabre.com/v1/shop/flights/cheapest/fares/LAX?pointofsalecountry=US";
		URL obj = new URL(urlget);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setRequestProperty("Authorization",
				"Bearer "+token);
		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			System.out.println(response.toString());
			return response.toString();
		} else {
			System.out.println("get request is not working");

		}
		return "unsuccessfull";
		

	}

}
