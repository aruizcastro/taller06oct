package com.example.taller06oct;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewEmpladoActivity extends Activity {

	// Progress Dialog
	private ProgressDialog pDialog;

	JSONParser jsonParser = new JSONParser();
	EditText inputName;
	EditText inputApe;
	EditText inputCed;
	EditText inputSueldo;

	// url to create new Empleado Reemplaza la IP de tu equipo o la direccion de tu servidor 
	// Si tu servidor es tu PC colocar IP Ej: "http://127.97.99.200/taller06oct/..", no colocar "http://localhost/taller06oct/.."
	private static String url_create_Empleado = "http://AquiTuServidor/taller06oct/nuevoempleado.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_empleado);

		// Edit Text
		inputName = (EditText) findViewById(R.id.inputName);
		inputCed = (EditText) findViewById(R.id.inputCedula);
		inputApe = (EditText) findViewById(R.id.inputApellido);
		inputSueldo = (EditText) findViewById(R.id.inputSueldo);

		// Create button
		Button btnCreateEmpleado = (Button) findViewById(R.id.btnCreateEmpleado);

		// button click event
		btnCreateEmpleado.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// creating new Empleado in background thread
				new CreateNewEmpleado().execute();
			}
		});
	}

	/**
	 * Background Async Task to Create new Empleado
	 * */
	class CreateNewEmpleado extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(NewEmpladoActivity.this);
			pDialog.setMessage("Registrando Empleado..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Creating Empleado
		 * */
		protected String doInBackground(String... args) {
			String name = inputName.getText().toString();
			String ape = inputApe.getText().toString();
			String cedula = inputCed.getText().toString();
			String sueldo = inputSueldo.getText().toString();

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("nombre", name));
			params.add(new BasicNameValuePair("apellido", ape));
			params.add(new BasicNameValuePair("cedula", cedula));
			params.add(new BasicNameValuePair("sueldo", sueldo));

			// getting JSON Object
			// Note that create Empleado url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(url_create_Empleado,
					"POST", params);
			
			// check log cat fro response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// successfully created Empleado
					Intent i = new Intent(getApplicationContext(), EmpleadosActivity.class);
					startActivity(i);
					
					// closing this screen
					finish();
				} else {
					// failed to create Empleado
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
		}

	}
}
