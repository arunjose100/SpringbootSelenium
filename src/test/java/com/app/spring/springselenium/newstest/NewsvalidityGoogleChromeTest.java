package com.app.spring.springselenium.newstest;

import com.app.spring.springselenium.SpringBaseTestNGTest;
import com.app.spring.springselenium.page.goguardian.GoguardianHome;
import com.app.spring.springselenium.page.google.GooglePage;
import com.app.spring.springselenium.utils.annotation.LazyAutowired;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewsvalidityGoogleChromeTest extends SpringBaseTestNGTest {

    @LazyAutowired
    private GooglePage googlePage;

    @LazyAutowired
    private GoguardianHome goguardianHome;

    @Value("${application.google}")
    private String url;

    @Value("${page.title}")
    private String pageTitle;

    @Test(description = "News validation test on Google source")
    public void test() throws InterruptedException {
        this.goguardianHome.goTo();
        Assert.assertEquals(this.goguardianHome.getTitle(), this.pageTitle, "Page title doesn't match");
        Assert.assertTrue(this.goguardianHome.isAt());
        this.goguardianHome.captureMainNewsDetails();

        this.googlePage.goTo(url);
        Assert.assertTrue(this.googlePage.isAt());
        this.goguardianHome.getNewsHeaders().stream().forEach(e->{
            this.googlePage.getSearchComponent().clearSearch();
            this.googlePage.getSearchComponent().search("\""+e+"\"");
            Assert.assertTrue(this.googlePage.getSearchResult().isAt());
            Assert.assertTrue(this.googlePage.getSearchResult().getCount() > 1);
            Assert.assertTrue(this.googlePage.getSearchResult().matchedResults.size()>=1,
                    "Less results displayed :: "+this.googlePage.getSearchResult().matchedResults.size());
            this.googlePage.reload();
        });

        this.googlePage.close();
    }

}
