package kim.figure.site.main.post;

import kim.figure.site.common.content.Content;
import kim.figure.site.common.tag.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
            return dto;
        }).orElseThrow(()->new RuntimeException(""));
    }

    public List<Content> getRecommendPostList() {
        return postRepository.findTop10OrderByRecommendStatDesc();
    }

    public List<PostDto.Get> getRecommendPostList(Long id) {
        int recommendContentCount = 5;
        Content targetContent = postRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
        List<PostDto.Get> resultContentList = new ArrayList<>();
        int confirmedListSize = 0;
        //확정 추천 목록

        List<PostDto.Get> recommendList = postRepository.findAllById(Optional.ofNullable(targetContent.getRecommendPostIdList()).orElseGet(()->new ArrayList<>())).stream().map(PostMapper.INSTANCE::contentToGet).toList();
        confirmedListSize = recommendList.size();
        resultContentList.addAll(recommendList);


        if(confirmedListSize ==5){

        }else{
            //확정 추천 목록이 최댓값에 못 미치는 경우 태그 일치 가장 많이 되는 순서 추천 목록 추천
            List<Content> additionalRecommendContentList = new ArrayList<>();
            List<Tag> tagList = targetContent.getTagList();
            Pageable pageRequestByTag =  PageRequest.of(0, recommendContentCount-confirmedListSize);
            List<Content> tagInList = postRepository.findByTagListInAndIdNot(tagList,pageRequestByTag, targetContent.getId());
            additionalRecommendContentList.addAll(tagInList);
            int tagInListSize = tagInList.size();

            if ((confirmedListSize+tagInListSize)<5){
                Pageable pageRequestByCategory =  PageRequest.of(0, recommendContentCount-confirmedListSize-tagInListSize);
                List<Content> categoryInList = postRepository.findByCategoryListInAndTagListNotInAndIdNot(targetContent.getCategoryList(), pageRequestByCategory, tagList, targetContent.getId());
                additionalRecommendContentList.addAll(categoryInList);
            }
            resultContentList.addAll(
                    additionalRecommendContentList
                            .stream()
                            .map(content -> PostMapper.INSTANCE.contentToGet(content))
                            .sorted(Comparator.comparingLong(content-> targetContent.getTagList().stream().filter(tag-> content.getTagList().contains(tag)).count()))
                            .limit(recommendContentCount-confirmedListSize)
                    .toList());
        }
        return resultContentList;
    }

    public List<PostDto.Get> getActivePostList() {
        return postRepository.findByIsPublished(true).stream().map(PostMapper.INSTANCE::contentToGet).toList();
    }

    public Content getRecommendPost() {
        return postRepository.findTopByOrderByRecommendStatDesc();
    }
}
