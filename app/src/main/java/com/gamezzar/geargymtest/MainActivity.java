package com.gamezzar.geargymtest;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ActivityResultLauncher<String[]> requestPermissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), permissions -> {
            boolean allGranted = true;
            for (Map.Entry<String, Boolean> entry : permissions.entrySet()) {
                if (!entry.getValue()) {
                    allGranted = false;
                    break;
                }
            }
        });

        checkAndRequestPermissions();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Find the NavHostFragment and Obtain the NavController
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();

        // Setup the BottomNavigationView with the NavController
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.homeFragment, R.id.loginFragment, R.id.registerFragment) // Adjust according to your top-level destinations
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    private void checkAndRequestPermissions() {
        String[] permissionsToRequest;
        permissionsToRequest = new String[]{Manifest.permission.CAMERA};

        boolean shouldRequest = false;
        for (String permission : permissionsToRequest) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                shouldRequest = true;
                break;
            }
        }

        if (shouldRequest) {
            requestPermissionLauncher.launch(permissionsToRequest);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp() || super.onSupportNavigateUp();
    }
}