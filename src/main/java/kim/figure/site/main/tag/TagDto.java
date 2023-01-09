package kim.figure.site.main.tag;

import kim.figure.site.common.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * author         : walker
 * date           : 2023. 01. 05.
 * description    :
 */
public class TagDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Get{

        String tag;
        Long count;

    }

}
