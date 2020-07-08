package com.karam.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.karam.db.pojo.ErrorResponse;
import com.karam.db.pojo.Laborer;
import com.karam.utils.BaseActivity;
import com.karam.view.restservice.RestService;
import com.karam.view.restservice.RetroFitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Page for a user to register themselves as a laborer for a job request
 */
public class WorkRequestSelf extends BaseActivity {

    @Override
    protected int getLayoutResource() {
        return R.layout.work_request_self;
    }

    /**
     * Handle what happens when the view.activity is created
     *
     * @param savedInstanceState: null for now
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.work_request_self);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.locations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        assignListenerToViews();
        //Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //setSupportActionBar(myToolbar);
    }

    /**
     * Assign all listener to different views of the view.activity
     */
    private void assignListenerToViews() {
        Button laborerReg = (Button) findViewById(R.id.register);
        laborerReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_rest_request();
                startActivity(new Intent(WorkRequestSelf.this, LaborerStatusPage.class));
            }
        });
    }

    // TODO: This function can be moved to a separate menu class as its
    // used by all view.activity class

    /**
     * Set up menu options
     *
     * @param menu: Menu options (https://pasteboard.co/Jc4U58s.png) to be shown in the view.activity
     * @return: true on no error

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_laborers, menu);
        return true;
    }
     */

    // TODO: This function can be moved to a separate menu class as its
    // used by all view.activity class

    /**
     * Responds to menu option (https://pasteboard.co/Jc4U58s.png) of this view.activity
     *
     * @return: return false in case of error, true otherwise

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.logout):
                startActivity(new Intent(WorkRequestSelf.this, LoginPage.class));
                break;
            case (R.id.user_settings):
                startActivity(new Intent(WorkRequestSelf.this, UserSettings.class));
                return true;
            case (R.id.check_status):
                startActivity(new Intent(WorkRequestSelf.this, LaborerStatusPage.class));
                break;
            case (R.id.about_us):
                startActivity(new Intent(WorkRequestSelf.this, AboutUs.class));
                break;
            default:
                Toast.makeText(WorkRequestSelf.this, "Oops! Error", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
         */

    private void send_rest_request() {
        RetroFitService retro = new RetroFitService(getApplicationContext());
        RestService service = retro.getService();

        Laborer laborer = new Laborer(2, "n", "n", "l", "123", 23, "m", "f", "skills");
        Call<ErrorResponse> callSync = service.putLaborers(2, laborer);
        callSync.enqueue(new Callback<ErrorResponse>() {
            @Override
            public void onResponse(Call<ErrorResponse> call, Response<ErrorResponse> response) {
                ErrorResponse apiResponse = response.body();
                System.out.println(apiResponse);
                Toast.makeText(getApplicationContext(), apiResponse.toString(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ErrorResponse> call, Throwable t) {

            }
        });
    }
}