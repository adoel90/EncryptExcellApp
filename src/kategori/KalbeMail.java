//https://poi.apache.org/apidocs/org/apache/poi/ss/util/CellReference.html
//https://poi.apache.org/apidocs/org/apache/poi/xssf/eventusermodel/XSSFReader.SheetIterator.html
//http://stackoverflow.com/questions/13109588/base64-encoding-in-java?rq=1
//http://stackoverflow.com/questions/24574490/changing-value-of-cell-in-excel-via-apache-poi

//http://www.tutorialspoint.com/apache_poi/apache_poi_cells.htm
//https://poi.apache.org/apidocs/org/apache/poi/ss/usermodel/Cell.html

//http://thinktibits.blogspot.co.id/2012/12/Update-Modify-Excel-File-Java-POI-Example.html
//https://crunchify.com/java-properties-files-how-to-update-config-properties-file-in-java/
package kategori;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
//import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

//Encrypt Library
//import java.security.Key;
//import javax.crypto.Cipher;
//import javax.crypto.spec.SecretKeySpec;

//String Utils
//import org.apache.commons.lang3.StringUtils;
import org.apache.commons.codec.binary.Base64;

//Ms Excell Library 
//import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//Config Properties
//import java.io.*;



public class KalbeMail {
	//#1
	public static void main(String[] args) throws IOException {
		KalbeMail emailKalbe = new KalbeMail();
		
		Properties prop = new Properties();
	    InputStream input = null;
	    try {

	        input = new FileInputStream("config.properties");

	        // load a properties file
	        prop.load(input);

	        // get the property value and print it out
	        System.out.println(prop.getProperty("lokasi_folder_to_read"));
	        System.out.println(prop.getProperty("lokasi_folder_update"));
	        
	        //String lokasiAwal = prop.getProperty("lokasi_folder_to_read");
	        //String lokasiAkhir = prop.getProperty("lokasi_folder_update");
	        

	    } catch (IOException ex) {
	        ex.printStackTrace();
	    } finally {
	        if (input != null) {
	            try {
	                input.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
		
		
		//FileInputStream file = new FileInputStream(new File("C:\\tempurKalbeApps\\emailSegmenting\\ComfortTraveller\\Comfort_Traveler.xlsx"));
		FileInputStream file = new FileInputStream(new File(prop.getProperty("lokasi_folder_to_read")));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		
		//String position_file = emailKalbe.getString("C:\\tempurKalbeApps\\emailSegmenting\\GroupTraveller\\Group_Traveler.xlsx");
		//FileInputStream file = new FileInputStream(new File(String position_file);
		//C:\tempurKalbeApps\emailSegmenting\Destination\\Destination.csv
		//FileInputStream file = new FileInputStream(new File("C:\\tempurKalbeApps\\decryptApp2\\config.properties"));
	
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			String rowPertama = row.getCell(0).toString();
			//System.out.println(rowPertama);
			//System.out.println(row.getCell(0).toString());
			//System.out.println(row.getCell(1).toString());

			//Encrpt Process.
			
			//byte[] encodedBytes = Base64.encodeBase64(rowPertama.getBytes());
			//System.out.println("encodedBytes " + new String(encodedBytes));
			//byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
			byte[] decodedBytes = Base64.decodeBase64(rowPertama);
			System.out.println("decodedBytes " + new String(decodedBytes));
			row.createCell(3).setCellValue(new String(decodedBytes));

			//Output Process
			FileOutputStream fileOut = new FileOutputStream(prop.getProperty("lokasi_folder_update"));
			//FileOutputStream fileOut = new FileOutputStream("C:\\tempurKalbeApps\\decryptApp2\\config.properties");
			workbook.write(fileOut);
			fileOut.close();

		}
		emailKalbe.okehKalbe();
		
	}

	//#2
	public static void okehKalbe() {
		System.out.println("Decrypt udah finish gan !!!");
	}

}

/*
 #3
 public void run() {
 
 try {

 String text = "Hello World";
 String key = "Bar12345Bar12345"; // 128 bit key
 // Create key and cipher
 Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
 Cipher cipher = Cipher.getInstance("AES");

 // encrypt the text
 cipher.init(Cipher.ENCRYPT_MODE, aesKey);
 byte[] encrypted = cipher.doFinal(text.getBytes());
 System.err.println(new String(encrypted));

 // decrypt the text
 cipher.init(Cipher.DECRYPT_MODE, aesKey);
 String decrypted = new String(cipher.doFinal(encrypted));
 System.err.println(decrypted);

 }catch(Exception e) {
 e.printStackTrace();
 }
 }

 */

