package com.haier.smarthomesdk.devicelist.service.repository;

import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;

import com.haier.smarthomesdk.devicelist.service.model.DeviceListBean;
import com.haier.smarthomesdk.devicelist.service.model.DeviceListEntryBean;
import com.haier.smarthomesdk.http.RetrofitBaseInfo;
import com.haier.smarthomesdk.http.RxApiManager;
import com.haier.smarthomesdk.login.LoginUtils;
import com.haier.smarthomesdk.utils.LogUtil;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DeviceListRepository {

    private static DeviceListRepository deviceListRepository;
    private static final String REFRESH_DEVICE_LIST = "refresh_device_list";
    private static final String REFRESH_ONLINE = "refresh_online";

    public synchronized static DeviceListRepository getInstance() {
        if (deviceListRepository == null) {
            deviceListRepository = new DeviceListRepository();
        }
        return deviceListRepository;
    }

    public MutableLiveData<DeviceListBean> getDeviceList() {
        return new MutableLiveData<>();
    }

    public MutableLiveData<DeviceListEntryBean> judgeOnLine() {
        return new MutableLiveData<>();
    }

    public void refreshDeviceList(final MutableLiveData<DeviceListBean> data) {

        Subscription subscribe = RetrofitBaseInfo.getApi().getDeviceList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DeviceListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        String message = e.getMessage();
                        if (!TextUtils.isEmpty(message) && message.contains("Unauthorized")) {
                            refreshDeviceList(data);
                            return;
                        }
                        data.setValue(null);
                    }

                    @Override
                    public void onNext(DeviceListBean deviceListBean) {
                        data.setValue(deviceListBean);
                    }
                });
        RxApiManager.get().add(REFRESH_DEVICE_LIST, subscribe);
    }

    public void refreshOnLine(final MutableLiveData<DeviceListEntryBean> data, final String applianceId) {

        Subscription subscribe = RetrofitBaseInfo.getApi().getDeviceListEntry(applianceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DeviceListEntryBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        String message = e.getMessage();
                        if (!TextUtils.isEmpty(message) && message.contains("Unauthorized")) {
                            refreshOnLine(data, applianceId);
                            return;
                        }
                        data.setValue(null);
                    }

                    @Override
                    public void onNext(DeviceListEntryBean deviceListEntryBean) {
                        data.setValue(deviceListEntryBean);
                    }
                });
        RxApiManager.get().add(REFRESH_ONLINE, subscribe);
    }

}
