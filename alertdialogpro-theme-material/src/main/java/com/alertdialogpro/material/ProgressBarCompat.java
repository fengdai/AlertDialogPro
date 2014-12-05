package com.alertdialogpro.material;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.alertdialogpro.material.drawable.CircularAnimatedDrawable;

public class ProgressBarCompat extends android.widget.ProgressBar {
    private static final int DEFAULT_MATERIAL_BORDER_WIDTH = 4;

    public ProgressBarCompat(Context context) {
        this(context, null);
    }

    public ProgressBarCompat(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.progressBarStyle);
    }

    public ProgressBarCompat(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP && isIndeterminate()) {
            final int color = getThemeAttrColor(context, R.attr.colorControlActivated);
            final float borderWidth = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    DEFAULT_MATERIAL_BORDER_WIDTH,
                    getResources().getDisplayMetrics()
            );

            if (color != 0) {
                setIndeterminateDrawable(new CircularAnimatedDrawable(color, borderWidth));
            }
        }
    }

    private int getThemeAttrColor(Context context, int attr) {
        if (context == null) {
            return 0;
        }

        TypedValue value = new TypedValue();
        if (context.getTheme().resolveAttribute(attr, value, true)) {
            if (value.type >= TypedValue.TYPE_FIRST_INT
                    && value.type <= TypedValue.TYPE_LAST_INT) {
                return value.data;
            } else if (value.type == TypedValue.TYPE_STRING) {
                return context.getResources().getColor(value.resourceId);
            }
        }

        return 0;
    }

}
