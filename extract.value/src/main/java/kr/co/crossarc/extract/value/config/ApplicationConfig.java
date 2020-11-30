package kr.co.crossarc.extract.value.config;

import kr.co.crossarc.extract.value.file.rcs.ExtractedValueController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ExtractedValueController extractedValueController() {
        return new ExtractedValueController();
    }
}
