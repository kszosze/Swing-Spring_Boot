package com.dev.frontend.services;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.dev.frontend.panels.model.ModelObject;

public class WSService {

	public static String wsGet(String entryPoint, Object... params) throws IOException {
		return wsCall("GET", entryPoint, params);
	}

	public static String wsDelete(String entryPoint, Object... params) throws IOException {
		return wsCall("DELETE", entryPoint, params);
	}

	public static String wsPost(String entryPoint, ModelObject param) throws IOException {
		return wsCallJson("POST", entryPoint, param);
	}

	public static String wsCall(String method, String entryPoint, Object... params) throws IOException {
		StringBuilder urlStr = new StringBuilder();
		if (params.length > 0) {
			for (Object param : params) {
				urlStr.append(param).append("/");
			}
			urlStr.deleteCharAt(urlStr.length() - 1);
		}
		URL url = new URL(entryPoint + urlStr.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(method);
		conn.setRequestProperty("Accept", "application/json");
		conn.connect();

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException(
					"Failed " + method + " - " + url.toString() + " : HTTP error code : " + conn.getResponseCode());
		}
		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		StringBuilder bsb = new StringBuilder();
		String line = "";
		while ((line = br.readLine()) != null) {
			bsb.append(line);
		}
		conn.disconnect();
		if (bsb.length() == 0) {
			System.out.println("No answer");
		}
		return bsb.toString();
	}

	public static String wsCallJson(String method, String entryPoint, ModelObject params) throws IOException {

		URL url = new URL(entryPoint);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(method);
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Accept", "application/json;charset=UTF-8");
		conn.setDoOutput(true);
		conn.connect();
		BufferedOutputStream bos = new BufferedOutputStream(conn.getOutputStream());
		bos.write(params.toJson().getBytes());
		bos.flush();

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException(
					"Failed " + method + " - " + url.toString() + " : HTTP error code : " + conn.getResponseCode());

		}

		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		StringBuilder bsb = new StringBuilder();
		while (br.ready()) {
			bsb.append(br.readLine());
		}
		conn.disconnect();
		return bsb.toString();
	}

}
