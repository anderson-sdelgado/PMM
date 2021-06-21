package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.util.ConexaoWeb;

public class DesengCarretaActivity extends ActivityGeneric {

    private PMMContext pmmContext;
    private TextView textViewMsgDesengCarreta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deseng_carreta);

        pmmContext = (PMMContext) getApplication();
        textViewMsgDesengCarreta = findViewById(R.id.textViewMsgDesengCarreta);

        Button buttonSimDesengate = findViewById(R.id.buttonSimDesengate);
        Button buttonNaoDesengate = findViewById(R.id.buttonNaoDesengate);

        textViewMsgDesengCarreta.setText(pmmContext.getMotoMecFertCTR().getDescrCarreta());

        buttonSimDesengate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pmmContext.getMotoMecFertCTR().delCarreta();

                ConexaoWeb conexaoWeb = new ConexaoWeb();
                if (conexaoWeb.verificaConexao(DesengCarretaActivity.this)) {
                    pmmContext.getConfigCTR().setStatusConConfig(1L);
                }
                else{
                    pmmContext.getConfigCTR().setStatusConConfig(0L);
                }

                pmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude());

                if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 19L){
                    Intent it = new Intent(DesengCarretaActivity.this, MenuPrincECMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 21L){
                    Intent it = new Intent(DesengCarretaActivity.this, MenuParadaECMActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        buttonNaoDesengate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 19L){
                    Intent it = new Intent(DesengCarretaActivity.this, MenuPrincECMActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(pmmContext.getConfigCTR().getConfig().getPosicaoTela() == 21L){
                    Intent it = new Intent(DesengCarretaActivity.this, MenuParadaECMActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

    }
}