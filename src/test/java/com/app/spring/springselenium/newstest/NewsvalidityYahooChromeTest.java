package com.app.spring.springselenium.newstest;

import com.app.spring.springselenium.SpringBaseTestNGTest;
import com.app.spring.springselenium.page.goguardian.GoguardianHome;
import com.app.spring.springselenium.page.yahoo.YahooPage;
import com.app.spring.springselenium.utils.annotation.LazyAutowired;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewsvalidityYahooChromeTest extends SpringBaseTestNGTest {

    @LazyAutowired
    private YahooPage yahooPage;

    @LazyAutowired
    private GoguardianHome goguardianHome;

    @Value("${application.yahoo}")
    private String url;

    @Test
    public void test() throws InterruptedException {
        this.goguardianHome.goTo();
        Assert.assertEquals(this.goguardianHome.getTitle(), "News | The Guardian", "Page title doesn't match");
        Assert.assertTrue(this.goguardianHome.isAt());
        this.goguardianHome.captureMainNewsDetails();

        this.yahooPage.goTo(url);
        Assert.assertTrue(this.yahooPage.isAt());
        this.goguardianHome.getNewsHeaders().stream().forEach(e->{
            this.yahooPage.getSearchComponent().clearSearch();
            this.yahooPage.getSearchComponent().search("\""+e+"\"");
            Assert.assertTrue(this.yahooPage.getSearchResult().isAt());
            Assert.assertTrue(this.yahooPage.getSearchResult().getCount() > 1);
            Assert.assertTrue(this.yahooPage.getSearchResult().matchedResults.size()>=1,
                    "Less results displayed :: "+this.yahooPage.getSearchResult().matchedResults.size());
            this.yahooPage.reload();
        });

        this.yahooPage.close();
    }
}
