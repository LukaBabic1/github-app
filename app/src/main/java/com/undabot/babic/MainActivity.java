package com.undabot.babic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.undabot.babic.app.R;
import com.undabot.babic.data.DataJavaInvoker;
import com.undabot.babic.device.DeviceJavaInvoker;
import com.undabot.babic.domain.DomainJavaInvoker;

public final class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "Hello world toast", Toast.LENGTH_SHORT)
             .show();

        // To ensure all modules are well connected.
        new DataJavaInvoker().invoke();
        new DeviceJavaInvoker().invoke();
        new DomainJavaInvoker().invoke();
    }
}
