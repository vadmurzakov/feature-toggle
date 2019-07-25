package com.github.vadmurzakov.featuretoggle.repository;

import com.github.vadmurzakov.featuretoggle.entity.Scan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScanRepository extends CrudRepository<Scan, Long> {
}
