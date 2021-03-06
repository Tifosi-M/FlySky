package com.telc.data.dbDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.telc.ui.main.MemoApplication;

public class CreateDB {

	/**
	 * 创建数据库，将第一次启动将assets中的空数据库导入
	 * @param path
	 * @param memoApplication
	 */
	public void createDB(String path,MemoApplication memoApplication)
	{
		try{
			InputStream is=memoApplication.getResources().getAssets().open("mydb.db3");
			InputStream is2=memoApplication.getResources().getAssets().open("campus.db3");
		    File file=new File(path);
		    file.mkdir();
		    String path1=path+"mydb.db3";
		    String path2=path+"campus.db3";
		    file=new File(path1);
		    file.createNewFile();
		    FileOutputStream os=new FileOutputStream(file);
		    byte temp[]=new byte[1024];
		    while(is.read(temp)!=-1)
		    {
		    	os.write(temp);
		    }	    
		    System.out.println("数据库创建成功");
		    os.close();
		    
		    file=new File(path2);
		    file.createNewFile();
		    FileOutputStream os2=new FileOutputStream(file);
		    byte temp1[]=new byte[1024];
		    while(is2.read(temp1)!=-1){
		    	os2.write(temp1);
		    }
		    os2.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
	}
	
}
