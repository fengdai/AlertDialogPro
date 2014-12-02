package com.alertdialogpro.material;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

/**
 * A {@link DrawableWrapper} which updates it's color filter using a {@link ColorStateList}.
 */
class TintDrawableWrapper extends DrawableWrapper {

    private final ColorStateList mTintStateList;
    private final PorterDuff.Mode mTintMode;

    private int mCurrentColor;

    public TintDrawableWrapper(Drawable drawable, ColorStateList tintStateList) {
        this(drawable, tintStateList, TintManager.DEFAULT_MODE);
    }

    public TintDrawableWrapper(Drawable drawable, ColorStateList tintStateList,
                               PorterDuff.Mode tintMode) {
        super(drawable);
        mTintStateList = tintStateList;
        mTintMode = tintMode;
    }

    @Override
    public boolean isStateful() {
        return (mTintStateList != null && mTintStateList.isStateful()) || super.isStateful();
    }

    @Override
    public boolean setState(int[] stateSet) {
        boolean handled = super.setState(stateSet);
        handled = updateTint(stateSet) || handled;
        return handled;
    }

    private boolean updateTint(int[] state) {
        if (mTintStateList != null) {
            final int color = mTintStateList.getColorForState(state, mCurrentColor);
            if (color != mCurrentColor) {
                if (color != Color.TRANSPARENT) {
                    setColorFilter(color, mTintMode);
                } else {
                    clearColorFilter();
                }
                mCurrentColor = color;
                return true;
            }
        }
        return false;
    }

}
