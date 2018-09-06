package com.haier.smarthomesdk.devicecontrol.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.haier.smarthomesdk.devicecontrol.sevice.model.DeviceAttributesBean;
import com.haier.smarthomesdk.devicecontrol.sevice.repository.DeviceControlRepository;

public class DeviceAttributesViewModel extends AndroidViewModel {
    private MutableLiveData<DeviceAttributesBean> deviceAttributes;
    private String applianceId;

    public DeviceAttributesViewModel(@NonNull Application application, String applianceId) {
        super(application);
        this.applianceId = applianceId;
        deviceAttributes = DeviceControlRepository.getInstance().getDeviceAttributes();

    }

    public LiveData<DeviceAttributesBean> getObservable() {
        return deviceAttributes;
    }

    public void refresh() {
        DeviceControlRepository.getInstance().refreshDeviceAttributes(deviceAttributes, applianceId);
    }

    public static class Factory implements ViewModelProvider.Factory {
        @NonNull
        private final Application application;
        private final String applianceId;

        public Factory(@NonNull Application application, String applianceId) {
            this.application = application;
            this.applianceId = applianceId;
        }
        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new DeviceAttributesViewModel(application, applianceId);
        }
    }

}
