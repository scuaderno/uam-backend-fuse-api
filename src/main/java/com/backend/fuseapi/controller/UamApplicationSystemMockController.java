package com.backend.fuseapi.controller;

import org.apache.camel.Exchange;
import org.apache.camel.FluentProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.backend.fuseapi.utils.ApplicationResponse;
import com.backend.fuseapi.utils.ResultCode;

@RestController
@RequestMapping("/uamMockApi")
public class UamApplicationSystemMockController {
	private static final Logger logger = LoggerFactory.getLogger(UamApplicationSystemApiController.class);

	@Autowired
	private FluentProducerTemplate fluentProducerTemplate;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.GET , value = "/getAllApplication")
	public ApplicationResponse getAllApplication() {

		Exchange result = fluentProducerTemplate
			    .withHeader("ms2-authorization", "bearer 3902804f-1439-4b16-b226-bfa9b3ebfb2e")
			    .withHeader("ms2-authorization", "ms2-origin")
			    .to("direct:uam-app-mock").send();

		logger.info("Exchange response: {}", result.getIn().getBody());
		return ApplicationResponse.data(result, result.getIn().getBody(), ResultCode.SUCCESS.getMessage());
	}
}
