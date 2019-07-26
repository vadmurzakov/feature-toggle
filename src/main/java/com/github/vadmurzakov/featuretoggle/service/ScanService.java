package com.github.vadmurzakov.featuretoggle.service;

import com.github.vadmurzakov.featuretoggle.entity.Scan;
import com.github.vadmurzakov.featuretoggle.repository.ScanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ScanService {
	private final ScanRepository scanRepository;
	private final ConverterService converterService;

	public Scan save(Scan scan) {
		converterService.convertDangerousFile(scan);
		return scanRepository.save(scan);
	}

	public List<Scan> getAll() {
		return StreamSupport.stream(scanRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public Scan get(Long id) {
		return scanRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
	}
}
