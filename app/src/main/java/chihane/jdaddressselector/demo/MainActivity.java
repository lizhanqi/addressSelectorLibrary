package chihane.jdaddressselector.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import chihane.jdaddressselector.AddressSelector;
import chihane.jdaddressselector.BottomDialog;
import chihane.jdaddressselector.OnAddressSelectedListener;
import chihane.jdaddressselector.model.City;
import chihane.jdaddressselector.model.County;
import chihane.jdaddressselector.model.Province;
import chihane.jdaddressselector.model.Street;

public class MainActivity extends AppCompatActivity implements OnAddressSelectedListener {
    BottomDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog = new BottomDialog(MainActivity.this);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameLayout);

        AddressSelector selector = new AddressSelector(this);
        selector.setOnAddressSelectedListener(this);
//        selector.setAddressProvider(new TestAddressProvider());

        assert frameLayout != null;
        frameLayout.addView(selector.getView());

        Button buttonBottomDialog = (Button) findViewById(R.id.buttonBottomDialog);
        assert buttonBottomDialog != null;
        buttonBottomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                BottomDialog.show(MainActivity.this, MainActivity.this);

                dialog.setOnAddressSelectedListener(MainActivity.this);
                dialog.show();
            }
        });
    }

    @Override
    public void onAddressSelected(Province province, City city, County county, Street street) {
        String s =
                (province == null ? "" : province.getId()) +
                        (city == null ? "" : "\n" + city.getId()) +
                        (county == null ? "" : "\n" + county.getId()) +
                        (street == null ? "" : "\n" + street.getId());
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        dialog.dismiss();

    }
}
