package com.brotherhui.cucumber.common.util;


public abstract class BaseDefs {
	
	/**
	 * This method must be implemented, in this method, you need to initialize the parameters used through the test case
	 */
	public abstract void initParameters();
	/**
	 * This method must be implemented, in this method, you need to clean the parameters used through the test case
	 */
	public abstract void cleanParameters();
	
	
	/**
	 * This method must be implemented, in this method, please prepare the data here
	 */
	public abstract void prepareTestData();
	
	/**
	 * This method must be implemented, in this method, please clean the data here
	 */
	public abstract void cleanTestData();
	
	
	public void beforeScenario() {
		this.initParameters();
		this.prepareTestData();
	}

	public void afterScenario() {
		this.cleanTestData();
		this.cleanParameters();
	}

}