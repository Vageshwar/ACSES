package in.kitcoek.acses2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    String EVENT;
    List<Student> studentslist;
    private AdView mAdView;

    ListView listView;
    DatabaseReference CRef,JRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

    }


    @Override
    protected void onStart() {
        super.onStart();
        listView = findViewById(R.id.listView);
        studentslist = new ArrayList<>();

        EVENT = "C Code War";
        CRef = FirebaseDatabase.getInstance().getReference().child("C Code War");
        JRef = FirebaseDatabase.getInstance().getReference().child("JAVA Code War");
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,R.array.Events,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                EVENT = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(ViewActivity.this,EVENT,Toast.LENGTH_SHORT).show();
                if(EVENT.equals("C Code War"))
                {
                    CRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            studentslist.clear();
                            for(DataSnapshot studentSnapshot : dataSnapshot.getChildren())
                            {
                                Student student = studentSnapshot.getValue(Student.class);
                                studentslist.add(student);
                            }
                            StudentList adapter = new StudentList(ViewActivity.this,studentslist);
                            listView.setAdapter(adapter);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else
                {
                    JRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            studentslist.clear();
                            for(DataSnapshot studentSnapshot : dataSnapshot.getChildren())
                            {
                                Student student = studentSnapshot.getValue(Student.class);
                                studentslist.add(student);
                            }
                            StudentList adapter = new StudentList(ViewActivity.this,studentslist);
                            listView.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }
}
