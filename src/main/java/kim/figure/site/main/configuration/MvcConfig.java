package kim.figure.site.main.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	private StaticPagePathFinder staticPagePathFinder;

	@Bean
	public StaticPagePathFinder staticPagePathFinder(ResourcePatternResolver resourcePatternResolver) {
		this.staticPagePathFinder = new StaticPagePathFinder(resourcePatternResolver);
		return this.staticPagePathFinder;
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		try {
			for (StaticPagePathFinder.PagePaths paths : staticPagePathFinder.findPaths()) {
				String urlPath = paths.getUrlPath();
				registry.addViewController(urlPath).setViewName("pages" + paths.getFilePath());
				if (!urlPath.isEmpty()) {
					registry.addViewController(urlPath + "/").setViewName("pages" + paths.getFilePath());
				}
			}
		}
		catch (IOException e) {
			throw new RuntimeException("Unable to locate static pages: " + e.getMessage(), e);
		}
	}

}