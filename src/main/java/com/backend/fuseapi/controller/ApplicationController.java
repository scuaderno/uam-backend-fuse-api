package com.backend.fuseapi.controller;

import org.apache.camel.Exchange;
import org.apache.camel.FluentProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.fuseapi.utils.ApplicationResponse;
import com.backend.fuseapi.utils.ResultCode;

@RestController
@RequestMapping("/application")
public class ApplicationController {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);

	@Autowired
	private FluentProducerTemplate fluentProducerTemplate;
	
	@SuppressWarnings("rawtypes")
	@GetMapping(path="/getAllApplication")
	public ApplicationResponse getAllApplication() {

		Exchange result = fluentProducerTemplate
//			    .withHeader("hkid", hkid)
			    .to("direct:uam-app").send();

		return ApplicationResponse.data(result, result.getIn().getBody(), ResultCode.SUCCESS.getMessage());
	}
}
