package com.testpages;


import com.basepages.SeleniumBasePage;
import com.driver.DriverManager;
import com.utility.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class ImdbScenairo extends SeleniumBasePage {

    //Burada constructor oluşturduk ; Web element FindyBy ile oluşturulan webelementleri initialize(başlatmak) oluyor.(eşittir yapmamak gibi.)
    public ImdbScenairo() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    //Homepage'teki; Menü butonu.
    @FindBy(xpath = "//label[@id = 'imdbHeader-navDrawerOpen--desktop']")
    WebElement btnMenu;

    //Menüdeki ; Awards&Event > Oscars butonu.
    @FindBy(xpath = "//a[@href = '/oscars/?ref_=nv_ev_acd']")
    WebElement btnOscars;

    //Event history ; 1929 butonu
    @FindBy(xpath = "//div[@class='event-history-widget']//a[contains(@href, '1929')]")
    WebElement btnEventHistory;

    //The jazz singer butonu
    @FindBy(xpath = "(//span[@class='event-widgets__nominee-name']//a[contains(@href,'tt0018037')])[2]")
    WebElement btnHonaryAward;

    //control text
    @FindBy(xpath = "(//div[@class='sc-fa02f843-0 fjLeDR']//li[@data-testid='title-pc-principal-credit'])[1]")
    WebElement textDirector;
    @FindBy(xpath = "(//div[@class='sc-fa02f843-0 fjLeDR']//li[@data-testid='title-pc-principal-credit'])[2]")
    WebElement textWriter;
    @FindBy(xpath = "(//div[@class='sc-fa02f843-0 fjLeDR']//li[@data-testid='title-pc-principal-credit'])[3]")
    WebElement textStars;


    @FindBy(xpath = "//a[@id='home_img_holder']")
    WebElement btnImdb;

    //arama cubuğu
    @FindBy(xpath = "//input[@id='suggestion-search']")//burada sıkıntı var.
            WebElement searchBox;


    //search result
    @FindBy(xpath = "(//a[contains(@href, 'tt0018037')])[1]")
    WebElement btnSearchBox;

    //search result
    @FindBy(xpath = "//a[@data-testid='photos-title']")
    WebElement btnPhoto;


    /**
     * @param addUrlPlugin : /payment, /account  gibi sayfalara direkt gitmek için kullanılabilir.
     * @return
     */
    public ImdbScenairo navigateToUrl(String addUrlPlugin) throws InterruptedException {

        DriverManager.getDriver().get("https://www.imdb.com/" + addUrlPlugin);
        Log.pass("IMDB Web sitesi açıldı.");
        Thread.sleep(3000);
        return this;
    }

    public ImdbScenairo stepByStepCreate() throws InterruptedException {

        btnMenu.click();
        Log.pass("Menü butonuna tıklandı.");
        Thread.sleep(2000);
        btnOscars.click();
        Log.pass("Oscars butonuna tıklandı.");

        btnEventHistory.click();
        Log.pass("Event history cardından 1929'a tıklandı.");

        btnHonaryAward.click();
        Log.pass("The jazz singer'a tıklandı.");

        String director = getTextOfElement(textDirector);
        String writer = getTextOfElement(textWriter);
        String stars = getTextOfElement(textStars);

        btnImdb.click();
        Log.pass("imdb butonuna tıklanarak anasayfaya gidilir.");
        Thread.sleep(2000);

        searchBox.sendKeys("The jazz singer");
        Log.pass("Arama çubuğuna The jazz singer girildi.");

        btnSearchBox.click();
        Log.pass("Resultta The jazz singer tıklandı.");

        Log.pass("Search result ile modal üzerinden geldiğimizde içerik :  " + director.contentEquals(director));
        Log.pass("Search result ile modal üzerinden geldiğimizde içerik :  " + writer.contentEquals(writer));
        Log.pass("Search result ile modal üzerinden geldiğimizde içerik :  " + stars.contentEquals(stars));

        scrollToElementBlockCenter(btnPhoto, "photos");
        btnPhoto.click();
        Thread.sleep(2000);
        brokenUrlControl("//img[contains(@src, 'https://m.media-amazon.com/images/')]");
        return this;

    }


    public void brokenUrlControl(String urlLink) {

        String homePage = urlLink;
        String url = "//a[@href]//img[contains(@src, 'https://m.media-amazon.com/images/')]";
        HttpURLConnection huc = null;
        int respCode = 200;

        List<WebElement> links = driver.findElements(By.tagName("a"));

        Iterator<WebElement> it = links.iterator();

        while (it.hasNext()) {

            url = it.next().getAttribute("href");

            System.out.println(url);

            if (url == null || url.isEmpty()) {
                System.out.println("URL is either not configured for anchor tag or it is empty");
                continue;
            }

            if (!url.startsWith(homePage)) {
                System.out.println("URL belongs to another domain, skipping it.");
                continue;
            }

            try {
                huc = (HttpURLConnection) (new URL(url).openConnection());

                huc.setRequestMethod("HEAD");

                huc.connect();

                respCode = huc.getResponseCode();

                if (respCode >= 400) {
                    System.out.println(url + " is a broken link");
                } else {
                    System.out.println(url + " is a valid link");
                }

            } catch (MalformedURLException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }


        }
        return;
    }
}
