package com.haier.smarthomesdk.devicecontrol.sevice.model;

import com.haier.smarthomesdk.RequestErrorBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lp. 2018/8/23
 * Update:
 *
 */

public class CommandBodyBean implements Serializable{

    public String kind;
    public String userId;
    public String applianceId;
    public String command;
    public List<DeviceProperty> data;

    public static class DeviceProperty {
        public String name;
        public String value;

        @Override
        public String toString() {
            return "DeviceProperty{" +
                    "name='" + name + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CommandBodyBean{" +
                "kind='" + kind + '\'' +
                ", userId='" + userId + '\'' +
                ", applianceId='" + applianceId + '\'' +
                ", command='" + command + '\'' +
                ", data=" + data +
                '}';
    }
}
