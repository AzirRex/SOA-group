package com.example.applyprovide.service.implement;

import com.example.applyprovide.dao.LostApplyDao;
import com.example.applyprovide.entities.LostApply;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LostApplyServiceImp implements LostApplyDao {
    @Resource
    LostApplyDao lostApplyDao;
    @Override
    public boolean addLostApply(String name, String e_mail, String QQ, String phone, Integer user_id, String message,Integer getter_id,Integer lost_ID) {
        return lostApplyDao.addLostApply(name, e_mail, QQ, phone, user_id, message,getter_id,lost_ID);
    }

    @Override
    public List<LostApply> getAppById(Integer user_id) {
        return lostApplyDao.getAppById(user_id);
    }

    @Override
    public List<LostApply> getAppByGetId(Integer getter_id) {
        return lostApplyDao.getAppByGetId(getter_id);
    }

    @Override
    public boolean DeleteByLostId(Integer lost_ID) {
        return lostApplyDao.DeleteByLostId(lost_ID);
    }

    @Override
    public boolean DeleteByBpplyId(Integer apply_id) {
        return lostApplyDao.DeleteByBpplyId(apply_id);
    }

}
