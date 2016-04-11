package compumoprojekt.httpsgithub.studonotatnik;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EditorActivity extends AppCompatActivity {


    EditText newRowEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        newRowEdit = (EditText) findViewById(R.id.editText);
    }

    public void saveOnClick(View view) {


        //File file = new File();

        /*public boolean isExternalStorageWritable() {
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                return true;
            }
            return false;
        }
         public void addRow(View view) {
        String newRow = newRowEdit.getText().toString();

        if ((!newRow.isEmpty())){
            BufferedWriter bufferedWriter=null;

            //zapisz w pliku
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(getFilesDir().getPath()+"/recycleview.txt",true));

                bufferedWriter.write(newRow);
                bufferedWriter.newLine();

                newRowEdit.setText("");

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }*/

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

            //Toast.makeText(EditorActivity.this, "Zapisano", Toast.LENGTH_SHORT).show();

            //Tu probowalem dodac odczyt z pliku, raczej jest zle i powinno byc pewnie
            // w MyNotesActivity, ale tam cos nie dzialalo i nie zdazylem juz przerobic.
            // Trzeba tez dodac pare linijek do tego kodu

        /*FileInputStream fileInputStream = null;
        try {
            String message;
            fileInputStream = openFileInput("notes");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }*/
        }
    }
}
