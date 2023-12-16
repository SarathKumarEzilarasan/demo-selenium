import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Demo {
    public static void main(String[] args) {
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        //APjFqb -> box
        //By -> locators -> id ,name , css , tagname , linkText , partial
        WebElement element = driver.findElement(By.id("APjFqb"));
        element.sendKeys("sjdnsjd");
        element.sendKeys(Keys.ENTER);
//        driver.quit();
    }
}
