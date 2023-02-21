package kim.figure.site.main;

import kim.figure.site.main.common.StaticGenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * author         : walker
 * date           : 2023. 02. 21.
 * description    :
 */
@Component
public class ApplicationStartup  implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    StaticGenService staticGenService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            staticGenService.init();
        } catch (IOException e) {
            e.printStackTrace();
//            throw new RuntimeException(e);
        }
    }
}
