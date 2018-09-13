package com.cq.bookCrawler;

import com.cq.bookCrawler.entity.TabuaClubMember;
import com.cq.bookCrawler.repository.TabuaClubMemberRespository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: Celine Q
 * @create: 2018-09-13 19:58
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private TabuaClubMemberRespository tcmRespository;


    @Test
    public void test() throws Exception {
        tcmRespository.save(new TabuaClubMember("KTVHGR","tchyj123","HE/YONGJU"));
        tcmRespository.save(new TabuaClubMember("DCRHBQ","djstb123","DU/JINSHENG MR"));
    }

}

