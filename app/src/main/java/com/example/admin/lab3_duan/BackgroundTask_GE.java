package com.example.admin.lab3_duan;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BackgroundTask_GE extends AsyncTask<Void, Void, Void> {
    String duongdan = MainActivity.SERVER_NAME;
    TextView tvResult;
    String strName, strScore;
    String str;
    ProgressDialog pDialog;
    Context context;

    public BackgroundTask_GE(Context context, TextView tvResult, String strName, String strScore) {
        this.context = context;
        this.tvResult = tvResult;
        this.strName = strName;
        this.strScore = strScore;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Sending...");
        pDialog.setIndeterminate(false);
        pDialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        duongdan += "?name=" + this.strName + "&score=" + this.strScore;
        try {
            URL url = new URL(duongdan);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = "";
            StringBuffer sb = new StringBuffer();
            while ((line = bfr.readLine()) != null) {
                sb.append(line);
            }
            str = sb.toString();
            //str = strName + " " + strScore;
            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
        tvResult.setText(str);
    }
}
