package lk.ijse.sms.main;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("lk.ijse.sms")
@Import(HibernateConfig.class)
public class AppConfig {
}
