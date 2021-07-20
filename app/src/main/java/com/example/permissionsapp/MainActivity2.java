package com.example.permissionsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    ListView listView;
    TextView textView;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         //receiving the intent of main activity
        Intent intent = getIntent();

        ArrayList<String> permissions =new ArrayList();

        permissions=intent.getStringArrayListExtra("plist");
        ArrayList<String> allApp=new ArrayList();
        int j = 0;
        String[] permissionsstrings=new String[permissions.size()];
        for (String str : permissions) {
            permissionsstrings[j] = str;
            j++;
        }
        //finding all apps with the help of package manager
        try {
        List<PackageInfo> ApplicationsList=getPackageManager().getPackagesHoldingPermissions(permissionsstrings,PackageManager.GET_META_DATA);
        int i=0;
        //checking apps for each particular permission
        for(PackageInfo app : ApplicationsList){
            allApp.add(app.packageName);
            i++;
        }
            listView = findViewById(R.id.appList);
            ArrayAdapter<String> arr;
            arr = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, allApp);
            listView.setAdapter(arr);}
        catch(Exception ignored){}

        textView=findViewById(R.id.app_count);
       //app count
        textView.setText(allApp.size()+" apps found");
        //getting to the settings of the particular app clicked
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = (String) parent.getItemAtPosition(position);
            Intent intent1 =new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri. fromParts("package", selectedItem, null));
            startActivity(intent1);

        });

    }
}