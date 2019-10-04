package com.example.qrcodescanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private IntentIntegrator qrscan;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = findViewById(R.id.textView);
        qrscan = new IntentIntegrator(this);
        qrscan.initiateScan();

        }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)  {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result!= null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Resut not found", Toast.LENGTH_LONG).show();
            } else {
                try {
                    JSONObject obj = new JSONObject(result.getContents());
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                    t1.setText(result.getContents());
                    if(URLUtil.isValidUrl(result.getContents())) {
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(result.getContents()));
                        startActivity(i);
                    }
                }
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
            }
    }


    @Override
    public void onClick(View view) {

    }
}
