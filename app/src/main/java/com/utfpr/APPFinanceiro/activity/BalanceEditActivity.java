package com.utfpr.APPFinanceiro.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.utfpr.APPFinanceiro.R;
import com.utfpr.APPFinanceiro.model.entity.Balance;

import com.utfpr.APPFinanceiro.model.dao.BalanceDAO;

public class BalanceEditActivity extends AppCompatActivity {

    private TextInputEditText inputDesc;
    private TextInputEditText inputDate;
    private TextInputEditText inputValue;
    private TextInputEditText inputType;
    private Balance balanceSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_edit);

        inputDesc = findViewById(R.id.textinput_desc);
        inputDate = findViewById(R.id.textinput_date);
        inputValue = findViewById(R.id.textinput_value);
        inputType = findViewById(R.id.textinput_type);


        Bundle extras = getIntent().getExtras();
        try {
            balanceSelecionado = (Balance) extras.getSerializable("balance");
        }catch (NullPointerException e){
            balanceSelecionado = null;
        }

        if (balanceSelecionado != null){
            inputDesc.setText(balanceSelecionado.getDesc());
            inputValue.setText(balanceSelecionado.getValue());
            inputDate.setText(balanceSelecionado.getDate());
            inputType.setText(balanceSelecionado.getTypeBalance());

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_save_balance, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.item_save_balance){

            if (validaCampos()){
                BalanceDAO dao = new BalanceDAO(getApplicationContext());
                Balance c = new Balance(inputDesc.getText().toString(),
                        inputDate.getText().toString(),
                        inputValue.getText().toString(),
                        inputType.getText().toString());
                if (balanceSelecionado != null){ //Atualização
                    c.setId(balanceSelecionado.getId());
                    dao.atualizar(c);
                }else {
                    dao.salvar(c);
                }
                finish();
            }else {
                Toast.makeText(getApplicationContext(), "Preencha todos os campos corretamente", Toast.LENGTH_LONG).show();
            }
//            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean validaCampos(){

        if (inputDate.getText().toString().equals("") ||
                inputValue.getText().toString().equals("") ||
                inputDesc.getText().toString().equals("") ||
                inputType.getText().toString().equals("")
        ){
            return false;
        }
        return true;
    }
}