package com.app.spring.springselenium.page.goguardian;

import com.app.spring.springselenium.page.Base;
import com.app.spring.springselenium.utils.annotation.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Page
public class GoguardianHome extends Base {

    @Value("${application.url}")
    private String url;

    @FindBy(xpath = "//h3[contains(@class,'fc-item__title')]/a")
    private List<WebElement> newsResults;

    private static Set<String> newsHeaders;

    public void goTo(){
        this.driver.get(url);
    }

    public String getTitle(){
        return this.driver.getTitle();
    }

    public void captureMainNewsDetails(){
        newsHeaders = new HashSet<>();
        newsResults.forEach(element -> newsHeaders.add(element.getText()));
        newsHeaders.removeIf(s->s.contains("Live"));
        System.out.println(newsHeaders);
        setNewsHeaders(this.newsHeaders);
    }

    public Set<String> getNewsHeaders() {
        return newsHeaders;
    }

    public void setNewsHeaders(Set<String> newsHeaders) {
        this.newsHeaders = newsHeaders;
    }

    @Override
    public boolean isAt() {
        return this.wait.until((d) -> !this.newsResults.isEmpty());
    }
}
