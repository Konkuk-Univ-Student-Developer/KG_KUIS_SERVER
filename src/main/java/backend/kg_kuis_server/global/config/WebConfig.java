package backend.kg_kuis_server.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000", "http://localhost:8080", "https://kuis.shop", "https://www.kuis.shop", "http://localhost:5173", "https://ng-kuis-web.vercel.app", "https://www.ng-kuis-web.vercel.app",
                                "https://ng-kuis-web-dev.vercel.app", "https://www.ng-kuis-web-dev.vercel.app", "https://ku-room-web-individual.vercel.app")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }
}
