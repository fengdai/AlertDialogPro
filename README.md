AlertDialogPro
==============

[![Build Status](https://travis-ci.org/fengdai/AlertDialogPro.svg?branch=master)](https://travis-ci.org/fengdai/AlertDialogPro)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AlertDialogPro-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1178)

It's not an easy thing to theme the Android's **AlertDialog**, even though you are developing against HoneyComb (which introduced some new APIs like ```android:singleChoiceItemLayout``` -- allows you specify your own single－choice item layout in theme. But **not** enough) or above. **AlertDialogPro** can make this thing easy. It includes (but is not limited to) all AlertDialog's APIs. You can use it the same as using the platform's AlertDialog (just replace ```AlertDialog.Builder``` with ```AlertDialogPro.Builder```) and benefit from some of the other sweet features.

There are two theme projects you can use directly: [alertdialogpro-theme-holo](https://github.com/fengdai/AlertDialogPro/tree/master/alertdialogpro-theme-holo) and [alertdialogpro-theme-material](https://github.com/fengdai/AlertDialogPro/tree/master/alertdialogpro-theme-material). One provides holo dialog and the other one provides material dialog. **AlertDialogPro** is very flexible, so you can use it to implement any other theme you want.

   ![holo](https://github.com/fengdai/AlertDialogPro/blob/master/image/holo_light_dialog_only.png)
   ![mtrl](https://github.com/fengdai/AlertDialogPro/blob/master/image/material_light_dialog_only.png)
   ![flavored-mtrl](https://github.com/fengdai/AlertDialogPro/blob/master/image/flavored_material_light.png)
   ![flavored-mtrl](https://github.com/fengdai/AlertDialogPro/blob/master/image/material_dark_dialog_only.png)


Usage
=====

1. **Build an AlertDialogPro**

   Include **alertdialogpro-core** to your project. And you also need to select a theme project from **alertdialogpro-theme-holo** and **alertdialogpro-theme-material**, and include it to your project too. You can define your own theme if you don't want to use both of them. In this case, you just need to include **alertdialogpro-core** only. Than you can use AlertDialogPro. AlertDialogPro's API is similar to native AlertDialog's. It also provides a **AlertDialogPro.Builder** for building itself.
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

2. **alertdialogpro-theme-holo**
   
   This a project which contains Holo resources for **AlertDialogPro**. Anyone who wants a Holo theme can include this project as an addition of alertdialogpro-core. It has two default Holo themes, ```Theme.AlertDialogPro.Holo``` and ```Theme.AlertDialogPro.Holo.Light```. The only thing you need to do is choose a theme from the two, and add it to your application's theme XML file. Your dialog will looks the same "Holo" across almost versions of Android backward to API level 3.
   1. Specify **AlertDialogPro**'s theme with attribute ```alertDialogProTheme```:

      ```xml
      <style name="AppTheme" parent="AppBaseTheme">
        ...
        <!-- Use Holo dark theme as global theme of this app -->
        <item name="alertDialogProTheme">@style/Theme.AlertDialogPro.Holo</item>
      </style>
      ```
   2. Your can override global theme with another in code when build dialog. Just specify it to the Builder:

      ```java
      // Build a AlertDialogPro with Holo light theme.
      AlertDialogPro.Builder builder = new AlertDialogPro.Builder(getContext(), R.style.Theme_AlertDialogPro_Holo_Light);
      ```
   
3. **alertdialogpro-theme-material**

   Similar to **alertdialogpro-theme-holo**, **alertdialogpro-theme-material** is a project to make material style **AlertDialogPro**. If you want a material dialog, you can simply include it to your project as an addition of **alertdialogpro-core**. Then you can use it's two themes: ```Theme.AlertDialogPro.Material``` and ```Theme.AlertDialogPro.Material.Light``` in your application's theme resource or code. Since it depends on AppCompat-v21, it only support minSdkVersion 7+ now.
   
4. **Theme customization**

   With AlertDialogPro, you can theme your dialog easily.

   1. If one of the built-in themes can meet almost your requirements, but you want to do some slight changes. You can use following attributes:

      ```xml
      <!-- Minimum height of title panel-->
      <attr name="adpTitleMinHeight" format="dimension" />

      <!-- The text appearance for the dialog's message text -->
      <attr name="adpMessageTextAppearance" format="reference" />

      <!-- Minimum height of ListView's items -->
      <attr name="adpListItemMinHeight" format="dimension" />

      <!-- The text color for ListView's items -->
      <attr name="adpListItemTextColor" format="reference|color" />

      <!-- The text appearance for normal ListView's items -->
      <attr name="adpListItemTextAppearance" format="reference" />

      <!-- The text appearance for "multi-choice" ListView's items -->
      <attr name="adpListMultiChoiceTextAppearance" format="reference" />

      <!-- The text appearance for "single-choice" ListView's items -->
      <attr name="adpListSingleChoiceTextAppearance" format="reference" />

      <!-- Divider for the ListView -->
      <attr name="adpListDivider" format="reference" />

      <!-- Selector in a ListView -->
      <attr name="adpListItemBackground" format="reference" />

      <!-- Style for button bars -->
      <attr name="adpButtonBarStyle" format="reference" />

      <!-- Style for buttons within button bars -->
      <attr name="adpButtonBarButtonStyle" format="reference" />

      <!-- Style for the "positive" buttons within button bars -->
      <attr name="adpButtonBarPositiveButtonStyle" format="reference" />

      <!-- Style for the "negative" buttons within button bars -->
      <attr name="adpButtonBarNegativeButtonStyle" format="reference" />

      <!-- Style for the "neutral" buttons within button bars -->
      <attr name="adpButtonBarNeutralButtonStyle" format="reference" />
      ```
   
   2. You can also specify your own layout to the AlertDialogPro. This can be very useful when you want to use custom view or you want a very special dialog style.

      ```xml
      <style name="AlertDialogPro.Material">
        <!-- As HoneyComb's android:layout.
             Specify your AlertDialogPro's layout -->
        <item name="adpLayout">@layout/adp_alert_dialog_material</item>
        <!-- As HoneyComb's android:listLayout.
             Specify your AlertDialogPro's ListView layout. -->
        <item name="adpListLayout">@layout/adp_select_dialog_material</item>
        <!-- As HoneyComb's android:listItemLayout.
             Specify your AlertDialogPro's list item layout. -->
        <item name="adpListItemLayout">@layout/adp_select_dialog_item_material</item>
        <!-- As HoneyComb's android:multiChoiceItemLayout.
             Specify your AlertDialogPro's multi choice list item layout. -->
        <item name="adpMultiChoiceItemLayout">@layout/adp_select_dialog_multichoice_material</item>
        <!-- As HoneyComb's android:singleChoiceItemLayout.
             Specify your AlertDialogPro's single choice list item layout. -->
        <item name="adpSingleChoiceItemLayout">@layout/adp_select_dialog_singlechoice_material</item>
      </style>
      ```
      And add it to your theme:
   
      ```xml
      <item name="alertDialogProStyle">@style/AlertDialogPro.Material</item>
      ```

   3. If none of the built-in themes can meet your requirements. Full customization is also supported. Do as what the **alertdialogpro-theme-holo** or **alertdialogpro-theme-material** did.


Gradle Dependence
=================

   Coming soon...


Thanks
======

   * This project contains some code ported from The Android Open Source Project. [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)
   * Thank [vbauer](https://github.com/vbauer) and his awesome site [Android-Arsenal](https://android-arsenal.com/)

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
