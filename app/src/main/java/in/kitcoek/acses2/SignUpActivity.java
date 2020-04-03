package in.kitcoek.acses2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    EditText EMAIL,PASSWORD,CONFIRM;
    Button SIGNUP;
    String Email,Password,Confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EMAIL = findViewById(R.id.suemail);
        PASSWORD = findViewById(R.id.supass);
        CONFIRM = findViewById(R.id.confirm);
        SIGNUP = findViewById(R.id.SignUp);
        mAuth = FirebaseAuth.getInstance();



        SIGNUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Email = EMAIL.getText().toString();
                Password = PASSWORD.getText().toString();
                Confirm = CONFIRM.getText().toString();
                if(Email.isEmpty()||Password.isEmpty()||Confirm.isEmpty())
                    Toast.makeText(SignUpActivity.this,"Please Fill all Fields",Toast.LENGTH_SHORT).show();
                else
                {
                    if(!Password.equals(Confirm))
                        Toast.makeText(SignUpActivity.this,"Passwords do not Match",Toast.LENGTH_SHORT).show();

                    else
                    {
                        mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignUpActivity.this, "User Signned UP successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignUpActivity.this,MenuActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else
                                {
                                    Toast.makeText(SignUpActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }

                        });
                    }
                }
            }
        });
    }

}

