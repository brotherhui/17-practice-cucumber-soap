package com.brotherhui.cucumber.service.constants;


public final class ConstantsUtil {

	public static final String EDGE_SERVICE_ENDPOINT = System.getProperty("edgeservice.endpoint",
			"https://clinicalresourcegw-service.qa.npe.ac2.io");
	
    public static final String INDENTITY_SERVICE_ENDPOINT = System.getProperty("indentityservice.endpoint",
            "https://clinicalviewgw-service.qa.npe.ac2.io");
    
    public static final String DOCUMENT_SERVICE_ENDPOINT = System.getProperty("documentservice.endpoint",
            "https://datasanitisation-service.qa.npe.ac2.io");

	public static final String MEDICARE_OVERVIEW_ENDPOINT = System.getProperty("medicareoverview.endpoint",
			"https://documentreference-service.qa.npe.ac2.io");
	
	public static final String EXAMPLE_WSDL = System.getProperty("example.wsdl",
			"https://localhost:8443/ws/users.wsdl?wsdl");
	
	public static final String EXAMPLE_WS = System.getProperty("example.ws",
			"http://www.hifreud.com/webservice");
	
}
