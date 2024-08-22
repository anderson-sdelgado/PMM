package br.com.usinasantafe.cmm.util.workmanager;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class StartProcessEnvio {
    private WorkManager workManager;

    public void startProcessEnvio(@NonNull Application application){
        workManager = WorkManager.getInstance(application);
        Constraints constraints = new Constraints
                .Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest
                .Builder(ProcessWorkManager.class)
                                .setConstraints(constraints)
                .setBackoffCriteria(
                        BackoffPolicy.LINEAR,
                        2, TimeUnit.MINUTES
                )
                .build();
        workManager.enqueueUniqueWork("WORKMANAGER-PCP", ExistingWorkPolicy.REPLACE, workRequest);
    }

}
