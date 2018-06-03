package com.test.sm.mstest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    TextView tvUsername;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvUsername = (TextView) findViewById(R.id.username);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        tvUsername.setText(getIntent().getExtras().getString(LoginActivity.PARAM_USERNAME));
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(iLogin);
            }
        });
    }
}
