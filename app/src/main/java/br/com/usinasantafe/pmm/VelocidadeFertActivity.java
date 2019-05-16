package br.com.usinasantafe.pmm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VelocidadeFertActivity extends ActivityGeneric {

    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velocidade_fert);

        pmmContext = (PMMContext) getApplication();

        Button buttonOkOS = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancOS = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (!editTextPadrao.getText().toString().equals("")) {
                    pmmContext.getApontaFertTO().setVelocApontaFert(Long.parseLong(editTextPadrao.getText().toString()));
                    Intent it = new Intent(VelocidadeFertActivity.this, RaioFertActivity.class);
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
                } else {
                    Intent it = new Intent(VelocidadeFertActivity.this,  PressaoFertActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

    }

    public void onBackPressed()  {
    }

}
