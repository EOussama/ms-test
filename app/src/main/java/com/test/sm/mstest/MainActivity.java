package com.test.sm.mstest;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.sax.Element;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ListView lvAnime, lvUsers;
    TextView tvUsername, tvUsers;
    EditText etSearch;
    Button btnLogout, btnSearch;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user = getUserFromUsername(getIntent().getExtras().getString(LoginActivity.PARAM_USERNAME));

        lvAnime = (ListView) findViewById(R.id.lvAnime);
        lvUsers = (ListView) findViewById(R.id.lvUsers);
        tvUsername = (TextView) findViewById(R.id.username);
        tvUsers = (TextView) findViewById(R.id.tvUsers);
        etSearch = (EditText) findViewById(R.id.etSearch);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnSearch = (Button) findViewById(R.id.btnSearch);

        tvUsers.setText(String.format("Users (%d)", LoginActivity.USERS.length - 1));
        tvUsername.setText(user.username);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.logout);
                builder.setIcon(R.drawable.avatar);
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
                    Intent webIntnt = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + etSearch.getText().toString()));
                    startActivity(webIntnt);
                }
            }
        });

        ArrayAdapter<String> aaAnime = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, user.favAnime);
        lvAnime.setAdapter(aaAnime);

        lvAnime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + parent.getItemAtPosition(position).toString()));
                startActivity(intent);
            }
        });

        ArrayList<HashMap<String, String>> Elements = new ArrayList<HashMap<String, String>>();
        for(User u : LoginActivity.USERS) {
            if(u.username.equals(user.username)) continue;

            HashMap<String, String> map = new HashMap<String, String>();
            map.put("icon", String.valueOf(R.drawable.avatar));
            map.put("username", u.username);
            Elements.add(map);
        }

        SimpleAdapter saUsers = new SimpleAdapter(this, Elements, R.layout.user_row_layout, new String[] {"icon", "username"}, new int[] {R.id.iconUser, R.id.usernameListRow});
        lvUsers.setAdapter(saUsers);
    }

    private User getUserFromUsername(String username) {
        for(User user : LoginActivity.USERS) {
            if(user.username.equals(username))
                return user;
        }

        return null;
    }
}
