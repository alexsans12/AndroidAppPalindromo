package com.projecto.apppalindromo;

import android.os.Build;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projecto.apppalindromo.databinding.FragmentRespuestaBinding;

public class RespuestaFragment extends Fragment {

    private FragmentRespuestaBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRespuestaBinding.inflate(inflater, container, false);

        try {

            boolean esPalindromo = getArguments().getBoolean("esPalindromo");
            String texto = getArguments().getString("text");

            if (esPalindromo) {
                binding.textView.setText(texto + " es un palindromo!");
                binding.imageView.setImageDrawable(binding.getRoot().getContext().getDrawable(R.drawable.ic_found));
            }
            else {
                binding.textView.setText(texto + " no es un palindromo.");
                binding.imageView.setImageDrawable(binding.getRoot().getContext().getDrawable(R.drawable.ic_not_found));
            }
        } catch(Exception e) {
            System.out.println(e);
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(RespuestaFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
                try {
                    finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}