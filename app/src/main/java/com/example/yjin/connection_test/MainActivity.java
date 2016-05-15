package com.example.yjin.connection_test;

import android.content.Context;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialProber;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        UsbSerialDriver driver = UsbSerialProber.acquire(manager);
        Log.d(TAG, "Read " + " bytes.");

        if(driver != null){
            try {
                driver.open();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try{
                driver.setBaudRate(115200);
                byte buffer[] = new byte[16];
                int numBytesRead = driver.read(buffer, 1000);
                Log.d(TAG, "Read " + numBytesRead + " bytes.");
                Toast toast = Toast.makeText(getApplicationContext(),"Read!!!!!!!!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            } catch (IOException e){

            }finally {
                try {
                    driver.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
