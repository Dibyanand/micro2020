package com.way2learnonline.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
//@Import({ThymeleafConfig.class})
public class MvcConfig extends WebMvcConfigurerAdapter {
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (!registry.hasMappingForPattern("/webjars/**")) {
	        registry.addResourceHandler("/webjars/**").addResourceLocations(
	                "classpath:/META-INF/resources/webjars/");
	    }
    }
	
	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		// Configure JSON support
		MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJacksonHttpMessageConverter.setSupportedMediaTypes(Arrays
				.asList(MediaType.APPLICATION_JSON));
		//mappingJacksonHttpMessageConverter.getObjectMapper().configure(
		//		Feature.WRITE_DATES_AS_TIMESTAMPS, true);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		// There is no need to set the timezone as Jackson uses GMT and not the
		// local time zone (which is exactly what you want)
		// Note: While SimpleDateFormat is not threadsafe, Jackson Marshaller's
		// StdSerializerProvider clones the configured formatter for each thread
		mappingJacksonHttpMessageConverter.getObjectMapper().setDateFormat(
				format);
		//mappingJacksonHttpMessageConverter.getObjectMapper().configure(
		//		Feature.INDENT_OUTPUT, true);
		// mappingJacksonHttpMessageConverter.getObjectMapper().getSerializationConfig().setSerializationInclusion(Inclusion.NON_NULL);
		converters.add(mappingJacksonHttpMessageConverter);
	}
}
