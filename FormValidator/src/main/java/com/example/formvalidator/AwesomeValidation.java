package com.example.formvalidator;

import android.app.Activity;
import android.content.Context;
import android.util.Range;
import android.view.View;
import android.widget.EditText;

import com.example.formvalidator.model.NumericRange;
import com.example.formvalidator.utility.custom.CustomErrorReset;
import com.example.formvalidator.utility.custom.CustomValidation;
import com.example.formvalidator.utility.custom.CustomValidationCallback;
import com.example.formvalidator.utility.custom.SimpleCustomValidation;
import com.example.formvalidator.validators.BasicValidator;
import com.example.formvalidator.validators.ColorationValidator;
import com.example.formvalidator.validators.TextInputLayoutValidator;
import com.example.formvalidator.validators.UnderlabelValidator;
import com.example.formvalidator.validators.Validator;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;


public class AwesomeValidation {

    private Validator mValidator = null;

    private static boolean autoFocusOnFirstFailure = true;

    public AwesomeValidation(ValidationStyle style) {
        switch (style) {
            case BASIC:
                if (mValidator == null || !(mValidator instanceof BasicValidator)) {
                    mValidator = new BasicValidator();
                }
                return;
            case COLORATION:
                if (mValidator == null || !(mValidator instanceof ColorationValidator)) {
                    mValidator = new ColorationValidator();
                }
                return;
            case UNDERLABEL:
                if (mValidator == null || !(mValidator instanceof UnderlabelValidator)) {
                    mValidator = new UnderlabelValidator();
                }
                return;
            case TEXT_INPUT_LAYOUT:
                if (mValidator == null || !(mValidator instanceof TextInputLayoutValidator)) {
                    mValidator = new TextInputLayoutValidator();
                }
                return;
            default:
        }
    }

    public static boolean isAutoFocusOnFirstFailureEnabled() {
        return autoFocusOnFirstFailure;
    }

    public static void disableAutoFocusOnFirstFailure() {
        autoFocusOnFirstFailure = false;
    }

    private void checkIsColorationValidator() {
        if (!(mValidator instanceof ColorationValidator)) {
            throw new UnsupportedOperationException("Only supported by ColorationValidator.");
        }
    }

    private void checkIsUnderlabelValidator() {
        if (!(mValidator instanceof UnderlabelValidator)) {
            throw new UnsupportedOperationException("Only supported by UnderlabelValidator.");
        }
    }

    private void checkIsTextInputLayoutValidator() {
        if (!(mValidator instanceof TextInputLayoutValidator)) {
            throw new UnsupportedOperationException("Only supported by TextInputLayoutValidator.");
        }
    }

    private void checkIsNotTextInputLayoutValidator() {
        if (mValidator instanceof TextInputLayoutValidator) {
            throw new UnsupportedOperationException("Not supported by TextInputLayoutValidator.");
        }
    }

    public void setContext(Context context) {
        checkIsUnderlabelValidator();
        ((UnderlabelValidator) mValidator).setContext(context);
    }

    public void setColor(int color) {
        checkIsColorationValidator();
        ((ColorationValidator) mValidator).setColor(color);
    }

    public void setTextInputLayoutErrorTextAppearance(int styleId) {
        checkIsTextInputLayoutValidator();
        ((TextInputLayoutValidator) mValidator).setErrorTextAppearance(styleId);
    }

    public void addValidation(Activity activity, int viewId, String regex, int errMsgId) {
        mValidator.set(activity, viewId, regex, errMsgId);
    }

    public void addValidation(Activity activity, int viewId, Pattern pattern, int errMsgId) {
        mValidator.set(activity, viewId, pattern, errMsgId);
    }


    public void addValidation(Activity activity, int viewId, Range range, int errMsgId) {
        mValidator.set(activity, viewId, new NumericRange(range), errMsgId);
    }

    public void addValidation(Activity activity, int confirmationViewId, int viewId, int errMsgId) {
        mValidator.set(activity, confirmationViewId, viewId, errMsgId);
    }


    public void addValidation(Activity activity, int viewId, SimpleCustomValidation simpleCustomValidation, int errMsgId) {
        mValidator.set(activity, viewId, simpleCustomValidation, errMsgId);
    }

    public void addValidation(Activity activity, int viewId, CustomValidation customValidation, CustomValidationCallback customValidationCallback, CustomErrorReset customErrorReset, int errMsgId) {
        mValidator.set(activity, viewId, customValidation, customValidationCallback, customErrorReset, errMsgId);
    }

    public boolean validate() {
        return mValidator.trigger();
    }

    public void clear() {
        mValidator.halt();
    }

}
