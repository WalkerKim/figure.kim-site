package kim.figure.site.main.common;


import kim.figure.springssg.HtmlGenerator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import java.io.IOException;
import java.util.List;

/**
 * @Project : figure.kim.page
 * @Date : 2021-06-22
 * @Author : "DoHyeong Walker Kim"
 * @ChangeHistory :
 * @Note :
 */
@Service
@Log4j2
@DependsOn(value={"staticPagePathFinder"})
public class StaticGenService {
    @Value("${server.port:8080}")
    String port;

    @Value("${path.static-locations:static}")
    String staticPathLocation;

    @Value("${dist.path:dist}")
    String distPath;

    @Autowired
    RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    HandlerMapping viewControllerHandlerMapping;

    @Autowired
    ResourceUrlProvider resourceUrlProvider;

    @Autowired
    ApplicationContext applicationContext;


    public void init() throws IOException {
        HtmlGenerator htmlGenerator = new HtmlGenerator(resourceUrlProvider, distPath, List.of(requestMappingHandlerMapping, viewControllerHandlerMapping), "8080", applicationContext, true);
        htmlGenerator.generateStaticSite();
    }
}

