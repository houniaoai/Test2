package com.haier.smarthomesdk.http;

import com.haier.smarthomesdk.devicelist.service.model.DeviceListBean;
import com.haier.smarthomesdk.devicelist.service.model.DeviceListEntryBean;
import com.haier.smarthomesdk.devicecontrol.sevice.model.ControlResultBean;
import com.haier.smarthomesdk.devicecontrol.sevice.model.DeviceAttributesBean;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface RetrofitHttpApi {

    /***
     * Get device list
     */
    @GET(ApiUrl.GET_APP_APPLIANCE)
    Observable<DeviceListBean> getDeviceList();

    /***
     * Determine whether the device is online.
     */
    @GET(ApiUrl.GET_APP_APPLIANCE + "/{applianceId}")
    Observable<DeviceListEntryBean> getDeviceListEntry(@Path("applianceId") String applianceId);

    /**
     * Get a device information
     *
     * @param applianceId
     * @return
     */
    @GET(ApiUrl.GET_APP_APPLIANCE + "/{applianceId}")
    Observable<DeviceListEntryBean> getDeviceInfo(
            @Path("applianceId") String applianceId
    );

    /**
     * Read all attributes of a device
     *
     * @param applianceId
     * @return
     */
    @GET(ApiUrl.GET_APP_APPLIANCE + "/{applianceId}/result")
    Observable<DeviceAttributesBean> getDeviceAttributes(
            @Path("applianceId") String applianceId
    );

    /**
     * Control an attribute of a device.
     *
     * @param applianceId
     * @param command
     * @param info
     * @return
     */
    @POST(ApiUrl.GET_APP_APPLIANCE + "/{applianceId}/control/{command}")
    Observable<ControlResultBean> sendCommend(
            @Path("applianceId") String applianceId,
            @Path("command") String command,
            @Body RequestBody info
    );

}
