package com.haier.smarthome.devicelist.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haier.smarthome.R;
import com.haier.smarthomesdk.devicelist.service.model.DeviceListBean;

import java.util.List;

/**
 * Created by 01506318 on 2018/7/21.
 */
public class DeviceListAdapter extends BaseQuickAdapter<DeviceListBean.ItemsBean, BaseViewHolder> {

    public DeviceListAdapter(@LayoutRes int layoutResId, @Nullable List<DeviceListBean.ItemsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeviceListBean.ItemsBean bean) {
        if (!TextUtils.isEmpty(bean.nickname)) {
            helper.setText(R.id.tv_name, bean.type);
        }
    }
}