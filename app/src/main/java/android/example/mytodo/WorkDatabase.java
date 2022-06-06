package android.example.mytodo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Work.class, version = 1)
public abstract class WorkDatabase extends RoomDatabase {

    private static WorkDatabase instance;
    public abstract WorkDAO workDAO();
    public static synchronized WorkDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),WorkDatabase.class,"work_database")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
