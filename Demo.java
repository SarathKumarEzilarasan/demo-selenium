import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class Demo {

    ChromeDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.leafground.com/input.xhtml");
        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void textBoxTest() {
        driver.findElement(By.xpath("//input[@name='j_idt88:name']"))
                .sendKeys("test user");
        driver.findElement(By.xpath("//input[@name='j_idt88:name']"))
                .sendKeys("test user");
        driver.findElement(By.xpath("//input[@name='j_idt88:j_idt91']"))
                .sendKeys(" India");
        boolean flag = driver.findElement(By.id("j_idt88:j_idt93")).isEnabled();
        Assert.assertFalse(flag);
        driver.findElement(By.id("j_idt88:j_idt95")).clear();
        String value = driver.findElement(By.xpath("//input[@id='j_idt88:j_idt97']"))
                .getAttribute("value");
        Assert.assertEquals(value, "My learning is superb so far.");
    }

    @Test
    public void buttonTest() throws InterruptedException {
        driver.get("https://www.leafground.com/button.xhtml");
        driver.findElement(By.id("j_idt88:j_idt90")).click();
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, "https://www.leafground.com/dashboard.xhtml");
        driver.get("https://www.leafground.com/button.xhtml");
        int x = driver.findElement(By.xpath("//button[@id='j_idt88:j_idt94']")).getLocation().getX();
        int y = driver.findElement(By.xpath("//button[@id='j_idt88:j_idt94']")).getLocation().getY();
        Assert.assertEquals(x, 81);
        Assert.assertEquals(y, 400);
        String actualColor = driver.findElement(By.xpath("//button[@id='j_idt88:j_idt96']"))
                .getCssValue("background");
        boolean color = actualColor.contains("rgb(96, 125, 139)");
        Assert.assertTrue(color);
//        driver.findElement(By.id("j_idt88:j_idt98")).getSize().getHeight();
//        driver.findElement(By.id("j_idt88:j_idt98")).getSize().getWidth();
        driver.findElement(By.xpath("//button[@name='j_idt88:j_idt102:imageBtn']"))
                .click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@name='j_idt88:j_idt102:imageBtn']"))
                .click();
        driver.findElement(By.id("j_idt88:j_idt107")).click();
        List<WebElement> list =
                driver.findElements(By.xpath("//button[contains(@class,\"rounded-button\")]"));
        Assert.assertEquals(list.size(), 4);

    }


    @Test
    public void dropDownTest() throws InterruptedException {
        driver.get("https://www.leafground.com/select.xhtml");
//        WebElement dropDown = driver.findElement(By.xpath("//select[@class=\"ui-selectonemenu\"]"));
//        Select select = new Select(dropDown);
//        select.selectByVisibleText("Puppeteer");
//        List<WebElement> options = select.getOptions();
//
//        for (WebElement option : options) {
//            String text = option.getText();
//            System.out.println(text);
//        }

        driver.findElement(By.id("j_idt87:country")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@data-label=\"India\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("j_idt87:city")).click();
        Thread.sleep(2000);
        List<WebElement> cities = driver.findElements(By.xpath("//li[contains(@id,'j_idt87:city')]"));
        List<String> expectedCities = Arrays.asList("Select City", "Bengaluru", "Chennai", "Delhi");

        for (int i = 0; i < cities.size(); i++) {
            WebElement city = cities.get(i);
            String actualCity = city.getText();
            Assert.assertEquals(actualCity, expectedCities.get(i));
        }
    }

    @Test
    public void checkBoxTest() throws InterruptedException {
        driver.get("https://www.leafground.com/checkbox.xhtml");
        boolean flag = driver.findElement(By.xpath("//input[@name=\"j_idt87:j_idt89_input\"]//parent::div//following-sibling::div"))
                .isSelected();
        if (flag == false) {
            driver.findElement(By.xpath("//input[@name=\"j_idt87:j_idt89_input\"]//parent::div//following-sibling::div")).click();
        }
        String favLang = "Javascript";
        // list of languages
        // list of checkboxes
        // for loop in languages to find the favLang => 2
        // checkboxes.get(2).click()
    }

    @Test
    public void radioButtonTest() {
        driver.get("https://www.leafground.com/radio.xhtml");
        int age = 18;
        List<WebElement> radioButtons = driver.findElements(By.xpath("//div[@id=\"j_idt87:age\"]//div[contains(@class,'ui-radiobutton-box')]"));
        List<String> ageGroups = Arrays.asList("1-20", "21-40", "41-60");//length, charAt , split
        int result = 0;

        for (int i = 0; i < ageGroups.size(); i++) {
            String ageGroup = ageGroups.get(i);
            String[] limits = ageGroup.split("-");
            int startAge = Integer.parseInt(limits[0]);
            int endAge = Integer.parseInt(limits[1]);

            if (age >= startAge && age <= endAge) {
                result = i;
            }
        }
        radioButtons.get(result).click();

    }

    @Test
    public void linkTest() {
        driver.get("https://www.leafground.com/link.xhtml");
        String url = driver.findElement(By.xpath("//a[@href=\"/grid.xhtml\"]"))
                .getAttribute("href");
        Assert.assertEquals(url, "/grid.xhtml");
        List<WebElement> links = driver.findElements(By.xpath("//a[@href=\"/dashboard.xhtml\"]"));
        links.get(3).click();
        driver.findElement(By.xpath("(//a[@href=\"/dashboard.xhtml\"])[4]")).click();
        //a
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        allLinks.size();
    }

    @Test
    public void waitTest() throws InterruptedException {
        driver.get("https://www.leafground.com/waits.xhtml");
//        driver.findElement(By.id("j_idt87:j_idt89")).click();
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt87:j_idt90")));
//        driver.findElement(By.id("j_idt87:j_idt90")).click();


        driver.findElement(By.id("j_idt87:j_idt95")).click();
        Thread.sleep(1000);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1)); //60
        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(5)) // 0 5 10 15 20 --> 12
                .ignoring(NoSuchElementException.class);


        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='j_idt87:growl_container']//div")));
        driver.findElement(By.id("j_idt87:j_idt96")).click();


    }

    @Test
    public void tableTest() throws InterruptedException {
        String country = "India";
        driver.get("https://www.leafground.com/table.xhtml");
        driver.findElement(By.id("form:j_idt89:globalFilter")).sendKeys(country);
        Thread.sleep(1000);
        WebElement tableBody = driver.findElement(By.xpath("//tbody[@id='form:j_idt89_data']"));
        List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.tagName("td"));
            WebElement column = columns.get(1);
            Assert.assertEquals(column.getText(), country);
        }
    }


    //https://www.leafground.com/dynamicgrid.xhtml
    // get the list of names -->  sort the list of names
    //click on the column header
    //List<WebElement> names --> List<String> list ;  list.add(name.getText())
    // Assert.assertEquals(name, );


    @Test
    public void messageTest() {
        driver.get("https://www.leafground.com/messages.xhtml");
        driver.findElement(By.id("j_idt89:j_idt90")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\"j_idt89:messages\"]//li")));

        String message = driver.findElement(By.xpath("//div[@id=\"j_idt89:messages\"]//li")).getText();
        Assert.assertEquals(message, "InfoMessage Detail");
    }


    @Test
    public void uploadTest() {
        driver.get("https://www.leafground.com/file.xhtml");
        driver.findElement(By.id("j_idt88:j_idt89_input")).sendKeys("/Users/preethir/Downloads/demo-jat-maven/pom.xml");
    }

    @Test
    public void displayAllProductsTest() {
        driver.get("https://www.leafground.com/list.xhtml");
        List<WebElement> products = driver.findElements(By.xpath("//div[@class=\"product-name\"]"));

        for (WebElement product : products) {
            System.out.println(product.getText());
        }


    }

    // @AfterClass
    public void tearDown() {
        driver.quit();
    }


}

// testNG + autoIT
