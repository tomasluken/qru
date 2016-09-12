package nl.tomasluken.helloworld3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.zxing.integration.android.*;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "nl.tomasluken.helloworld3.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        MyAppCore core = new MyAppCore();
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = core.YourNameIs(editText.getText().toString());
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void startScan(View view) {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        if (scanResult != null) {
            editText.setText(scanResult.getContents());
        }
        else {
            // else continue with any other code you need in the method
            editText.setText("scanresult was null...");
        }
    }
}
