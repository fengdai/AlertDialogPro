package com.alertdialogpro.demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.alertdialogpro.AlertDialogPro;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        findViewById(R.id.showDialog).setOnClickListener(this);
        findViewById(R.id.showLightDialog).setOnClickListener(this);
        findViewById(R.id.showNativeDialog).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showDialog:
                showAlertDialog(new AlertDialogPro.Builder(this));
                break;
            case R.id.showLightDialog:
                showAlertDialog(new AlertDialogPro.Builder(this, R.style.AlertDialogProTheme_Holo_Light));
                break;
            case R.id.showNativeDialog:
                showAlertDialog(new AlertDialog.Builder(this));
                break;
        }
    }

    private void showAlertDialog(AlertDialog.Builder builder) {
        builder.setTitle("Title").//
                setIcon(R.drawable.ic_launcher).//
                //setMessage("Message").//
                setMultiChoiceItems(new String[]{"A", "B", "C"}, //
                new boolean[]{true, false, true}, //
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    }
                }).
                setNeutralButton("Neutral", null).//
                setPositiveButton("OK", null).//
                setNegativeButton("Cancel", null).//
                show();
    }


}
