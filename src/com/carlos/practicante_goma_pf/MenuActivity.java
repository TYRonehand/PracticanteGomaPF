package com.carlos.practicante_goma_pf;

import java.io.File;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends Activity implements OnClickListener
{
	File sdcard,file;
	Button btn_ingpedido,btn_elipedido,btn_ingarticulo,btn_eliarticulo,btn_ingcliente,btn_elicliente,btn_resumen,btn_pedidohoy,btn_salir;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //botones escuchen
        btn_ingpedido = (Button) findViewById(R.id.btningresarpedido);
        btn_ingpedido.setOnClickListener(this);
        btn_elipedido = (Button) findViewById(R.id.btneliminarpedido);
        btn_elipedido.setOnClickListener(this);
        btn_ingarticulo = (Button) findViewById(R.id.btningresararticulo);
        btn_ingarticulo.setOnClickListener(this);
        btn_eliarticulo = (Button) findViewById(R.id.btneliminararticulo);
        btn_eliarticulo.setOnClickListener(this);
        btn_ingcliente = (Button) findViewById(R.id.btningresarcliente);
        btn_ingcliente.setOnClickListener(this);
        btn_elicliente = (Button) findViewById(R.id.btneliminarcliente);
        btn_elicliente.setOnClickListener(this);
        btn_resumen = (Button) findViewById(R.id.btnresumen);
        btn_resumen.setOnClickListener(this);
        btn_pedidohoy = (Button) findViewById(R.id.btn_hoy);
        btn_pedidohoy.setOnClickListener(this);
        btn_salir = (Button) findViewById(R.id.btnsalir);
        btn_salir.setOnClickListener(this);
    }
    //accion de boton
    @Override
    public void onClick(View v) 
    {
    	Intent myIntent;
        switch(v.getId()) 
        {
	        case R.id.btningresarpedido:
	        	myIntent = new Intent(this, IngresarPedidoActivity.class);
                startActivity(myIntent);
	        break;
	        case R.id.btneliminarpedido:
	        	sdcard = Environment.getExternalStorageDirectory();
		        file = new File(sdcard,"PedidosHoy.txt");
		        // If file does not exists, then create it
		        if (file.exists()) 
				{
				  file.delete();
				  Toast.makeText(MenuActivity.this, "Pedidos de Hoy eliminados!", Toast.LENGTH_LONG).show();
				}
	        break;
	        case R.id.btningresarcliente:
	        	myIntent = new Intent(this, ActivityPersonas.class);
                startActivity(myIntent);
	        break;
	        case R.id.btneliminarcliente:
	        	sdcard = Environment.getExternalStorageDirectory();
		        file = new File(sdcard,"Clientes.txt");
		        // If file does not exists, then create it
		        if (file.exists()) 
				{
				  file.delete();
				  Toast.makeText(MenuActivity.this, "TODOS los Clientes eliminados!", Toast.LENGTH_LONG).show();
				}
	        break;
	        case R.id.btningresararticulo:
	        	myIntent = new Intent(this, IngresoProducto.class);
                startActivity(myIntent);
	        break;
            case R.id.btneliminararticulo:
            	sdcard = Environment.getExternalStorageDirectory();
		        file = new File(sdcard,"Productos.txt");
		        // If file does not exists, then create it
		        if (file.exists()) 
				{
				  file.delete();
				  Toast.makeText(MenuActivity.this, "TODOS los Productos eliminados!", Toast.LENGTH_LONG).show();
				}
            break;
            case R.id.btnresumen:
            	//myIntent = new Intent(this, IngresarPedidoActivity.class);
                //startActivity(myIntent);
            break;
            case R.id.btn_hoy:
            	myIntent = new Intent(this, Pedidoshoy.class);
                startActivity(myIntent);
            break;
            case R.id.btnsalir:
            	super.onBackPressed();
            break;
        }
    }
}

