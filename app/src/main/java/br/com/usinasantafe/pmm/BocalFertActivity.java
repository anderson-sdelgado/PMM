package br.com.usinasantafe.pmm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BocalFertActivity extends ActivityGeneric {

    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bocal_fert);

        pmmContext = (PMMContext) getApplication();

        Button buttonOkOS = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancOS = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (!editTextPadrao.getText().toString().equals("")) {
                    pmmContext.getApontaAplicFertTO().setBocalApontaAplicFert(Long.parseLong(editTextPadrao.getText().toString()));
                    Intent it = new Intent(BocalFertActivity.this, PressaoFertActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

        buttonCancOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
//                else {
//                    Intent it = new Intent(BocalFertActivity.this,  ListaAtividadeActivity.class);
//                    startActivity(it);
//                    finish();
//                }
            }
        });

    }

    public void onBackPressed()  {
            Intent it = new Intent(BocalFertActivity.this,  ListaAtividadeActivity.class);
            startActivity(it);
            finish();
    }

}
