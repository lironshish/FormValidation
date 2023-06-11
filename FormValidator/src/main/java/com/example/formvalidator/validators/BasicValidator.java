package com.example.formvalidator.validators;


import com.example.formvalidator.ValidationHolder;
import com.example.formvalidator.utility.ValidationCallback;

import java.util.regex.Matcher;

public class BasicValidator extends Validator {

    private ValidationCallback mValidationCallback = new ValidationCallback() {
        @Override
        public void execute(ValidationHolder validationHolder, Matcher matcher) {
            validationHolder.getEditText().setError(validationHolder.getErrMsg());
        }
    };

    @Override
    public boolean trigger() {
        return checkFields(mValidationCallback);
    }

    @Override
    public void halt() {
        for (ValidationHolder validationHolder : mValidationHolderList) {
            if (validationHolder.isSomeSortOfView()) {
                validationHolder.resetCustomError();
            } else {
                validationHolder.getEditText().setError(null);
            }
        }
    }

}