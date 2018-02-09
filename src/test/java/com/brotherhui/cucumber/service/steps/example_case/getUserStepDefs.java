package com.brotherhui.cucumber.service.steps.example_case;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.brotherhui.cucumber.common.config.SOAPSSLConfiguration;
import com.brotherhui.cucumber.common.util.BaseDefs;
import com.brotherhui.cucumber.service.dataprepare.ExampleServiceImpl;

@ContextConfiguration(classes = {SOAPSSLConfiguration.class,
        ExampleServiceImpl.class
})
@TestPropertySource(locations = { "/application-test.properties" })
public class getUserStepDefs extends BaseDefs {
	
	
	ExampleServiceImpl exampleService;

	@Given("^The endpoint is \"([^\"]*)\"$")
	public void the_endpoint_is(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		exampleService.initialUserRequest(arg1);
	}

	@Given("^The user id is \"([^\"]*)\"$")
	public void the_user_id_is(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		exampleService.setUserId(arg1);
	}
	
	@When("^I try to get document$")
	public void i_try_to_get_document() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		exampleService.getUser();
	}
	
	@Then("^Verify the response contains$")
	public void the_response_contains(DataTable arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
    	List<Map<String,String>> queryList = arg1.asMaps(String.class, String.class);
    	HashMap<String, String> querys = new HashMap<String, String>();
    	for(Map<String,String> queryMap : queryList){
    		querys.put(queryMap.get("Key"), queryMap.get("Value"));
    	}
    	if(querys.containsKey("userName")){
    		assertEquals(querys.get("userName"), exampleService.getUserName());
    	}
    	
	}
	

	@Override
	public void initParameters() {
		exampleService = new ExampleServiceImpl();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cleanParameters() {
		// TODO Auto-generated method stub
		exampleService = null;
	}

	@Override
	public void prepareTestData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cleanTestData() {
		// TODO Auto-generated method stub
		
	}
	
	@Before
	public void before(){
		super.beforeScenario();
	}
	
	@After
	public void after(){
		super.afterScenario();
	}
}
