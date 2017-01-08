package kategori;


import java.io.FileInputStream;

import java.io.IOException;


import java.util.Properties;

//Encrypt Library


//String Utils


//Config Properties
import java.io.*;


public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties prop = new Properties();
	    InputStream input = null;

	    try {

	        input = new FileInputStream("config.properties");

	        // load a properties file
	        prop.load(input);

	        // get the property value and print it out
	        System.out.println(prop.getProperty("database"));
	        System.out.println(prop.getProperty("dbuser"));
	        System.out.println(prop.getProperty("dbpassword"));

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
	}

}
