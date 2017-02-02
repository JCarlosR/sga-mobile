package com.youtube.sorcjc.sga_mobile.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.youtube.sorcjc.sga_mobile.R;
import com.youtube.sorcjc.sga_mobile.domain.User;
import com.youtube.sorcjc.sga_mobile.io.SgaApiAdapter;
import com.youtube.sorcjc.sga_mobile.io.response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // Buttons
    private Button btnLogin;
    private Context context;
    private Activity activity;
    private ProgressDialog dialogLoading;
    private EditText edtusuario, edtclave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        activity = this;

        // buttons
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        // edit texts
        edtusuario = (EditText) findViewById(R.id.codigo);
        edtclave = (EditText) findViewById(R.id.clave);

        User user = Global.getFromSharedPreferences(this,"user_login");
        if (user != null){
            Intent intent = new Intent(this, PanelActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                validar(edtusuario.getText().toString(), edtclave.getText().toString(), "1");
                break;
        }
    }

    private void validar(String user, String pass, String sede) {

        // MyFirebaseInstanceIDService mfiids = new MyFirebaseInstanceIDService();
        // Log.d("Test/LoginActivity", "Firebase Token => " + FirebaseInstanceId.getInstance().getToken());

        Call<LoginResponse> call = SgaApiAdapter.getApiService().getLoginResponse(user, pass,sede, FirebaseInstanceId.getInstance().getToken());
        // Log.d("Test/LoginActivity", "Se lanzó el llamado al WS");
        dialogLoading = ProgressDialog.show(context, "Inicio de sesión", "Verificando sus datos.", true);
        dialogLoading.setMessage(Html.fromHtml("<font color='white'>" + "Verificando..." + "</font>"));

        // Async callback
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call,Response<LoginResponse> response) {
                // Log.d("Test/LoginActivity", "Se recibió una respuesta");
                if (response.isSuccessful()) {
                    String rpta = response.body().getRespuesta();
                    // Log.d("Test/LoginActivity", "Respuesta => " + rpta);
                    if (dialogLoading != null)
                        dialogLoading.dismiss();

                    if(response.body().isExito()) {
                        Global.saveInSharedPreferences(activity, "user_login", response.body().getUsuario());
                        Intent intent = new Intent(context, PanelActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(context,  response.body().getRespuesta() , Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call,Throwable t) {
                if (dialogLoading != null)
                    dialogLoading.dismiss();

                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });

    }

}
