package com.cq.bookCrawler.service;

import com.cq.bookCrawler.entity.TabuaClubMember;
import com.cq.bookCrawler.repository.TabuaClubMemberRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Celine Q
 * @create: 2018-09-12 21:25
 **/
@Service
public class TabuaClubMemberServiceImpl implements TabuaClubMemberService {

    @Autowired
    private TabuaClubMemberRespository tabuaClubMemberRespository;

    @Override
    public TabuaClubMember findbycardNumber(String cardNumber) {
        return tabuaClubMemberRespository.findbycardNumber(cardNumber);
    }

    @Override
    public void saveOrUpdate(TabuaClubMember tabuaClubMember) {
        //更新
        if(tabuaClubMember.getCardNumber() !=null && tabuaClubMember.getPassword()!=null){
            TabuaClubMember tcm = tabuaClubMemberRespository.findbycardNumber(tabuaClubMember.getCardNumber());
            tcm.setName(tabuaClubMember.getName());
            tcm.setStatusCredit(tabuaClubMember.getStatusCredit());
            tcm.setUpgradeCredit(tabuaClubMember.getUpgradeCredit());
            tcm.setExpiryDate(tabuaClubMember.getExpiryDate());
            tcm.setEmail(tabuaClubMember.getEmail());
            tcm.setPassword(tcm.getPassword());
            tcm.setApplyDate(tcm.getApplyDate());
            tabuaClubMemberRespository.save(tcm);
        } else {
            tabuaClubMemberRespository.save(tabuaClubMember);
        }
    }

}

