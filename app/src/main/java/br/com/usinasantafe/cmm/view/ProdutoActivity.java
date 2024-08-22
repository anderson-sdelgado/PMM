package br.com.usinasantafe.cmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.cmm.CMMContext;
import br.com.usinasantafe.cmm.R;
import br.com.usinasantafe.cmm.model.bean.estaticas.ProdutoBean;
import br.com.usinasantafe.cmm.model.dao.LogProcessoDAO;

public class ProdutoActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private TextView txtResult;
    private ProdutoBean produtoBean;
    private CMMContext cmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        cmmContext = (CMMContext) getApplication();
        txtResult = findViewById(R.id.txResult);

        Button buttonOkOS = findViewById(R.id.buttonOkProd);
        Button buttonCancOS = findViewById(R.id.buttonCancProd);
        Button btnCapturaBarra = findViewById(R.id.btnCapturaBarra);

        buttonOkOS.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkOS.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());

            if(!txtResult.getText().equals("PRODUTO:")) {

                LogProcessoDAO.getInstance().insertLogProcesso("if(!txtResult.getText().equals(\"PRODUTO:\")) {\n" +
                        "                    if (connectNetwork) {\n" +
                        "                        pmmContext.getConfigCTR().setStatusConConfig(1L);\n" +
                        "                    }\n" +
                        "                    else{\n" +
                        "                        pmmContext.getConfigCTR().setStatusConConfig(0L);\n" +
                        "                    }", getLocalClassName());

                if (connectNetwork) {
                    cmmContext.getConfigCTR().setStatusConConfig(1L);
                }
                else{
                    cmmContext.getConfigCTR().setStatusConConfig(0L);
                }

                LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude(), getLocalClassName());\n" +
                        "                    pmmContext.getConfigCTR().setPosFluxoCarregComposto(2L);\n" +
                        "                    pmmContext.getCompostoCTR().abrirCarregInsumo(produtoBean);\n" +
                        "                    Intent it = new Intent(ProdutoActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
                cmmContext.getMotoMecFertCTR().salvarApont(cmmContext, getLongitude(), getLatitude(), getLocalClassName());
                cmmContext.getCompostoCTR().abrirCarregInsumo(produtoBean);

                Intent it = new Intent(ProdutoActivity.this, MenuPrincPCOMPActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonCancOS.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancOS.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Intent it = new Intent(ProdutoActivity.this, MenuPrincPCOMPActivity.class);", getLocalClassName());
            Intent it = new Intent(ProdutoActivity.this, MenuPrincPCOMPActivity.class);
            startActivity(it);
            finish();

        });

        btnCapturaBarra.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("btnCapturaBarra.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Intent it = new Intent(ProdutoActivity.this, br.com.usinasantafe.pmm.zxing.CaptureActivity.class);", getLocalClassName());
            Intent it = new Intent(ProdutoActivity.this, br.com.usinasantafe.cmm.zxing.CaptureActivity.class);
            startActivityForResult(it, REQUEST_CODE);

        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            LogProcessoDAO.getInstance().insertLogProcesso("if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){\n" +
                    "            String cod = data.getStringExtra(\"SCAN_RESULT\");", getLocalClassName());
            String cod = data.getStringExtra("SCAN_RESULT");
            if (cmmContext.getCompostoCTR().verProduto(cod)) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (pmmContext.getCompostoCTR().verProduto(cod)) {\n" +
                        "                produtoBean = pmmContext.getCompostoCTR().getProduto(cod);\n" +
                        "                txtResult.setText(\"PRODUTO: \" + produtoBean.getCodProduto() + \"\\n\" + produtoBean.getDescProduto());", getLocalClassName());
                produtoBean = cmmContext.getCompostoCTR().getProduto(cod);
                txtResult.setText("PRODUTO: " + produtoBean.getCodProduto() + "\n" + produtoBean.getDescProduto());
            }

        }

    }

    public void onBackPressed()  {
    }

}