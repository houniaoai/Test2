package com.haier.smarthomesdk.devicelist.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.haier.smarthomesdk.devicelist.service.model.DeviceListEntryBean;
import com.haier.smarthomesdk.devicelist.service.repository.DeviceListRepository;

public class JudgeOnlineViewModel extends AndroidViewModel {
    private MutableLiveData<DeviceListEntryBean> judgeOnlineObservable;
    public JudgeOnlineViewModel(@NonNull Application application) {
        super(application);
        judgeOnlineObservable = DeviceListRepository.getInstance().judgeOnLine();
    }

    public MutableLiveData<DeviceListEntryBean> getObservable() {
        return judgeOnlineObservable;
    }

    public void refresh(String applianceId) {
        DeviceListRepository.getInstance().refreshOnLine(judgeOnlineObservable, applianceId);
    }
}
