package wabbo.com.lab22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import wabbo.com.lab22.db.User;
import wabbo.com.lab22.db.SQLHelper;
import wabbo.com.lab22.db.SQLAdapter;

public class MainActivity2 extends AppCompatActivity {
    TextView name , mob ;
    Button  close , setsh ,setfile ,setsql , getsh , getfile ,getsql ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name = findViewById(R.id.name) ;
        mob = findViewById(R.id.mob) ;
        close = findViewById(R.id.close) ;

        setsh = findViewById(R.id.setsh) ;
        setfile = findViewById(R.id.setfile) ;
        setsql = findViewById(R.id.setsql) ;
        getsh = findViewById(R.id.getsh) ;
        getfile = findViewById(R.id.getfile) ;
        getsql = findViewById(R.id.getsql) ;

        Intent intent = getIntent() ;

        name.setText(intent.getStringExtra("name"));
        mob.setText(intent.getStringExtra("mob"));

        close.setOnClickListener(v -> {
            finish();
        });

        setsh.setOnClickListener(v -> {
            SharedPreferences preferences  = getSharedPreferences("myData" , MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("mob",mob.getText().toString());
            editor.putString("name",name.getText().toString());
            editor.commit();
            mob.setText("");
            name.setText("");

        });
        getsh.setOnClickListener(v -> {
            SharedPreferences preferences = getSharedPreferences("myData", MODE_PRIVATE) ;
            mob.setText(preferences.getString("mob","??"));
            name.setText(preferences.getString("name","??"));
        });

        setfile.setOnClickListener(v -> {
            try {
                // el sny3y
                FileOutputStream stream = openFileOutput("myData",MODE_PRIVATE);
                // mohands el dikor
                DataOutputStream outputStream = new DataOutputStream(stream) ;
                outputStream.writeUTF(mob.getText().toString());
                outputStream.writeUTF(name.getText().toString());
                outputStream.flush();
                outputStream.close();
                stream.close();
                mob.setText("");
                name.setText("");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        getfile.setOnClickListener(v -> {
            try {
                FileInputStream inputStream = openFileInput("myData") ;
                DataInputStream dataInputStream = new DataInputStream(inputStream) ;
                mob.setText(dataInputStream.readUTF());
                name.setText(dataInputStream.readUTF());
                dataInputStream.close();
                inputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        setsql.setOnClickListener(v -> {

            String name = this.name.getText().toString();
            String phone = this.mob.getText().toString();

            User user = new User(name, phone);

            SQLAdapter dataBaseAdapter = new SQLAdapter(this);
            dataBaseAdapter.insert(user);

            this.mob.setText("");
        });
        getsql.setOnClickListener(v -> {

            SQLAdapter dataBaseAdapter = new SQLAdapter(this);
            User user = dataBaseAdapter.getUserByName( this.name.getText().toString() );
            if (user != null) {
                this.name.setText(user.getName());
                this.mob.setText(user.getPhone());
            }
        });
    }
}