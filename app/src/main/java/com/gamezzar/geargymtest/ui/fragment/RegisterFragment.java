package com.gamezzar.geargymtest.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.gamezzar.geargymtest.core.UserModel;
import com.gamezzar.geargymtest.database.entities.User;
import com.gamezzar.geargymtest.databinding.RegisterFragmentBinding;
import com.gamezzar.geargymtest.viewmodel.RegisterViewModel;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterFragment extends BaseFragment {
    private RegisterViewModel mViewModel;
    private RegisterFragmentBinding binding;
    private boolean areTermsChecked = false;
    private boolean areInputsValid = false;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = RegisterFragmentBinding.inflate(inflater, container, false);
        binding.ibReturn.setOnClickListener(v -> NavHostFragment.findNavController(this).navigate(R.id.action_registerFragment_to_loginFragment));
        binding.btnRegister.setOnClickListener(v -> NavHostFragment.findNavController(this).navigate(R.id.action_registerFragment_to_HomeFragment));

        setUpInputNameField();
        setUpInputEmailField();
        setUpInputPasswordField();
        setUpInputRepeatPasswordField();

        binding.mrbTermsAndConditions.setOnClickListener(v -> {
            MaterialRadioButton radioButton = ((MaterialRadioButton) v);
            areTermsChecked = !areTermsChecked;
            radioButton.setChecked(areTermsChecked);
            updateLoginButtonState();
        });
        binding.btnRegister.setOnClickListener(v -> signUpUser());
        return binding.getRoot();
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
        binding = null;
    }

    private void signUpUser() {
        final String name = binding.inputNameField.getText().toString();
        final String email = binding.inputEmailField.getText().toString();
        final String password = binding.inputPasswordField.getText().toString();
        final String repeatPassword = binding.inputRepeatPasswordField.getText().toString();
        final boolean isUserValid = UserModel.isUserValid(name, email, password, repeatPassword);
        if (!isUserValid) {
            Toast.makeText(getContext(), "Please, check the fields above if you have fulfilled correctly.", Toast.LENGTH_SHORT).show();
        } else {
            User user = new User();
            user.Name = name;
            user.Email = email;
            user.Password = password;
            mViewModel.signUp(user);
            navigateToHome();
        }
    }

    private void navigateToHome() {
        NavHostFragment.findNavController(this).navigate(R.id.action_registerFragment_to_HomeFragment);
    }

    private void setUpInputField(TextInputLayout inputLayout, String regex, String helperText, Runnable validationFlagUpdate) {
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
                int boxStrokeColor = isValid
                        ? getResources().getColor(R.color.purple_600, getResources().newTheme())
                        : getResources().getColor(R.color.red, getResources().newTheme());
                inputLayout.setBoxStrokeColor(boxStrokeColor);
                inputLayout.setHelperTextEnabled(!isValid);
                inputLayout.setErrorEnabled(!isValid);
                inputLayout.setHelperText(helperText);
                areInputsValid = false;
                if (isValid) {
                    inputLayout.setErrorEnabled(false);
                    inputLayout.setHelperText("");
                    areInputsValid = true;
                }
                validationFlagUpdate.run();
                updateLoginButtonState();
            }
        });
    }

    private void setUpInputNameField() {
        TextInputLayout nameInputLayout = binding.tilNameRegister;
        String nameRegex = "^[A-Za-z]+(([',. -][A-Za-z ])?[A-Za-z]*)*$";
        String helperText = "Please, enter correct data.";
        setUpInputField(nameInputLayout, nameRegex, helperText, this::updateLoginButtonState);
    }

    private void setUpInputEmailField() {
        TextInputLayout emailInputLayout = binding.tilEmailRegister;
        String emailRegex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String helperText = "Please, enter correct email address.";
        setUpInputField(emailInputLayout, emailRegex, helperText, this::updateLoginButtonState);
    }

    private void setUpInputPasswordField() {
        TextInputLayout passwordInputLayout = binding.tilPasswordRegister;
        String passwordRegex = "^.{6,}$";
        String helperText = "Please, you must enter at least 6 characters.";
        setUpInputField(passwordInputLayout, passwordRegex, helperText, this::updateLoginButtonState);
    }

    private void setUpInputRepeatPasswordField() {
        TextInputLayout repeatPasswordInputLayout = binding.tilRepeatPasswordRegister;
        String repeatPasswordRegex = "^.{6,}$";
        String helperText = "Please, you must enter at least 6 characters.";
        setUpInputField(repeatPasswordInputLayout, repeatPasswordRegex, helperText, this::updateLoginButtonState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
    }

    private void updateLoginButtonState() {
        boolean isFormValid = areInputsValid;
        final boolean areTermsChecked = binding.mrbTermsAndConditions.isChecked();
        binding.btnRegister.setEnabled(isFormValid && areTermsChecked);
    }
}