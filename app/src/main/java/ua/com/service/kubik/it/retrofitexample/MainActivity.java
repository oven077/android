package ua.com.service.kubik.it.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.UUID;

import ua.com.service.kubik.it.retrofitexample.workmanager.WorkData;

public class MainActivity extends AppCompatActivity {
    private Button myButtonImport;
    private Button myButtonExport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        myButtonImport = (Button) findViewById(R.id.idButtonImport);
        myButtonExport = (Button) findViewById(R.id.idButtonExport);

        myButtonImport.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Data dataLoad = new Data.Builder()
                        .putString("param1", "param")
                        .build();
                OneTimeWorkRequest loadDataRequest = new OneTimeWorkRequest.Builder(WorkData.class)
                        .setInputData(dataLoad)
                        .build();
                WorkManager.getInstance().enqueue(loadDataRequest);

                setResultWorker(loadDataRequest.getId());

            }
        });

       /* myButtonExport.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Data dataLoad = new Data.Builder()
                        .putString("param1", "param")
                        .build();
                OneTimeWorkRequest loadDataRequest = new OneTimeWorkRequest.Builder(WorkData.class)
                        .setInputData(dataLoad)
                        .build();
                WorkManager.getInstance().enqueue(loadDataRequest);

                setResultWorker(loadDataRequest.getId());

            }
        });*/





    }

    private void setResultWorker(UUID workID) {
        final AppCompatActivity context = this;
        LiveData<WorkInfo> ld = WorkManager.getInstance().getWorkInfoByIdLiveData(workID);
        ld.observe(context, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                if (workInfo.getState().isFinished()) {
                    String msg = workInfo.getOutputData().getString("textMessage");
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
