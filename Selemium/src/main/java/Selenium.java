import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Selenium {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is your username?");
        String username = scanner.nextLine();

        System.out.println("What is your password?");
        String password = scanner.nextLine();

        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.aac.ac.il/");
        WebElement menu = driver.findElement(By.id("menu-%d7%9b%d7%9c%d7%9c%d7%99"));
        if (menu != null)
            {
                List<WebElement> menuItems = menu.findElements(By.tagName("li"));
                WebElement personalInfo = null;
                for (int i = 0; i < menuItems.size(); i++)
                    {
                        WebElement menuItem = menuItems.get(i);
                        if (menuItem.getText().contains("מידע אישי"))
                        {
                            personalInfo = menuItem;
                            break;
                        }
                    }
                    personalInfo.click();
                    WebElement usernameInput = driver.findElement(By.id("Ecom_User_ID"));
                    if (usernameInput != null)
                        {
                            usernameInput.sendKeys(username);
                        }

                    WebElement passwordInput = driver.findElement(By.id("Ecom_Password"));
                    if (passwordInput != null)
                    {
                        passwordInput.sendKeys(password);
                    }

                    WebElement enterButton = driver.findElement(By.id("wp-submit"));
                    if (enterButton != null)
                    {
                        enterButton.click();
                    }

                    List<WebElement> buttons = driver.findElements((By.className("col-sm-6")));

                    while (buttons.isEmpty()){
                        System.out.println("Wrong login/password! Try again!");
                        System.out.println("= = = = = = = = = = = = = = = = =");
                        System.out.println("What is your username?");
                        username = scanner.nextLine();

                        System.out.println("What is your password?");
                        password = scanner.nextLine();

                        usernameInput = driver.findElement(By.id("Ecom_User_ID"));
                        if (usernameInput != null)
                        {
                            usernameInput.sendKeys(username);
                        }

                        passwordInput = driver.findElement(By.id("Ecom_Password"));
                        if (passwordInput != null)
                        {
                            passwordInput.sendKeys(password);
                        }

                        enterButton = driver.findElement(By.id("wp-submit"));
                        if (enterButton != null)
                        {
                            enterButton.click();
                        }
                        buttons = driver.findElements((By.className("col-sm-6")));
                    }

                    for (WebElement button: buttons) {
                        if (button.getText().contains("מערכת Moodle"))
                        {
                            button.click();
                            break;
                        }
                    }
                Thread.sleep(3000);

                List<WebElement> courses = driver.findElements((By.className("multiline")));
                int counter = 0;
                for (WebElement course: courses) {

                    System.out.println("Number of course: " + counter);
                    System.out.println(("Name of course: " + course.getText()));
                    counter++;
                    System.out.println("= = = = = = = = = = = =  = = = = = = = = =");
                }

                choiseCheck(courses);

                Thread.sleep(3000);

                WebElement dropMenuOpen = driver.findElement(By.className("action-menu-trigger")).findElement(By.className("dropdown"));
                dropMenuOpen.click();

                WebElement moodleLogOff = dropMenuOpen.findElement(By.id("actionmenuaction-6"));
                moodleLogOff.click();

                WebElement logOff = driver.findElement(By.partialLinkText("יציאה"));
                logOff.click();

                }
    }

    public static void loginCheck(ChromeDriver driver){
        System.out.println("ERROR ERROR ERROR");
    }

    public static void choiseCheck(List<WebElement> courses)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(("Which course you want to enter?"));

        try{
            int choise = scanner.nextInt();
            System.out.println("Enter course with name " + courses.get(choise).getText());
            courses.get(choise).click();

        }
        catch (Exception e)
        {
            System.out.println("Wrong choice! Repeat please");
            choiseCheck(courses);

        }
    }
}
