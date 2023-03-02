package vcc.viv.vccfinanceenginedemo;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import vcc.viv.vccfinance.manager.VccFinanceEngine;
import vcc.viv.vccfinanceenginedemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    /* **********************************************************************
     * Area : Variable
     ********************************************************************** */
    private ActivityMainBinding binding;
    private VccFinanceEngine vccFinanceEngine;
    private List<String> content = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vccFinanceEngine = VccFinanceEngine.getInstance();

        binding.replace.addView(vccFinanceEngine.getVccFinanceView(this), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        binding.btn1.setOnClickListener(view -> {
            vccFinanceEngine.startVccProfitActivity(this);
        });

        binding.btn2.setOnClickListener(view -> {
            vccFinanceEngine.startVccLoanActivity(this);
        });

        binding.btn3.setOnClickListener(view -> {
            vccFinanceEngine.startVccTimeActivity(this);
        });

        binding.btn4.setOnClickListener(view -> {
            vccFinanceEngine.startVccFinanceActivity(this);
            //FragmentDemoActivity.start(this);
        });
    }
}