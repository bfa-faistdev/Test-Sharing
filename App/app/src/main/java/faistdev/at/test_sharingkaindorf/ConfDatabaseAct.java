package faistdev.at.test_sharingkaindorf;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class ConfDatabaseAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf_database);

        Button btDBsave = findViewById(R.id.btSaveDB);
        final EditText etDBusername = findViewById(R.id.etDBusername);
        final EditText etDBpassword = findViewById(R.id.etDBpassword);


        btDBsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * Get Database instance
                 * Check if Database Username and Database Password are filled out
                 * Call db.buildDatabase()
                 * If configuration successfully completed -> open Registration form
                 */

                Database db;
                try{
                    db = Database.getInstance();
                    if(etDBusername.getText()!=null && etDBpassword.getText()!=null){
                        db.setUsername(etDBusername.getText().toString());
                        db.setPassword(etDBpassword.getText().toString());
                        if(db.buildDatabase()){
                            //Database Con established
                            System.out.println("Database established");
                            ConfDatabaseAct.this.startActivity(new Intent(ConfDatabaseAct.this, RegisterActivity.class));
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "All fields must be filled out!", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    System.out.println("Error!");
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Is your login information correct?", Toast.LENGTH_SHORT).show();

                }

            }
        });



    }




}


