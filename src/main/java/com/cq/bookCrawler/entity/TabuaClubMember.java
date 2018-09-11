package com.cq.bookCrawler.entity;

import java.util.Date;

/**
 * @author: Celine Q
 * @create: 2018-09-11 21:49
 **/
public class TabuaClubMember {

    private String TBCard;

    private String passpor;

    private String name;

    private Date expiryDate;

    private String statusCredit;

    private String upgradeCredit;

    private String email;


    public String getPasspor() {
        return passpor;
    }

    public void setPasspor(String passpor) {
        this.passpor = passpor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTBCard() {
        return TBCard;
    }

    public void setTBCard(String TBCard) {
        this.TBCard = TBCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getStatusCredit() {
        return statusCredit;
    }

    public void setStatusCredit(String statusCredit) {
        this.statusCredit = statusCredit;
    }

    public String getUpgradeCredit() {
        return upgradeCredit;
    }

    public void setUpgradeCredit(String upgradeCredit) {
        this.upgradeCredit = upgradeCredit;
    }
}

