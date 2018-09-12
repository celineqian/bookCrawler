package com.cq.bookCrawler.entity;

import java.util.Date;

/**
 * @author: Celine Q
 * @create: 2018-09-11 21:49
 **/
public class TabuaClubMember {

    private String TBCard;

    private String password;

    private String name;

    private Date applyDate;
    
    private Date expiryDate;

    private String statusCredit;

    private String upgradeCredit;

    private String email;

	public String getTBCard() {
		return TBCard;
	}

	public void setTBCard(String tBCard) {
		TBCard = tBCard;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}

