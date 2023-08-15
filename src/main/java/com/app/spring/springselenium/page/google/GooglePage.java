package com.app.spring.springselenium.page.google;

import com.app.spring.springselenium.utils.annotation.Page;
import com.app.spring.springselenium.page.Base;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Page
public class GooglePage extends Base {

    @Autowired
    private GoogleSearchComponent googleSearchComponent;

    @Autowired
    private GoogleSearchResult googleSearchResult;

    @Value("${application.google}")
    private String url;

    public void reload(){
        this.driver.navigate().to(this.url);
    }

    public GoogleSearchComponent getSearchComponent() {
        return googleSearchComponent;
    }

    public GoogleSearchResult getSearchResult() {
        return googleSearchResult;
    }

    @Override
    public boolean isAt() {
        return this.googleSearchComponent.isAt();
    }

}
