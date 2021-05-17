package com.projecto.apppalindromo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projecto.apppalindromo.databinding.FragmentRespuestaBinding;

public class RespuestaFragment extends Fragment {

    private FragmentRespuestaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRespuestaBinding.inflate(inflater, container, false);

        try {

            boolean esPalindromo = getArguments().getBoolean("esPalindromo");
            String texto = getArguments().getString("text");

            if (esPalindromo)
                binding.textView.setText(texto + " es un palindromo!");
            else
                binding.textView.setText(texto + " no es un palindromo.");
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
                NavHostFragment.findNavController(RespuestaFragment.this).navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}