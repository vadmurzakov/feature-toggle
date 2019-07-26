package com.github.vadmurzakov.featuretoggle.web.api.v1;

import com.github.vadmurzakov.featuretoggle.entity.Scan;
import com.github.vadmurzakov.featuretoggle.service.ScanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ScanController.PATH)
@RequiredArgsConstructor
public class ScanController {
	static final String PATH = "/v1/scan";

	private final ScanService service;

	@GetMapping
	public List<Scan> getAll() {
		return service.getAll();
	}
}
