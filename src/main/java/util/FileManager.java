package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import exception.MyException;

public class FileManager {

	public static void addFile(String name) throws MyException {
		FileOutputStream fout = null;

		try {
			// 新建文件
			File file = new File(FileProperties.addFIlePath + name
					+ FileProperties.addFIleSuffix);

			if (!file.isFile()) {
				System.out.println(file.getPath());
				file.createNewFile();
				fout = new FileOutputStream(file);
				fout.write(("local_root=" + FileProperties.addFIlePath+ name).getBytes());
				fout.write(FileProperties.fileContent.getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new MyException();
		} finally {
			if (null != fout) {
				try {
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void deleteFile(String name) throws MyException{
		try{
			File file= new File(FileProperties.addFIlePath + name
					+ FileProperties.addFIleSuffix) ;
			if(file.isFile()){
				file.delete();
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new  MyException();
		}
	}
	
	public static void updateFile(String oldname,String name) throws MyException{
		FileOutputStream fout=null;
		try{
			File file= new File(FileProperties.addFIlePath + oldname
					+ FileProperties.addFIleSuffix) ;
			File destfile=new File(FileProperties.addFIlePath + name
					+ FileProperties.addFIleSuffix);
			if(file.isFile()){
				
				file.renameTo(destfile);
				 fout = new FileOutputStream(destfile);
				fout.write(("local_root=" + FileProperties.addFIlePath + name).getBytes());
				fout.write(FileProperties.fileContent.getBytes());
//				BufferedReader bf=new BufferedReader(new FileReader(FileProperties.addFIlePath + name
//						+ FileProperties.addFIleSuffix));
//				String onelineString=bf.readLine();
//				onelineString.replace(oldname, name);
				
//				file.delete();
			}else{
				destfile.createNewFile();
				 fout = new FileOutputStream(destfile);
					fout.write(("local_root=" + FileProperties.addFIlePath + name).getBytes());
					fout.write(FileProperties.fileContent.getBytes());
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new  MyException();
		}finally{
			if(null!=fout){
				try {
					fout.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
