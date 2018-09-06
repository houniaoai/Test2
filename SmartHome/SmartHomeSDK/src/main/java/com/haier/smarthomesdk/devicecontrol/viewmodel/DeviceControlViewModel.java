package com.haier.smarthomesdk.devicecontrol.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.haier.smarthomesdk.devicecontrol.sevice.model.CommandBodyBean;
import com.haier.smarthomesdk.devicecontrol.sevice.model.ControlResultBean;
import com.haier.smarthomesdk.devicecontrol.sevice.repository.DeviceControlRepository;

import java.util.List;


public class DeviceControlViewModel extends AndroidViewModel {
    private MutableLiveData<ControlResultBean> controlResult;
    private String userId;
    private String applianceId;

    public DeviceControlViewModel(@NonNull Application application, String applianceId, String userId) {
        super(application);
        this.userId = userId;
        this.applianceId = applianceId;
        controlResult = DeviceControlRepository.getInstance().getControlDevice();
    }

    public MutableLiveData<ControlResultBean> getObservable() {
        return controlResult;
    }


    public void refresh(String command, List<CommandBodyBean.DeviceProperty> properties) {
        DeviceControlRepository.getInstance().refreshControlDevice(controlResult, applianceId, userId, command, properties);
    }

    public static class Factory implements ViewModelProvider.Factory {
        @NonNull
        private final Application application;
        private String applianceId;
        private String userId;

        public Factory(@NonNull Application application, String applianceId, String userId) {
            this.application = application;
            this.applianceId = applianceId;
            this.userId = userId;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new DeviceControlViewModel(application, applianceId, userId);
        }
    }

}
