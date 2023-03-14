package kim.figure.site.main.tag;

import kim.figure.site.common.tag.Tag;
import kim.figure.site.main.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * author         : walker
 * date           : 2023. 01. 05.
 * description    :
 */
@Service
public class TagService {
    @Autowired
    TagRepository tagRepository;

    @Autowired
    PostRepository postRepository;


    public List<TagDto.Get> getTagList() {
        List<Tag> tagList = tagRepository.findAll();



        return null;
    }


    public List<TagDto.Get> aggregateTagPostCount(){
        return postRepository.groupByTagAndSortByCountDecrease();
    }

    public Tag getTagById(String id) {
        return tagRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    public List<Map<String, String>> getTagIdList(){
        return tagRepository.findAll().stream().map(i -> {
            Map<String, String> map = new HashMap();
            map.put("id", i.getId().toString());
            return map;
        }).collect(Collectors.toList());
    }


}
