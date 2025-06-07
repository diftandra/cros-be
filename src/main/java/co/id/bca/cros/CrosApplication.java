package co.id.bca.cros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class CrosApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CrosApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
        return applicationBuilder.sources(Application.class);
    }

    // @Bean
    // public ServletRegistrationBean<DispatcherServlet> dispatcherServletRegistration() {
    //     DispatcherServlet servlet = new DispatcherServlet();
    //     ServletRegistrationBean<DispatcherServlet> registration = new ServletRegistrationBean<>(servlet);
    //     registration.addUrlMappings("/*");
    //     registration.setName("dispatcher");
    //     registration.setLoadOnStartup(1);
    //     return registration;
    // }

}
