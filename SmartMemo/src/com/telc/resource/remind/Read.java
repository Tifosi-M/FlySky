package com.telc.resource.remind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Read {

	public String request(String url) throws Exception {
		URL server = null;
		HttpURLConnection conexion = null;
		BufferedReader reader = null;
		String result = null;
		try {
			server = new URL(url);

			conexion = (HttpURLConnection) server.openConnection();
			conexion.setRequestProperty("Charset", "UTF-8");
			conexion.setRequestMethod("POST");
			conexion.setUseCaches(false);
			conexion.setRequestProperty("Connection", "Keep-Alive");
			conexion.setDoOutput(true);
			conexion.connect();
			reader = new BufferedReader(new InputStreamReader(server.openStream()));
			StringBuilder sb = new StringBuilder();
			String messager = null;
			while ((messager = reader.readLine()) != null) {
				sb.append(messager);
			}
			result = sb.toString();
			if (result == null || "".equals(result)) {
				throw new Exception("���ؽ��Ϊ��");
			}
		} catch (MalformedURLException e) {
			throw new Exception(e);
		} catch (IOException e) {
			throw new Exception(e);
		} finally{
			if(conexion!=null)
				conexion.disconnect();
			if(reader!=null)
				reader.close();
			server = null;
			conexion = null;
			reader = null;
		}
		return result;
	}
}
