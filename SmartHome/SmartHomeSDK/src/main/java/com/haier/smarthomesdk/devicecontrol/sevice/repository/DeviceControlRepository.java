package com.haier.smarthomesdk.devicecontrol.sevice.repository;

import com.google.gson.Gson;

import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;

import com.haier.smarthomesdk.devicecontrol.sevice.model.CommandBodyBean;
import com.haier.smarthomesdk.devicecontrol.sevice.model.ControlResultBean;
import com.haier.smarthomesdk.devicecontrol.sevice.model.DeviceAttributesBean;
import com.haier.smarthomesdk.http.RetrofitBaseInfo;
import com.haier.smarthomesdk.http.RxApiManager;
import com.haier.smarthomesdk.utils.LogUtil;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DeviceControlRepository {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static DeviceControlRepository deviceListRepository;
    private static final String REFRESH_CONTROL_DEVICE = "refresh_control_device";
    private static final String REFRESH_DEVICE_ATTRIBUTES = "refresh_device_attributes";

    public synchronized static DeviceControlRepository getInstance() {
        if (deviceListRepository == null) {
            deviceListRepository = new DeviceControlRepository();
        }
        return deviceListRepository;
    }

    public MutableLiveData<DeviceAttributesBean> getDeviceAttributes() {
        MutableLiveData<DeviceAttributesBean> DeviceAttributesData = new MutableLiveData<>();
        return DeviceAttributesData;
    }

    public MutableLiveData<ControlResultBean> getControlDevice() {
        MutableLiveData<ControlResultBean> DeviceControlData = new MutableLiveData<>();
        return DeviceControlData;
    }

    public void refreshControlDevice(final MutableLiveData<ControlResultBean> data, final String applianceId, final String userId, final String command, final List<CommandBodyBean.DeviceProperty> properties) {
        CommandBodyBean commandBody = new CommandBodyBean();
        commandBody.kind = "appliance#control";
        commandBody.userId = userId;
        commandBody.applianceId = applianceId;
        commandBody.command = command;
        commandBody.data = properties;
        String bodyStr = new Gson().toJson(commandBody);
        RequestBody body = RequestBody.create(JSON, bodyStr);

        Subscription subscribe = RetrofitBaseInfo.getApi().sendCommend(applianceId, command, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ControlResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        String message = e.getMessage();
                        if (!TextUtils.isEmpty(message) && message.contains("Unauthorized")) {
                            refreshControlDevice(data, applianceId, userId, command, properties);
                            return;
                        }
                        data.setValue(null);
                    }

                    @Override
                    public void onNext(ControlResultBean controlResultBean) {
                        data.setValue(controlResultBean);
                    }
                });
        RxApiManager.get().add(REFRESH_CONTROL_DEVICE, subscribe);
    }

    public void refreshDeviceAttributes(final MutableLiveData<DeviceAttributesBean> data, final String applianceId) {

        Subscription subscribe = RetrofitBaseInfo.getApi().getDeviceAttributes(applianceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DeviceAttributesBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        String message = e.getMessage();
                        if (!TextUtils.isEmpty(message) && message.contains("Unauthorized")) {
                            refreshDeviceAttributes(data, applianceId);
                            return;
                        }
                        data.setValue(null);
                    }

                    @Override
                    public void onNext(DeviceAttributesBean deviceAttributesBean) {
                        data.setValue(deviceAttributesBean);
                    }
                });
        RxApiManager.get().add(REFRESH_DEVICE_ATTRIBUTES, subscribe);

    }

}
