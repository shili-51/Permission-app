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
        //adding permissions in the form of list in permissionlist string
        //and creating adapter to show the permission list in the form of list
        Add_button.setOnClickListener(view->{
            String permissions =editText.getText().toString();
            permissions="android.permission."+permissions;
            permissionsList.add(permissions);
            ArrayAdapter<String> arr;
            arr = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,permissionsList);
            listView.setAdapter(arr);
            editText.setText("");

        });
       //when we click on search button
        //intent passes permission list to another activity
        Search_button.setOnClickListener(view -> {
            Intent intent = new Intent (this,MainActivity2.class);
            intent.putStringArrayListExtra("plist", permissionsList);
            startActivity(intent);

        });
         //to remove an element from permission list on clicking on that permission
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = (String) parent.getItemAtPosition(position);
            permissionsList.remove(selectedItem);
            ArrayAdapter<String> arr;
            arr = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,permissionsList);
            listView.setAdapter(arr);


        });







    }
}