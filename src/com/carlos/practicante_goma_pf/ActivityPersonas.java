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

public class ActivityPersonas extends Activity 
{
	String tipo ="Clientes";
	EditText et1;
    //EditText et2;
	Button addcliente;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_personas);
        
        //caja texto con nombre
        et1 = (EditText) findViewById(R.id.nombrecliente);
        
        //boton agregar
        addcliente = (Button)findViewById(R.id.btn_agregarcliente);
        addcliente.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{	
				//si no esta vacio
				if(et1.length()>=3)
				{
					String aux = recuperar(tipo);
					grabar(tipo,aux+et1.getText().toString());
					finish();
				}
				else
				{
					Toast.makeText(ActivityPersonas.this, "Llenar campos", Toast.LENGTH_SHORT).show();
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
			Toast.makeText(ActivityPersonas.this, "Cliente agregado!!", Toast.LENGTH_SHORT).show();
			return true;
	    } 
        catch (IOException e) 
        {
		  e.printStackTrace();
		  Toast.makeText(ActivityPersonas.this, "Error al agregar!!", Toast.LENGTH_SHORT).show();
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
        		 output.append(line);
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
