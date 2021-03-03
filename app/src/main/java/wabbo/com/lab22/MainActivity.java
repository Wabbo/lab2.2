package wabbo.com.lab22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText name , mob ;
    Button send , close ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.ET_name);
        mob = findViewById(R.id.ET_mob);
        send = findViewById(R.id.send);
        close = findViewById(R.id.close);

        close.setOnClickListener(v -> {
            finish();
        });
        send.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this , MainActivity2.class) ;
            intent.putExtra("name",name.getText().toString());
            intent.putExtra("mob",mob.getText().toString());
            startActivity(intent);
        });

    }
}