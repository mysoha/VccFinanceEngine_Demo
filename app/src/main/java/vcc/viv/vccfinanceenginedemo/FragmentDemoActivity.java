package vcc.viv.vccfinanceenginedemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import vcc.viv.vccfinance.manager.VccFinanceEngine;
import vcc.viv.vccfinanceenginedemo.databinding.ActivityFragmentDemoBinding;

public class FragmentDemoActivity extends AppCompatActivity {
    private ActivityFragmentDemoBinding binding;
    private VccFinanceEngine vccFinanceEngine;

    public static void start(Context context) {
        Intent starter = new Intent(context, FragmentDemoActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFragmentDemoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vccFinanceEngine = VccFinanceEngine.getInstance();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(binding.placeHolder.getId(), vccFinanceEngine.getVccFinanceFragment());
        transaction.commit();
    }
}