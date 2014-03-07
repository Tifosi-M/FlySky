package com.businesscard.data.dbDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;

public class CreateUserDB {

	public CreateUserDB(String path, String user_DB_Name, Activity activity) {
		try {
			InputStream is = activity.getResources().getAssets()
					.open("BusinessCardUser.db3");
			File file = new File(path);
			file.mkdir();
			path = path + user_DB_Name + ".db3";
			file = new File(path);
			file.createNewFile();
			FileOutputStream os = new FileOutputStream(file);
			byte temp[] = new byte[1024];
			while (is.read(temp) != -1) {
				os.write(temp);
			}
			System.out.println("��ݿⴴ���ɹ�");
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
