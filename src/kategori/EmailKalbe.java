package kategori;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class EmailKalbe {
	
	public static void main(String[] args) throws IOException {
		EmailKalbe emailKalbe = new EmailKalbe();
		
		// FileInputStream file ini di perlukan untuk ambil data dari dalam Path yang telah di tentukan 
		FileInputStream file = new FileInputStream(new File("C:\\Users\\Personal_3\\Desktop\\Double Booking1.xlsx"));

		//XSSFWorkbook workbook ini di perlukan untuk merepresentasikan Workbook Spreadsheet
		//This is the first object most users will construct whether they are reading or writing a workbook.
		//It is also the top level object for creating new sheets/etc.
		XSSFWorkbook workbook = new XSSFWorkbook(file);

		//getSheetAt(int index) ini di perlukan untuk mendapatkan HSSFSheet object sesuai dengan Index.
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		//Iterator<Row> ini di perlukan untuk merepresentasikan sebuah row dalam sebuah Spreadsheet.
		// iterator() adalah method yang di perlukan untuk membuat sheet secara 
		Iterator<Row> rowIterator = sheet.iterator();
		
		//ArrayList<String> ini di perlukan untuk merepresentasikan sebuah col dalam sebuah Spreadsheet.
		ArrayList<String> col = new ArrayList<String>();
		Cell tempCell = null;
		boolean isDouble = false;
		
		
		//hasNext() adalah method milik Constructor Iterator<row> yang di perlukan untuk mengembalikan value TRUE jika iteration memiliki more elements.
			while (rowIterator.hasNext()) {
				
				//Row row ini di perlukan untuk merepresentasi-kan Row pada sebuah Spreadsheet.
				//next() ini di perlukan untuk input stream of the next sheet in the iteration
				Row row = rowIterator.next();
				
				System.out.println(row.getRowNum());
				Iterator<Cell> cellIterator = row.cellIterator();
				
				isDouble = false;
				if(row.getCell(1)!=null){
					if(tempCell!=null){
						if(!row.getCell(1).toString().equals("")){
							System.out.println(tempCell.toString());
							if(row.getCell(1).toString().equals(tempCell.toString())) {
								System.out.println("Sama nih");
								row.createCell(7).setCellValue("DOUBLE");
							}
						}
					}else{
						if(!row.getCell(1).toString().equals("")){
							System.out.println("cek bawah");
							if(row.getCell(1).toString().equals(sheet.getRow(row.getRowNum()+1).getCell(1).toString())) {
								System.out.println("Sama nih");
								row.createCell(7).setCellValue("DOUBLE");
							}
						}
					}
				//col.add(cell.getStringCellValue());
					System.out.print(row.getCell(1).toString());
					
					if(!row.getCell(1).toString().equals("Order ID") && !row.getCell(1).toString().equals(""))
						tempCell = row.getCell(1);
					else
						tempCell = null;
				}
	
				System.out.println();
				emailKalbe.okehKalbe();
				
			}
		

		//emailKalbe.okehKalbe();

		//FileOutputStream fileOut ini di perlukan untuk menampung data dari path yang telah di tentukan melalui contructor 'new FileOutputStream'
		FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Personal_3\\Desktop\\Kalbe.xlsx");
		//workbook adalah representasi file Excell
		//write() ini di perlukan untuk menulis data untuk argumen 'fileOut'
		workbook.write(fileOut);
		fileOut.close();
	}
	
	public static void okehKalbe(){
		
		System.out.println("Okeh gan !!!");
	}

 }

