package com.hi.utils.network;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * 网络请求的同步工具
 * Created by lzw on 14-6-7.
 */
public abstract class NetAsyncTask extends AsyncTask<Void, Void, Void> {
  ProgressDialog dialog;
  protected Context ctx;
  boolean openDialog = true;
  Exception exception;

  protected NetAsyncTask(Context ctx) {
    this.ctx = ctx;
  }

  protected NetAsyncTask(Context ctx, boolean openDialog) {
    this.ctx = ctx;
    this.openDialog = openDialog;
  }

  public NetAsyncTask setOpenDialog(boolean openDialog) {
    this.openDialog = openDialog;
    return this;
  }

  public ProgressDialog getDialog() {
    return dialog;
  }

  @Override
  protected void onPreExecute() {
    super.onPreExecute();
    if (openDialog) {
      dialog = showSpinnerDialog((Activity) ctx);
    }
  }

  @Override
  protected Void doInBackground(Void... params) {
    try {
      doInBack();
    } catch (Exception e) {
      e.printStackTrace();
      exception = e;
    }
    return null;
  }

  @Override
  protected void onPostExecute(Void aVoid) {
    super.onPostExecute(aVoid);
    if (openDialog) {
      if (dialog.isShowing()) {
        dialog.dismiss();
      }
    }
    onPost(exception);
  }

  protected abstract void doInBack() throws Exception;

  protected abstract void onPost(Exception e);

    public static ProgressDialog showSpinnerDialog(Activity activity) {
        //activity = modifyDialogContext(activity);

        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(true);
        dialog.setMessage("努力加载中...");
        if (activity.isFinishing() == false) {
            dialog.show();
        }
        return dialog;
    }
}
