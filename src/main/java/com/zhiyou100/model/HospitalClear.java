package com.zhiyou100.model;

import java.io.Serializable;

public class HospitalClear implements Serializable{
    private Integer id;

    private String medicalRecord;

    private String clearStatus;

    private Double totalCost;

    private String cashPledge;

    private Double stillNeedPay;

    private Double balance;

    private String clearTime;

    private Double medicalCosts;

    private Double drugCost;

    private Double amountPaid;

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

    public String getClearStatus() {
        return clearStatus;
    }

    public void setClearStatus(String clearStatus) {
        this.clearStatus = clearStatus == null ? null : clearStatus.trim();
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public String getCashPledge() {
        return cashPledge;
    }

    public void setCashPledge(String cashPledge) {
        this.cashPledge = cashPledge == null ? null : cashPledge.trim();
    }

    public Double getStillNeedPay() {
        return stillNeedPay;
    }

    public void setStillNeedPay(Double stillNeedPay) {
        this.stillNeedPay = stillNeedPay;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getClearTime() {
        return clearTime;
    }

    public void setClearTime(String clearTime) {
        this.clearTime = clearTime == null ? null : clearTime.trim();
    }

    public Double getMedicalCosts() {
        return medicalCosts;
    }

    public void setMedicalCosts(Double medicalCosts) {
        this.medicalCosts = medicalCosts;
    }

    public Double getDrugCost() {
        return drugCost;
    }

    public void setDrugCost(Double drugCost) {
        this.drugCost = drugCost;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

	@Override
	public String toString() {
		return "HospitalClear [id=" + id + ", medicalRecord=" + medicalRecord + ", clearStatus=" + clearStatus
				+ ", totalCost=" + totalCost + ", cashPledge=" + cashPledge + ", stillNeedPay=" + stillNeedPay
				+ ", balance=" + balance + ", clearTime=" + clearTime + ", medicalCosts=" + medicalCosts + ", drugCost="
				+ drugCost + ", amountPaid=" + amountPaid + "]";
	}
    
}