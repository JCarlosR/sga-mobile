package com.youtube.sorcjc.sga_mobile.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.youtube.sorcjc.sga_mobile.R;
import com.youtube.sorcjc.sga_mobile.domain.User;
import com.youtube.sorcjc.sga_mobile.io.SgaApiAdapter;
import com.youtube.sorcjc.sga_mobile.io.fcm.MyFirebaseInstanceIDService;
import com.youtube.sorcjc.sga_mobile.io.response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // Buttons
    private Button btnLogin;
    private Context contexto;
    private Activity activity;
    private ProgressDialog prgrssdlgCargando;
    private EditText edtusuario,edtclave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        contexto = this;
        activity = this;
        // Button references
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        edtusuario = (EditText) findViewById(R.id.codigo);
        edtclave = (EditText) findViewById(R.id.clave);
        User usuario = Global.getFromSharedPreferences(this,"user_login");
        if(usuario!=null){
            Intent intent = new Intent(this, PanelActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                validar(edtusuario.getText().toString(),edtclave.getText().toString(),"1");
                break;
        }
    }


    Boolean respondido = false;
    Boolean answer = false;
    private void validar(String user,String pass,String sede) {
        // Load genres data from the webservice
        MyFirebaseInstanceIDService mfiids = new MyFirebaseInstanceIDService();
        Log.d("RARAZOOO",FirebaseInstanceId.getInstance().getToken());
        Call<LoginResponse> call = SgaApiAdapter.getApiService().getLoginResponse(user,pass,sede, FirebaseInstanceId.getInstance().getToken());
        Log.d("Test/Main", "Se lanzó el llamado al WS");
        prgrssdlgCargando = ProgressDialog.show(contexto, "Login|Entrar", "Verificando sus datos.", true);
        prgrssdlgCargando.setMessage(Html.fromHtml("<font color='white'>" + "Verificando..." + "</font>"));

        // Async callback
        respondido = false;
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call,Response<LoginResponse> response) {
                respondido = true;
                Log.d("Test/Main", "Se recibió una respuesta");
                if (response != null) {
                    String rpta = response.body().getRespuesta();
                    Log.d("Test/Main", "Respuesta => " + rpta);
                    if (prgrssdlgCargando!= null)
                        prgrssdlgCargando.dismiss();
                    if(response.body().isExito()){
                        Global.saveInSharedPreferences(activity, "user_login", response.body().getUsuario());
                        Intent intent = new Intent(contexto, PanelActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(contexto,  response.body().getRespuesta() , Toast.LENGTH_LONG).show();
                    }//true;//respuesta.isExito();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call,Throwable t) {
                respondido = true;
                answer = false;
                if (prgrssdlgCargando!= null)
                    prgrssdlgCargando.dismiss();
                Toast.makeText(contexto,  t.getMessage() , Toast.LENGTH_LONG).show();
                //Toast.makeText(contexto, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

        });
        //return user.equals("admin");
    }

}
