package com.gamezzar.geargymtest.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gamezzar.geargymtest.R;
import com.gamezzar.geargymtest.seedwork.shared.BaseFragment;
import com.gamezzar.geargymtest.database.entities.User;
import com.gamezzar.geargymtest.databinding.LoginFragmentBinding;
import com.gamezzar.geargymtest.viewmodel.LoginViewModel;
import com.gamezzar.geargymtest.viewmodel.SessionManager;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class LoginFragment extends BaseFragment {

    private LoginViewModel viewModel;
    private LoginFragmentBinding binding;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private SessionManager sessionManager;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = LoginFragmentBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        sessionManager = new SessionManager(requireContext());
        setUpInputFields();
        setUpListeners();
        return binding.getRoot();
    }

    private void setUpInputFields() {
        setUpInputField(binding.tilEmail, "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        setUpInputField(binding.tilPassword, "^.{6,}$");
    }

    private void setUpListeners() {
        binding.btnLogin.setOnClickListener(v -> attemptLogin());
        binding.tvSignUp.setOnClickListener(v -> navigate(R.id.action_loginFragment_to_registerFragment));
        binding.tvForgotPassword.setOnClickListener(v -> navigate(R.id.action_loginFragment_to_forgotPasswordFragment));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkUserSessionAndNavigate();
    }

    private void attemptLogin() {
        String email = Objects.requireNonNull(binding.tilEmail.getEditText()).getText().toString();
        String password = Objects.requireNonNull(binding.tilPassword.getEditText()).getText().toString();

        LiveData<User> userLiveData = viewModel.login(email, password);
        userLiveData.observe(getViewLifecycleOwner(), this::handleLoginResponse);
    }

    private void handleLoginResponse(User user) {
        if (user != null) {
            sessionManager.createLoginSession(user.getUID(), user.getEmail());
            navigateToHome();
        } else {
            Toast.makeText(getContext(), "Invalid credentials.", Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToHome() {
        if (sessionManager.isLoggedIn()) {
            safeNavigate(R.id.action_loginFragment_to_homeFragment);
        }
    }

    private void checkUserSessionAndNavigate() {
        if (sessionManager.isLoggedIn()) {
            navigateToHome();
        }
    }

    private void setUpInputField(TextInputLayout inputLayout, String regex) {
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
                inputLayout.setBoxStrokeColor(getResources().getColor(isValid ? R.color.primary : R.color.red, getContext().getTheme()));
                inputLayout.setHelperTextEnabled(!isValid);
                updateLoginButtonState();
            }
        });
    }

    private void updateLoginButtonState() {
        boolean isEmailValid = Objects.requireNonNull(binding.tilEmail.getEditText()).getText().toString().matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        boolean isPasswordValid = Objects.requireNonNull(binding.tilPassword.getEditText()).getText().toString().matches("^.{6,}$");
        binding.btnLogin.setEnabled(isEmailValid && isPasswordValid);
    }

    private void navigate(int destinationId) {
        NavHostFragment.findNavController(this).navigate(destinationId);
    }

    private void safeNavigate(int destinationId) {
        NavController navController = NavHostFragment.findNavController(this);
        if (Objects.requireNonNull(navController.getCurrentDestination()).getId() == R.id.loginFragment) {
            navController.navigate(destinationId);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hideFab();
        hideBottomAppBar();
        hideToolBar();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        disposables.clear();
        binding = null;
    }
}
