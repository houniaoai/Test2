package com.haier.smarthome.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haier.smarthome.R;

public class DialogUtils {

    public static Dialog createLoadingDialog(final Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_new, null);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);
        tipTextView.setText(msg);

        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);
        loadingDialog.setCancelable(false);
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        loadingDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    dialog.dismiss();
                }
                return false;
            }
        });
        return loadingDialog;
    }

}
