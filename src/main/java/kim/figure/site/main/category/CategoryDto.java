package kim.figure.site.main.category;

import lombok.*;

import java.util.List;

/**
 * author         : walker
 * date           : 2022. 12. 22.
 * description    :
 */
public class CategoryDto {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Get{

        private String id;

        private String name;

        private String uriKey;

        private Integer depth;

        private List<CategoryDto.Get> childCategoryList;

        private Long contentCount;
    }
}
