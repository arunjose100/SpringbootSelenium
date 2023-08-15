package com.app.spring.springselenium.page.yahoo;

import com.app.spring.springselenium.page.Base;
import com.app.spring.springselenium.utils.annotation.PageFragment;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PageFragment
public class YahooSearchComponent extends Base {

    @FindBy(name = "p")
    private WebElement searchBox;

    @FindBy(id = "ybar-search")
    private WebElement searchBtn;

    public void clearSearch(){
        this.searchBox.clear();
    }

    public void search(final String keyword){
        this.wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        this.searchBox.sendKeys(keyword);
        if(searchBtn.isDisplayed() && searchBtn.isEnabled()){
            searchBtn.click();
        }
    }

    @Override
    public boolean isAt() {
        return this.wait.until((d) -> this.searchBox.isDisplayed());
    }
}
