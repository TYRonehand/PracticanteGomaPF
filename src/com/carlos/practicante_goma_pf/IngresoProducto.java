package com.carlos.practicante_goma_pf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IngresoProducto extends Activity 
{	
		String tipo ="Productos";
		EditText etname,etcash;
		Button addcliente;
		
	    @Override
	    protected void onCreate(Bundle savedInstanceState) 
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_ingreso_producto);
	        
	        //caja texto con nombre
	        etname = (EditText) findViewById(R.id.et_prodname);
	        etcash = (EditText) findViewById(R.id.et_prodcash);
	        
	        //boton agregar
	        addcliente = (Button)findViewById(R.id.btn_addproducto);
	        addcliente.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) 
				{
					//si no esta vacio
					if(etname.getText().length()>=3 && etcash.getText().length()>=3)
					{
						String aux = recuperar(tipo);
						Toast.makeText(IngresoProducto.this, "Producto Agregado!", Toast.LENGTH_SHORT).show();
						grabar(tipo, aux + etname.getText().toString()+"-"+etcash.getText().toString());
						finish();
					}
					else
					{
						Toast.makeText(IngresoProducto.this, "llena todos los campos!!", Toast.LENGTH_LONG).show();
					}
				}
			});
	    }

	    public Boolean grabar(String fname, String fcontent){
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
				Toast.makeText(IngresoProducto.this, fname+"agregado!!", Toast.LENGTH_SHORT).show();
				return true;
		    } 
	        catch (IOException e) 
	        {
			  e.printStackTrace();
			  Toast.makeText(IngresoProducto.this, fname+" NO agregado!!", Toast.LENGTH_SHORT).show();
			  return false;
		    }
	     }

	    public String recuperar(String fname){
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