package com.brotherhui.cucumber.service;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import com.brotherhui.cucumber.common.velocity.VelocityEngineConfig;
import com.brotherhui.cucumber.common.velocity.VelocityWrapper;

/**
 * @author brijesh.thakkar
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(glue = {"com.brotherhui.cucumber.service.steps"},
				features = {
						"src/test/resources/features"
		        },
				plugin ="pretty")
@ContextConfiguration(
		classes =  {VelocityEngineConfig.class, VelocityWrapper.class},
		loader = SpringBootContextLoader.class)
public class FeaturesTest {
}
