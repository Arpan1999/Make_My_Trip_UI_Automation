package com.testrunner;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/main/java/step/make_my_trip.feature"}, glue = "step")
public class test_run extends AbstractTestNGCucumberTests
{
	
	@BeforeSuite
	public void before_suit()
	{
		System.out.println("Starting the test");
	}
	
	
	    
	@AfterSuite
		public  void SendingMail() throws AddressException, IOException, MessagingException{
			
			Runtime r=Runtime.getRuntime();  	  
			r.addShutdownHook(new Thread(){  
			public void run(){  
				Mail sm = new Mail();
				try {
					sm.mail();
					System.out.println("Report has been sent"); 
				} catch (IOException | MessagingException e) {
					e.printStackTrace();
				}
				
			    }  
			}  
			);  
			try{Thread.sleep(5000);}catch (Exception e) 
			{
				System.out.println(e);
			}  
			
			
			}
	  	
}