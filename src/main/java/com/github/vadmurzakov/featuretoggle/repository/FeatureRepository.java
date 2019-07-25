package com.github.vadmurzakov.featuretoggle.repository;

import com.github.vadmurzakov.featuretoggle.entity.Feature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRepository extends CrudRepository<Feature, String> {
}
