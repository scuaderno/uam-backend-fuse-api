package com.backend.fuseapi.controller;

import org.apache.camel.Exchange;
import org.apache.camel.FluentProducerTemplate;
import org.apache.camel.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.backend.fuseapi.request.UamStockAppCreateRequest;
import com.backend.fuseapi.utils.ApplicationResponse;
import com.backend.fuseapi.utils.ResultCode;
import com.backend.fuseapi.utils.SampleRequest;
import com.backend.fuseapi.utils.SampleResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/uamapi/stockapp")
public class UamStockAppApiController {

	private static final Logger logger = LoggerFactory.getLogger(UamStockAppApiController.class);
	private ObjectMapper objectMapper = new ObjectMapper();
	private JSONParser parser = new JSONParser();

	@Autowired
	private FluentProducerTemplate fluentProducerTemplate;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST , value = "/createUser")
	public ApplicationResponse createUser(@RequestBody UamStockAppCreateRequest request) throws Exception {
		//SampleResponse response = new SampleResponse("Sample Return", "of FuseAPI");
		
		Exchange result = fluentProducerTemplate
			    .to("direct:uam-app-stock-create").withBody(objectMapper.writeValueAsString(request)).send();
		logger.info("Mule Cloud response: {}", result.getIn().getBody());
		//return ApplicationResponse.data(response);
		return ApplicationResponse.data(result, result.getIn().getBody(String.class), ResultCode.SUCCESS.getMessage());
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST , value = "/mockApi", produces = "application/json; charset=UTF-8")
	public ResponseEntity<SampleResponse> mockApi(@RequestBody SampleRequest request){
		logger.info("Request : {} , {}", request.getUsername(), request.getTitle());
		SampleResponse response = new SampleResponse("TestSuccess!", "TestSuccess!");
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
