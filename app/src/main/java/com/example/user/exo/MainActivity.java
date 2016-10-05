package com.example.user.exo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("리스트뷰 테스트");

        final EditText edtItem = (EditText) findViewById(R.id.editText);
        final Button btn = (Button) findViewById(R.id.button);


        final String[] mid = {"히어로즈", "24시", "로스트", "로스트룸", "스몰빌", "탐정몽크", "빅뱅이론", "프렌즈", "덱스터", "글리", "가쉽걸", "테이큰", "슈퍼내추럴", "브이"};

        ArrayList<String> entries = new ArrayList<String>(Arrays.asList(mid));

        final ArrayAdapter<String> midList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, entries);
        final ListView list = (ListView) findViewById(R.id.listView1);
        list.setAdapter(midList);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, mid[position], Toast.LENGTH_SHORT).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                midList.setNotifyOnChange(true);

                midList.add(edtItem.getText().toString());

                edtItem.setText("");

                list.setSelection(midList.getCount() - 1);

            }
        });

    }
}
