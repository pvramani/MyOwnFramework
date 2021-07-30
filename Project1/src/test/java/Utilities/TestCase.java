package Utilities;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class TestCase{


	protected HashMap<String, String> config = new HashMap<String, String>();
	protected LinkedHashMap<String, LinkedHashMap<String, String>> TestMethods = new LinkedHashMap<String, LinkedHashMap<String, String>>();
	protected LinkedHashMap<String, String> TestData = new LinkedHashMap<String, String>();	
	String TestCaseName =  this.getClass().getName().split("\\.")[1];
	WebDriver driver;
	//Method to launch browser

	@BeforeMethod
	public void browserLaunch() throws IOException {
		ReadConfigDataToHashMap();
		LoadTestDataToHashMap();
		String BrowserName = config.get("Browser");
		String driverName;
		String driverPath;
		switch(BrowserName.toUpperCase()) {
		case "CHROME":
			driverName = "webdriver.chrome.driver";
			driverPath = "D:\\\\Prac\\\\Softwares\\\\chromedriver.exe";
			System.setProperty(driverName,driverPath);  
			driver = new ChromeDriver();
			break;
		case "FIREFOX":
			driverName = "webdriver.gecko.driver";
			driverPath = "D:\\Prac\\Softwares\\\\geckodriver.exe";
			System.setProperty(driverName,driverPath); 

			/**Inorder to set any specific Settings related to Firefox browser like home page, save password, 
			 * any security exception, cookies we can use FirefoxOptions or 
			We can create a Profile and pass to options**/

			//FirefoxOptions options = new FirefoxOptions(); 
			driver = new FirefoxDriver();
			break;
		}

				driver.get("https://fuscdrmsmc167-fa-ext.us.oracle.com");
				System.out.println("browser launch");

	}
	
	@AfterMethod
	public void closeBrowsers() throws IOException {
		driver.quit();	
		System.out.println("browser closed");
		PrintWriter out = new PrintWriter(new FileWriter("D:\\Prac\\Projects\\Project1\\Reports\\" +TestCaseName+".html"));
		out.println("<html><body><p>First Report" + TestCaseName + " </p></body></html>");
		out.close();
	}
	
	//Method to read data from config.xslx file to haspmap
	public HashMap<String, String> ReadConfigDataToHashMap() throws IOException {


		FileInputStream file = new FileInputStream(new File("D:\\Prac\\Projects\\Project1\\Config.xls"));

		HSSFWorkbook wb = new HSSFWorkbook(file);
		HSSFSheet sheet = wb.getSheet("RunSetUp");

		Iterator<Row> itrRow = sheet.iterator();
		while(itrRow.hasNext()) {
			Row rw = itrRow.next();
			config.put(rw.getCell(0).getStringCellValue(), rw.getCell(1).getStringCellValue());
		}
		wb.close();
		
		return config;
	}

	//Method to read data from testdata file to haspmap
	public LinkedHashMap<String, LinkedHashMap<String, String>> LoadTestDataToHashMap() throws IOException {

		

		FileInputStream file = new FileInputStream(new File("D:\\Prac\\Projects\\Project1\\src\\test\\java\\TestSheets\\" + TestCaseName + ".xls"));

		HSSFWorkbook Testwb = new HSSFWorkbook(file);
		HSSFSheet Tsheet = Testwb.getSheetAt(0);

		Iterator<Row> itrRow = Tsheet.iterator();
		while(itrRow.hasNext()) {
			Row rw = itrRow.next();
			Iterator<Cell> itrcolumn = rw.iterator();
			while(itrcolumn.hasNext())
			{
				String CellData = itrcolumn.next().getStringCellValue();
				if(CellData.contains(":"))
				{
					//					System.out.println(CellData);
					String Key = CellData.split("\\:")[0];
					String Value = CellData.split("\\:")[1];
					TestData.put(Key, Value);
					//System.out.println(TestData.get(Key));
				}

			}
			TestMethods.put(rw.getCell(0).getStringCellValue(), TestData);
		}
		//Lamda expression to travser through hashmap using keyset
		TestData.keySet().forEach((key) -> {
			// System.out.println(TestData.get(key));
		});
		Testwb.close();
		return TestMethods;
	}
}
