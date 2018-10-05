package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/saber")
public class SabreController {

	private final String url = "https://api-crt.cert.havail.sabre.com/v2/auth/token";

	private static final String Basic_PARAMS = "grant_type=client_credentials&username=ss&password=ss";

	@RequestMapping(value = "/posttoken", method = RequestMethod.POST)
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

	@RequestMapping(value = "/getflights", method = RequestMethod.POST)
	public String getCheaperFlights(@RequestParam String token) throws IOException {
		String urlget = "https://api-crt.cert.havail.sabre.com/v1/shop/flights/cheapest/fares/LAX?pointofsalecountry=US";
		URL obj = new URL(urlget);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setRequestProperty("Authorization",
				"Bearer T1RLAQKfNXIMMr8VDs/vm2T11bQGlN+g7RAIGrucFlO5LNlUVyqBLrUnAACwMCkgd3Aw0olTL51ImsrYkrdOVszfNKStlcqGnBYNFQfdAcQumulBeTJ8Me7rgSVhca3FC0L0GApWTjZcAALW8nCpUyfMPg3V92AYvDG7MV8DoRneoWIZaOEexB7OpsFUbO+8fFKxd/ajYcHvF9uyFKPh2IvAadPrMEaCZrVkKPO74ZEH0yu/jJLsRnKUtICgxodbX7LotOBCt4RgFtlQyoPlhSniXQzz3q/crbh5I1o*");

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
		// con.setRequestProperty("Bearer", token);
		// con.setRequestProperty("Accept", "application/x-www-form-urlencoded");
		/*
		 * HttpClient client = new DefaultHttpClient(); HttpGet request = new
		 * HttpGet(url);
		 * 
		 * // add request header // request.addHeader("User-Agent", USER_AGENT);
		 * 
		 * HttpResponse response = client.execute(request);
		 * 
		 * System.out.println("\nSending 'GET' request to URL : " + urlget);
		 * System.out.println("Response Code : " +
		 * response.getStatusLine().getStatusCode()); BufferedReader rd = new
		 * BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		 * 
		 * StringBuffer result = new StringBuffer(); String line = ""; while ((line =
		 * rd.readLine()) != null) { result.append(line); }
		 * 
		 * System.out.println(result.toString()); return url;
		 */

	}

}
