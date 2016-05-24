package compumoprojekt.httpsgithub.studonotatnik;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{

    EditText passwordEditText;
    EditText loginEditText;
    Button loginButton;
    Button logoutButton;

    String personName;
    String personPhotoUrl;
    String personEmail;
    String personId;
    Uri personPhoto;

    private static final String TAG = "SignInActivity";
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loginEditText = (EditText) findViewById(R.id.loginEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        //loginButton = (Button) findViewById(R.id.loginButton);
        logoutButton = (Button) findViewById(R.id.logoutButton);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.logoutButton).setOnClickListener(this);



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
// options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


    }


    public void logoutOnClick(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.logoutButton:
                signOut();
                Toast.makeText(getApplicationContext() , "WYLOGOWANO :) " , Toast.LENGTH_SHORT).show();
                break;
        }

    }


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);


        // informacje o uzytkowniku , data - niezdefiniowane
        /*
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        GoogleSignInAccount acct = result.getSignInAccount();
        String personName = acct.getDisplayName();
        String personEmail = acct.getEmail();
        String personId = acct.getId();
        Uri personPhoto = acct.getPhotoUrl();*/

    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // ...
                    }
                });
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

//inf o uzytkowniku

        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        GoogleSignInAccount acct = result.getSignInAccount();
        personName = acct.getDisplayName();
        personEmail = acct.getEmail();
        personId = acct.getId();
        personPhoto = acct.getPhotoUrl();
        //personPhotoUrl = acct.getUrl();
        //String personGooglePlusProfile = currentPerson.getUrl();
        //Person currentPerson = mPlusClient.getCurrentPerson();


        Log.e(TAG, "Name: " + personName + ", plusProfile: "
                + ", email: " + personEmail
                + ", Image: " + personPhoto);

        Toast.makeText(getApplicationContext(), "Zalogowano :) " + personPhoto, Toast.LENGTH_SHORT).show();
    }




    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            //mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
            //updateUI(true);
            //Toast.makeText(getApplicationContext() , "Zalogowano :) "+ personName + " " + personEmail + " " + personId , Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);

        } else {
            // Signed out, show unauthenticated UI.
            //updateUI(false);
        }
    }
}