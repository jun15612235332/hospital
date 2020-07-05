package com.zhiyou100.model;

import java.io.Serializable;

public class ChargeManager implements Serializable{
    private Integer id;

    private String medicalRecord;

    private Integer payItemsId;

    private Double chargeMoney;

    private String chargeTime;
    
    private RegistrationInfor registrationInfor;
    
    private PayItems payItems;

    public RegistrationInfor getRegistrationInfor() {
		return registrationInfor;
	}

	public void setRegistrationInfor(RegistrationInfor registrationInfor) {
		this.registrationInfor = registrationInfor;
	}

	public PayItems getPayItems() {
		return payItems;
	}

	public void setPayItems(PayItems payItems) {
		this.payItems = payItems;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(String medicalRecord) {
        this.medicalRecord = medicalRecord == null ? null : medicalRecord.trim();
    }

    public Integer getPayItemsId() {
        return payItemsId;
    }

    public void setPayItemsId(Integer payItemsId) {
        this.payItemsId = payItemsId;
    }

    public Double getChargeMoney() {
        return chargeMoney;
    }

    public void setChargeMoney(Double chargeMoney) {
        this.chargeMoney = chargeMoney;
    }

    public String getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(String chargeTime) {
        this.chargeTime = chargeTime == null ? null : chargeTime.trim();
    }

	@Override
	public String toString() {
		return "ChargeManager [id=" + id + ", medicalRecord=" + medicalRecord + ", payItemsId=" + payItemsId
				+ ", chargeMoney=" + chargeMoney + ", chargeTime=" + chargeTime + ", registrationInfor="
				+ registrationInfor + ", payItems=" + payItems + "]";
	}
    
}