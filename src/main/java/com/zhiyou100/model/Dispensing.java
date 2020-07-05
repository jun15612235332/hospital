package com.zhiyou100.model;

public class Dispensing {
    private String medicalRecord;

    private String drugNum;

    private Integer dispensNum;

    private Integer alreadSendNum;

    private Integer notSendNum;

    private String time;

    public String getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(String medicalRecord) {
        this.medicalRecord = medicalRecord == null ? null : medicalRecord.trim();
    }

    public String getDrugNum() {
        return drugNum;
    }

    public void setDrugNum(String drugNum) {
        this.drugNum = drugNum == null ? null : drugNum.trim();
    }

    public Integer getDispensNum() {
        return dispensNum;
    }

    public void setDispensNum(Integer dispensNum) {
        this.dispensNum = dispensNum;
    }

    public Integer getAlreadSendNum() {
        return alreadSendNum;
    }

    public void setAlreadSendNum(Integer alreadSendNum) {
        this.alreadSendNum = alreadSendNum;
    }

    public Integer getNotSendNum() {
        return notSendNum;
    }

    public void setNotSendNum(Integer notSendNum) {
        this.notSendNum = notSendNum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

	@Override
	public String toString() {
		return "Dispensing [medicalRecord=" + medicalRecord + ", drugNum=" + drugNum + ", dispensNum=" + dispensNum
				+ ", alreadSendNum=" + alreadSendNum + ", notSendNum=" + notSendNum + ", time=" + time + "]";
	}
    
}