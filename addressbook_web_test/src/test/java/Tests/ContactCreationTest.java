import Tests.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class ContactCreationTest extends TestBase {
  private WebDriver driver;


  @Test
  public void canCreateContact() {
    driver.get("http://localhost/addressbook/");
    driver.manage().window().setSize(new Dimension(1386, 742));
    driver.findElement(By.name("user")).sendKeys("admin");
    driver.findElement(By.name("pass")).sendKeys("secret");
    driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
    driver.findElement(By.linkText("add new")).click();
    driver.findElement(By.name("firstname")).sendKeys("fname");
    driver.findElement(By.name("middlename")).sendKeys("mname");
    driver.findElement(By.name("lastname")).sendKeys("lname");
    driver.findElement(By.name("nickname")).sendKeys("nickname");
   // driver.findElement(By.name("photo")).click();
   // driver.findElement(By.name("photo")).sendKeys("C:\\fakepath\\1Uw9_iO8MNI.jpg");
    driver.findElement(By.xpath("(//input[@name=\'submit\'])[2]")).click();
    driver.findElement(By.linkText("home page")).click();
    driver.findElement(By.linkText("Logout")).click();
  }
}
