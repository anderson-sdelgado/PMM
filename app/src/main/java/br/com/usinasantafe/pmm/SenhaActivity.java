package br.com.usinasantafe.pmm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import br.com.usinasantafe.pmm.to.tb.variaveis.ConfigTO;

public class SenhaActivity extends ActivityGeneric {

    private EditText editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senha);

        editTextSenha = (EditText)  findViewById(R.id.editTextSenha);
        Button btOkSenha =  (Button) findViewById(R.id.buttonOkSenha);
        Button btCancSenha = (Button) findViewById(R.id.buttonCancSenha);

        btOkSenha.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ConfigTO configTO = new ConfigTO();

                if (!configTO.hasElements()) {

                    Intent it = new Intent(SenhaActivity.this, ConfiguracaoActivity.class);
                    startActivity(it);
                    finish();

                } else {

                    List<ConfigTO> lista = configTO.get("senhaConfig", editTextSenha.getText().toString());

                    if (lista.size() > 0) {

                        configTO.setEquipConfig(((ConfigTO) lista.get(0)).getEquipConfig());

                        Intent it = new Intent(SenhaActivity.this, ConfiguracaoActivity.class);
                        startActivity(it);
                        finish();

                    }

                }

            }
        });

        btCancSenha.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(SenhaActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    public void onBackPressed()  {
    }

}
