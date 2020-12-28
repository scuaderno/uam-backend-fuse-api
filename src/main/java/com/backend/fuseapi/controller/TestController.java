package com.backend.fuseapi.controller;

import org.apache.camel.Exchange;
import org.apache.camel.FluentProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.backend.fuseapi.utils.ApplicationResponse;
import com.backend.fuseapi.utils.ResultCode;

@RestController
@RequestMapping("/testApi")
public class TestController {
	
	@Autowired
	private FluentProducerTemplate fluentProducerTemplate;

	@RequestMapping(method = RequestMethod.GET , value = "/testController")
	@SuppressWarnings("rawtypes")
	public ApplicationResponse getAllApplication() {

		Exchange result = fluentProducerTemplate
//			    .withHeader("hkid", hkid)
			    .to("direct:uam-app").send();

		return ApplicationResponse.data(result, result.getIn().getBody(), ResultCode.SUCCESS.getMessage());
		//return new ApplicationResponse();
	}
}
