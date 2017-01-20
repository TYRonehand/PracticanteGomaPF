package com.carlos.practicante_goma_pf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class IngresarPedidoActivity extends Activity 
{
	String articulo = "", cliente="";
	boolean estado = false;
	EditText etdinero;
	Button addpedido;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ingresar_pedido);
		
		File sdcard = Environment.getExternalStorageDirectory();
		File filecliente = new File(sdcard,"Clientes.txt");
		File filearticulo = new File(sdcard,"Productos.txt");
		
		etdinero = (EditText) findViewById(R.id.et_unidad);
        
		// If file does not exists, then create it
        if (filearticulo.exists() && filecliente.exists()) 
		{
    		productosView();
    		registerClickCallback();	
		}else{
        	Toast.makeText(IngresarPedidoActivity.this, "Ingresar clientes y/o productos.", Toast.LENGTH_LONG).show();
		}
        
        //boton agregar
        addpedido = (Button)findViewById(R.id.btn_ingpedido);
        addpedido.setOnClickListener(new View.OnClickListener() 
        {	
			@Override
			public void onClick(View v) 
			{
				String tipo ="PedidosHoy";
				//si no esta vacio
				if(!etdinero.getText().toString().equals(""))
				{
					if(!articulo.equals(""))
					{
						if(!cliente.equals(""))
						{
							//obtener fecha pedido
							Date d = new Date();
						    CharSequence fecha  = DateFormat.format("dd/MM/yyyy ", d.getTime());
							//recupera pedidos agregados
						    String aux = recuperar(tipo);
							String [] artsplit = articulo.split("-");//cadena con info de articulo
							int costo = Integer.parseInt(artsplit[1]);//pasa a intger el costo artiulo
							int prestado = Integer.parseInt(etdinero.getText().toString());//pasa a integer el dinero prestado
							grabar(tipo,aux+fecha+"\nPedido: "+artsplit[0]+"\nNombre: "+cliente+"\nCancelo: "+prestado+"\nCosto: "+costo+"\nVuelto: "+(prestado-costo)+",");
							finish();
						}else{
							Toast.makeText(IngresarPedidoActivity.this, "y quien quiere?", Toast.LENGTH_LONG).show();
						}
					}else{
						Toast.makeText(IngresarPedidoActivity.this, "y que quiere?", Toast.LENGTH_LONG).show();
					}
				}else{
					Toast.makeText(IngresarPedidoActivity.this, "cuanta luca pasarón?", Toast.LENGTH_LONG).show();
				}
				
			}
		});
		
	}

	
	private void productosView() 
	{
		estado = false;
		//create list churrascas
		//String[] articulos = {"Jamon Queso-550","Palta Queso-650"};
		String nomarchivo = "Productos";
		//create list clientes
		String art = recuperar(nomarchivo);
		String[] articulos = art.split(",");
		
		//adaptador
		ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this, R.layout.da_item, articulos);
		
		//configurar listview (listaarticulos)
		ListView lista = (ListView) findViewById(R.id.listaarticulos);
		lista.setAdapter(adapter);
		
	}

	private void registerClickCallback() 
	{
		//lista con articulos
		ListView lista = (ListView) findViewById(R.id.listaarticulos);
		//cliclear un articulo
		lista.setOnItemClickListener(new AdapterView.OnItemClickListener() 
		{
			//si clickea guarda posicion y nombre
			@Override
			public void onItemClick(AdapterView<?> parent, View viewclicked, int position, long id) 
			{
				//accion objeto seleccionado
				TextView textview = (TextView)viewclicked;
				if(!estado){
					articulo = textview.getText().toString();
					clientesView();
				}else{
					cliente = textview.getText().toString();
					//productosView();
				}
			}
		});	
	}
	
	private void clientesView() 
	{
		estado = true;
		String nomarchivo = "Clientes";
		//create list clientes
		String cli = recuperar(nomarchivo);
		String[] clientes = cli.split(",");
		
		//adaptador
		ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this, R.layout.da_item, clientes);
		
		//configurar listview (listaarticulos)
		ListView lista = (ListView) findViewById(R.id.listaarticulos);
		lista.setAdapter(adapter);
	}
	
	public Boolean grabar(String fname, String fcontent)
	{
        try 
        {
        	File sdcard = Environment.getExternalStorageDirectory();
	        File file = new File(sdcard,fname+".txt");
	        // If file does not exists, then create it
	        if (!file.exists()) 
			{
			  file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(fcontent+",");
			bw.close();
			Toast.makeText(IngresarPedidoActivity.this, fname+"agregado!!", Toast.LENGTH_SHORT).show();
			return true;
	    } 
        catch (IOException e) 
        {
		  e.printStackTrace();
		  Toast.makeText(IngresarPedidoActivity.this, fname+"Error al agregar!!", Toast.LENGTH_SHORT).show();
		  return false;
	    }
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
