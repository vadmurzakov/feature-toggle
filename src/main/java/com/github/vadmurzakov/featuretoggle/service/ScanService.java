package com.github.vadmurzakov.featuretoggle.service;

import com.github.vadmurzakov.featuretoggle.entity.Scan;
import com.github.vadmurzakov.featuretoggle.repository.ScanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScanService {
	private final ScanRepository scanRepository;

	public Scan save(Scan scan) {
		Scan save = scanRepository.save(scan);
		return save;
	}

	public Scan get(Long id) {
		return scanRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
	}
}
