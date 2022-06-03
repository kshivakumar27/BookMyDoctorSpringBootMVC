package com.bookMyDoctor.config;


import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class StaticResourceConfiguration implements WebMvcConfigurer {
    //@Value("${upload.location}")
    private static final String PICTURE_RESOURCE_HANDLER = "/mm_pics/**";
    private static final String PICTURE_RESOURCE_LOCATION = "file:///C:/Users/shahm/Pictures/doctor_pic/";

    private static final String[] RESOURCE_LOCATIONS = {"classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/"};

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/**")) {
            registry.addResourceHandler("/**").addResourceLocations(RESOURCE_LOCATIONS);
        }

        registry.addResourceHandler(PICTURE_RESOURCE_HANDLER).addResourceLocations(PICTURE_RESOURCE_LOCATION);
    }

//    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//        super.configurePathMatch(configurer);
//
//        configurer.setUseSuffixPatternMatch(false);
//    }
   
}
