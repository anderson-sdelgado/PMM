package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import br.com.usinasantafe.pmm.R;

public class MsgDataHoraActivity extends ActivityGeneric {

    private final int interval = 3000;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_data_hora);

        handler.postDelayed(runnable, interval);

    }

    private Runnable runnable = new Runnable(){
        public void run() {
            Intent it = new Intent( MsgDataHoraActivity.this, DataHoraActivity.class);
            startActivity(it);
            finish();
        }
    };

}
