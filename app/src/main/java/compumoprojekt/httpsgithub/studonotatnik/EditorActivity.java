package compumoprojekt.httpsgithub.studonotatnik;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.onegravity.rteditor.RTEditText;
import com.onegravity.rteditor.RTManager;
import com.onegravity.rteditor.RTToolbar;
import com.onegravity.rteditor.api.RTApi;
import com.onegravity.rteditor.api.RTMediaFactoryImpl;
import com.onegravity.rteditor.api.RTProxyImpl;
import com.onegravity.rteditor.api.format.RTFormat;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;




public class EditorActivity extends AppCompatActivity {


    EditText newRowEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set theme before calling setContentView!
        setTheme(R.style.RTE_ThemeDark);

        // set layout

        setContentView(R.layout.activity_editor);


        newRowEdit = (EditText) findViewById(R.id.editNote);
        // create RTManager

    }


    public void saveOnClick(View view) {
        // Zapis notatki do pliku

        String newRow = newRowEdit.getText().toString();

        if ((!newRow.isEmpty())) {
            BufferedWriter bufferedWriter = null;

            //zapisz w pliku
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(getFilesDir().getPath() + "/note.txt", true));

                bufferedWriter.write(newRow);
                bufferedWriter.newLine();

                newRowEdit.setText("");

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        }

    }
}
