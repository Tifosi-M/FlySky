package test;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import redis.clients.jedis.Jedis;

public class TestJedis {
	static String host = "127.0.0.1"; 
	static int port = 6379; 
	public static void main(String args[]){
//		Jedis jedis = new Jedis(host, port); 
//		jedis.set("name", "xmong");
//		String value = jedis.get("name");
//		System.out.println(value); 
//		jedis.del("name"); 
//		jedis.exists("name");
		
		BufferedInputStream in = null;
		File file = new File("e:/AF4Ad.ppt");
		try {
			in = new BufferedInputStream(new FileInputStream(file));
			int length = (int) file.length();
			System.out.println();
			ByteArrayOutputStream out = new ByteArrayOutputStream(1024);      
		    byte[] temp = new byte[1024];        
		    int size = 0;
		    while ((size = in.read(temp)) != -1) {        
		    	out.write(temp, 0, size);        
			}
		    byte[] content = out.toByteArray();
		    System.out.println(content.length);
		    Jedis jedis = new Jedis(host, port); 
			jedis.set("123".getBytes(), content);
			byte[] b=jedis.get("123".getBytes());
			System.out.println(b.length);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		
		
}

