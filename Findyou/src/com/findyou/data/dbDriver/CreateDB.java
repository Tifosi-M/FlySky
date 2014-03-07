package com.findyou.data.dbDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.findyou.ui.main.systemManagement.FindYouApplication;

public class CreateDB {

	public CreateDB(String path,
			FindYouApplication findYouApplication) {
		try {
			InputStream is = findYouApplication.getResources().getAssets()
					.open("FindYou.db3");
			File file = new File(path);
			file.mkdir();
			path = path + "FindYou.db3";
			file = new File(path);
			file.createNewFile();
			FileOutputStream os = new FileOutputStream(file);
			byte temp[] = new byte[1024];
			while (is.read(temp) != -1) {
				os.write(temp);
			}
			System.out.println("数据库创建成功");
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
