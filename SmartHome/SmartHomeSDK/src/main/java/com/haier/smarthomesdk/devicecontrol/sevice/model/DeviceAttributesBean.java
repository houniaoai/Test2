package com.haier.smarthomesdk.devicecontrol.sevice.model;

import com.haier.smarthomesdk.RequestErrorBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lp. 2018/8/23
 * Update:
 */

public class DeviceAttributesBean implements Serializable {
    public String kind;
    public String userId;
    public String applianceId;
    public List<Items> items;

    @Override
    public String toString() {
        return "DeviceAttributesBean{" +
                "kind='" + kind + '\'' +
                ", userId='" + userId + '\'' +
                ", applianceId='" + applianceId + '\'' +
                ", items=" + items +
                '}';
    }

    public class Items implements Serializable{
        public String name;
        public String numberValue;
        public String stringValue;
        public String time;

        @Override
        public String toString() {
            return "Items{" +
                    "name='" + name + '\'' +
                    ", numberValue='" + numberValue + '\'' +
                    ", stringValue='" + stringValue + '\'' +
                    ", time='" + time + '\'' +
                    '}';
        }
    }
}
