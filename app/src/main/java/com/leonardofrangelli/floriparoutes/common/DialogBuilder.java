package com.leonardofrangelli.floriparoutes.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;

/**
 * Created by frangelli on 9/8/14.
 */
public class DialogBuilder {

    private static  ProgressDialog PROGRESS_DIALOG = null;

    public static Dialog createMessageDialog(String message, String title, Activity activity) {
        Dialog dialog = null;

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(message);
        builder.setTitle((title != null && !title.isEmpty()) ? title : "Alert!");
        dialog = builder.create();

        return dialog;
    }

    public static void showProgressDialog(String message, String title, Activity activity){
        DialogBuilder.PROGRESS_DIALOG = ProgressDialog.show(activity,title,message,false,true);
    }

    public static void closeProgressDialog() {
        if (DialogBuilder.PROGRESS_DIALOG != null && DialogBuilder.PROGRESS_DIALOG.isShowing()) {
           DialogBuilder.PROGRESS_DIALOG.dismiss();
           DialogBuilder.PROGRESS_DIALOG = null;
        }
    }
}
