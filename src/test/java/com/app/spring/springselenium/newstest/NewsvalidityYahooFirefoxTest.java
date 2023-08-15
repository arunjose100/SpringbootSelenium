package com.app.spring.springselenium.newstest;

import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = {"browser=firefox"})
public class NewsvalidityYahooFirefoxTest extends NewsvalidityYahooChromeTest {
}
