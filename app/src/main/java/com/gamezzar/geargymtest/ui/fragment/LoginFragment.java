package com.gamezzar.geargymtest.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gamezzar.geargymtest.R;
import com.gamezzar.geargymtest.core.BaseFragment;
import com.gamezzar.geargymtest.database.entities.User;
import com.gamezzar.geargymtest.databinding.LoginFragmentBinding;
import com.gamezzar.geargymtest.viewmodel.LoginViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class LoginFragment extends BaseFragment {

    private LoginViewModel mViewModel;
    private LoginFragmentBinding binding;
    private boolean isEmailValidated;
    private boolean isPasswordValidated;

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

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        hideToolBar();
        hideBottomAppBar();
        hideFab();
    }

    private void setUpInputField(TextInputEditText inputField, String regex, int validBg, int invalidBg, Runnable validationFlagUpdater) {
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
                int bgResource = isValid ? validBg : invalidBg;
                inputField.setBackground(ResourcesCompat.getDrawable(getResources(), bgResource, null));
                validationFlagUpdater.run();
                updateLoginButtonState();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void navigateToHome() {
        String email = Objects.requireNonNull(binding.tiEmailField.getText()).toString();
        String password = Objects.requireNonNull(binding.tiPasswordField.getText()).toString();

        LiveData<User> userLiveData = mViewModel.login(email, password);

        userLiveData.observe(getViewLifecycleOwner(), user -> {
            if (user != null && user.Email != null) {
                NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_homeFragment);
            } else {
                Toast.makeText(getContext(), "Login failed. User not found or incorrect credentials.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void navigateToRegister() {
        NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_registerFragment);
    }

    private void updateLoginButtonState() {
        binding.btnLogin.setEnabled(isEmailValidated && isPasswordValidated);
    }

    private void updateEmailValidation(boolean isValid) {
        isEmailValidated = isValid;
    }

    private void updatePasswordValidation(boolean isValid) {
        isPasswordValidated = isValid;
    }

    // Usage
    public void setUpEmailInputField() {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        setUpInputField(binding.tiEmailField, emailRegex, R.drawable.layout_border, R.drawable.layout_border_error, () -> updateEmailValidation(binding.tiEmailField.getText().toString().matches(emailRegex)));
    }

    public void setUpPasswordInputField() {
        String passwordRegex = "^.{6,}$";
        setUpInputField(binding.tiPasswordField, passwordRegex, R.drawable.layout_border, R.drawable.layout_border_error, () -> updatePasswordValidation(binding.tiPasswordField.getText().toString().matches(passwordRegex)));
    }


}