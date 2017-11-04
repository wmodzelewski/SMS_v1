package org.wmodzelewski.sts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wmodzelewski.sts.models.License;
import org.wmodzelewski.sts.services.LicenseService;

@RestController
@RequestMapping(value = "v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {

	@Autowired
	LicenseService service;

	@RequestMapping(value = "/{licenseId}", method = RequestMethod.GET)
	License getLicenses(@PathVariable("organizationId") String organizationId,
			@PathVariable("licenseId") String licenseId) {
		return service.getLicense(organizationId, licenseId);

	}
}
