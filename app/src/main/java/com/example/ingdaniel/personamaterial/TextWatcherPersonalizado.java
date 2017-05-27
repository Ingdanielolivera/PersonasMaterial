package com.example.ingdaniel.personamaterial;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by ING DANIEL on 20/05/2017.
 */

public abstract class TextWatcherPersonalizado implements TextWatcher {

    private TextInputLayout icaja;
    private String textError;

    public TextWatcherPersonalizado(TextInputLayout icaja, String textError) {
        this.icaja = icaja;
        this.textError = textError;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(estavacio(s))icaja.setError(textError);
        else if (icaja.isErrorEnabled()){
            icaja.setError(null);
        }
    }

    public abstract boolean estavacio(Editable s);

}
