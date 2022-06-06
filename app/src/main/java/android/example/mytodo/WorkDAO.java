package android.example.mytodo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WorkDAO {

    @Insert
    public void insert(Work work);
    @Update
    public void update(Work work);
    @Delete
    public void delete(Work work);

    @Query("SELECT * FROM my_work")
    public LiveData<List<Work>> getAllData();
}
