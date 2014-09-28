AlertDialogPro
==============

Port from Android AlertDialog and add some nice features.



Usage
=====

1. Build an AlertDialogPro

   AlertDialogPro's API is similar to native AlertDialog's. It also provides a **AlertDialogPro.Builder** for building dialog.
   ```java
   AlertDialogPro builder = new AlertDialogPro.Builder(getContext());
   builder.setTitle("Title").
           setIcon(R.drawable.ic_launcher).
           setMessage("Message").
           setNeutralButton("Neutral", null).
           setPositiveButton("OK", null).
           setNegativeButton("Cancel", null).
           show();
   ```

2. Theme customization

   The AlertDialogPro's default theme is **Holo**. There are two default holo themes, ```AlertDialogProTheme.Holo``` and ```AlertDialogProTheme.Holo.Light```. If your app needs a Holo theme dialog, you don't need to do any customization. The only thing you need to do is choose a theme from the two default themes, and add it to your app theme. Your dialog will looks the same "Holo" across all versions of Android.
   1. Add AlertDialogPro theme into your app theme with attribute ```alertDialogProTheme```:

      ```xml
      <style name="AppTheme" parent="AppBaseTheme">
        ...
        <item name="alertDialogProTheme">@style/AlertDialogProTheme.Holo</item>
        ...
      </style>
      ```
   2. Your can override this theme with another in code when build dialog. Just specify it to the Builder:

      ```java
      AlertDialogPro builder = new AlertDialogPro.Builder(getContext(), R.style.AlertDialogProTheme_Holo_Light);
      ```

   If the default theme does not meet your requirements. AlertDialogPro also gives you a chance to custom it to match your design pattern.

   You can define your dialog style like this:

      ```xml
      <style name="AppTheme.AlertDialogProTheme" parent="AlertDialogProTheme.Holo">
        <!-- Window -->
        <item name="android:windowBackground">@drawable/dialog_full_holo_light</item>
        <item name="android:windowContentOverlay">@null</item>
        <!-- Title -->
        <item name="android:windowTitleStyle">@style/DialogWindowTitle.Holo.Light</item>
        <!-- ListView -->
        <item name="android:listViewStyle">@style/ListView</item>
        <item name="adpListItemHeight">48dip</item>
        <item name="adpListItemTextColor">@color/primary_text_holo_light</item>
        <item name="adpListItemBackground">@drawable/item_background_holo_light</item>
        <item name="adpListDivider">@drawable/list_divider_holo_light</item>
        <!-- Title -->
        <item name="adpTitleDividerBackground">@color/holo_blue_light</item>
        <item name="adpTitleDividerHeight">@dimen/alert_dialog_title_divider_height</item>
        <item name="adpTitleHeight">@dimen/alert_dialog_title_height</item>
        <!-- ButtonBar-->
        <item name="adpButtonBarStyle">@style/ButtonBar</item>
        <item name="adpButtonBarDividerHorizontal">@drawable/list_divider_holo_light</item>
        <item name="adpButtonBarDividerVertical">@drawable/list_divider_holo_light</item>
        <item name="adpButtonBarButtonStyle">@style/Holo.Button.Light</item>
      </style>
      ```



License
=======

    Copyright 2014 Feng Dai

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
