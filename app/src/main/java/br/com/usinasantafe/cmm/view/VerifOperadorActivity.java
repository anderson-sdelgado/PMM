package br.com.usinasantafe.cmm.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class VerifOperadorActivity extends ActivityGeneric {

    private CMMContext cmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verif_operador);

        cmmContext = (CMMContext) getApplication();

        Button buttonManterMotorista = findViewById(R.id.buttonManterMotorista);
        Button buttonAlterarMotorista = findViewById(R.id.buttonAlterarMotorista);
        TextView textViewCodMotorista = findViewById(R.id.textViewCodMotorista);
        TextView textViewNomeMotorista = findViewById(R.id.textViewNomeMotorista);

        textViewCodMotorista.setText(String.valueOf(cmmContext.getMotoMecFertCTR().getMatricFunc().getMatricFunc()));
        textViewNomeMotorista.setText(cmmContext.getMotoMecFertCTR().getMatricFunc().getNomeFunc());

        buttonManterMotorista.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonManterMotorista.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Intent it = new Intent(VerifOperadorActivity.this, MsgSaidaCampoActivity.class);", getLocalClassName());
            Intent it = new Intent(VerifOperadorActivity.this, MsgSaidaCampoActivity.class);
            startActivity(it);
            finish();

        });

        buttonAlterarMotorista.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonAlterarMotorista.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                AlertDialog.Builder alerta = new AlertDialog.Builder(VerifOperadorActivity.this);\n" +
                    "                alerta.setTitle(\"ATENÇÃO\");\n" +
                    "                alerta.setMessage(\"AO REALIZAR A ALTERAÇÃO O PROCESSO ENCERRARÁ O BOLETIM ATUAL E UM NOVO SERÁ CRIADO. DESEJA REALMENTE ALTERAR O MOTORISTA?\");", getLocalClassName());
            AlertDialog.Builder alerta = new AlertDialog.Builder(VerifOperadorActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("AO REALIZAR A ALTERAÇÃO O PROCESSO ENCERRARÁ O BOLETIM ATUAL E UM NOVO SERÁ CRIADO. DESEJA REALMENTE ALTERAR O MOTORISTA?");
            alerta.setPositiveButton("SIM", (dialog, which) -> {
                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                        "                    @Override\n" +
                        "                    public void onClick(DialogInterface dialog, int which) {\n" +
                        "                        pmmContext.getConfigCTR().setPosicaoTela(17L);\n" +
                        "                        pmmContext.getMotoMecFertCTR().inserirParadaTrocaMotorista(getLocalClassName());\n" +
                        "                        Intent it = new Intent(VerifOperadorActivity.this, HorimetroActivity.class);", getLocalClassName());
                cmmContext.getConfigCTR().setPosicaoTela(17L);
                cmmContext.getMotoMecFertCTR().inserirParadaTrocaMotorista(getLocalClassName());
                Intent it = new Intent(VerifOperadorActivity.this, HorimetroActivity.class);
                startActivity(it);
                finish();
            });

            alerta.setNegativeButton("NÃO", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                    "                    @Override\n" +
                    "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));
            alerta.show();

        });

    }

    public void onBackPressed()  {
    }

}