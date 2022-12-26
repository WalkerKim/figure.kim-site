package kim.figure.site.main.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author         : walker
 * date           : 2022. 12. 22.
 * description    :
 */
@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public PostDto.Get getPost(Long id) {
        //TODO impl 404 Exceptiuon
        return postRepository.findById(id).map(post->{
            PostDto.Get dto = PostMapper.INSTANCE.contentToGet(post);
            List<String> headingIdList = new ArrayList<>();
            Pattern headingPattern = Pattern.compile("#{1,6}.+");
            Matcher matcher = headingPattern.matcher(dto.getRawContent());
            while (matcher.find()) {
                headingIdList.add(matcher.group().replaceAll("#","").trim().replaceAll(" ","-"));
            }
            dto.setHeadingIdList(headingIdList);
            return dto;
        }).orElseThrow(()->new RuntimeException(""));
    }
}
