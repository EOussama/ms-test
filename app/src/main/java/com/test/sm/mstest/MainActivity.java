package com.test.sm.mstest;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvUsername;
    EditText etSearch;
    Button btnLogout;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvUsername = (TextView) findViewById(R.id.username);
        etSearch = (EditText) findViewById(R.id.etSearch);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnSearch = (Button) findViewById(R.id.btnSearch);

        tvUsername.setText(getIntent().getExtras().getString(LoginActivity.PARAM_USERNAME));
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.logout);
                builder.setMessage(R.string.logoutQuestion);
                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent lIntent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(lIntent);
                        finish();
                    }
                });
                builder.setNegativeButton(R.string.no, null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etSearch.getText().toString().length() <= 0)
                    Toast.makeText(MainActivity.this, R.string.searchEmptyError, Toast.LENGTH_SHORT).show();
                else {
                    Intent webIntnt = new Intent(Intent.ACTION_VIEW, Uri.parse(etSearch.getText().toString()));
                    startActivity(webIntnt);
                }
            }
        });
    }
}
