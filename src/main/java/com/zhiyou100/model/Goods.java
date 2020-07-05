package com.zhiyou100.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Goods {
    private  int gId;
    private String gName;
    private String gDesc;
    private String gImgurl;

    @Override
    public String toString() {
        return "Goods{" +
                "gId=" + gId +
                ", gName='" + gName + '\'' +
                ", gDesc='" + gDesc + '\'' +
                ", gImgurl='" + gImgurl + '\'' +
                '}';
    }

    public Goods() {
    }

    public Goods(int gId, String gName, String gDesc, String gImgurl) {
        this.gId = gId;
        this.gName = gName;
        this.gDesc = gDesc;
        this.gImgurl = gImgurl;
    }
}
