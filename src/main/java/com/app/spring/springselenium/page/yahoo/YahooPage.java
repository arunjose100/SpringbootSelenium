package com.app.spring.springselenium.page.yahoo;

import com.app.spring.springselenium.page.Base;
import com.app.spring.springselenium.utils.annotation.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Page
public class YahooPage extends Base {

    @Autowired
    private YahooSearchComponent yahooSearchComponent;

    @Autowired
    private YahooSearchResult yahooSearchResult;

    @Value("${application.yahoo}")
    private String url;

    public YahooSearchComponent getSearchComponent() {
        return yahooSearchComponent;
    }

    public YahooSearchResult getSearchResult() {
        return yahooSearchResult;
    }

    public void reload(){
        this.driver.navigate().to(this.url);
    }

    @Override
    public boolean isAt() {
        return this.yahooSearchComponent.isAt();
    }
}
