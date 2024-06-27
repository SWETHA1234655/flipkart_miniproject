package project;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataFile {
	
	public static String Search;
	
	static XSSFCell orgin;
	

		public static void ExcelData() {
		try {
			File excelData=new File("C:\\Users\\2318398\\eclipse-workspace\\Selenium_Mini\\Excel\\Myfile.xlsx");
			FileInputStream input=new FileInputStream(excelData);
			XSSFWorkbook workbook=new XSSFWorkbook(input);
			XSSFSheet sheet=workbook.getSheetAt(0);
			XSSFRow row=sheet.getRow(1);
			orgin=row.getCell(0);
			
			Search=orgin.getStringCellValue();
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		


		}
		public static void setExcelValue(String actual,String expected/*,int act_amt,int exp_amt*/) throws Exception{
			
			FileOutputStream file=new FileOutputStream("C:\\Users\\2318398\\eclipse-workspace\\Selenium_Mini\\setValue.xlsx");
				
			XSSFWorkbook wb=new XSSFWorkbook();
				
			XSSFSheet sheet=wb.createSheet("output");
				
			XSSFRow row=sheet.createRow(0);
				
			row.createCell(1).setCellValue("expected_output");
			row.createCell(0).setCellValue("Actual_output");
			row.createCell(2).setCellValue("Expected_Result");
			row.createCell(3).setCellValue("Actual_Result");
				
			XSSFRow row1=sheet.createRow(1);
				
			row1.createCell(0).setCellValue(actual);
			row1.createCell(1).setCellValue(expected);
			row1.createCell(2).setCellValue("PASS");
			row1.createCell(3).setCellValue((actual.equals(expected))?"PASS":"FAILED");
			
			wb.write(file);
 
			file.close();
				
				
		}


}
