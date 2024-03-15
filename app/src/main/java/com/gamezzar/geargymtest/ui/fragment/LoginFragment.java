package com.gamezzar.geargymtest.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava3.RxDataStore;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.gamezzar.geargymtest.R;
import com.gamezzar.geargymtest.core.BaseFragment;
import com.gamezzar.geargymtest.database.entities.User;
import com.gamezzar.geargymtest.databinding.LoginFragmentBinding;
import com.gamezzar.geargymtest.viewmodel.LoginViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginFragment extends BaseFragment {

    private LoginViewModel mViewModel;
    private LoginFragmentBinding binding;
    private boolean isEmailValidated;
    private boolean isPasswordValidated;
    private RxDataStore<Preferences> dataStore;
    private final CompositeDisposable disposables = new CompositeDisposable();
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = LoginFragmentBinding.inflate(inflater, container, false);
        setUpEmailInputField();
        setUpPasswordInputField();
        binding.btnLogin.setOnClickListener(v -> navigateToHome());
        binding.tvSignUp.setOnClickListener(v -> navigateToRegister());
        binding.tvForgotPassword.setOnClickListener(v -> navigateToForgotPassword());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        retrieveAndSetEmail();
    }

    @Override
    public void onResume() {
        super.onResume();
        hideToolBar();
        hideBottomAppBar();
        hideFab();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        disposables.clear();
        binding = null;
    }

    private void setUpInputField(TextInputLayout inputLayout, String regex, Runnable validationFlagUpdater) {
        TextInputEditText inputField = (TextInputEditText) inputLayout.getEditText();
        assert inputField != null;
        inputField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean isValid = s.toString().matches(regex);
                int boxStrokeColor = isValid ? getResources().getColor(R.color.purple_600, getResources().newTheme()) : getResources().getColor(R.color.red, getResources().newTheme());
                inputLayout.setBoxStrokeColor(boxStrokeColor);
                inputLayout.setHelperTextEnabled(!isValid);
                if (inputLayout.getId() == R.id.tilEmail) {
                    isEmailValidated = isValid;
                } else if (inputLayout.getId() == R.id.tilPassword) {
                    isPasswordValidated = isValid;
                }
                validationFlagUpdater.run();
                updateLoginButtonState();
            }
        });
    }

    private void setUpEmailInputField() {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        setUpInputField(binding.tilEmail, emailRegex, this::updateLoginButtonState);
    }

    private void setUpPasswordInputField() {
        String passwordRegex = "^.{6,}$";
        setUpInputField(binding.tilPassword, passwordRegex, this::updateLoginButtonState);
    }

    private void navigateToHome() {
        String email = Objects.requireNonNull(Objects.requireNonNull(binding.tilEmail.getEditText()).getText()).toString();
        String password = Objects.requireNonNull(Objects.requireNonNull(binding.tilPassword.getEditText()).getText()).toString();

        LiveData<User> userLiveData = mViewModel.login(email, password);

        userLiveData.observe(getViewLifecycleOwner(), user -> {
            if (user != null && user.Email != null) {
                boolean isRememberMeChecked = binding.cbxRememberMe.isChecked();
                if (isRememberMeChecked) {
                    saveEmailInDataStore(email);
                } else {
                    removeEmailFromDataStore();
                }
                NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_homeFragment);
            } else {
                Toast.makeText(getContext(), "You may have entered incorrect credentials.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToRegister() {
        NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_registerFragment);
    }

    private void navigateToForgotPassword() {
        NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_forgotPasswordFragment);
    }

    private void updateLoginButtonState() {
        binding.btnLogin.setEnabled(isEmailValidated && isPasswordValidated);
    }

    private void retrieveAndSetEmail() {
        disposables.add(mViewModel.retrieveEmailFromDataStore().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(email -> {
            binding.tilEmail.getEditText().setText(email);
            binding.cbxRememberMe.setChecked(!email.isEmpty());
        }, throwable -> {
            Log.e("LoginFragment", "Error retrieving email from DataStore", throwable);
        }));
    }

    private void saveEmailInDataStore(String email) {
        // Add the Disposable from the ViewModel's save method to the disposables
        disposables.add(mViewModel.saveEmailToDataStore(email).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(() -> {
            // Email saved successfully
            Log.d("LoginFragment", "Email saved in DataStore");
        }, throwable -> {
            // Handle error
            Log.e("LoginFragment", "Error saving email to DataStore", throwable);
        }));
    }

    private void removeEmailFromDataStore() {
        // Add the Disposable from the ViewModel's remove method to the disposables
        disposables.add(mViewModel.removeEmailFromDataStore().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(() -> {
            // Email removed successfully
            Log.d("LoginFragment", "Email removed from DataStore");
        }, throwable -> {
            // Handle error
            Log.e("LoginFragment", "Error removing email from DataStore", throwable);
        }));
    }

}