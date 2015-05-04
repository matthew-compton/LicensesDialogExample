package com.ambergleam.licensesdialogexample;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;


public class MainActivity extends AppCompatActivity {

    private AlertDialog mAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAlertDialog != null) {
            mAlertDialog.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_licenses_alert:
                displayLicensesAlertDialog();
                return true;
            case R.id.action_licenses_fragment:
                displayLicensesFragmentDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void displayLicensesAlertDialog() {
        if (mAlertDialog == null) {
            WebView view = (WebView) LayoutInflater.from(this).inflate(R.layout.dialog_licenses, null);
            view.loadUrl("file:///android_asset/open_source_licenses.html");
            mAlertDialog = new AlertDialog.Builder(this, R.style.Theme_AppCompat_Light_Dialog_Alert)
                    .setTitle(getString(R.string.action_licenses))
                    .setView(view)
                    .setPositiveButton(android.R.string.ok, null)
                    .create();
        } else {
            mAlertDialog.show();
        }
    }

    private void displayLicensesFragmentDialog() {
        LicensesDialogFragment dialog = LicensesDialogFragment.newInstance();
        dialog.show(getSupportFragmentManager(), "LicensesDialog");
    }

}