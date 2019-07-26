package com.github.vadmurzakov.featuretoggle.service;

import com.github.vadmurzakov.featuretoggle.configuration.annotation.Feature;
import com.github.vadmurzakov.featuretoggle.entity.Scan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConverterService {

	@Feature(name = "convertDangerousFile")
	public void convertDangerousFile(Scan scan) {
		scan.setObject("очень безопасный файл".getBytes());
		scan.setExtension("безопасный формат");
	}
}
