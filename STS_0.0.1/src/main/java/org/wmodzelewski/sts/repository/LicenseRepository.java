package org.wmodzelewski.sts.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.wmodzelewski.sts.models.License;

@Repository
public interface LicenseRepository extends CrudRepository<License, String> {

	public List<License> findByOrganizationId(String organizationId);

	public License findByOrganizationIdAndId(String organizatonId, String id);
}
