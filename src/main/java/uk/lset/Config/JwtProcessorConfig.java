package uk.lset.Config;


import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtProcessorConfig {

    @Bean
    public ConfigurableJWTProcessor<SecurityContext> jwtProcessor() {
        return new DefaultJWTProcessor<>();
    }
}
