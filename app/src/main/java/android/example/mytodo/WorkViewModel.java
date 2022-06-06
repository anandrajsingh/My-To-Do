package android.example.mytodo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WorkViewModel extends AndroidViewModel {
    private WorkRepo workRepo;
    private LiveData<List<Work>> worklist;

    public WorkViewModel(@NonNull Application application) {
        super(application);

        workRepo = new WorkRepo(application);
        worklist = workRepo.getAllData();
    }

    public void insert(Work work){workRepo.insertData(work);}
    public void update(Work work){workRepo.updateData(work);}
    public void delete(Work work){workRepo.deleteData(work);}

    public LiveData<List<Work>> getAllWorks(){return worklist;}
}
