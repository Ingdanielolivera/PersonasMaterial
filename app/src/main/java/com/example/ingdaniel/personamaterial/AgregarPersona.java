package com.example.ingdaniel.personamaterial;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import org.w3c.dom.Text;

public class AgregarPersona extends AppCompatActivity {
    private EditText cajaCedula;
    private EditText cajaNombre;
    private EditText cajaApellido;

    private TextInputLayout icajaCedula;
    private TextInputLayout icajaNombre;
    private TextInputLayout icajaApellido;

    private boolean guardado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_persona);

        cajaCedula=(EditText)findViewById(R.id.txtCedula);
        cajaNombre=(EditText)findViewById(R.id.txtNombre);
        cajaApellido=(EditText)findViewById(R.id.txtApellido);

        icajaCedula=(TextInputLayout)findViewById(R.id.cedulaPersona);
        icajaNombre=(TextInputLayout)findViewById(R.id.nombrePersona);
        icajaApellido=(TextInputLayout)findViewById(R.id.apellidoPersona);

        guardado=false;
        cajaCedula.addTextChangedListener(new TextWatcherPersonalizado(icajaCedula,"Diguite la cedula") {
            @Override
            public boolean estavacio(Editable s ) {
                if(TextUtils.isEmpty(s) && !guardado) return true;
                else return false;
            }
        });

        cajaNombre.addTextChangedListener(new TextWatcherPersonalizado(icajaNombre,"Diguite la Nombre") {
            @Override
            public boolean estavacio(Editable s ) {
                if(TextUtils.isEmpty(s)&& !guardado) return true;
                else return false;
            }
        });

        cajaApellido.addTextChangedListener(new TextWatcherPersonalizado(icajaApellido,"Diguite la Apellido") {
            @Override
            public boolean estavacio(Editable s ) {
                if(TextUtils.isEmpty(s)&& !guardado) return true;
                else return false;
            }
        });



    }

    public int fotoAleatoria() {
        int fotos[] = {R.drawable.images, R.drawable.images2, R.drawable.images3};
        int numero = (int) (Math.random() * 3);
        return fotos[numero];
    }

    public boolean validartodo() {
        if (cajaCedula.getText().toString().isEmpty()) {
            icajaCedula.setError("Digite cedula");
            cajaCedula.requestFocus();
            return false;
        }
        if (cajaNombre.getText().toString().isEmpty()) {
            icajaNombre.setError("Diguite Nombre");
            cajaNombre.requestFocus();
            return false;
        }
        if (cajaApellido.getText().toString().isEmpty()) {
            icajaApellido.setError(("Diguite Apellido"));
            cajaApellido.requestFocus();
            return false;
        }

        return true;
    }


    public void guardar(View v) {
        String urlfoto, cedula, nombre, apellido,idfoto;
        Persona p;
        if (validartodo()) {

            cedula = cajaCedula.getText().toString();

            nombre = cajaNombre.getText().toString();
            apellido = cajaApellido.getText().toString();
            idfoto=String.valueOf(fotoAleatoria());
            Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),Integer.parseInt(idfoto));

            p = new Persona(urlfoto, cedula, nombre, apellido,idfoto);
            p.guardar(getApplicationContext());

            //Ocultar teclado
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(cajaCedula.getWindowToken(), 0);

            Snackbar.make(v,"Persona Guardada Exitosamente!",Snackbar.LENGTH_SHORT).show();
            guardado=true;
            limpiar();
        }
    }

    public void limpiar() {
        cajaCedula.setText("");
        cajaNombre.setText("");
        cajaApellido.setText("");
        guardado=false;
        cajaCedula.requestFocus();
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(AgregarPersona.this,Principal.class);
        startActivity(i);
    }



}
