package com.github.vadmurzakov.featuretoggle.service;

import com.github.vadmurzakov.featuretoggle.configuration.annotation.Feature;
import com.github.vadmurzakov.featuretoggle.entity.Scan;
import com.github.vadmurzakov.featuretoggle.repository.ScanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScanService {
	private final ScanRepository scanRepository;

	public Scan save(Scan scan) {
		convertDangerousFile(scan);
		return scanRepository.save(scan);
	}

	public List<Scan> getAll() {
		return StreamSupport.stream(scanRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public Scan get(Long id) {
		return scanRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
	}

	@Feature(name = "convertDangerousFile")
	private void convertDangerousFile(Scan scan) {
		log.info("Scan {} convert to a secure format", scan.getName());
		scan.setObject("очень безопасный файл".getBytes());
		scan.setExtension("безопасный формат");
	}
}
