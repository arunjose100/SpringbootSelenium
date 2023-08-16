package com.app.spring.springselenium.page.google;

import com.app.spring.springselenium.page.Base;
import com.app.spring.springselenium.utils.annotation.LazyAutowired;
import com.app.spring.springselenium.utils.annotation.PageFragment;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@PageFragment
public class GoogleSearchResult extends Base {

    @FindBy(css = "div.g")
    private List<WebElement> results;

    @FindBy(xpath = "//*[@id='rso']//div/a/h3")
    public List<WebElement> matchedResults;

    public int getCount(){
        return this.results.size();
    }

    @Override
    public boolean isAt() {
        return this.wait.until((d) -> !this.results.isEmpty());
    }

}
