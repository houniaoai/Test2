package com.haier.smarthome.devicecontrol.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.haier.smarthome.BaseActivity;
import com.haier.smarthome.R;
import com.haier.smarthome.devicecontrol.adapter.AttributesAdapter;
import com.haier.smarthomesdk.SmartHomeSdk;
import com.haier.smarthomesdk.devicecontrol.sevice.model.CommandBodyBean;
import com.haier.smarthomesdk.devicecontrol.sevice.model.ControlResultBean;
import com.haier.smarthomesdk.devicecontrol.sevice.model.DeviceAttributesBean;
import com.haier.smarthomesdk.devicecontrol.viewmodel.DeviceAttributesViewModel;
import com.haier.smarthomesdk.devicecontrol.viewmodel.DeviceControlViewModel;
import com.haier.smarthomesdk.devicelist.service.model.DeviceListEntryBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lp. 2018/8/23
 * Update:
 */

public class DeviceControlActivity extends BaseActivity implements View.OnClickListener {

    public static final String DEVICEINFO = "deviceInfo";
    private static final String LOG_TAG = "DeviceControlActivity";
    private TextView tv_info;
    private DeviceListEntryBean deviceInfo;
    private RecyclerView lv_attr;
    private AttributesAdapter attributesAdapter;
    private TextView tv_range, et_temperature, et_pod_count, tv_pod_range;
    private LinearLayout fridge_hot_water, ice_mark_state, washer_start_stop, dishwasher_pod_counts,
            washer_sabbath_mode;
    private Button hot_water_send, ice_mark_on, ice_mark_off, clothes_washer_start,
            clothes_washer_stop, pod_count_send, washer_sabbath_on, washer_sabbath_off;
    private List<DeviceAttributesBean.Items> deviceAttributesList = new ArrayList<>();
    private DeviceAttributesViewModel deviceAttributesViewModel;
    private DeviceControlViewModel deviceControlViewModel;
    boolean isFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refrigerator);
        tv_info = (TextView) findViewById(R.id.tv_info);
        lv_attr = (RecyclerView) findViewById(R.id.lv_attr);
        ScrollView sv_root = (ScrollView) findViewById(R.id.sv_root);
        sv_root.smoothScrollTo(0, 0);

        fridge_hot_water = (LinearLayout) findViewById(R.id.fridge_hot_water);
        tv_range = (TextView) findViewById(R.id.tv_range);
        et_temperature = (TextView) findViewById(R.id.et_temperature);
        hot_water_send = (Button) findViewById(R.id.hot_water_send);
        hot_water_send.setOnClickListener(this);

        ice_mark_state = (LinearLayout) findViewById(R.id.ice_mark_state);
        ice_mark_on = (Button) findViewById(R.id.ice_mark_on);
        ice_mark_off = (Button) findViewById(R.id.ice_mark_off);

        washer_start_stop = (LinearLayout) findViewById(R.id.washer_start_stop);
        clothes_washer_start = (Button) findViewById(R.id.clothes_washer_start);
        clothes_washer_stop = (Button) findViewById(R.id.clothes_washer_stop);
        clothes_washer_start.setOnClickListener(this);
        clothes_washer_stop.setOnClickListener(this);

        dishwasher_pod_counts = (LinearLayout) findViewById(R.id.dishwasher_pod_counts);
        et_pod_count = (TextView) findViewById(R.id.et_pod_count);
        tv_pod_range = (TextView) findViewById(R.id.tv_pod_range);
        pod_count_send = (Button) findViewById(R.id.pod_count_send);
        pod_count_send.setOnClickListener(this);

        washer_sabbath_mode = (LinearLayout) findViewById(R.id.washer_sabbath_mode);
        washer_sabbath_on = (Button) findViewById(R.id.washer_sabbath_on);
        washer_sabbath_off = (Button) findViewById(R.id.washer_sabbath_off);
        washer_sabbath_on.setOnClickListener(this);
        washer_sabbath_off.setOnClickListener(this);

        deviceInfo = (DeviceListEntryBean) getIntent().getSerializableExtra(DEVICEINFO);
        if (deviceInfo == null) {
            finish();
        }

        tv_info.setText("type : " + deviceInfo.type + "\nonline : " + deviceInfo.online + "\nkind : "
                + deviceInfo.kind + "\nuserId : " + deviceInfo.userId + "\napplianceId : " + deviceInfo.applianceId
                + "\nnickname : " + deviceInfo.nickname + "\nmodel : " + deviceInfo.model + "\nserial : "
                + deviceInfo.serial + "\njid : " + deviceInfo.jid);

        observeDeviceAttributes();
        observeDeviceControl();
    }

    public void observeDeviceAttributes() {
        attributesAdapter = new AttributesAdapter(R.layout.item_attribute, null);
        lv_attr.setLayoutManager(new LinearLayoutManager(this));
        lv_attr.setAdapter(attributesAdapter);

        DeviceAttributesViewModel.Factory factory = new DeviceAttributesViewModel.Factory(getApplication(), deviceInfo.applianceId);
        deviceAttributesViewModel = ViewModelProviders.of(this, factory).get(DeviceAttributesViewModel.class);
        deviceAttributesViewModel.getObservable().observe(this, new Observer<DeviceAttributesBean>() {
            @Override
            public void onChanged(@Nullable DeviceAttributesBean deviceAttributesBean) {
                dismissDialog();
                if (deviceAttributesBean != null) {
                    attributesAdapter.replaceData(deviceAttributesBean.items);
                    refreshAttributeUI(deviceAttributesBean);
                }
            }
        });
        showDialog();
        deviceAttributesViewModel.refresh();
    }

    public void observeDeviceControl() {
        DeviceControlViewModel.Factory factory = new DeviceControlViewModel.Factory(getApplication(), deviceInfo.applianceId, deviceInfo.userId);
        deviceControlViewModel = ViewModelProviders.of(this, factory).get(DeviceControlViewModel.class);
        deviceControlViewModel.getObservable().observe(this, new Observer<ControlResultBean>() {
            @Override
            public void onChanged(@Nullable ControlResultBean controlResultBean) {
                if (isFirst) {
                    dismissDialog();
                    if (controlResultBean != null) {
                        Toast.makeText(DeviceControlActivity.this, controlResultBean.status, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DeviceControlActivity.this, "control appliance failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    private void refreshAttributeUI(DeviceAttributesBean attributes) {
        boolean showHotWaterUi = false;
        boolean showIcemakerUi = false;
        boolean showStartClothWasher = false;
        boolean showPodCountControl = false;
        boolean showSabbathMode = false;
        for (DeviceAttributesBean.Items item : attributes.items) {
            if ("refrigerator-hot-water-brew-module-status".equals(item.name)) {
                showHotWaterUi = true;
                fridge_hot_water.setVisibility(View.VISIBLE);
                switch (item.numberValue) {
                    case "0":
                    case "1":
                        tv_range.setText("number range 90-190");
                        break;
                    case "2":
                    case "3":
                        tv_range.setText("can only enter number 190");
                        break;
                    case "255":
                        tv_range.setText("number range 90-185");
                        break;
                }

            }

            if ("refrigerator-icemaker-control-fz".equals(item.name)) {
                showIcemakerUi = true;
                ice_mark_state.setVisibility(View.VISIBLE);
                ice_mark_on.setText("ice-maker-freezer on");
                ice_mark_off.setText("ice-maker-freezer off");
            }

            if ("refrigerator-icemaker-control-ff".equals(item.name)) {
                showIcemakerUi = true;
                ice_mark_state.setVisibility(View.VISIBLE);
                ice_mark_on.setText("ice-maker-fresh-food on");
                ice_mark_off.setText("ice-maker-fresh-food off");
            }

            if ("laundry-remote-status".equals(item.name)) {
                showStartClothWasher = true;
                //washer_start_stop.setVisibility(View.VISIBLE);
            }

            if ("dishwasher-pods-count".equals(item.name)) {
                showPodCountControl = true;
                dishwasher_pod_counts.setVisibility(View.VISIBLE);
                tv_pod_range.setText("number range 0-999");
            }

            if ("dishwasher-sabbath-mode".equals(item.name)) {
                showSabbathMode = true;
                washer_sabbath_mode.setVisibility(View.VISIBLE);
            }
        }

        if (!showHotWaterUi) {
            fridge_hot_water.setVisibility(View.GONE);
        }
        if (!showIcemakerUi) {
            ice_mark_state.setVisibility(View.GONE);
        }

        if (!showStartClothWasher) {
            washer_start_stop.setVisibility(View.GONE);
        }

        if (!showPodCountControl) {
            dishwasher_pod_counts.setVisibility(View.GONE);
        }

        if (!showSabbathMode) {
            washer_sabbath_mode.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View view) {
        isFirst = true;
        switch (view.getId()) {
            case R.id.hot_water_send:
                String temperature = et_temperature.getText().toString().trim();
                if (TextUtils.isEmpty(temperature) || !isNumeric(temperature)) {
                    Toast.makeText(DeviceControlActivity.this, "input illegal", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (deviceInfo != null) {
                    List<CommandBodyBean.DeviceProperty> properties = new ArrayList<>();
                    CommandBodyBean.DeviceProperty property1 = new CommandBodyBean.DeviceProperty();
                    property1.name = "temperature-units";
                    property1.value = "fahrenheit";
                    CommandBodyBean.DeviceProperty property2 = new CommandBodyBean.DeviceProperty();
                    property2.name = "temperature-hot-water";
                    property2.value = temperature;
                    properties.add(property1);
                    properties.add(property2);
                    showDialog();
                    deviceControlViewModel.refresh("refrigerator-start-hot-water", properties);
                }
                break;

            case R.id.clothes_washer_start:
                if (deviceInfo != null) {
                    showDialog();
                    deviceControlViewModel.refresh("washer-start-cycler", null);
                }
                break;
            case R.id.clothes_washer_stop:
                if (deviceInfo != null) {
                    showDialog();
                    deviceControlViewModel.refresh("washer-stop-cycle", null);
                }
                break;

            case R.id.pod_count_send:
                String podCount = et_pod_count.getText().toString().trim();
                if (TextUtils.isEmpty(podCount) || !isNumeric(podCount)) {
                    Toast.makeText(DeviceControlActivity.this, "input illegal", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (deviceInfo != null) {
                    List<CommandBodyBean.DeviceProperty> properties = new ArrayList<>();
                    CommandBodyBean.DeviceProperty property1 = new CommandBodyBean.DeviceProperty();
                    property1.name = "operation";
                    property1.value = "set";
                    CommandBodyBean.DeviceProperty property2 = new CommandBodyBean.DeviceProperty();
                    property2.name = "pods-count";
                    property2.value = podCount;

                    properties.add(property1);
                    properties.add(property2);
                    showDialog();
                    deviceControlViewModel.refresh("dishwasher-set-pods", properties);
                }
                break;

            case R.id.washer_sabbath_on:
                if (deviceInfo != null) {
                    List<CommandBodyBean.DeviceProperty> properties = new ArrayList<>();
                    CommandBodyBean.DeviceProperty property1 = new CommandBodyBean.DeviceProperty();
                    property1.name = "status";
                    property1.value = "on";
                    properties.add(property1);
                    showDialog();
                    deviceControlViewModel.refresh("ddishwasher-set-sabbath-mode", properties);
                }
                break;
            case R.id.washer_sabbath_off:
                if (deviceInfo != null) {
                    List<CommandBodyBean.DeviceProperty> properties = new ArrayList<>();
                    CommandBodyBean.DeviceProperty property1 = new CommandBodyBean.DeviceProperty();
                    property1.name = "status";
                    property1.value = "off";
                    properties.add(property1);
                    showDialog();
                    deviceControlViewModel.refresh("dishwasher-set-sabbath-mode", properties);
                }

                break;
        }
    }

    private boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SmartHomeSdk.getInstance().cancelRequest();
    }
}