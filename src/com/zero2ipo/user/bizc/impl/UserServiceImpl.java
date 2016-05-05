package com.zero2ipo.user.bizc.impl;

import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.mobile.dao.base.IbatisBaseDao;
import com.zero2ipo.user.bizc.IUserService;
import com.zero2ipo.user.bo.UserBo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by zhengyunfei on 2016/2/24.
 */
@Service("UserService")
public class UserServiceImpl extends IbatisBaseDao implements IUserService {
 public final static String NAMESPACE="mobile.User.";
 public final static String COMMON="User";
 public final static String ADD=NAMESPACE+"add"+COMMON;
 public final static String UPDATE=NAMESPACE+"upd"+COMMON;
 public final static String DELETE=NAMESPACE+"del"+COMMON;
 public final static String FINDALLLIST=NAMESPACE+"find"+COMMON+"List";
 public final static String FINDALLLISTCOUNT=NAMESPACE+"find"+COMMON+"ListCount";
 public final static String FINDBYID=NAMESPACE+"find"+COMMON+"ById";


    @Override
    public long add(UserBo bo) {
        long id = 0;
        try{
            id=(Long)this.insert(ADD, bo);
        }catch(Exception e){
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public boolean update(UserBo bo) {
        boolean flag=false;
        try{
            this.update(UPDATE, bo);
            flag=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean delete(String ids) {
        boolean flag=false;
        try {
            Map map = new HashMap();
            map.put("id", ids.split(","));
           this.delete(DELETE, map);
            // 删除成功
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            BaseLog.e(this.getClass(), "del_UserBo 删除出错", e);
            throw new BaseException("删除出错！", e);
        }
        return flag;
    }

    @Override
    public List<UserBo> findAllList(Map<String, Object> queryMap) {
        List<UserBo> list = null;
        try {
            list = (List<UserBo>) this.queryAll(FINDALLLIST, queryMap);
        } catch (Exception e) {
            e.printStackTrace();
            BaseLog.e(this.getClass(), "findAllList 查询列表", e);
            throw new BaseException("查询列表出错！", e);
        }
        return list;
    }

    @Override
    public List<UserBo> findAllList(Map<String, Object> queryMap, int skip, int max) {
        List<UserBo> list = null;
        List<UserBo> fs=null;
        try {
            list = (List<UserBo>) this.queryAll(FINDALLLIST, queryMap);
            //fs = ListUtils.getSubListPage(list, (Integer.valueOf(skip) - 1) * max, max);
        } catch (Exception e) {
            e.printStackTrace();
            BaseLog.e(this.getClass(), "findAllList 查询列表", e);
            throw new BaseException("查询列表出错！", e);
        }
        return fs;
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        int count = 0;
        try {
            count = (Integer) this.query(FINDALLLISTCOUNT, queryMap);
        } catch (Exception e) {
            BaseLog.e(this.getClass(), "findUserBoCouponListCount查询优惠券支付个数", e);
        }
        return count;
    }
    public UserBo findById(String id){
        UserBo bo=null;
        try {
            Map<String,Object> queryMap=new HashMap<String,Object>();
            queryMap.put("id", id);
            bo = (UserBo) this.query(FINDBYID, queryMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bo;
    }
}
