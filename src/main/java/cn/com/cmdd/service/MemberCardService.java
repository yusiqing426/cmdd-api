package cn.com.cmdd.service;

import cn.com.cmdd.dao.MemberCardDao;
import cn.com.cmdd.domain.MemberCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberCardService {

    @Autowired
    private MemberCardDao memberCardDao;

    @Transactional
    public Long insert(MemberCard memberCard){
       // memberCard.setSync_status(1);
        memberCardDao.insert(memberCard);
        return memberCard.getId();
    }

    @Transactional
    public Long update(MemberCard memberCard){

        //if(!memberCard.getSync_status().equals(new Integer(0)))memberCard.setSync_status(1);
        memberCardDao.update(memberCard);
        return memberCard.getId();
    }
}
