package com.github.vadmurzakov.featuretoggle.web.api.v1;

import com.github.vadmurzakov.featuretoggle.entity.Feature;
import com.github.vadmurzakov.featuretoggle.service.FeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(FeatureController.PATH)
@RequiredArgsConstructor
public class FeatureController {
	static final String PATH = "/v1/feature";

	private final FeatureService service;

	@GetMapping
	public List<Feature> getAll() {
		return service.getAll();
	}

}
