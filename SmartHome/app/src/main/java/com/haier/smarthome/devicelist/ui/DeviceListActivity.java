package com.haier.smarthome.devicelist.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.haier.smarthome.BaseActivity;
import com.haier.smarthome.R;
import com.haier.smarthome.devicecontrol.ui.DeviceControlActivity;
import com.haier.smarthomesdk.SmartHomeSdk;
import com.haier.smarthomesdk.devicelist.service.model.DeviceListBean;
import com.haier.smarthomesdk.devicelist.service.model.DeviceListEntryBean;
import com.haier.smarthome.devicelist.adapter.DeviceListAdapter;
import com.haier.smarthomesdk.devicelist.viewmodel.DeviceListViewModel;
import com.haier.smarthomesdk.devicelist.viewmodel.JudgeOnlineViewModel;
import com.haier.smarthomesdk.login.LoginCallBack;
import com.haier.smarthomesdk.login.LoginUtils;
import com.haier.smarthomesdk.login.RetrieveNewAccessTokenCallBack;
import com.haier.smarthomesdk.utils.LogUtil;
import com.haier.smarthomesdk.login.bean.AccessTokenBean;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DeviceListActivity extends BaseActivity {

    RecyclerView rvRecycle;
    DeviceListAdapter myRecycleAdapter;
    JudgeOnlineViewModel judgeOnlineViewModel;
    DeviceListViewModel deviceListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_list);
        initView();

        observeDeviceListViewModel();
        observeJudgeOnlineViewModel();

        initMdt();
    }

    private void initMdt() {
        SmartHomeSdk.getInstance().getMDT(this, new LoginCallBack() {
            @Override
            public void onAddAccountFailed(String errMsg, boolean isUserManuallyCancel) {
                finish();
            }

            @Override
            public void onAddAccountSucceed(String mdt) {

                showDialog();
                deviceListViewModel.refresh();
            }

            @Override
            public void onUserCancelLogin() {
                finish();
            }

            @Override
            public void onUserSkipLogin() {
                finish();
            }

            @Override
            public void onLoginAppNotInstall() {
                finish();

            }
        });
    }

    private void initView() {
        rvRecycle = findViewById(R.id.rv_recycle);
        rvRecycle.setLayoutManager(new LinearLayoutManager(this));
        myRecycleAdapter = new DeviceListAdapter(R.layout.adapter_device_list, null);
        rvRecycle.setAdapter(myRecycleAdapter);

        myRecycleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DeviceListBean.ItemsBean bean = (DeviceListBean.ItemsBean) adapter.getItem(position);
                if (!TextUtils.isEmpty(bean.applianceId)) {
                    showDialog();
                    judgeOnlineViewModel.refresh(bean.applianceId);
                }
            }
        });
    }

    private void observeDeviceListViewModel() {
        showDialog();
        deviceListViewModel = ViewModelProviders.of(this).get(DeviceListViewModel.class);
        deviceListViewModel.getObservable().observe(this, new Observer<DeviceListBean>() {
            @Override
            public void onChanged(@Nullable DeviceListBean deviceListBean) {
                dismissDialog();
                if (deviceListBean != null && !deviceListBean.items.isEmpty()) {
                    myRecycleAdapter.replaceData(deviceListBean.items);
                }
            }
        });
    }

    private void observeJudgeOnlineViewModel() {
        judgeOnlineViewModel = ViewModelProviders.of(this).get(JudgeOnlineViewModel.class);
        judgeOnlineViewModel.getObservable().observe(this, new Observer<DeviceListEntryBean>() {
            @Override
            public void onChanged(@Nullable DeviceListEntryBean applianceListEntryBean) {
                dismissDialog();
                if (applianceListEntryBean != null && !TextUtils.isEmpty(applianceListEntryBean.online)) {
                    if (applianceListEntryBean.online.equals("ONLINE")) {
                        jump2DeviceActivity(applianceListEntryBean);
                    } else {
                        Toast.makeText(DeviceListActivity.this, getResources().getString(R.string.offline), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void jump2DeviceActivity(DeviceListEntryBean bean) {
        Intent intent1 = new Intent(DeviceListActivity.this, DeviceControlActivity.class);
        intent1.putExtra(DeviceControlActivity.DEVICEINFO, bean);
        startActivity(intent1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SmartHomeSdk.getInstance().cancelRequest();
    }
}
