package in.kitcoek.acses2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuActivity extends AppCompatActivity
{
    Toolbar toolbar;
    EditText name,clg,amount,mobile,email;
    private AdView mAdView;
    Button REGISTER;
    String EVENT;
    long maxidJ=0;
    long maxidC=0;
    int sr=0;
    DatabaseReference CRef,JRef;
    boolean C=true;
    String NAME,CLG,AMOUNT,MOBILE,EMAIL,SERIAL;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Spinner spinner = findViewById(R.id.event);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        name = findViewById(R.id.etStudent);
        clg = findViewById(R.id.etCollege);
        amount = findViewById(R.id.etAmount);
        mobile = findViewById(R.id.etMobile);
        email = findViewById(R.id.etEmail);
        REGISTER = findViewById(R.id.register);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,R.array.Events,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);



        CRef = FirebaseDatabase.getInstance().getReference().child("C Code War");
        JRef = FirebaseDatabase.getInstance().getReference().child("JAVA Code War");
        EVENT = "C Code War";

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                EVENT = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(MenuActivity.this,EVENT,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        CRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                maxidC =(dataSnapshot.getChildrenCount());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        JRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                maxidJ =(dataSnapshot.getChildrenCount());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        REGISTER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(EVENT.equals("C Code War"))
                {
                    NAME = name.getText().toString();
                    CLG = clg.getText().toString();
                    AMOUNT = amount.getText().toString();
                    MOBILE = mobile.getText().toString();
                    EMAIL = email.getText().toString();
                    sr = (int)maxidC;
                    sr++;
                    SERIAL= Integer.toString(sr);
                    Student student = new Student(SERIAL, NAME,EMAIL,MOBILE,CLG);
                    CRef.child(String.valueOf(maxidC+1)).setValue(student);
                    Toast.makeText(MenuActivity.this, "Student Registered", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    NAME = name.getText().toString();
                    CLG = clg.getText().toString();
                    AMOUNT = amount.getText().toString();
                    MOBILE = mobile.getText().toString();
                    EMAIL = email.getText().toString();
                    Student student = new Student(Long.toString(maxidJ+1),NAME,EMAIL,MOBILE,CLG);
                    JRef.child(String.valueOf(maxidJ+1)).setValue(student);
                    Toast.makeText(MenuActivity.this, "Student Registered", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.SignOut:
                signOut();
                break;

            case R.id.View_Data:
                Intent intent = new Intent(MenuActivity.this,ViewActivity.class);
                startActivity(intent);
                break;

            case R.id.about:
                Toast.makeText(MenuActivity.this,"Hmmmmmmmmmmm",Toast.LENGTH_SHORT).show();
                break;

            case android.R.id.home:
                dialogBox();
        }
        return true;
    }

    void signOut()
    {
        FirebaseUser mUser = mAuth.getCurrentUser();
        mAuth.signOut();

        startActivity(new Intent(MenuActivity.this,LoginActivity.class));
        finish();
    }

    private void dialogBox()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);

        builder.setMessage("Are you Sure you want to Exit?").setCancelable(false)
                .setPositiveButton("Nikal Pehili Fursat me", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();

            }
        }).setNegativeButton("Nahi Sorry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        dialogBox();
    }
}
