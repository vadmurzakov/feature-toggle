package com.github.vadmurzakov.featuretoggle.configuration.annotation;

import com.github.vadmurzakov.featuretoggle.service.FeatureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class FeatureBeanPostProcessor implements BeanPostProcessor {

	@Autowired
	private FeatureService featureService;

	private Map<String, Class> featureBeans = new HashMap<>();

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		Class<?> beanClass = bean.getClass();
		if (Arrays.stream(beanClass.getMethods()).anyMatch(method -> method.isAnnotationPresent(Feature.class))) {
			featureBeans.put(beanName, beanClass);
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		Class originalBean = featureBeans.get(beanName);
		if (originalBean != null) {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(originalBean);
			enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
				Optional<Method> originalMethod = Arrays.stream(originalBean.getDeclaredMethods())
						.filter(method::equals)
						.findFirst();

				if (originalMethod.isPresent()) {
					Feature annotation = originalMethod.get().getAnnotation(Feature.class);
					String nameFeature = annotation.name();

					com.github.vadmurzakov.featuretoggle.entity.Feature feature = featureService.getByName(nameFeature);

					Object invoke = null;

					if (feature.isActive()) {
						log.debug("Feature {} is active and running", nameFeature);
						invoke = proxy.invoke(bean, args);
					}
					return invoke;
				}
				return null;
			});
			return enhancer.create();
		}
		return bean;
	}
}

