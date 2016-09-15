package nl.tomasluken.qru;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.zxing.integration.android.*;

import nl.tomasluken.qru.TextEncryptor;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "nl.tomasluken.helloworld3.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private boolean scanMessage = true;
    private String theMessage;

    public void startScan(View view) {
        scanMessage = true;
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }

    public void startKeyScan(View view) {
        scanMessage = false;
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        TextView lblResult = (TextView) findViewById(R.id.scan_result);
        try {
            IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
            //TextView lblResultDecrypted = (TextView) findViewById(R.id.scan_decrypted);
            if (scanResult != null) {
                String scannedValue = scanResult.getContents();
                if (scanMessage) {
                    theMessage = scannedValue;
                    lblResult.setText(theMessage);
                } else {
                    String theKey = scannedValue;
                    DES des = new DES();
                    String decryptedValue = des.decrypt(theMessage, theKey);
                    lblResult.setText(decryptedValue);
                }
                //TextEncryptor encryptor = new TextEncryptor();
                //String resultDecrypted = encryptor.Decrypt(result);
                //lblResult.setText(result);
                //lblResultDecrypted.setText(resultDecrypted);
            } else {
                lblResult.setText("scanresult was null...");
                //lblResultDecrypted.setText("no value to decrypt...");
            }
        }
        catch (Exception ex)
        {
            lblResult.setText(ex.getMessage());
        }
    }
}
