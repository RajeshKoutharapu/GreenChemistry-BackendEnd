package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Forward all routes except those containing a dot (e.g., .js, .css, .png)
        // and except API routes
        registry.addViewController("/{path:^(?!api$).*$}")
                .setViewName("forward:/index.html");
        registry.addViewController("/**/{path:^(?!api$).*$}")
                .setViewName("forward:/index.html");
    }
}