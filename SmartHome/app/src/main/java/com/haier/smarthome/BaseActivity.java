package com.haier.smarthome;

import android.app.Dialog;
import android.arch.lifecycle.LifecycleActivity;
import android.content.Intent;
import android.os.Bundle;

import com.haier.smarthome.widget.DialogUtils;

public class BaseActivity extends LifecycleActivity {

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = DialogUtils.createLoadingDialog(this, "Load...");
    }

    public void showDialog() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    public void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog = null;
        }
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.from_right_in, R.anim.to_left_out);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.from_left_in, R.anim.to_right_out);
    }

}
