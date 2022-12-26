package kim.figure.site.main.post;

import kim.figure.site.common.content.Content;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * author         : walker
 * date           : 2022. 12. 24.
 * description    :
 */
@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    PostDto.Get contentToGet(Content content);
}
