package cn.edu.nju.software.service.impl;

import cn.edu.nju.software.dao.StoryDao;
import cn.edu.nju.software.dao.WorksDao;
import cn.edu.nju.software.entity.Story;
import cn.edu.nju.software.service.StoryService;
import cn.edu.nju.software.util.Const;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * Created by xmc1993 on 2017/5/12.
 */
@Service
public class StoryServiceImpl implements StoryService {

    @Autowired
    private StoryDao storyDao;
    @Autowired
    private WorksDao worksDao;

    @Override
    public boolean saveStory(Story story) {
        return storyDao.saveStory(story);
    }

    @Override
    public boolean deleteStoryById(int id) {
        return storyDao.deleteStoryById(id);
    }

    @Override
    public Story getStoryById(int id) {
        return storyDao.getStoryById(id);
    }

    @Override
    public Story getStoryByIdHard(int id) {
        return storyDao.getStoryByIdHard(id);
    }

    @Override
    public List<Story> getAllStories() {
        return storyDao.getAllStories();
    }

    @Override
    public Story updateStory(Story story) {
        story.setUpdateTime(new Date());
        Story _story = storyDao.getStoryById(story.getId());
        boolean res = storyDao.updateStory(story);
        if (!res) {
            return null;
        }
        //如果故事的名字发生改变，那么更新冗余字段
        if (!_story.getTitle().equals(story.getTitle())) {
            worksDao.updateStoryTitle(story.getId(), story.getTitle());
        }
        if (!_story.getCoverUrl().equals(story.getCoverUrl())) {
            worksDao.updateCoverUrl(story.getId(), story.getCoverUrl());
        }
        return storyDao.getStoryById(story.getId());

    }

    @Override
    public List<Story> getStoryListByPage(int offset, int limit) {
        offset = offset < 0 ? Const.DEFAULT_OFFSET : offset;
        limit = limit < 0 ? Const.DEFAULT_LIMIT : limit;
        return storyDao.getStoryListByPage(offset, limit);
    }

    @Override
    public List<Story> getStoryListByIdList(List<Integer> idList) {
        idList.add(-1);//防止mybatis查询出错
        return storyDao.getStoryListByIdList(idList);
    }

    @Override
    public List<Story> getStoryListByTitle(String title, int offset, int limit) {
        offset = offset < 0 ? Const.DEFAULT_OFFSET : offset;
        limit = limit < 0 ? Const.DEFAULT_LIMIT : limit;
        return storyDao.getStoryListByTitle(title, offset, limit);
    }

    @Override
    public boolean recommendStory(int id) {
        return storyDao.recommendStory(id);
    }

    @Override
    public boolean cancelRecommendStory(int id) {
        return storyDao.cancelRecommendStory(id);
    }

    @Override
    public List<Story> getRecommendedStoryListByPage(int offset, int limit) {
        return storyDao.getRecommendedStoryListByPage(offset, limit);
    }

    @Override
    public boolean newTell(int id) {
        return storyDao.newTell(id);
    }

    @Override
    public boolean deleteTell(int id) {
        return storyDao.deleteTell(id);
    }

    @Override
    public Integer getStoryCount(){
        return storyDao.getStoryCount();
    }

    @Override
    public String getOriginSoundLength(File file) {
        Encoder encoder = new Encoder();
        MultimediaInfo m = null;
        try {
            m = encoder.getInfo(file);
            long length = m.getDuration()/1000;
            int hours= (int) (length/3600);
            int minutes= (int) ((length%3600)/60);
            int seconds= (int) (length%60);
            StringBuilder stringBuilder=new StringBuilder();
            stringBuilder.append(hours==0?"0":String.valueOf(hours));
            stringBuilder.append(":");
            stringBuilder.append(minutes==0?"0":String.valueOf(minutes));
            stringBuilder.append(":");
            stringBuilder.append(seconds==0?"0":String.valueOf(seconds));
            return stringBuilder.toString();
        } catch (EncoderException e) {
            e.printStackTrace();
        }
        return null;
    }
}
