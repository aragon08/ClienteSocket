package itcelaya.clientesocket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Cliente extends AppCompatActivity {

    @BindView(R.id.txtMsjs)
    TextView txtMsjs;

    @BindView(R.id.edtMsj)
    EditText edtMsj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnEnviar)
    public void enviarMsj()
    {
        String mensaje = edtMsj.getText().toString();
        new Hilo(this,txtMsjs).execute(mensaje);
    }
}
