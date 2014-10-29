AlertDialogPro
==============

It's not an easy thing to theme the Android's **AlertDialog**, even though you are developing against HoneyComb (which introduced some new APIs like ```android:singleChoiceItemLayout``` -- allows you specify your own single－choice item layout in theme. But **not** enough). **AlertDialogPro** can make this thing easy. It includes (but is not limited to) all AlertDialog‘s APIs. You can use it the same as using the platform's AlertDialog and benefit from some of the other sweet features.

   ![holo](https://github.com/fengdai/AlertDialogPro/blob/master/image/holo_light_dialog_only.png)
   ![mtrl](https://github.com/fengdai/AlertDialogPro/blob/master/image/material_light_dialog_only.png)


Usage
=====

1. **Build an AlertDialogPro**

   Add **[alertdialogpro](https://github.com/fengdai/AlertDialogPro/tree/master/alertdialogpro)** to your project.
AlertDialogPro's API is similar to native AlertDialog's. It also provides a **AlertDialogPro.Builder** for building itself.
   ```java
   AlertDialogPro.Builder builder = new AlertDialogPro.Builder(getContext());
   builder.setTitle("Title").
           setIcon(R.drawable.ic_launcher).
           setMessage("Message").
           setNeutralButton("Neutral", null).
           setPositiveButton("OK", null).
           setNegativeButton("Cancel", null).
           show();
   ```

2. **Default Theme**
   
   The **AlertDialogPro**'s default theme is **Holo**. There are two default **Holo** themes, ```AlertDialogProTheme.Holo``` and ```AlertDialogProTheme.Holo.Light```. If your app needs a **Holo** theme dialog, you don't need to do any customization. The only thing you need to do is choose a theme from the two default themes, and add it to your app theme. Your dialog will looks the same **"Holo"** across all versions of Android.
   1. Specify **AlertDialogPro**'s theme with attribute ```alertDialogProTheme```:

      ```xml
      <style name="AppTheme" parent="AppBaseTheme">
        ...
        <!-- Use Holo dark theme as global theme of this app -->
        <item name="alertDialogProTheme">@style/AlertDialogProTheme.Holo</item>
      </style>
      ```
   2. Your can override global theme with another in code when build dialog. Just specify it to the Builder:

      ```java
      // Build a AlertDialogPro with Holo light theme.
      AlertDialogPro builder = new AlertDialogPro.Builder(getContext(), R.style.AlertDialogProTheme_Holo_Light);
      ```
   
3. **Material theme**

   **[alertdialogpro-theme-material](https://github.com/fengdai/AlertDialogPro/tree/master/alertdialogpro-theme-material)** is a project to make a **Material** theme **AlertDialogPro**. If you want a **Material** design dialog UI, you can simply add it to your project as an addition of **[alertdialogpro](https://github.com/fengdai/AlertDialogPro/tree/master/alertdialogpro)**. Then you can use it's two themes: ```AlertDialogProTheme.Material``` and ```AlertDialogProTheme.Material.Light``` in your application's theme resource or code.
   
4. **Theme customization**

   If the default themes do not meet your requirements. **AlertDialogPro** also gives you a chance to custom it to match your design pattern.

   1. Do slight changes with following attributes:
   

      ```xml
      <!-- Specify the dialog's background -->
      <item name="android:windowBackground">@drawable/dialog_background_material_dark</item>
      <!-- Style the dialog's title -->
      <item name="android:windowTitleStyle">@style/DialogWindowTitle.Material</item>
      <!-- Specify the item's height -->
      <item name="adpListItemHeight">48dip</item>
      <!-- Specify the item's text color -->
      <item name="adpListItemTextColor">@color/primary_text_material_dark</item>
      <!-- Specify the list selector -->
      <item name="adpListItemBackground">@drawable/item_background_material_dark</item>
      <!-- Specify the list divider -->
      <item name="adpListDivider">@null</item>
      <!-- Style the dialog's listView -->
      <item name="android:listViewStyle">@style/Widget.Material.ListView</item>
      <!-- Style the ButtonBar -->
      <item name="adpButtonBarStyle">@style/Widget.Material.ButtonBar</item>
      <!-- Style the Buttons -->
      <item name="adpButtonBarButtonStyle">@style/Widget.Material.Button</item>
      <!-- Style the Buttons respectively -->
      <item name="adpButtonBarPositiveButtonStyle">?attr/adpButtonBarButtonStyle</item>
      <item name="adpButtonBarNegativeButtonStyle">?attr/adpButtonBarButtonStyle</item>
      <item name="adpButtonBarNeutralButtonStyle">?attr/adpButtonBarButtonStyle</item>
      ```
   
   2. You can also specify your own widget layout to the AlertDialogPro:

      ```xml
      <style name="AlertDialogPro.Material">
        <!-- As HoneyComb's android:layout. Specify your AlertDialogPro's layout -->
        <item name="adpLayout">@layout/alert_dialog_material</item>
        <!-- As HoneyComb's android:listLayout. Specify your AlertDialogPro's ListView layout. -->
        <item name="adpListLayout">@layout/select_dialog_material</item>
        <!-- As HoneyComb's android:listItemLayout. Specify your AlertDialogPro's list item layout. -->
        <item name="adpListItemLayout">@layout/select_dialog_item_material</item>
        <!-- As HoneyComb's android:multiChoiceItemLayout. Specify your AlertDialogPro's multi choice list item layout. -->
        <item name="adpMultiChoiceItemLayout">@layout/select_dialog_multichoice_material</item>
        <!-- As HoneyComb's android:singleChoiceItemLayout. Specify your AlertDialogPro's single choice list item layout. -->
        <item name="adpSingleChoiceItemLayout">@layout/select_dialog_singlechoice_material</item>
      </style>
      ```
      And add it to your theme:
   
      ```xml
      <item name="alertDialogProStyle">@style/AlertDialogPro.Material</item>
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
