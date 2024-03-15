package com.gamezzar.geargymtest.ui.fragment;

import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gamezzar.geargymtest.R;
import com.gamezzar.geargymtest.databinding.ForgotPasswordFragmentBinding;
import com.gamezzar.geargymtest.viewmodel.ForgotPasswordViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class ForgotPasswordFragment extends Fragment {

    private ForgotPasswordViewModel mViewModel;
    private ForgotPasswordFragmentBinding binding;
    private String email;
    private boolean isEmailValidated;

    public static ForgotPasswordFragment newInstance() {
        return new ForgotPasswordFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ForgotPasswordFragmentBinding.inflate(inflater, container, false);
        setUpEmailInputField();
        binding.ibReturn.setOnClickListener(v -> navigateToLogin());
        binding.btnResetPassword.setOnClickListener((v -> {
            if (isEmailValidated) {
                Toast.makeText(getContext(), "We have sent you a link to your email!", Toast.LENGTH_SHORT).show();
            }
        }));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ForgotPasswordViewModel.class);
    }

    private void updateLoginButtonState() {
        binding.btnResetPassword.setEnabled(isEmailValidated);
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
                email = s.toString();
                boolean isValid = email.matches(regex);
                int bgResource = isValid ? validBg : invalidBg;
                inputField.setBackground(ResourcesCompat.getDrawable(getResources(), bgResource, null));
                validationFlagUpdater.run();
                updateLoginButtonState();
            }
        });
    }

    private void updateEmailValidation(boolean isValid) {
        isEmailValidated = isValid;
    }

    public void setUpEmailInputField() {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        setUpInputField(binding.tietEmailField, emailRegex, R.drawable.layout_border, R.drawable.layout_border_error, () -> updateEmailValidation(Objects.requireNonNull(binding.tietEmailField.getText()).toString().matches(emailRegex)));
    }

    public void navigateToLogin() {
        NavHostFragment.findNavController(this).navigate(R.id.action_forgotPasswordFragment_to_loginFragment);
    }

}