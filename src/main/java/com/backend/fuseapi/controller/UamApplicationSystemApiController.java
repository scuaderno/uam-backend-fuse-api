package com.backend.fuseapi.controller;

import org.apache.camel.Exchange;
import org.apache.camel.FluentProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.backend.fuseapi.utils.ApplicationResponse;
import com.backend.fuseapi.utils.ResultCode;

@RestController
@RequestMapping("/uamAppApi")
public class UamApplicationSystemApiController {

	private static final Logger logger = LoggerFactory.getLogger(UamApplicationSystemApiController.class);

	@Autowired
	private FluentProducerTemplate fluentProducerTemplate;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.GET , value = "/getAllApplication")
	public ApplicationResponse getAllApplication() {

		Exchange result = fluentProducerTemplate
			    .to("direct:uam-app").send();
		logger.info("Cloud response: {}", result.getIn().getBody());
		return ApplicationResponse.data(result, result.getIn().getBody(), ResultCode.SUCCESS.getMessage());
	}
}
