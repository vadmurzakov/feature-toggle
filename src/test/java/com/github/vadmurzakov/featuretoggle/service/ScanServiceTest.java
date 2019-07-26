package com.github.vadmurzakov.featuretoggle.service;

import com.github.vadmurzakov.featuretoggle.FeatureToggleApplicationTests;
import com.github.vadmurzakov.featuretoggle.entity.Feature;
import com.github.vadmurzakov.featuretoggle.entity.Scan;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@Slf4j
public class ScanServiceTest extends FeatureToggleApplicationTests {

	@Autowired private ScanService scanService;
	@Autowired private FeatureService featureService;

	@Test
	public void save() {
		Scan scan = new Scan();
		scan.setName("График платежей");
		scan.setObject("<график_платежей.pdf>".getBytes());
		scan.setExtension("pdf");

		//проверяем, что фича выключена
		Feature feature = featureService.getByName("convertDangerousFile");
		assertFalse(feature.isActive());

		//сохраняем опасный файл и убеждаемся, что конвертации не было (расширение не поменялось)
		Scan save = scanService.save(scan);
		log.info(save.toString());
		assertEquals(save.getExtension(), "pdf");

		//включаем фичу по конвертации
		feature = featureService.getByName("convertDangerousFile");
		feature.setActive(true);
		featureService.update(feature);

		//создаем новый файл
		scan = new Scan();
		scan.setName("График платежей");
		scan.setObject("<график_платежей.pdf>".getBytes());
		scan.setExtension("pdf");

		//сохраняем опасный файл и убеждаемся, что конвертации прошла успешно
		save = scanService.save(scan);
		log.info(save.toString());
		assertEquals(save.getExtension(), "безопасный формат");

	}
}