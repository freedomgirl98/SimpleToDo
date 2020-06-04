package sg.edu.rp.c346.id19036308.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etTasks;
    Button btnAdd, btnClear, btnDelete;
    ListView lvTasks;
    Spinner spnTasks;

    ArrayList<String> alTasks;
    ArrayAdapter<String> aaTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTasks = findViewById(R.id.editTextTasks);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        lvTasks = findViewById(R.id.listViewTasks);
        spnTasks = findViewById(R.id.spinner);
        btnDelete = findViewById(R.id.buttonDelete);

        alTasks = new ArrayList<>();

        aaTasks = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTasks);

        lvTasks.setAdapter(aaTasks);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = etTasks.getText().toString();
                alTasks.add(taskName);
                aaTasks.notifyDataSetChanged();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                for(int i=0; i<alTasks.size(); i++){
//                    alTasks.remove(i);
//                }
//                int pos = Integer.parseInt(etTasks.getText().toString());
                alTasks.clear();
                aaTasks.notifyDataSetChanged();
                etTasks.setText(null);
                Toast.makeText(MainActivity.this, "Task Cleared.", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alTasks.size()==0){
                    Toast.makeText(MainActivity.this, "You do not have any task to be removed", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    int i = Integer.parseInt(etTasks.getText().toString());
                    if(alTasks.size()<= i){
                        Toast.makeText(MainActivity.this, "You have entered the wrong index number", Toast.LENGTH_SHORT).show();
                        return;
                    } else{
                        alTasks.remove(i);
                        aaTasks.notifyDataSetChanged();
                        etTasks.setText(null);
                    }
                }
            }
        });
        spnTasks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        //Your code for item 1 selected
                        etTasks.setHint("Type in a new task here");
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        break;

                    case 1:
                        //Your code for item 1 selected
                        etTasks.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
