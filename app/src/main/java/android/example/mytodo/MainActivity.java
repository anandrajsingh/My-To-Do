package android.example.mytodo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.example.mytodo.databinding.ActivityMainBinding;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private WorkViewModel workViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        workViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(WorkViewModel.class);

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DataInsertActivity.class);
                intent.putExtra("type","addMode");
                startActivityForResult(intent,1);
            }
        });

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter();
        binding.recyclerView.setAdapter(adapter);

        workViewModel.getAllWorks().observe(this, new Observer<List<Work>>() {
            @Override
            public void onChanged(List<Work> works) {
                adapter.submitList(works);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(direction==ItemTouchHelper.RIGHT) {
                    workViewModel.delete(adapter.getWork(viewHolder.getAdapterPosition()));
                    Toast.makeText(MainActivity.this, "Work Deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(MainActivity.this, DataInsertActivity.class);
                    intent.putExtra("type","update");
                    intent.putExtra("heading",adapter.getWork(viewHolder.getAdapterPosition()).getHeading());
                    intent.putExtra("description",adapter.getWork(viewHolder.getAdapterPosition()).getDescription());
                    intent.putExtra("id",adapter.getWork(viewHolder.getAdapterPosition()).getId());
                    startActivityForResult(intent,2);
                }
            }
        }).attachToRecyclerView(binding.recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            String heading = data.getStringExtra("heading");
            String description = data.getStringExtra("description");
            Work work = new Work(heading, description);
            workViewModel.insert(work);
            Toast.makeText(this,"Work Added",Toast.LENGTH_SHORT).show();

        }else if(requestCode == 2){
            String heading = data.getStringExtra("heading");
            String description = data.getStringExtra("description");
            Work work = new Work(heading,description);
            work.setId(data.getIntExtra("id",0));
            workViewModel.update(work);
            Toast.makeText(this,"Work Updated",Toast.LENGTH_SHORT).show();
        }
    }
}