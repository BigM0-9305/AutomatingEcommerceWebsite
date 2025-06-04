package com.selenium.practice.test.cucumbertests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src\\test\\java\\com\\selenium\\practice\\test\\cucumbertests",
                 glue = "com.selenium.practice.test.stepdefinitions",
                 monochrome = true,
                 /*tags = "@Regression",*/
                 plugin = "html:TestReport\\CucumberReport.html"
                 )
public class TestNGRunnerForProductOrdering extends AbstractTestNGCucumberTests{}
