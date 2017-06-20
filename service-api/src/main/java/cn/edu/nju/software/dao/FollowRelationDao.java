package cn.edu.nju.software.dao;

import cn.edu.nju.software.entity.FollowRelation;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xmc1993 on 2017/5/12.
 */
@Repository
public interface FollowRelationDao {

    boolean saveFollowRelation(FollowRelation followRelation);

    boolean deleteFollowRelation(int followerId, int followeeId);

    FollowRelation getFollowRelation(int followerId, int followeeId);

    boolean deleteFollowRelationById(int id);

    List<Integer> getFollowerIdListByUserId(int userId, int offset, int limit);

    List<Integer> getFolloweeIdListByUserId(int userId, int offset, int limit);

    Integer getFollowerCountByUserId(int userId);

    Integer getFolloweeCountByUserId(int userId);
}
