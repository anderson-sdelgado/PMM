package br.com.usinasantafe.pmm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import br.com.usinasantafe.pmm.R;
import br.com.usinasantafe.pmm.model.bean.variaveis.ConfigBean;

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

                ConfigBean configBean = new ConfigBean();

                if (!configBean.hasElements()) {

                    Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);
                    startActivity(it);
                    finish();

                } else {

                    List<ConfigBean> configList = configBean.get("senhaConfig", editTextSenha.getText().toString());

                    if (configList.size() > 0) {

                        configList.clear();

                        Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);
                        startActivity(it);
                        finish();

                    }

                }

            }
        });

        btCancSenha.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(SenhaActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    public void onBackPressed()  {
    }

}
