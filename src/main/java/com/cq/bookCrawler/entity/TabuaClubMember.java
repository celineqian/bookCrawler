package com.cq.bookCrawler.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: Celine Q
 * @create: 2018-09-11 21:49
 **/
@Entity
@Table(name = "t_tabuaclubmember")
public class TabuaClubMember {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private String password;

    @Column
    private String name;

    @Column
    private Date applyDate;

    @Column
    private Date expiryDate;

    @Column
    private String statusCredit;

    @Column
    private String upgradeCredit;

    @Column
    private String email;

	public TabuaClubMember(String cardNumber, String password, String name) {
		this.cardNumber = cardNumber;
		this.password = password;
		this.name = name;
	}

	public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

