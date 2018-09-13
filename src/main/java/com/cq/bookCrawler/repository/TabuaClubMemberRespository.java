package com.cq.bookCrawler.repository;

import com.cq.bookCrawler.entity.TabuaClubMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author: Celine Q
 * @create: 2018-09-13 19:22
 **/

public interface TabuaClubMemberRespository extends JpaRepository<TabuaClubMember,Long> {

    @Query("from TabuaClubMember  u where u.cardNumber=:cardNumber")
    TabuaClubMember findbycardNumber(String cardNumber);

}

