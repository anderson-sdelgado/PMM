package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;

public class MsgDataHoraActivity extends ActivityGeneric {

    private final int interval = 3000;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_data_hora);

        LogProcessoDAO.getInstance().insertLogProcesso("handler.postDelayed(runnable, interval);", getLocalClassName());
        handler.postDelayed(runnable, interval);

    }

    private Runnable runnable = () -> {
        LogProcessoDAO.getInstance().insertLogProcesso("private Runnable runnable = new Runnable(){\n" +
                "        public void run() {\n" +
                "            Intent it = new Intent( MsgDataHoraActivity.this, DataHoraActivity.class);", getLocalClassName());
        Intent it = new Intent( MsgDataHoraActivity.this, DataHoraActivity.class);
        startActivity(it);
        finish();
    };

}
