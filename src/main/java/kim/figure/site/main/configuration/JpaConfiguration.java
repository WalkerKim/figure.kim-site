package kim.figure.site.main.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * author         : walker
 * date           : 2022. 11. 21.
 * description    :
 */

@Configuration
@EnableJpaRepositories(basePackages={"kim.figure.site.common"})
public class JpaConfiguration {
}
