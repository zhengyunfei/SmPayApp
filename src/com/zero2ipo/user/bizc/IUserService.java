package com.zero2ipo.user.bizc;

import com.zero2ipo.user.bo.UserBo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zhengyunfei on 2016/2/24.
 */
@Service
public interface IUserService {
    public long add(UserBo bo);
    public boolean update(UserBo bo);
    public boolean delete(String id);
    public List<UserBo> findAllList(Map<String, Object> queryMap);
    public List<UserBo> findAllList(Map<String, Object> queryMap, int skip, int max);
    public int getTotal(Map<String, Object> queryMap);
    public UserBo findById(String id);
}
