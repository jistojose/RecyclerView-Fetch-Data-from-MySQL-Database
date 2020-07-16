package com.example.inspirem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.toolbox.Authenticator;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/* created by jisto jose on 10-02-2020 */
public class myevents extends AppCompatActivity {
    private static final String url=Constants.URL_MYEVENTS;
    ListView lv;
    Bundle b;
    TextView email;
    //String email,password;
    ArrayList<String>holder1=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myevents);

         email= (TextView) findViewById(R.id.email);
         //final String a;


        SharedPreferences bb = getSharedPreferences("my_prefs", 0);
        String m = bb.getString("email", "");
        email.setText(m);
        //a=m;




        fetchdata();
/*
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position<ja.length){
                    Intent intent = new Intent(getApplicationContext(), Eventdetails.class);
                    startActivity(intent);

                }
            }
        });

*/

    }



    private void fetchdata() {
        lv=(ListView)findViewById(R.id.lv);



        class dbManager extends AsyncTask<String,Void,String>
        {


            protected void onPostExecute(String data)
            {
                try{
                    final JSONArray ja=new JSONArray(data);
                    JSONObject jo=null;
                    for(int i=0;i<ja.length();i++)
                    {

                       // if (jo.getString("email").equals(myevents.this.email));
                      //  {


                        jo=ja.getJSONObject(i);
                        String name="\n\n\t\t"+"EVENT NAME"+"\t\t"+":"+"\t\t" +jo.getString( "name").toUpperCase()+"\n\n\t\t"+"LOCATION"+"\t\t"+"\t\t\t"+":"+"\t\t" +jo.getString( "ewhere").toUpperCase()+"\n\n\t\t"+"USERNAME"+"\t\t\t\t"+":"+"\t\t" +jo.getString( "username").toUpperCase()+"\n\n\t\t"+"PHONE "+"\t\t\t\t\t\t\t"+":"+"\t\t" +jo.getString( "phone").toUpperCase()+"\n\n";
                        holder1.add(0,name);
                    }


                   // }


                    ArrayAdapter<String> at=new ArrayAdapter<String>(getApplicationContext(),R.layout.row,holder1);

                    lv.setAdapter(at);
                    at.notifyDataSetChanged();
                    lv.smoothScrollToPosition(0);




                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
                }

            }
            @Override
            protected String doInBackground(String... strings)
            {
                try{
                    URL url = new URL(strings[0]);
                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                    BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuffer data=new StringBuffer();
                    String line;
                    while ((line=br.readLine())!=null)
                    {
                        data.append(line+"\n");
                    }
                    br.close();
                    return data.toString();
                }catch(Exception ex){
                    return ex.getMessage();
                }
            }
        }
        dbManager obj=new dbManager();
        obj.execute(url);
    }










}


