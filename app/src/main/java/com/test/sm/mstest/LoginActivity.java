package com.test.sm.mstest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static final String PARAM_USERNAME = "com.test.sm.mstest.USERNAME";
    private static final User[] USERS = {
            new User("Oussama", "123456789"),
            new User("Compton", "oussama"),
            new User("Jikesh", "teamdrd"),
            new User("teamdrd", "drddayz"),
            new User("admin", "123")
    };

    Button btnLogin;
    Button btnReset;
    EditText etUsername;
    EditText etPassword;
    CheckBox cbTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnReset = (Button) findViewById(R.id.btnReset);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        cbTerms = (CheckBox) findViewById(R.id.cbTerms);

        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(etUsername.getText().toString().length() > 0 && etPassword.getText().toString().length() > 0 && cbTerms.isChecked() == true)
                    btnLogin.setClickable(true);
                else
                    btnLogin.setClickable(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(etUsername.getText().toString().length() > 0 && etPassword.getText().toString().length() > 0 && cbTerms.isChecked() == true)
                    btnLogin.setClickable(true);
                else
                    btnLogin.setClickable(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cbTerms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(etUsername.getText().toString().length() > 0 && etPassword.getText().toString().length() > 0 && cbTerms.isChecked() == true)
                    btnLogin.setClickable(true);
                else
                    btnLogin.setClickable(false);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etUsername.setText("");
                etPassword.setText("");
                cbTerms.setChecked(false);
                btnLogin.setClickable(false);
            }
        });
        
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isValidUser(etUsername.getText().toString(), etPassword.getText().toString()))
                    Toast.makeText(LoginActivity.this, R.string.errorLogin, Toast.LENGTH_SHORT).show();
                else {
                    Intent iMain = new Intent(LoginActivity.this, MainActivity.class);
                    iMain.putExtra(PARAM_USERNAME, etUsername.getText().toString());
                    startActivity(iMain);
                    finish();
                }
            }
        });

        etUsername.setText("");
        etPassword.setText("");
        cbTerms.setChecked(false);
        btnLogin.setClickable(false);
    }

    public static boolean isValidUser(String username, String password) {
        for (User user : USERS) {
            if(user.username.equals(username) && user.password.equals(password))
                return true;
        }

        return false;
    }
}
