package br.com.usinasantafe.pmm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PressaoFertActivity extends ActivityGeneric {

    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressao_fert);

        pmmContext = (PMMContext) getApplication();

        Button buttonOkOS = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancOS = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (!editTextPadrao.getText().toString().equals("")) {
                    String pressao = editTextPadrao.getText().toString();
                    Double pressaoNum = Double.valueOf(pressao.replace(",", "."));
                    pmmContext.getApontaFertTO().setPressaoApontaFert(pressaoNum);
                    Intent it = new Intent(PressaoFertActivity.this, VelocidadeFertActivity.class);
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
            }
        });

    }

    public void onBackPressed() {
        Intent it = new Intent(PressaoFertActivity.this, BocalFertActivity.class);
        startActivity(it);
        finish();
    }

}
