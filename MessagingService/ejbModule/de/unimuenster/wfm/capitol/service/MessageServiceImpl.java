package de.unimuenster.wfm.capitol.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.ejb.Stateless;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


@Stateless
public class MessageServiceImpl implements MessageService{
	
	
	public void sendJSON(Object input, String urlString){
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			URL url = new URL(urlString);
			String json = ow.writeValueAsString(input);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(10000);
			connection.setReadTimeout(10000);
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(json);
			out.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			while (in.readLine() != null) {
				System.out.println(in.toString());
			}
			System.out.println("REST Service Invoked Successfully");
			in.close();
		} catch (Exception e) {
			System.out.println("Error while calling  REST Service");
			System.out.println(e);
		}
	}

}
