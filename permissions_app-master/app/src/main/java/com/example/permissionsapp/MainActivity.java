package com.example.permissionsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button Search_button;
    Button Add_button;
    EditText editText;
    ListView listView;
    public static final String plist= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Search_button=findViewById(R.id.search_button);
        Add_button=findViewById(R.id.add_button);
        editText=findViewById(R.id.permissions_search);
        listView=findViewById(R.id.addedList);

        ArrayList<String> permissionsList=new ArrayList();

        Add_button.setOnClickListener(view->{
            String permissions =editText.getText().toString();
            permissions="android.permission."+permissions;
            permissionsList.add(permissions);
            ArrayAdapter<String> arr;
            arr = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,permissionsList);
            listView.setAdapter(arr);
            editText.setText("");

        });

        Search_button.setOnClickListener(view -> {
            Intent intent = new Intent (this,MainActivity2.class);
            intent.putStringArrayListExtra("plist", permissionsList);
            startActivity(intent);

        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = (String) parent.getItemAtPosition(position);
            permissionsList.remove(selectedItem);
            ArrayAdapter<String> arr;
            arr = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,permissionsList);
            listView.setAdapter(arr);


        });







    }
}