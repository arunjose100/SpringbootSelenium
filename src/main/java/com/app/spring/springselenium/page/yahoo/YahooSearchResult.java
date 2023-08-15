package com.app.spring.springselenium.page.yahoo;

import com.app.spring.springselenium.page.Base;
import com.app.spring.springselenium.utils.annotation.PageFragment;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@PageFragment
public class YahooSearchResult extends Base {

    @FindBy(xpath = "//h3/a")
    private List<WebElement> results;

    @FindBy(xpath = "//*[@id='main']//div/h3/a")
    public List<WebElement> matchedResults;

    public int getCount(){
        this.wait.until((d) -> this.matchedResults.size() > 0);
        return this.matchedResults.size();
    }

    @Override
    public boolean isAt() {
        return this.wait.until((d) -> !this.results.isEmpty());
    }


}
