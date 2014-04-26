package com.flysky.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.flysky.dao.CampusDAO;
import com.flysky.domain.CampusMemo;

@Service
public class CampusService {
	static String host = "127.0.0.1";
	static int port = 6379;
	@Autowired
	private CampusDAO campusDAO;

	public void saveCampus(CampusMemo campusMemo) {
		Jedis jedis = new Jedis(host, port);
		byte[] db = jedis.get("10000".getBytes());
		getFileFromBytes(db, "/mnt/ext3/memo/10000.db3");
		//getFileFromBytes(db, "e:/10000.db3");
		campusDAO.save(campusMemo);
		jedis.set("10000".getBytes(), getBytesFromFile("/mnt/ext3/memo/10000.db3"));
		//jedis.set("10000".getBytes(), getBytesFromFile("e:/10000.db3"));
	}

	public List<CampusMemo> getAllCampusMemos(){

		return campusDAO.getAllCampus();
	}
	
	public void deleteCampusMemoById(String id){
		campusDAO.deleteById(id);
		Jedis jedis = new Jedis(host,port);
		jedis.set("10000".getBytes(), getBytesFromFile("/mnt/ext3/memo/10000.db3"));
		//jedis.set("10000".getBytes(), getBytesFromFile("e:/10000.db3"));
	}
	
	
	/**
	 * 二进制字节流转换为文件
	 * 
	 * @param b
	 * @param outputFile
	 * @return
	 */
	public static File getFileFromBytes(byte[] b, String outputFile) {
		File ret = null;
		BufferedOutputStream stream = null;
		try {
			ret = new File(outputFile);
			FileOutputStream fstream = new FileOutputStream(ret);
			stream = new BufferedOutputStream(fstream);
			stream.write(b);
		} catch (Exception e) {
			// log.error("helper:get file from byte process error!");
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					// log.error("helper:get file from byte process error!");
					e.printStackTrace();
				}
			}
		}
		return ret;
	}
	/**
	 * 将文件转换为二进制byte[]
	 * @param path
	 * @return
	 */
	public byte[] getBytesFromFile(String path) {
		BufferedInputStream in = null;
		File file = new File(path);
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
			return content;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
