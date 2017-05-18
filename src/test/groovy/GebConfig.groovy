import org.openqa.selenium.firefox.FirefoxDriver

import io.github.bonigarcia.wdm.FirefoxDriverManager

reportsDir = 'build/reports/geb'
baseUrl = "http://localhost:${System.properties['jettyPort']}/"
driver = {
  FirefoxDriverManager.getInstance().setup()
  new FirefoxDriver()
}