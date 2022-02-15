package pl.edu.wszib.dngmp.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "pl.edu.wszib.dngmp.controllers",
        "pl.edu.wszib.dngmp.services",
        "pl.edu.wszib.dngmp.session",
})
public class TestConfiguration {

}
