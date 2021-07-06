package step;




import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testrunner.test_run;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class stepdefination extends test_run{
	
	private static String loginpopup= "//div[@class = 'autopop__wrap makeFlex column defaultCursor']";
	private static final By loginclick= By.id("root");
	private static final By fromBox= By.xpath("//label[@for='fromCity']/parent::div");
	private static final By fromInputBox= By.xpath("//input[@placeholder='From']");
	private static final By toBox= By.xpath("//label[@for='toCity']/parent::div");
	private static final By toInputBox= By.xpath("//input[@placeholder='To']");
	private static String ddValue = "//li[@role='option']/descendant::p[contains(text(),%s)]/parent::div";
	private static String ddValue1 = "//p[contains(text(),'%s')]/ancestor::div[@class='makeFlex hrtlCenter']";
	private static String ddValue2 = "//p[contains(text(),'%s')]/ancestor::div[@class='makeFlex hrtlCenter']";
	private static final By depBox= By.xpath("//label[@for='departure']/parent::div");
	private static String calDate="//div[@class='DayPicker-Day'][contains(@aria-label,'%s')]";
	private static String filter = "//div[@class='filtersOuter']//p[contains(text(),'Airlines')]/following-sibling::div//span[@class='filterName' and @title= '%s']/parent::span/preceding-sibling::span";
	private static String flight_sort = "//span[contains(text(),'Arrival')]";
	private static String flight_row = "//div[@class = 'fli-list '][%s]//button[@class = 'ViewFareBtn  text-uppercase ']";
	private static String flight_card = "//div[@class='fli-list '][%s]//p[contains(text(),'%s')]/parent::div/parent::div//button[contains(text(),'Book Now')]";
	private static String deperture_flight = "(//div[@class = 'paneView'][1]//div[@class = 'makeFlex spaceBetween appendBottom15'])[%s]";
	private static String return_flight = "(//div[@class = 'paneView'][2]//div[@class = 'makeFlex spaceBetween appendBottom15'])[%s]";
	private static String deperture_flight_card = "//div[@class='multifareContent appendBottom10'][1]//p[contains(text(),'%s')]";
	private static String return_flight_card = "//div[@class='multifareContent appendBottom10'][2]//p[contains(text(),'%s')]";
	private static final By Book= By.xpath("//button[contains(text(),'Book Now')]");
	private static final By final_Book= By.xpath("//button[contains(text(),'Continue')]");						
	private static final By check_box = By.xpath("//span[@class = 'check']");
	private static final By radio_button = By.xpath("(//div[@id = 'digit-insurance-section']//span[@class = 'outer marR8'])[1]");
	private static String infant = "(//span[@class ='font16 LatoBold text-right']/span)[3]";
	private static String children = "(//span[@class ='font16 LatoBold text-right']/span)[2]";
	public WebDriver driver;
	
		@Before
		public void webdriverinitialization() {
			System.setProperty("webdriver.chrome.driver","C:\\Users\\arpan.dey\\Desktop\\Eclipse\\eclipse-jee-photon-R-win32-x86_64\\Makemytrip\\src\\main\\resource1\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		
	
	@Given("^Open chrome browser$")
	public void open_chrome_browser_and_enter_url() throws Throwable 
	{
		
		
		driver.get("https://www.makemytrip.com/");
		//Maximizing Window
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		Boolean check = check_availibility(loginpopup);
		if(check)
		{
			new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(loginclick));
			WebElement el29 = driver.findElement(loginclick);
		    //((JavascriptExecutor)driver).executeScript("arguments[0].click()", el29);
		    Actions action = new Actions(driver);
		    //action.moveToElement(el29).click();
		    action.doubleClick(el29).perform();
                 
		}
		
		
		
	}
	@Then("^Enter travel type \"([^\"]*)\"")
	public void method_for_travel_type(String s)
	{
		new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(String.format("li[data-cy='%s']", s))));
		WebElement type = driver.findElement(By.cssSelector(String.format("li[data-cy='%s']", s)));
		((JavascriptExecutor)driver).executeScript("arguments[0].click()", type);
		
	}
	@Then("^Enter Deperture city \"([^\"]*)\" and destination City \"([^\"]*)\"")
	public void method_for_entering_city(String s1,String s2)throws Throwable
	{
		new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(fromBox));
		driver.findElement(fromBox).click();
		//Thread.sleep(4000);
		new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(fromInputBox));
		driver.findElement(fromInputBox).sendKeys(s1);
		new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(setLocator(ddValue1,s1)));
		WebElement city = driver.findElement(setLocator(ddValue1,s1));
		((JavascriptExecutor)driver).executeScript("arguments[0].click()", city);
		//driver.findElement(setLocator(ddValue,s1)).click();
		
		new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(toBox));
		driver.findElement(toBox).click();
		new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(toInputBox));
		driver.findElement(toInputBox).sendKeys(s2);
		new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(setLocator(ddValue2,s2)));
		driver.findElement(setLocator(ddValue2,s2)).click();
}
	@Then("^Enter Date \"([^\"]*)\" and return date \"([^\"]*)\" for \"([^\"]*)\" round trip")
	public void method_for_date(String s3, String s8, String s9 ) throws InterruptedException
	{
		if(s9.compareToIgnoreCase("oneWayTrip")==0)
		{
			String flag3 = "False";
			while(flag3=="False") {
			   
			   if(driver.findElements(setLocator(calDate,s3)).size()>0) {
			    
			   driver.findElement(setLocator(calDate,s3)).click(); 
			   flag3="True";
			   //Thread.sleep(5000);
			   }
			   
			   else {
			    //Thread.sleep(5000);
			    driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();
			   }
			      
			  }
		
		}
		else
		{
			String flag = "False";
			while(flag=="False") {
			   
			   if(driver.findElements(setLocator(calDate,s3)).size()>0) {
			    
			   driver.findElement(setLocator(calDate,s3)).click(); 
			   flag="True";
			   //Thread.sleep(5000);
			   }
			   
			   else {
			    //Thread.sleep(5000);
			    driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();
			   }
			      
			  }
			String flag1 = "False";
			while(flag1=="False") {
			   
			   if(driver.findElements(setLocator(calDate,s8)).size()>0) {
			    
			   driver.findElement(setLocator(calDate,s8)).click(); 
			   flag1="True";
			   //Thread.sleep(5000);
			   }
			   
			   else {
			    //Thread.sleep(5000);
			    driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();
			   }
			      
			  }
			
		}
	}
	@Then("^Enter traveller detail adult \"([^\"]*)\" and children \"([^\"]*)\" and infant \"([^\"]*)\"")
	public void method_for_traveller_detail(String s4, String s5, String i4)
	{
		new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='travellers']")));
		WebElement el1 = driver.findElement(By.cssSelector("label[for='travellers']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click()", el1); 
		new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(String.format("li[data-cy='%s']", s4))));
		WebElement el2 = driver.findElement(By.cssSelector(String.format("li[data-cy='%s']", s4)));
		((JavascriptExecutor)driver).executeScript("arguments[0].click()", el2);
		new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(String.format("li[data-cy='%s']", s5))));
		WebElement el3 = driver.findElement(By.cssSelector(String.format("li[data-cy='%s']", s5)));
		((JavascriptExecutor)driver).executeScript("arguments[0].click()", el3);
		
		new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(String.format("li[data-cy='%s']", i4))));
		WebElement ell = driver.findElement(By.cssSelector(String.format("li[data-cy='%s']", i4)));
		((JavascriptExecutor)driver).executeScript("arguments[0].click()", ell);
		new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-cy='travellerApplyBtn']")));
		WebElement el4 = driver.findElement(By.cssSelector("button[data-cy='travellerApplyBtn']"));
	    ((JavascriptExecutor)driver).executeScript("arguments[0].click()", el4);
	    
	     //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", radio_button);
	    
		
	}
	@Then("^Enter Search button$")
	public void method_for_search_action()
	{
		new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Search']")));
		WebElement el5 = driver.findElement(By.xpath("//a[text()='Search']"));
	    ((JavascriptExecutor)driver).executeScript("arguments[0].click()", el5);
		
	}
	@Then("^Enter flight name \"([^\"]*)\"")
	public void method_for_flight_name(String s6)
	{
		    new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(setLocator(filter,s6)));
			WebElement el6 = driver.findElement(setLocator(filter,s6));
		    ((JavascriptExecutor)driver).executeScript("arguments[0].click()", el6);
		    new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class ='appliedFilter']/li")));
		    WebElement appliedfilter =  driver.findElement(By.xpath("//ul[@class ='appliedFilter']/li"));
		    String appliedfilter1= appliedfilter.getText();
		    System.out.println(appliedfilter1);
		    if(appliedfilter1.compareToIgnoreCase(s6)==0)
		    {
		    	System.out.println("Flight name validated");
		    }
		    else
		    {
		    	System.out.println("Flight name not validated");
		    }
	}
	@Then ("^Enter Sorting order \"([^\"]*)\" for return flight \"([^\"]*)\" in case of \"([^\"]*)\" round trip and passing \"([^\"]*)\" and \"([^\"]*)\" for validation")
	public void method_for_Sorting_order(String s9,String s10,String s11,String i2, String i3)
	{
		if(s11.compareToIgnoreCase("oneWayTrip")==0)
		{
			 new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class = 'font24 blackFont whiteText appendBottom20']/b[1]")));
			 WebElement dep_city =  driver.findElement(By.xpath("//p[@class = 'font24 blackFont whiteText appendBottom20']/b[1]"));
			 String dep_city1 = dep_city.getText();
			 System.out.println(dep_city1);
			 if(dep_city1.compareToIgnoreCase(i2)==0)
			    {
			    	System.out.println("Deperture city name validated");
			    }
			    else
			    {
			    	System.out.println("Deperture city name not validated");
			    }
			 new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class = 'font24 blackFont whiteText appendBottom20']/b[2]")));
			 WebElement des_city =  driver.findElement(By.xpath("//p[@class = 'font24 blackFont whiteText appendBottom20']/b[2]"));
			 String des_city1 = des_city.getText();
			 System.out.println(des_city1);
			 if(des_city1.compareToIgnoreCase(i3)==0)
			    {
			    	System.out.println("Destination city name validated");
			    }
			    else
			    {
			    	System.out.println("Destination city name not validated");
			    }

			 new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(setLocator(flight_sort,s9)));
			 WebElement e20 = driver.findElement(setLocator(flight_sort,s9));
			 ((JavascriptExecutor)driver).executeScript("arguments[0].click()", e20);

		}
		else
		{
			 String d_date = "";
			new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[@class ='fontSize16 blackText appendLR20 appendBottom20 paddingTop20'])[1]")));
			 WebElement deperture_date =  driver.findElement(By.xpath("(//p[@class ='fontSize16 blackText appendLR20 appendBottom20 paddingTop20'])[1]")); 
			 String deperture_date1 = deperture_date.getText();
			 Pattern pattern4 = Pattern.compile(",\\s(\\d*)\\s(\\w*)");
			  Matcher macher4 = pattern4.matcher(deperture_date1);
			  if(macher4.find())
			  {
			        	
			    	  d_date = macher4.group(2)+ " " + macher4.group(1);
			        	
			   }
			  System.out.println(d_date);
			 new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[@class ='fontSize16 blackText appendLR20 appendBottom20 paddingTop20'])[2]")));
			 WebElement return_date =  driver.findElement(By.xpath("(//p[@class ='fontSize16 blackText appendLR20 appendBottom20 paddingTop20'])[2]"));
			 String return_date1 = return_date.getText();
			 String deperture_city = "";
			 Pattern pattern1 = Pattern.compile("(\\w*)");
		     Matcher macher1 = pattern1.matcher(deperture_date1);
		      if(macher1.find())
		        {
		        	
		    	   deperture_city = macher1.group(1);
		        	
		        }
		      System.out.println(deperture_city);
		      if(deperture_city.compareToIgnoreCase(i2)==0)
			    {
			    	System.out.println("Deperture city detail validated");
			    }
			    else
			    {
			    	System.out.println("Deperture city detail not validated");
			    }
		      String r_date ="";
			  Pattern pattern3 = Pattern.compile(",\\s(\\d*)\\s(\\w*)");
			  Matcher macher3 = pattern3.matcher(return_date1);
			  if(macher3.find())
			  {
			        	
			    	r_date = macher3.group(2)+ " " + macher3.group(1);
			        	
			   }
			  System.out.println(r_date);
		      String destination_city = " ";
		      Pattern pattern2 = Pattern.compile("\\s→\\s(\\w*)");
			  Matcher macher2 = pattern2.matcher(deperture_date1);
			  if(macher2.find())
			  {
			        	
			    	  destination_city = macher2.group(1);
			        	
			   }
			  System.out.println(destination_city);
			  if(destination_city.compareToIgnoreCase(i3)==0)
			    {
			    	System.out.println("Destination city detail validated");
			    }
			    else
			    {
			    	System.out.println("Destination city detail not validated");
			    }

			  
			  
			 
			 new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(setLocator(flight_sort,s9)));
			WebElement e21 = driver.findElement(setLocator(flight_sort,s9));
		    ((JavascriptExecutor)driver).executeScript("arguments[0].click()", e21);
		    new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(setLocator(flight_sort,s10)));
			WebElement e22 = driver.findElement(setLocator(flight_sort,s10));
		    ((JavascriptExecutor)driver).executeScript("arguments[0].click()", e22);
		    
		}
		
	}
	@Then("^Enter flight row \"([^\"]*)\" and flight card \"([^\"]*)\" for return deperture flight row \"([^\"]*)\", return flight row \"([^\"]*)\", deperture flight card \"([^\"]*)\" and return flight card \"([^\"]*)\" in case of \"([^\"]*)\" round trip")
	public void method_for_row_and_card(String i,String s7,String s11,String s12,String s13,String s14,String s15) throws InterruptedException
	{
		if(s15.compareToIgnoreCase("oneWayTrip")==0)
		{
		new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(setLocator(flight_row,i)));
		WebElement el8 = driver.findElement(setLocator(flight_row,i));
	    ((JavascriptExecutor)driver).executeScript("arguments[0].click()", el8);
	    new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//div[@class='fli-list '][%s]//p[contains(text(),'%s')]/parent::div/parent::div//button[contains(text(),'Book Now')]",i,s7))));
		WebElement el9 = driver.findElement(By.xpath(String.format("//div[@class='fli-list '][%s]//p[contains(text(),'%s')]/parent::div/parent::div//button[contains(text(),'Book Now')]",i,s7)));
	    ((JavascriptExecutor)driver).executeScript("arguments[0].click()", el9);
	    ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
	      //switch to new tab
	    driver.switchTo().window(newTb.get(1));
	    
		}
		else
		{
			new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("(//div[@class = 'paneView'][1]//div[@class = 'makeFlex spaceBetween appendBottom15'])[%s]",s11))));
			WebElement el23 = driver.findElement(By.xpath(String.format("(//div[@class = 'paneView'][1]//div[@class = 'makeFlex spaceBetween appendBottom15'])[%s]",s11)));
		    ((JavascriptExecutor)driver).executeScript("arguments[0].click()", el23);
		   Thread.sleep(5000);
		   new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("(//div[@class = 'paneView'][2]//div[@class = 'makeFlex spaceBetween appendBottom15'])[%s]",s12))));
			WebElement el24 = driver.findElement(By.xpath(String.format("(//div[@class = 'paneView'][2]//div[@class = 'makeFlex spaceBetween appendBottom15'])[%s]",s12)));
		    ((JavascriptExecutor)driver).executeScript("arguments[0].click()", el24);
		    new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(Book));
			WebElement el25 = driver.findElement(Book);
		    ((JavascriptExecutor)driver).executeScript("arguments[0].click()", el25);
		    new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(setLocator(deperture_flight_card,s13)));
			WebElement el26 = driver.findElement(setLocator(deperture_flight_card,s13));
		    ((JavascriptExecutor)driver).executeScript("arguments[0].click()", el26);
		    new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(setLocator(return_flight_card,s14)));
			WebElement el27 = driver.findElement(setLocator(return_flight_card,s14));
		    ((JavascriptExecutor)driver).executeScript("arguments[0].click()", el27);
		    
		    new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(final_Book));
			WebElement el28 = driver.findElement(final_Book);
		    ((JavascriptExecutor)driver).executeScript("arguments[0].click()", el28);
		    ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
		      //switch to new tab
		    driver.switchTo().window(newTb.get(1));
		    
		    
		}
		
		
	}
	@Then("^Deselect the Donate option$")
	public void method_validation_and_deselcting_donate_option() throws InterruptedException
	{
		 new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class ='font16 LatoBold text-right']/span)[1]")));
		 WebElement base_fare =  driver.findElement(By.xpath("(//span[@class ='font16 LatoBold text-right']/span)[1]"));
		 String base_fare1 = base_fare.getText();
		 String b_fare2_a="";
		 String b_fare2_c="";
		 String b_fare2_i="";
		 String e_fare2="";
		 String d_fare="";
		 String t_fare2="";
		 int base_i =0;
		 int base_c =0;
		 Pattern pattern5 = Pattern.compile("₹\\s*([\\d\\,\\.]+)");
		  Matcher macher5 = pattern5.matcher(base_fare1);
		  if(macher5.find())
		  {
		        	
		    	  String b_fare = macher5.group(1);
		    	  String[] b_fare1 = b_fare.split(",");
		    	  b_fare2_a = String.join("", b_fare1);
		        	
		 }
		 int base_a = Integer.parseInt(b_fare2_a);
		 System.out.println(base_a);
		 Boolean children_check = check_availibility(children);
		 if(children_check)
		 {
		 new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class ='font16 LatoBold text-right']/span)[2]")));
		 
		 WebElement base_fare_c =  driver.findElement(By.xpath("(//span[@class ='font16 LatoBold text-right']/span)[2]"));
		 String base_fare1_c = base_fare_c.getText();
		 
		 Matcher macher7 = pattern5.matcher(base_fare1_c);
		  if(macher7.find())
		  {
		        	
		    	  String b_fare = macher7.group(1);
		    	  String[] b_fare1 = b_fare.split(",");
		    	  b_fare2_c = String.join("", b_fare1);
		        	
		 }
		  base_c = Integer.parseInt(b_fare2_c);
		 System.out.println(base_c);
		 }
		 Boolean infant_check = check_availibility(infant);
		 Pattern pattern6 = Pattern.compile("₹\\s*([\\d\\,\\.]+)");
		 if(infant_check)
		 {
		 new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class ='font16 LatoBold text-right']/span)[3]")));
		 WebElement base_fare_i =  driver.findElement(By.xpath("(//span[@class ='font16 LatoBold text-right']/span)[3]"));
		 String base_fare1_i = base_fare_i.getText();
		
		 Matcher macher8 = pattern6.matcher(base_fare1_i);
		  if(macher8.find())
		  {
		        	
		    	  String b_fare = macher8.group(1);
		    	  String[] b_fare1 = b_fare.split(",");
		    	  b_fare2_i = String.join("", b_fare1);
		        	
		 }
		  base_i = Integer.parseInt(b_fare2_i);
		 System.out.println(base_i);
		 }
		 new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class ='font16'])[1]")));
		 WebElement extra_charge =  driver.findElement(By.xpath("(//span[@class ='font16'])[1]"));
		 String extra_charge1 = extra_charge.getText();
		 Matcher macher6 = pattern5.matcher(extra_charge1);
		  if(macher6.find())
		  {
		        	
		    	  String e_fare = macher6.group(1);
		    	  String[] e_fare1 = e_fare.split(","); 
		    	  e_fare2 = String.join("", e_fare1);
		        	
		 }
		 int extra = Integer.parseInt(e_fare2);
		 System.out.println(extra);
		 new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class ='font16'])[2]")));
		 WebElement donation =  driver.findElement(By.xpath("(//span[@class ='font16'])[2]"));
		 String donation1 = donation.getText();
		 Matcher macher9 = pattern6.matcher(donation1);
		  if(macher9.find())
		  {
		        	
		    	   d_fare = macher9.group(1);
		    	  
		        	
		 }
		  int donate = Integer.parseInt(d_fare);
		  System.out.println(donate);
		 new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class ='font20']/span/span")));
		 WebElement total =  driver.findElement(By.xpath("//span[@class ='font20']/span/span"));
		 String total1 = total.getText();
		 Matcher macher10 = pattern6.matcher(total1);
		  if(macher10.find())
		  {
		        	 
		    	  String t_fare = macher10.group(1);
		    	  String[] t_fare1 = t_fare.split(",");
		    	  
		    	  t_fare2 = String.join("", t_fare1);
		        	
		 }
		  int final_price = Integer.parseInt(t_fare2);
		  System.out.println(final_price);
		  if(final_price == (base_a + base_c + base_i + extra + donate ))
		  {
			  System.out.println("Price validated"); 
		  }
		  else
		  {
			  System.out.println("Price not validated"); 
		  }
		 
		 ((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		 //Thread.sleep(2000);
		 new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(check_box));
		 WebElement el10 = driver.findElement(check_box);
		((JavascriptExecutor)driver).executeScript("arguments[0].click()", el10);
		Thread.sleep(2000);
		new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(radio_button));
	    WebElement el11 = driver.findElement(radio_button);
	    ((JavascriptExecutor)driver).executeScript("arguments[0].click()", el11);
	  
	  }
	@After
	public void closing_driver()
	{
		  driver.quit();
	}
	 public static By setLocator(String path, String text)
	{
		String loc= String.format(path, text);
		By locPath= By.xpath(loc);
		return locPath;
	}
	public  boolean check_availibility(String li) throws NoSuchElementException {
		 try {
			 new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(li)));
		    	return true;
		 }
		catch(Exception exception){
			return false;
		}
	}
	
	

	
}