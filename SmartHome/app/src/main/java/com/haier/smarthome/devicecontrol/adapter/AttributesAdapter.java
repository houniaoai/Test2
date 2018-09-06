package com.haier.smarthome.devicecontrol.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haier.smarthome.R;
import com.haier.smarthomesdk.devicecontrol.sevice.model.DeviceAttributesBean;

import java.util.List;

/**
 * Created by lp. 2018/8/13
 * Update:
 */

public class AttributesAdapter extends BaseQuickAdapter<DeviceAttributesBean.Items, BaseViewHolder> {

    public AttributesAdapter(int layoutResId, @Nullable List<DeviceAttributesBean.Items> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeviceAttributesBean.Items item) {

        if (!TextUtils.isEmpty(item.name)) {
            helper.setText(R.id.tv_name, item.name);
        }

        if (!TextUtils.isEmpty(item.numberValue)) {
            helper.setText(R.id.tv_number, item.numberValue);
        }

        if (!TextUtils.isEmpty(item.stringValue)) {
            helper.setText(R.id.tv_value, item.stringValue);
        }

        if (!TextUtils.isEmpty(item.time)) {
            helper.setText(R.id.tv_time, item.time);
        }
    }
}