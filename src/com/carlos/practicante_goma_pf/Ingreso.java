package com.carlos.practicante_goma_pf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Ingreso extends Activity implements OnClickListener{
	
	Button btn_login_1;
	EditText user, pass;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);
        btn_login_1 = (Button) findViewById(R.id.btn_login);
        btn_login_1.setOnClickListener(this);
        user = (EditText)findViewById(R.id.useralia);
    	pass = (EditText)findViewById(R.id.userpass);
    }

    @Override
    public void onClick(View arg0) 
    {
        switch(arg0.getId()) 
        {
            case R.id.btn_login:
            	if( user.getText().toString().equals("carlos"))
            	{
            		if(pass.getText().toString().equals("1234"))
            		{
            			Intent myIntent = new Intent(this, MenuActivity.class);
                        startActivity(myIntent);
            		}
            		else
            		{
            			Toast msg = Toast.makeText(getBaseContext(),"pass no valida!",Toast.LENGTH_LONG);
            			msg.show();
            		}
            	}
            	else
            	{
            		Toast msg = Toast.makeText(getBaseContext(),"alias no valida!",Toast.LENGTH_LONG);
        			msg.show();
            	}
            break;
        }
    }
}
