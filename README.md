AlertDialogPro
==============

Since some attributes like ```android:layout```(which can be used to specify your own layout for AlertDialog) are introduced in HoneyComb. It is not an easy thing to custom the theme of Android's **AlertDialog** when you are developing against Pre-HoneyComb platform. **AlertDialogPro** can make this thing easy. You can use it the same as using HoneyComb's AlertDialog.

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
