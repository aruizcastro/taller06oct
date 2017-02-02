package com.example.taller06oct;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


public class MainActivity extends Activity {
	String busquedapor="";
	EditText txtbuscar;
	private static final String TAG_CEDULA = "cedula";
	private static final String TAG_BUSCAR = "buscar";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
final Spinner cmbOpciones = (Spinner)findViewById(R.id.buscarpor);
        
        final String[] datos =
            new String[]{"Cedula","Nombre"};
     
        ArrayAdapter<String> adaptador =
            new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos);
        
        adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
         
        cmbOpciones.setAdapter(adaptador);
    	cmbOpciones.setOnItemSelectedListener(
            	new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                        android.view.View v, int position, long id) {
                            busquedapor= datos[position];
                    }
             
                    public void onNothingSelected(AdapterView<?> parent) {
                            busquedapor ="Cedula";
                    }
            });
        
        

        
    }
    
    public void busquedaempleado(View v){

    	
    	if(busquedapor.equals("Cedula")){
    	// getting values from selected ListItem
    	txtbuscar = (EditText) findViewById(R.id.inputBuscar);
        String cedula;
        cedula = txtbuscar.getText().toString();
		// Starting new intent
		Intent in = new Intent(getApplicationContext(),
				EditEmpleadosActivity.class);
		// sending pid to next activity
		in.putExtra(TAG_CEDULA, cedula);
		
		// starting new activity and expecting some response back
		startActivityForResult(in, 100);
    	}else if(busquedapor.equals("Nombre")){
    		// getting values from selected ListItem
        	txtbuscar = (EditText) findViewById(R.id.inputBuscar);
            String buscar;
            buscar = txtbuscar.getText().toString();
    		// Starting new intent
    		Intent in = new Intent(getApplicationContext(),
    				BuscarEmpleado.class);
    		// sending pid to next activity
    		in.putExtra(TAG_BUSCAR, buscar);
    		
    		// starting new activity and expecting some response back
    		startActivityForResult(in, 100);
    	}
		
	 }
    
	 public void listadoempleados(View v){
		 Intent i=new Intent(MainActivity.this, EmpleadosActivity.class);
			startActivity(i);
	 }
	 public void nuevoempleado(View v){
		 Intent i=new Intent(MainActivity.this, NewEmpladoActivity.class);
			startActivity(i);
	 }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
