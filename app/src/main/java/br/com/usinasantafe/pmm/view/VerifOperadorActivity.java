package br.com.usinasantafe.pmm.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.dao.LogProcessoDAO;

public class VerifOperadorActivity extends ActivityGeneric {

    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verif_operador);

        pmmContext = (PMMContext) getApplication();

        Button buttonManterMotorista = findViewById(R.id.buttonManterMotorista);
        Button buttonAlterarMotorista = findViewById(R.id.buttonAlterarMotorista);
        TextView textViewCodMotorista = findViewById(R.id.textViewCodMotorista);
        TextView textViewNomeMotorista = findViewById(R.id.textViewNomeMotorista);

        textViewCodMotorista.setText(String.valueOf(pmmContext.getMotoMecFertCTR().getMatricFunc().getMatricFunc()));
        textViewNomeMotorista.setText(pmmContext.getMotoMecFertCTR().getMatricFunc().getNomeFunc());

        buttonManterMotorista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonManterMotorista.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(VerifOperadorActivity.this, MsgSaidaCampoActivity.class);", getLocalClassName());
                Intent it = new Intent(VerifOperadorActivity.this, MsgSaidaCampoActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonAlterarMotorista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonAlterarMotorista.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                AlertDialog.Builder alerta = new AlertDialog.Builder(VerifOperadorActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"DESEJA REALMENTE TROCA O MOTORISTA? ISSO ENCERRA-LA O BOLETIM.\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(VerifOperadorActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE TROCA O MOTORISTA? ISSO ENCERRA-LA O BOLETIM.");
                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {\n" +
                                "                        pmmContext.getConfigCTR().setPosicaoTela(17L);\n" +
                                "                        Intent it = new Intent(VerifOperadorActivity.this, HorimetroActivity.class);", getLocalClassName());
                        pmmContext.getConfigCTR().setPosicaoTela(17L);
                        Intent it = new Intent(VerifOperadorActivity.this, HorimetroActivity.class);
                        startActivity(it);
                        finish();
                    }
                });

                alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                    }
                });

                alerta.show();

            }
        });

    }

    public void onBackPressed()  {
    }

}