package android.example.mytodo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WorkRepo {
    private WorkDAO workDAO;
    private LiveData<List<Work>> worklist;

    public WorkRepo(Application application){
        WorkDatabase workDatabase = WorkDatabase.getInstance(application);
        workDAO = workDatabase.workDAO();
        worklist = workDAO.getAllData();
    }

    public void insertData(Work work){new InsertTask(workDAO).execute(work);}
    public void updateData(Work work){new UpdateTask(workDAO).equals(work);}
    public void deleteData(Work work){new DeleteTask(workDAO).equals(work);}

    public LiveData<List<Work>> getAllData(){
        return worklist;
    }

    private static class InsertTask extends AsyncTask<Work, Void, Void>{

        private WorkDAO workDAO;

        public InsertTask(WorkDAO workDAO) {
            this.workDAO = workDAO;
        }

        @Override
        protected Void doInBackground(Work... works) {
            workDAO.insert(works[0]);
            return null;
        }
    }private static class DeleteTask extends AsyncTask<Work, Void, Void>{

        private WorkDAO workDAO;

        public DeleteTask(WorkDAO workDAO) {
            this.workDAO = workDAO;
        }

        @Override
        protected Void doInBackground(Work... works) {
            workDAO.delete(works[0]);
            return null;
        }
    }private static class UpdateTask extends AsyncTask<Work, Void, Void>{

        private WorkDAO workDAO;

        public UpdateTask(WorkDAO workDAO) {
            this.workDAO = workDAO;
        }

        @Override
        protected Void doInBackground(Work... works) {
            workDAO.update(works[0]);
            return null;
        }
    }
}
