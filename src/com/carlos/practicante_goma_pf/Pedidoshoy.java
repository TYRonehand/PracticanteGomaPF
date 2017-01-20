package com.carlos.practicante_goma_pf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateFormat;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Pedidoshoy extends Activity
{

	String tipo ="PedidosHoy";
	//String PedidoActual;
	String fin =",";
	EditText etdinero;
	TextView Titulo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pedidoshoy);
		//titulo con fecha
		//obtener fecha pedido
		Date d = new Date();
	    CharSequence fecha  = DateFormat.format("dd/MM/yyyy ", d.getTime());
		Titulo = (TextView)findViewById(R.id.TituloPedidoHoy);
		Titulo.setText("PEDIDOS DE HOY("+fecha+")");
		
		//archivo existe
		File sdcard = Environment.getExternalStorageDirectory();
		File filepedidos = new File(sdcard,tipo+".txt");
        // If file does not exists, then create it
        if (filepedidos.exists()) 
		{
    		productosView();
		}
        else
        {
        	Toast.makeText(Pedidoshoy.this, "No existen Pedidos Hoy!.", Toast.LENGTH_LONG).show();
		}
	}

	
	private void productosView() 
	{
		//create list churrascas
		//create list pedidos
		String art = recuperar(tipo);
		String[] pedidoshoy = art.split(",");
		
		//adaptador
		ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this, R.layout.da_item, pedidoshoy);
		
		//configurar listview (listaarticulos)
		ListView lista = (ListView) findViewById(R.id.listapedidos);
		lista.setAdapter(adapter);
		
	}
	
	public String recuperar(String fname)
	{
         BufferedReader br = null;
         String response = "";
         try
         {
        	 StringBuffer output = new StringBuffer();
        	 File sdcard = Environment.getExternalStorageDirectory();
        	 File file = new File(sdcard,fname+".txt");
        	 FileInputStream fIn = new FileInputStream(file);
        	 InputStreamReader archivo = new InputStreamReader(fIn);
        	 br = new BufferedReader(archivo);
        	 String line = "";
        	 while ((line = br.readLine()) != null) 
        	 {
        		 output.append(line +",");
        	 }
        	 response = output.toString();
        	 br.close();
	     }
         catch (IOException e) 
         {
	           e.printStackTrace();
	           return "";
         }
         return response;
    }
	
}
