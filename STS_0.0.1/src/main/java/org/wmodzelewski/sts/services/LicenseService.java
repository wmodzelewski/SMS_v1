package org.wmodzelewski.sts.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wmodzelewski.sts.models.License;
import org.wmodzelewski.sts.repository.LicenseRepository;

@Service
public class LicenseService {

	@Autowired
	LicenseRepository licenseRepository;

	@Autowired
	ServiceConfig config;

	public License getLicense(String organizationId, String id) {
		return licenseRepository.findByOrganizationIdAndId(organizationId, id).withComment(config.getExampleProperty());
	}

	public List<License> getLicensesByOrg(String organizationId) {
		return licenseRepository.findByOrganizationId(organizationId);
	}

	public void saveLicense(License license) {
		license.withId(UUID.randomUUID().toString());
		licenseRepository.save(license);
	}
}
