package kr.co.crossarc.extract.value.config;

import kr.co.crossarc.extract.value.files.ExtractedValueController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ExtractedValueController<Double> extractedValueController() {
        return new ExtractedValueController<Double>();
    }
}
