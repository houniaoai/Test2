package com.haier.smarthomesdk.devicelist.service.model;

import com.haier.smarthomesdk.RequestErrorBean;

import java.io.Serializable;
import java.util.List;

public class DeviceListBean implements Serializable {


    /**
     * kind : appliance#applianceList
     * userId : abcdwefasdfsl
     * items : [{"applianceId":"D828C900000","type":"Refrigerator","nickname":"Refrigerator"},{"applianceId":"D28C900000","type":"DishWasher","nickname":"DishWasher"}]
     */

    public String kind;
    public String userId;
    public List<ItemsBean> items;

    @Override
    public String toString() {
        return "DeviceListBean{" +
                "kind='" + kind + '\'' +
                ", userId='" + userId + '\'' +
                ", items=" + items +
                '}';
    }


    public static class ItemsBean implements Serializable {
        /**
         * applianceId : D828C900000
         * type : Refrigerator
         * nickname : Refrigerator
         */

        public String applianceId;
        public String type;
        public String nickname;

        @Override
        public String toString() {
            return "ItemsBean{" +
                    "applianceId='" + applianceId + '\'' +
                    ", type='" + type + '\'' +
                    ", nickname='" + nickname + '\'' +
                    '}';
        }

    }
}
