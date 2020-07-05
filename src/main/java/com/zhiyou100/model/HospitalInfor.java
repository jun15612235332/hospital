package com.zhiyou100.model;

import java.io.Serializable;

public class HospitalInfor implements Serializable{
    private String medicalRecord;

    private String caregiver;

    private String bedId;

    private Double payTheDeposit;

    private String stateIllness;
    
    private RegistrationInfor registrationInfor;
    
    private HospitalClear hospitalClear;
    
    private Section section;
    
    private Doctor doctor;
    
    public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public RegistrationInfor getRegistrationInfor() {
		return registrationInfor;
	}

	public void setRegistrationInfor(RegistrationInfor registrationInfor) {
		this.registrationInfor = registrationInfor;
	}

	public HospitalClear getHospitalClear() {
		return hospitalClear;
	}

	public void setHospitalClear(HospitalClear hospitalClear) {
		this.hospitalClear = hospitalClear;
	}

	public String getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(String medicalRecord) {
        this.medicalRecord = medicalRecord == null ? null : medicalRecord.trim();
    }

    public String getCaregiver() {
        return caregiver;
    }

    public void setCaregiver(String caregiver) {
        this.caregiver = caregiver == null ? null : caregiver.trim();
    }

    public String getBedId() {
        return bedId;
    }

    public void setBedId(String bedId) {
        this.bedId = bedId == null ? null : bedId.trim();
    }

    public Double getPayTheDeposit() {
        return payTheDeposit;
    }

    public void setPayTheDeposit(Double payTheDeposit) {
        this.payTheDeposit = payTheDeposit;
    }

    public String getStateIllness() {
        return stateIllness;
    }

    public void setStateIllness(String stateIllness) {
        this.stateIllness = stateIllness == null ? null : stateIllness.trim();
    }

	@Override
	public String toString() {
		return "HospitalInfor [medicalRecord=" + medicalRecord + ", caregiver=" + caregiver + ", bedId=" + bedId
				+ ", payTheDeposit=" + payTheDeposit + ", stateIllness=" + stateIllness + ", registrationInfor="
				+ registrationInfor + ", hospitalClear=" + hospitalClear + ", section=" + section + ", doctor=" + doctor
				+ "]";
	}
    
}