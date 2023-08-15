package com.app.spring.springselenium.newstest;

import com.app.spring.springselenium.SpringBaseTestNGTest;
import com.app.spring.springselenium.page.goguardian.GoguardianHome;
import com.app.spring.springselenium.page.google.GooglePage;
import com.app.spring.springselenium.utils.annotation.LazyAutowired;
import com.app.spring.springselenium.utils.service.ScreenshotService;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoGuardianChromeTest extends SpringBaseTestNGTest {

    @LazyAutowired
    private GooglePage googlePage;

    @LazyAutowired
    private GoguardianHome goguardianHome;

    @LazyAutowired
    private ScreenshotService screenshotService;

    @Value("${application.google}")
    private String url;

    @Test
    public void test() throws InterruptedException {
        this.goguardianHome.goTo();
        Assert.assertEquals(this.goguardianHome.getTitle(), "News | The Guardian", "Page title doesn't match");
        Assert.assertTrue(this.goguardianHome.isAt());
        this.goguardianHome.captureMainNewsDetails();

        this.googlePage.goTo(url);
        Assert.assertTrue(this.googlePage.isAt());
        this.goguardianHome.getNewsHeaders().stream().forEach(e->{
            this.googlePage.getSearchComponent().clearSearch();
            this.googlePage.getSearchComponent().search("\""+e+"\"");
            Assert.assertTrue(this.googlePage.getSearchResult().isAt());
            Assert.assertTrue(this.googlePage.getSearchResult().getCount() > 0);
            Assert.assertTrue(this.googlePage.getSearchResult().matchedResults.size()>0,
                    "Less results displayed :: "+this.googlePage.getSearchResult().matchedResults.size());
            this.googlePage.reload();
        });

        this.googlePage.close();
    }

}
