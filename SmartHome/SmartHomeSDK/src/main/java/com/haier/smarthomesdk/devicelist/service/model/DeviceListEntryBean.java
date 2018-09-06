package com.haier.smarthomesdk.devicelist.service.model;

import com.haier.smarthomesdk.RequestErrorBean;

import java.io.Serializable;

public class DeviceListEntryBean implements Serializable {

    /**
     * kind : appliance#applianceList
     * userId : abcdwefasdfsl
     * applianceId : asdfwerasfe
     * nickname : My Refrigerator
     * type : Refrigerator
     * model : MODELNUMBER
     * serial : SERIALNUMBER
     * online : ONLINE
     */

    public String kind;
    public String userId;
    public String applianceId;
    public String nickname;
    public String type;
    public String model;
    public String serial;
    public String online;
    public String jid;

    @Override
    public String toString() {
        return "DeviceListEntryBean{" +
                "kind='" + kind + '\'' +
                ", userId='" + userId + '\'' +
                ", applianceId='" + applianceId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", type='" + type + '\'' +
                ", model='" + model + '\'' +
                ", serial='" + serial + '\'' +
                ", online='" + online + '\'' +
                ", jid='" + jid + '\'' +
                '}';
    }

}
