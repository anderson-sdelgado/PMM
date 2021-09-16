package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pmm.PMMContext;
import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.bean.estaticas.ProdutoBean;

public class ProdutoActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private TextView txtResult;
    private ProdutoBean produtoBean;
    private PMMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        pmmContext = (PMMContext) getApplication();
        txtResult = findViewById(R.id.txResult);

        Button buttonOkOS = findViewById(R.id.buttonOkProd);
        Button buttonCancOS = findViewById(R.id.buttonCancProd);
        Button btnCapturaBarra = findViewById(R.id.btnCapturaBarra);

        buttonOkOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!txtResult.getText().equals("PRODUTO:")) {

                    if (connectNetwork) {
                        pmmContext.getConfigCTR().setStatusConConfig(1L);
                    }
                    else{
                        pmmContext.getConfigCTR().setStatusConConfig(0L);
                    }

                    pmmContext.getMotoMecFertCTR().salvarApont(getLongitude(), getLatitude());
                    pmmContext.getConfigCTR().setPosFluxoCarregComposto(2L);
                    pmmContext.getCompostoCTR().abrirCarregInsumo(produtoBean);

                    Intent it = new Intent(ProdutoActivity.this, MenuPrincPCOMPActivity.class);
                    startActivity(it);
                    finish();

                }

            }
        });

        buttonCancOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(ProdutoActivity.this, MenuPrincPCOMPActivity.class);
                startActivity(it);
                finish();

            }
        });

        btnCapturaBarra.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(ProdutoActivity.this, br.com.usinasantafe.pmm.zxing.CaptureActivity.class);
                startActivityForResult(it, REQUEST_CODE);

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){

            String cod = data.getStringExtra("SCAN_RESULT");
            if (pmmContext.getCompostoCTR().verProduto(cod)) {
                produtoBean = pmmContext.getCompostoCTR().getProduto(cod);
                txtResult.setText("PRODUTO: " + produtoBean.getCodProduto() + "\n" + produtoBean.getDescProduto());
            }

        }

    }

    public void onBackPressed()  {
    }

}