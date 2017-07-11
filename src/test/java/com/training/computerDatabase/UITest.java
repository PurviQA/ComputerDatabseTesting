package com.training.computerDatabase;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UITest {
	Computers comp = new Computers();

	WebDriver driver;

	@BeforeTest
	public void loadBrowser() throws IOException {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\purviagarwal\\Downloads\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	public void loadSite() throws IOException {

		driver.get(comp.Attributes("URL"));
	}

	@Test
	public void addComputer() throws InterruptedException, IOException {
		driver.get(comp.Attributes("URL"));
		driver.findElement(comp.Locators("AddClick")).click();
		driver.findElement(comp.Locators("NameInput")).sendKeys(comp.Attributes("ComputerName"));
		driver.findElement(comp.Locators("IntroducedDate")).sendKeys(comp.Attributes("IntroducedDate"));
		driver.findElement(comp.Locators("DiscontinuedDate")).sendKeys(comp.Attributes("DiscontinuedDate"));
		WebElement element = driver.findElement(comp.Locators("CompanySelection"));
		Select listbox = new Select(element);
		listbox.selectByVisibleText(comp.Attributes("Company"));
		driver.findElement(comp.Locators("CreateComputer")).click();
	}

	@Test
	public void searchComputer() throws IOException {
		driver.get(comp.Attributes("URL"));
		driver.findElement(comp.Locators("FilterInput")).sendKeys(comp.Attributes("ComputerName"));
		driver.findElement(comp.Locators("Search")).click();
		driver.findElement(comp.Locators("Name")).click();
		String compName = driver.findElement(comp.Locators("NameInput")).getAttribute("value");
		String compdate = driver.findElement(comp.Locators("IntroducedDate")).getAttribute("value");
		String compdateend = driver.findElement(comp.Locators("DiscontinuedDate")).getAttribute("value");
		assertThat(comp.Attributes("ComputerName").compareToIgnoreCase(compName)).isEqualTo(0);
		assertThat(comp.Attributes("IntroducedDate").compareToIgnoreCase(compdate)).isEqualTo(0);
		assertThat(comp.Attributes("DiscontinuedDate").compareToIgnoreCase(compdateend)).isEqualTo(0);
		String selectedOption = new Select(driver.findElement(comp.Locators("CompanySelection")))
				.getFirstSelectedOption().getText();
		assertThat(comp.Attributes("Company").compareToIgnoreCase(selectedOption)).isEqualTo(0);
		driver.close();

	}

}
