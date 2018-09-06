package com.haier.smarthomesdk.devicelist.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.haier.smarthomesdk.devicelist.service.model.DeviceListBean;
import com.haier.smarthomesdk.devicelist.service.repository.DeviceListRepository;

import java.util.List;

public class DeviceListViewModel extends AndroidViewModel {
    private MutableLiveData<DeviceListBean> deviceListObservable;

    public DeviceListViewModel(@NonNull Application application) {
        super(application);
        deviceListObservable = DeviceListRepository.getInstance().getDeviceList();
    }

    public LiveData<DeviceListBean> getObservable() {
        return deviceListObservable;
    }

    public void refresh() {
        DeviceListRepository.getInstance().refreshDeviceList(deviceListObservable);
    }
}
