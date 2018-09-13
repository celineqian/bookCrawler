package com.cq.bookCrawler.service;

import com.cq.bookCrawler.entity.TabuaClubMember;

/**
 * @author: Celine Q
 * @create: 2018-09-12 21:23
 **/
public interface TabuaClubMemberService {

     TabuaClubMember findbycardNumber(String cardNumber);

     void saveOrUpdate(TabuaClubMember tabuaClubMember);
}
