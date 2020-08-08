package com.example.claficados.ui.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.claficados.ConecionSQLiteHelper;
import com.example.claficados.MainActivity;
import com.example.claficados.R;
import com.example.claficados.oi.utilities;
import com.example.claficados.txrx;
import org.json.JSONException;
import org.json.JSONObject;


public class login<Cursor> extends AppCompatActivity {
    private ProgressDialog pDialog;

    public static EditText etEmail, etPassword;
    TextView tvRegister;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tvRegister = (TextView) findViewById(R.id.tv_register);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                System.out.println(email + "<---User Pass-->" + password);
                // Check for empty data in the form
                if (!email.isEmpty() && !password.isEmpty()) {
                    // login user
                    checkLogin(email, password);
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            ("No valido"), Toast.LENGTH_LONG)
                            .show();
                }
            }

        });
    }
        private void checkLogin(final String email, final String password) {
            // Tag used to cancel the request
            ConecionSQLiteHelper conn = new ConecionSQLiteHelper(this, "db_user4",null,6);
            SQLiteDatabase db = conn.getWritableDatabase();
            String tag_string_req = "req_login";
            //pDialog.setMessage("Logging in ...");
            //showDialog();
            //Request.Method.POST;
            System.out.println("Se harà primera com con dominio");
            String paramas = "?p1=" + email + "&p2=" + password;
            txrx hild = new txrx(this);
            hild.execute("https://comcop.com.co/bikes/login.php", "8");
        /*String cadena = "{\"error\":false,\"status\":2,\"uid\":5,\"user\":{\"clases\":\"layuxo92\"," +
                "\"user_registered\":\"2020-05-03 21:55:40\",\"user_activation_key\":" +
                "\"1588542940:$P$B4SpJiExqz20oip2VgGPUWyVK\\/9UXR.\",\"user_email\":\"asdf\"," +
                "\"user_login\":\"carlos.argoti\",\"user_pass\":\"$P$B2t6S4JqLNkW9DGMJfgsAYA9K.hC7D.\"" +
                ",\"user_status\":0,\"user_nicename\":\"carlos-argoti\",\"user_url\":\"\",\"display_name\"" +
                ":\"carlos Argoti\"}}";*/
            try {
                Thread.sleep(2000);
            }catch (Exception e) {
                System.out.println("98 "+e);
            }

            //cache1+" TEXT ,"+cache3+ " TEXT ,"+cahche2
            String cadena="{\"Errror\":\"true\", \"status\":0}";
            String consulta ="SELECT  "+ utilities.cache1+" FROM "+ utilities.tblconfig+" WHERE  id=1 ;";
            String clear  ="DELETE FROM "+ utilities.tblconfig+" WHERE  id=1 ;";
            Cursor resc = (Cursor) db.rawQuery(consulta,null);
            hild.cancel(true);

            try {
                ((android.database.Cursor) resc).moveToFirst();
                cadena= ((android.database.Cursor) resc).getString(0);
                System.out.println(cadena);

            } catch (Exception e) {
                e.printStackTrace();
            }
            //System.out.println("102"+resc.getString(1));
            onResponse (cadena);
            //System.out.println("desde cache "+cadena);
            db.execSQL(clear);
            db.close();
        }

        public void onResponse(String response) {
            // CRUD  1 2 3 4
            //System.out.println("111 "+response);
            try {
                //System.out.println("debugCMP: "+response);
                JSONObject jObj = new JSONObject(response);
                //System.out.println("debugCMP: "+response);
                int status = jObj.getInt("status");
                //observa el estado de la transacción
                // Check for error node in json
                if (status == 2) {
                    // user successfully logged in
                    Object session;
                    // Now store the user in SQLite
                    JSONObject user = jObj.getJSONObject("user");
                    String name = user.getString("user_login");
                    //db.addUser(name, email, user_id, null);
                    Toast.makeText(getApplicationContext(), ("Mensaje de bienvenida") + name, Toast.LENGTH_LONG).show();
                    // Launch main activity
                    Intent intent = new Intent(login.this,
                            MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Error in login. Get the error message
                    String errorMsg=("Cod de error");
                    Toast.makeText(getApplicationContext(),
                            errorMsg, Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                // JSON error
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }



        private void showDialog() {
            if (!pDialog.isShowing())
                pDialog.show();
        }

        private void hideDialog() {
            if (pDialog.isShowing())
                pDialog.dismiss();
        }
    }
