package com.projecto.apppalindromo;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projecto.apppalindromo.databinding.FragmentAboutUsBinding;
import com.projecto.apppalindromo.databinding.FragmentRespuestaBinding;

public class aboutUsFragment extends Fragment {

    private FragmentAboutUsBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAboutUsBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}