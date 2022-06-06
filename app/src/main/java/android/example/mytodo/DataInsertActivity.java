package android.example.mytodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.example.mytodo.databinding.ActivityDataInsertBinding;
import android.os.Bundle;
import android.view.View;

public class DataInsertActivity extends AppCompatActivity {

    ActivityDataInsertBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String type = getIntent().getStringExtra("type");

        if(type.equals("update")){
            setTitle("update");
            binding.heading.setText(getIntent().getStringExtra("heading"));
            binding.description.setText(getIntent().getStringExtra("description"));
            int id = getIntent().getIntExtra("id",0);
            binding.addWork.setText("Update Note");
            binding.addWork.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("heading", binding.heading.getText().toString());
                    intent.putExtra("description", binding.description.getText().toString());
                    intent.putExtra("id", id);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });

        }else {
            setTitle("Add Mode");
            binding.addWork.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("heading", binding.heading.getText().toString());
                    intent.putExtra("description", binding.description.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DataInsertActivity.this, MainActivity.class));
    }
}