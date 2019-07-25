package com.github.vadmurzakov.featuretoggle.service;

import com.github.vadmurzakov.featuretoggle.entity.Feature;
import com.github.vadmurzakov.featuretoggle.repository.FeatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class FeatureService {
	private final FeatureRepository repository;

	public Feature getByName(String name) {
		return repository.findById(name).orElseThrow(() -> new EntityNotFoundException(name));
	}

	public List<Feature> getAll() {
		return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
	}
}
