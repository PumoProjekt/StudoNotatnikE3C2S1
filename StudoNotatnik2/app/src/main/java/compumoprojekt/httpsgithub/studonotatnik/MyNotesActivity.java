package compumoprojekt.httpsgithub.studonotatnik;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MyNotesActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter=new MyAdapter(this);
        mRecyclerView.setAdapter(adapter);
    }


    //Lista przewijana, na razie sa tylko zwykle linijki tekstowe, tak jak na labkach robilismy

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView firstLine;
        TextView secondLine;

        public MyViewHolder(View itemView) {
            super(itemView);
            firstLine = (TextView) itemView.findViewById(R.id.first_line);
            secondLine = (TextView) itemView.findViewById(R.id.second_line);
        }
    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        ArrayList<String> dataArray;

        public MyAdapter(Context context) {

            loadData(context);
        }



        public void loadData(Context context)
        {
            dataArray = new ArrayList<>();

            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(context.getFilesDir().getPath()+"/note.txt"));
                String line;
                while ((line=bufferedReader.readLine()) != null){
                    dataArray.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }


        }

       /* String message;
        FileInputStream fileInputStream = openFileInput("notes");
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuffer stringBuffer = new StringBuffer();*/

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(view);

            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.firstLine.setText("Notatka" + position);
            //holder.secondLine.setText();
            holder.secondLine.setText(dataArray.get(position));
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}
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