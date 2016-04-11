package compumoprojekt.httpsgithub.studonotatnik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    EditText passwordEditText;
    EditText loginEditText;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loginEditText = (EditText) findViewById(R.id.loginEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        loginButton = (Button) findViewById(R.id.loginButton);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    public void loginOnClick(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        getResources().getColor(R.color.my_color);
        return true;
    }
}