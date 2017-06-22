package cn.edu.nju.software.dao;

import cn.edu.nju.software.entity.Story;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xmc1993 on 2017/5/12.
 */

@Repository
public interface StoryDao {

    boolean saveStory(Story story);

    /**
     * 软删除
     * @param id
     * @return
     */
    boolean deleteStoryById(int id);

    Story getStoryById(int id);

    Story getStoryByIdHard(int id);

    List<Story> getAllStories();

    boolean updateStory(Story story);

    boolean deleteHard(int id);

    List<Story> getStoryListByTitle(String title, int offset, int limit);

    List<Story> getStoryListByPage(int offset, int limit);

    List<Story> getStoryListByIdList(@Param("idList") List<Integer> idList, @Param("offset")Integer offset,@Param("limit") Integer limit);

    boolean recommendStory(int id);

    boolean cancelRecommendStory(int id);

    List<Story> getRecommendedStoryListByPage(int offset, int limit);

    boolean newTell(int id);

    boolean deleteTell(int id);

    Integer getStoryCount();

    Integer getStoryCountByTitle(String title);

    Integer getStoryIdCountByTagIdList(@Param("idList")List<Integer> idList);

    Integer getRecommendedStoryCount();

    Integer getStoryCountByIdList(@Param("idList") List<Integer> idList);

    List<Story> getStoryByFuzzyQuery(@Param("query") List<String> queryList, @Param("offset")Integer offset, @Param("limit")Integer limit);

    int addStoryLikeCount(int storyId);

    int delStoryLikeCount(int storyId);


    List<Story> getStoryListByClassifyFuzzyQuery(@Param("title") String title, @Param("author") String author,@Param("content") String content,
                                                 @Param("press") String press, @Param("tag") String tag, @Param("offset") Integer offset,
                                                 @Param("limit") Integer limit);

    Integer getStoryCountByClassifyFuzzyQuery(@Param("title") String title, @Param("author") String author,@Param("content") String content,
                                                  @Param("press") String press, @Param("tag") String tag);

    List<Story> getDraftList(Integer offset,Integer limit);

    Integer getDraftCount();

    boolean setDraftCompleteByStoryId(@Param("storyId") Integer storyId);
}
