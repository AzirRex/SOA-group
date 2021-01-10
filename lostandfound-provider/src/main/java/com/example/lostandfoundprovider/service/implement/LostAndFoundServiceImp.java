package com.example.lostandfoundprovider.service.implement;

import com.example.lostandfoundprovider.Entity.LostAndFound;
import com.example.lostandfoundprovider.dao.LostAndFoundDao;
import org.springframework.stereotype.Service;
import com.example.lostandfoundprovider.Entity.LostAndFound;
import com.example.lostandfoundprovider.service.*;
import javax.annotation.Resource;
import java.util.List;
@Service
public class LostAndFoundServiceImp implements LostAndFoundService{
    @Resource
    private LostAndFoundDao lostAndFoundDao;

    @Override
    public LostAndFound getLosById(Integer id) {
        return lostAndFoundDao.getLosById(id);
    }

    @Override
    public List<LostAndFound> getAll() {
        return lostAndFoundDao.getAll();
    }

    @Override
    public boolean addlost(Integer userId, String name, String location, String time, String information, Integer type) {
        return lostAndFoundDao.addlost(userId, name, location, time, information, type);
    }

    @Override
    public boolean DeleteByLostId(Integer lost_ID) {
        return lostAndFoundDao.DeleteByLostId(lost_ID);
    }
}
