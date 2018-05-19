package com.borrow.blockchain.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.borrow.blockchain.entity.PropertyConfig;

@Repository
public interface PropertyConfigRepository extends CrudRepository<PropertyConfig, String> {

	@Query("FROM PropertyConfig a WHERE a.prcfConfigStatus = 'A' ORDER BY a.prcfConfigKey DESC")
	public List<PropertyConfig> getPropertyConfig();

}
