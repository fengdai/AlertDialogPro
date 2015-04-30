package com.alertdialogpro.demo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.alertdialogpro.AlertDialogPro;
import com.alertdialogpro.ProgressDialogPro;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int NATIVE_THEME = Integer.MIN_VALUE;
    private int mTheme = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        RadioGroup rg = (RadioGroup) findViewById(R.id.radio_group);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.theme_flavored_material:
                        // Use the default theme defined in xml.
                        mTheme = -1;
                        break;
                    case R.id.theme_material:
                        mTheme = R.style.Theme_AlertDialogPro_Material;
                        break;
                    case R.id.theme_material_light:
                        mTheme = R.style.Theme_AlertDialogPro_Material_Light;
                        break;
                    case R.id.theme_holo:
                        mTheme = R.style.Theme_AlertDialogPro_Holo;
                        break;
                    case R.id.theme_holo_light:
                        mTheme = R.style.Theme_AlertDialogPro_Holo_Light;
                        break;
                    case R.id.theme_native:
                        mTheme = NATIVE_THEME;
                        break;
                }
            }
        });

        findViewById(R.id.showMessage).setOnClickListener(this);
        findViewById(R.id.showLongMessage).setOnClickListener(this);
        findViewById(R.id.showProgress).setOnClickListener(this);
        findViewById(R.id.showProgressHorizontal).setOnClickListener(this);
        findViewById(R.id.showList).setOnClickListener(this);
        findViewById(R.id.showMultiChoiceList).setOnClickListener(this);
        findViewById(R.id.showSingleChoiceList).setOnClickListener(this);
        findViewById(R.id.showCustomView).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showMessage:
                showMessageAlertDialog();
                break;
            case R.id.showLongMessage:
                showLongMessageAlertDialog();
                break;
            case R.id.showProgress:
                showProgressDialog();
                break;
            case R.id.showProgressHorizontal:
                showProgressHorizontalDialog();
                break;
            case R.id.showList:
                showListAlertDialog();
                break;
            case R.id.showMultiChoiceList:
                showMultiChoiceListAlertDialog();
                break;
            case R.id.showSingleChoiceList:
                showSingleChoiceListAlertDialog();
                break;
            case R.id.showCustomView:
                showCustomViewDialog();
                break;
        }
    }

    private AlertDialog.Builder createAlertDialogBuilder() {
        if (mTheme == NATIVE_THEME) {
            return new AlertDialog.Builder(this);
        }

        return new AlertDialogPro.Builder(this, mTheme);
    }

    private AlertDialog createProgressDialog() {
        if (mTheme == NATIVE_THEME) {
            return new ProgressDialog(this);
        }

        return new ProgressDialogPro(this, mTheme);
    }

    private void showMessageAlertDialog() {
        createAlertDialogBuilder()
                .setTitle(R.string.app_name)
                .setMessage("Hello, charming AlertDialogPro!")
                .setPositiveButton("Nice Job", new ButtonClickedListener("Dismiss"))
                .show();
    }

    private void showLongMessageAlertDialog(){
        createAlertDialogBuilder()
                .setTitle(R.string.app_name)
                .setMessage("Hello, charming AlertDialogPro! This message is a bit longer than you would expect, so you can see how nicely this dialog behaves on tablets and landscape orientations. The Dialog does not stretch to fill the width but maintains a nice aspect ratio, yeaaa!")
                .setPositiveButton("Nice Job", new ButtonClickedListener("Dismiss"))
                .show();
    }

    private void showProgressDialog() {
        AlertDialog dialog = createProgressDialog();
        dialog.setMessage("Hello, charming ProgressDialogPro!");
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void showProgressHorizontalDialog() {
        AlertDialog dialog = createProgressDialog();
        dialog.setMessage("Hello, charming ProgressDialogPro horizontal!");

        if (dialog instanceof ProgressDialog) {
            ProgressDialog progressDialog = (ProgressDialog) dialog;
            progressDialog.setProgressStyle(ProgressDialogPro.STYLE_HORIZONTAL);
            progressDialog.setIndeterminate(true);
        }

        if (dialog instanceof ProgressDialogPro) {
            ProgressDialogPro progressDialog = (ProgressDialogPro) dialog;
            progressDialog.setProgressStyle(ProgressDialogPro.STYLE_HORIZONTAL);
            progressDialog.setIndeterminate(true);
        }

        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void showListAlertDialog() {
        final String[] list = new String[]{"Argentina", "Canada", "China (中国)", "Japan (日本)",
                "United States"};
        createAlertDialogBuilder()
                .setTitle("Choose your country")
                .setItems(list, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showToast(list[which]);
                    }
                })
                .show();
    }


    private List<String> mCheckedItems = new ArrayList<String>();

    private void showMultiChoiceListAlertDialog() {
        final String[] list = new String[]{"Material theme", "Holo theme", "Custom theme"};

        createAlertDialogBuilder()
                .setTitle(R.string.app_name)
                .setMultiChoiceItems(list,
                        new boolean[]{false, false, false},
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    mCheckedItems.add(list[which]);
                                } else {
                                    mCheckedItems.remove(list[which]);
                                }
                                showToast(
                                        list[which] + " is "
                                                + (isChecked ? "checked" : "unchecked" + ".")
                                );
                            }
                        })
                .setNeutralButton("More info", new ButtonClickedListener("More info"))
                .setNegativeButton("Cancel", new ButtonClickedListener("Cancel"))
                .setPositiveButton(
                        "Choose",
                        new ButtonClickedListener("Chose " + mCheckedItems.toString())
                )
                .show();

    }

    private String mCheckedItem;

    private void showSingleChoiceListAlertDialog() {
        final String[] list = new String[]{"Female", "Male"};
        int checkedItemIndex = 0;
        mCheckedItem = list[checkedItemIndex];

        createAlertDialogBuilder()
                .setTitle("Edit your gender")
                .setSingleChoiceItems(list,
                        checkedItemIndex,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mCheckedItem = list[which];
                                showToast(mCheckedItem);
                            }
                        })
                .setNegativeButton("Cancel", new ButtonClickedListener("Cancel"))
                .setPositiveButton(
                        "Save",
                        new ButtonClickedListener(mCheckedItem + " has been chosen.")
                )
                .show();
    }

    private void showCustomViewDialog() {
        createAlertDialogBuilder()
                .setTitle("Edit your name")
                .setView(getLayoutInflater().inflate(R.layout.input_view, null))
                .setNegativeButton("Cancel", new ButtonClickedListener("Cancel"))
                .setPositiveButton("Save", null).show();
    }


    private Toast mToast = null;

    private void showToast(CharSequence toastText) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, toastText, Toast.LENGTH_SHORT);
        mToast.show();
    }

    private class ButtonClickedListener implements DialogInterface.OnClickListener {
        private CharSequence mShowWhenClicked;

        public ButtonClickedListener(CharSequence showWhenClicked) {
            mShowWhenClicked = showWhenClicked;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            showToast("\"" + mShowWhenClicked + "\"" + " button clicked.");
        }
    }

}
