package in.kitcoek.acses2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    EditText Email,Password;
    String EMAIL,PASSWORD;
    Button login;
    TextView SignUp;
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email = findViewById(R.id.username);
        Password = findViewById(R.id.pass);
        login = findViewById(R.id.login);
        SignUp = findViewById(R.id.btSignUp);
        firebaseAuth = FirebaseAuth.getInstance();


        if(firebaseUser!=null)
    {
        Intent intent = new Intent(LoginActivity.this,MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EMAIL = Email.getText().toString();
                    PASSWORD = Password.getText().toString();
                    if(EMAIL.isEmpty()||PASSWORD.isEmpty())
                    {
                        Toast.makeText(LoginActivity.this,"Enter Required Field",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        firebaseAuth.signInWithEmailAndPassword(EMAIL,PASSWORD)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Intent intent1 = new Intent(LoginActivity.this,MenuActivity.class);
                                    intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent1);
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(LoginActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            });


        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

}
