package compumoprojekt.httpsgithub.studonotatnik;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.onegravity.rteditor.RTEditText;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MyNotesActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    MyAdapter adapter;
    LinearLayout optionsClicker;
    TextView secondLine;
    RTEditText secondLineRTE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);

        optionsClicker =(LinearLayout) findViewById(R.id.optionsTouch);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter=new MyAdapter(this);
        mRecyclerView.setAdapter(adapter);
        /* TUTAJ TRZEBA ZROBIC tak zebysmy po kliknieciu wchodzili w notatke, a po przytrzymaniu wyswietlaly sie opcje.
        Udalo mi sie zrobic ten kod niżej  na przycisku ale na naszych layoutach nie dziala. onClick na linearLayout dziala
        bo sprawdzalem ale to nie smiga nie wiem czemu.
        optionsClicker.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                    {
                        Toast.makeText(MyNotesActivity.this, "kliknięcie", Toast.LENGTH_SHORT).show();
                        return true;
                    }

                    case MotionEvent.ACTION_UP:
                    {

                        Toast.makeText(MyNotesActivity.this, "przytrzymanie", Toast.LENGTH_SHORT).show();
                    return true;
                    }

                    default: return false;
                }
            }
        });*/

    }



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
                bufferedReader = new BufferedReader(new FileReader(context.getFilesDir().getPath()+"/note.json"));
                String line;
                while ((line=bufferedReader.readLine()) != null){
                    //secondLineRTE.setRichTextEditing(true, line);
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
            holder.secondLine.setText(Html.fromHtml(dataArray.get(position)));


        }

        @Override
        public int getItemCount() {

            return dataArray.size();
        }
    }
}