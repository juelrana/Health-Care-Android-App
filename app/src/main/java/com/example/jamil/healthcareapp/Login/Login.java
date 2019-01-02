package com.example.jamil.healthcareapp.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jamil.healthcareapp.MainActivity;
import com.example.jamil.healthcareapp.R;

import java.util.HashMap;

public class Login extends AppCompatActivity {

    SessionManager manager;
    EditText et_email, et_pass;
    Button btn_login;
    Preference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        manager = new SessionManager();

        et_email = (EditText) findViewById(R.id.et_email);
        et_pass = (EditText) findViewById(R.id.et_pass);
        btn_login = (Button) findViewById(R.id.btn_login);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=et_email.getText().toString();
                String pass=et_pass.getText().toString();

                if (name.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(Login.this, "Enter value in all field", Toast.LENGTH_SHORT).show();
                } else {
                    preference = new Preference(Login.this);
                    boolean isLogin = preference.isLogin();
                    if (isLogin) {

                        HashMap<String, String> map = preference.retrieveData();
                        String loginName = map.get(Preference.NAME_KEY);
                        String loginPass = map.get(Preference.PASS_KEY);

                        if (loginName.equalsIgnoreCase(name) && loginPass.equalsIgnoreCase(pass)) {
                            manager.setPreferences(Login.this, "status", "1");
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(Login.this, "Username & Password doesn't Match  ", Toast.LENGTH_SHORT).show();

                        }

                    } else {

                        preference.save(name, pass);
                        Toast.makeText(Login.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                        manager.setPreferences(Login.this, "status", "1");
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                    }

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }
}
