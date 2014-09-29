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
        findViewById(R.id.showHoloDialog).setOnClickListener(this);
        findViewById(R.id.showHoloLightDialog).setOnClickListener(this);
        findViewById(R.id.showMaterialDialog).setOnClickListener(this);
        findViewById(R.id.showMaterialLightDialog).setOnClickListener(this);
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
            case R.id.showHoloDialog:
                showAlertDialog(new AlertDialogPro.Builder(this));
                break;
            case R.id.showHoloLightDialog:
                showAlertDialog(new AlertDialogPro.Builder(this, R.style.AlertDialogProTheme_Holo_Light));
                break;
            case R.id.showMaterialDialog:
                showAlertDialog(new AlertDialogPro.Builder(this, R.style.AlertDialogProTheme_Material));
                break;
            case R.id.showMaterialLightDialog:
                showAlertDialog(new AlertDialogPro.Builder(this, R.style.AlertDialogProTheme_Material_Light));
                break;
            case R.id.showNativeDialog:
                showAlertDialog(new AlertDialog.Builder(this));
                break;
        }
    }

    private void showAlertDialog(AlertDialog.Builder builder) {
        builder.setTitle(R.string.app_name).
                setItems(new String[]{"Holo theme", "Material theme", "Custom theme"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).
//                setMessage("Message").
//                setMultiChoiceItems(new String[]{"A", "B", "C"},
//                new boolean[]{true, false, true},
//                new DialogInterface.OnMultiChoiceClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                    }
//                }).
//                setPositiveButton("OK", null).
//                setNegativeButton("Cancel", null).
        show();
    }


}
