package kim.figure.site.main.post;

import kim.figure.site.common.category.Category;
import kim.figure.site.common.content.ContentFormat;
import kim.figure.site.common.tag.Tag;
import lombok.*;

import java.time.Instant;
import java.util.List;

/**
 * author         : walker
 * date           : 2022. 12. 24.
 * description    :
 */
public class PostDto {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Get{

        private Long id;

        private String title;

        private ContentFormat contentFormat;

        private String rawContent;

        private String renderedContent;

        private String description;

        private Instant createdAt;

        private Boolean isDraft;

        private Instant lastModifiedAt;

        private Instant publishAt;

        private List<String> ogKeywordList;

        private List<Tag> tagList;

        private List<Category> categoryList;

        private List<String> headingIdList;

        //ogTag image
        String ogImage;

        Boolean isPublished;

    }
}
