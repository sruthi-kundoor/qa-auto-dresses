package stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import io.cucumber.java.en.And;


public class StepDefs {
	
	private String maxPrice;
	private int indexOfMaxPrice;
	
	@Given("I navigate to the website")
	public void i_navigate_to_the_website() {
		Hooks.driver.get("http://automationpractice.com/index.php");
	}
	
	@When("I click on Dresses Menu")
	public void i_click_on_dresses_menu() {
		Hooks.driver.findElement(By.cssSelector("#block_top_menu > ul > li:nth-child(2) > a")).click();
	}

	@And("I select the highest price item")
	public void i_select_the_highest_price_item() {

	    List<WebElement> getDressesPrice = Hooks.driver.findElements(By.xpath("//*[@id=\"center_column\"]/ul/li[*]/div/div[2]/div[1]//*[contains(@class, \"product-price\")][1]"));
	    List<String> list = new ArrayList<>();
	    for (int i = 0; i < getDressesPrice.size(); i++) {
	    		list.add(getDressesPrice.get(i).getText());
	    	}	 
	    System.out.println("All dresses price list: " + list);
		maxPrice = Collections.max(list);
		System.out.println("Highest Price Dress is = " + maxPrice);
		indexOfMaxPrice = list.indexOf(maxPrice);
	}
	
	@Then("Add the selected highest price item to the cart")
	public void add_the_selected_highest_price_item_to_the_cart() {
		 
		List<WebElement> addToCart = Hooks.driver.findElements(By.xpath("//*[@id=\"center_column\"]/ul/li//span[contains(text(), \"Add to cart\")]"));
		JavascriptExecutor executor = (JavascriptExecutor)Hooks.driver;
		executor.executeScript("arguments[0].click();", addToCart.get(indexOfMaxPrice));
        Hooks.driver.findElement(By.xpath("//span[contains(text(), \"Proceed to checkout\")]")).click();
        String cartPrice = Hooks.driver.findElement(By.xpath("//table//td[contains(@id, \"total_product\")]")).getText();
        System.out.println("Total Product Price in the Cart is = " + cartPrice);
        assertEquals("The cart value does not match to the max dress price", maxPrice, cartPrice);
	 }
}

