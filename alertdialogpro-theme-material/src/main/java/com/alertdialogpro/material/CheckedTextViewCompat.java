package com.alertdialogpro.material;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Checkable;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Compatible CheckedTextView for Pre-Lollipop devices. Draw checkMark at start.
 */
public class CheckedTextViewCompat extends TextView implements Checkable {
    private static final int[] CHECKED_TEXT_VIEW_ATTRS = new int[]{
            android.R.attr.checked, android.R.attr.checkMark
    };
    private boolean mChecked;
    private int mCheckMarkResource;
    private Drawable mCheckMarkDrawable;

    private int mBasePadding;
    private int mCheckMarkWidth;

    private boolean mNeedRequestLayout;

    private static final int[] CHECKED_STATE_SET = {
            android.R.attr.state_checked
    };

    private AppCompatDrawableManager mDrawableManager;

    private Field mPaddingLeftField;
    private Field mPaddingRightField;

    public CheckedTextViewCompat(Context context) {
        this(context, null);
    }

    public CheckedTextViewCompat(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckedTextViewCompat(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setBasePadding(isCheckMarkAtStart());
        final TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs, CHECKED_TEXT_VIEW_ATTRS,
                defStyle, 0);

        final boolean checked = a.getBoolean(0, false);
        setChecked(checked);

        final Drawable d = a.getDrawable(1);
        if (d != null) {
            setCheckMarkDrawable(d);
        }

        a.recycle();

        mDrawableManager = AppCompatDrawableManager.get();
    }

    public void toggle() {
        setChecked(!mChecked);
    }

    public boolean isChecked() {
        return mChecked;
    }

    public void setChecked(boolean checked) {
        if (mChecked != checked) {
            mChecked = checked;
            refreshDrawableState();
        }
    }


    /**
     * Set the checkmark to a given Drawable, identified by its resourece id. This will be drawn
     * when {@link #isChecked()} is true.
     *
     * @param resId The Drawable to use for the checkmark.
     */
    public void setCheckMarkDrawable(int resId) {
        if (resId != 0 && resId == mCheckMarkResource) {
            return;
        }

        mCheckMarkResource = resId;
        setCheckMarkDrawable(mDrawableManager.getDrawable(getContext(), resId));
    }

    /**
     * Set the checkmark to a given Drawable. This will be drawn when {@link #isChecked()} is true.
     *
     * @param d The Drawable to use for the checkmark.
     */
    public void setCheckMarkDrawable(Drawable d) {
        if (mCheckMarkDrawable != null) {
            mCheckMarkDrawable.setCallback(null);
            unscheduleDrawable(mCheckMarkDrawable);
        }
        if (d != null) {
            d.setCallback(this);
            d.setVisible(getVisibility() == VISIBLE, false);
            d.setState(CHECKED_STATE_SET);
            setMinHeight(d.getIntrinsicHeight());

            mCheckMarkWidth = d.getIntrinsicWidth();
            d.setState(getDrawableState());
        } else {
            mCheckMarkWidth = 0;
        }
        mCheckMarkDrawable = d;
        updatePadding();
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);

        if (mCheckMarkDrawable != null) {
            mCheckMarkDrawable.setVisible(visibility == VISIBLE, false);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();

        if (mCheckMarkDrawable != null) {
            mCheckMarkDrawable.jumpToCurrentState();
        }
    }

    @Override
    protected boolean verifyDrawable(Drawable who) {
        return who == mCheckMarkDrawable || super.verifyDrawable(who);
    }

    /**
     * Gets the checkmark drawable
     *
     * @return The drawable use to represent the checkmark, if any.
     * @see #setCheckMarkDrawable(Drawable)
     * @see #setCheckMarkDrawable(int)
     */
    public Drawable getCheckMarkDrawable() {
        return mCheckMarkDrawable;
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        super.setPadding(left, top, right, bottom);
        setBasePadding(isCheckMarkAtStart());
        updatePadding();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void setPaddingRelative(int start, int top, int end, int bottom) {
        super.setPaddingRelative(start, top, end, bottom);
        setBasePadding(isCheckMarkAtStart());
        updatePadding();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onRtlPropertiesChanged(int layoutDirection) {
        super.onRtlPropertiesChanged(layoutDirection);
        invokeResetPaddingToInitialValues();
        setBasePadding(isCheckMarkAtStart());
        updatePadding();
    }

    private void updatePadding() {
        int newPadding = (mCheckMarkDrawable != null) ?
                mCheckMarkWidth + mBasePadding : mBasePadding;
        if (isCheckMarkAtStart()) {
            mNeedRequestLayout |= (getPaddingLeftField() != newPadding);
            setPaddingLeftField(newPadding);
        } else {
            mNeedRequestLayout |= (getPaddingRightField() != newPadding);
            setPaddingRightField(newPadding);
        }
        if (mNeedRequestLayout) {
            requestLayout();
            mNeedRequestLayout = false;
        }
    }

    private void setBasePadding(boolean checkmarkAtStart) {
        if (checkmarkAtStart) {
            mBasePadding = getPaddingLeftField();
        } else {
            mBasePadding = getPaddingRightField();
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private boolean isCheckMarkAtStart() {
        boolean isCheckMarkAtStart = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            isCheckMarkAtStart = getLayoutDirection() == LAYOUT_DIRECTION_LTR;
        }
        return isCheckMarkAtStart;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final Drawable checkMarkDrawable = mCheckMarkDrawable;
        if (checkMarkDrawable != null) {
            final int verticalGravity = getGravity() & Gravity.VERTICAL_GRAVITY_MASK;
            final int height = checkMarkDrawable.getIntrinsicHeight();

            int y = 0;

            switch (verticalGravity) {
                case Gravity.BOTTOM:
                    y = getHeight() - height;
                    break;
                case Gravity.CENTER_VERTICAL:
                    y = (getHeight() - height) / 2;
                    break;
            }

            final boolean checkMarkAtStart = isCheckMarkAtStart();
            final int width = getWidth();
            final int top = y;
            final int bottom = top + height;
            final int left;
            final int right;
            if (checkMarkAtStart) {
                left = mBasePadding;
                right = left + mCheckMarkWidth;
            } else {
                right = width - mBasePadding;
                left = right - mCheckMarkWidth;
            }
            checkMarkDrawable.setBounds(getScrollX() + left, top, getScrollX() + right, bottom);
            checkMarkDrawable.draw(canvas);
            checkMarkDrawable.draw(canvas);
        }
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();

        if (mCheckMarkDrawable != null) {
            int[] myDrawableState = getDrawableState();

            // Set the state of the Drawable
            mCheckMarkDrawable.setState(myDrawableState);

            invalidate();
        }
    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        boolean populated = super.dispatchPopulateAccessibilityEvent(event);
        if (!populated) {
            event.setChecked(mChecked);
        }
        return populated;
    }

    private void invokeResetPaddingToInitialValues() {
        try {
            Method resetPaddingToInitialValues = View.class.getDeclaredMethod("resetPaddingToInitialValues");
            try {
                resetPaddingToInitialValues.invoke(this);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private void ensurePaddingLeftField() {
        if (mPaddingLeftField == null) {
            try {
                mPaddingLeftField = View.class.getDeclaredField("mPaddingLeft");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    private int getPaddingLeftField() {
        ensurePaddingLeftField();
        try {
            return mPaddingLeftField.getInt(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void setPaddingLeftField(int paddingLeft) {
        ensurePaddingLeftField();
        try {
            mPaddingLeftField.setInt(this, paddingLeft);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void ensurePaddingRightField() {
        if (mPaddingRightField == null) {
            try {
                mPaddingRightField = View.class.getDeclaredField("mPaddingRight");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    private int getPaddingRightField() {
        ensurePaddingRightField();
        try {
            return mPaddingRightField.getInt(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void setPaddingRightField(int paddingRight) {
        ensurePaddingRightField();
        try {
            mPaddingRightField.setInt(this, paddingRight);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}