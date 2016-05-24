package compumoprojekt.httpsgithub.studonotatnik;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.onegravity.rteditor.RTEditText;
import com.onegravity.rteditor.RTManager;
import com.onegravity.rteditor.RTToolbar;
import com.onegravity.rteditor.api.RTApi;
import com.onegravity.rteditor.api.RTMediaFactoryImpl;
import com.onegravity.rteditor.api.RTProxyImpl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;




public class EditorActivity extends AppCompatActivity {



    EditText newRowEdit;
    RTEditText newEditRte;
    String message = "Treść";
    RTManager rtManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set theme before calling setContentView!

        setTheme(R.style.RTE_ThemeDark);
        setContentView(R.layout.activity_editor);
        // set layout
        newRowEdit = (EditText) findViewById(R.id.editNote);
        newEditRte = (RTEditText) findViewById(R.id.rtEditText);
        // create RTManager
        RTApi rtApi = new RTApi(this, new RTProxyImpl(this), new RTMediaFactoryImpl(this, true));
        // create RTManager
        rtApi = new RTApi(this, new RTProxyImpl(this), new RTMediaFactoryImpl(this, true));
        rtManager = new RTManager(rtApi, savedInstanceState);

// register toolbar
        ViewGroup toolbarContainer = (ViewGroup) findViewById(R.id.rte_toolbar_container);
        RTToolbar rtToolbar = (RTToolbar) findViewById(R.id.rte_toolbar);
        if (rtToolbar != null) {
            rtManager.registerToolbar(toolbarContainer, rtToolbar);
        }

// register editor & set text
        RTEditText rtEditText = (RTEditText) findViewById(R.id.rtEditText);
        rtManager.registerEditor(rtEditText, true);
        //rtEditText.setRichTextEditing(true, message);


    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        rtManager.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        rtManager.onDestroy(isFinishing());
    }


    public void saveOnClick(View view) {
        // Zapis notatki do pliku

        Note note = new Note();

        note.Temat = newRowEdit.getText().toString();
        note.Tresc = newEditRte.getText().toString();
        //newEditRte.setRichTextEditing(true, "My content");

        if ((!note.Temat.isEmpty())) {
            BufferedWriter bufferedWriter = null;

            //zapisz w pliku
            try {

                bufferedWriter = new BufferedWriter(new FileWriter(getFilesDir().getPath() + "/note.json", true));

                //bufferedWriter.write(newRow);
                bufferedWriter.newLine();

                Gson gson = new GsonBuilder().create();
                //gson.toJson(note.Temat , bufferedWriter);
                gson.toJson(note , bufferedWriter);


                newRowEdit.setText("");
                newEditRte.setText("");

               /* JSONObject jsonRootObject = new JSONObject(strJson);

                //Get the instance of JSONArray that contains JSONObjects
                JSONArray jsonArray = jsonRootObject.optJSONArray("Temat");
                for(int i=0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String temat = jsonObject.optString("tem").toString();
                    //strJson = "{\"Temat\":[{\"tem\"}}"; //+newRow;
                    data += "Temat: "+ temat;
                }*/


                /*for(int i=0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    int id = Integer.parseInt(jsonObject.optString("id").toString());
                    String name = jsonObject.optString("name").toString();
                    float salary = Float.parseFloat(jsonObject.optString("salary").toString());

                    data += "Node"+i+" : \n id= "+ id +" \n Name= "+ name +" \n Salary= "+ salary +" \n ";
                }*/


                newRowEdit.setText("");

                Toast.makeText(getApplicationContext(), "Zapisano w : " + getFilesDir().getPath(), Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
                // } catch (JSONException e) {
                //    e.printStackTrace();
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


    public void shareOnClick(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        EditText toEditText = (EditText)findViewById(R.id.editTo);
        String To = toEditText.getText().toString();
        emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{To});
        emailIntent.putExtra(Intent.EXTRA_CC, new String[]{""});
        String Subject = newRowEdit.getText().toString();
        String Body = newEditRte.getText().toString();
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, Subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT,Body);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Choose email client"));
    }
}
