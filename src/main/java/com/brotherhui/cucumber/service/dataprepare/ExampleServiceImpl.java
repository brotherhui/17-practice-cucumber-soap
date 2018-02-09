package com.brotherhui.cucumber.service.dataprepare;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;


import com.brotherhui.cucumber.service.constants.ConstantsUtil;
import com.brotherhui.cucumber.service.wsimport.example.UserRequest;
import com.brotherhui.cucumber.service.wsimport.example.UserRequest_Type;
import com.brotherhui.cucumber.service.wsimport.example.UserResponse;

public class ExampleServiceImpl {


	public UserRequest userRequest = null;
	
	public UserRequest_Type userRequestType = null;
	
	public UserResponse userResponse = null;
	
	public void initialUserRequest(String serviceName) throws MalformedURLException{
		URL url = new URL(ConstantsUtil.EXAMPLE_WSDL);
        QName qname = new QName(ConstantsUtil.EXAMPLE_WS, serviceName);  
        Service service = Service.create(url,qname);
        userRequest = service.getPort(UserRequest.class);
	}
	
	public void setUserId(String userID){
        userRequestType = new UserRequest_Type();
        userRequestType.setUserId(userID);
	}
	
	public void getUser(){
		if(userRequest != null){
			userResponse = userRequest.user(userRequestType);
		}
	}
	
	public String getUserName(){
		if(userResponse != null){
			return userResponse.getUsername();
		}else{
			return "user is not retrieved";
		}
         
	}
	
}
