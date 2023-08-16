package com.app.spring.springselenium.page.google;

import com.app.spring.springselenium.page.Base;
import com.app.spring.springselenium.page.goguardian.GoguardianHome;
import com.app.spring.springselenium.utils.annotation.LazyAutowired;
import com.app.spring.springselenium.utils.annotation.PageFragment;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@PageFragment
public class GoogleSearchComponent extends Base {

    @FindBy(name = "q")
    private WebElement searchBox;

    @FindBy(name = "btnK")
    private List<WebElement> searchBtns;

    @LazyAutowired
    private GoogleSearchResult googleSearchResult;

    public GoogleSearchResult search(final String keyword){
        this.searchBox.sendKeys(keyword);
        this.searchBox.sendKeys(Keys.TAB);
        this.searchBtns
                .stream()
                .filter(e -> e.isDisplayed() && e.isEnabled())
                .findFirst()
                .ifPresent(WebElement::click);
        return this.googleSearchResult;
    }

    public GoogleSearchComponent clearSearch(){
        this.searchBox.clear();
        return this;
    }

    @Override
    public boolean isAt() {
        return this.wait.until((d) -> this.searchBox.isDisplayed());
    }
}
