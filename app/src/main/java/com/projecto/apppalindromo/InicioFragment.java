package com.projecto.apppalindromo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.projecto.apppalindromo.databinding.FragmentInicioBinding;

public class InicioFragment extends Fragment {

    private FragmentInicioBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                getActivity().finish();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentInicioBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String texto = binding.editTextInicio.getText().toString().replaceAll("(\\r|\\n)", " ");
                if(texto.length() > 0) {
                    boolean esPalindromo = PalindromoLibro(texto);
                    Bundle bundle = new Bundle();

                    bundle.putBoolean("esPalindromo", esPalindromo);
                    bundle.putString("text", texto);

                    NavHostFragment.findNavController(InicioFragment.this)
                            .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
                } else {
                    binding.editTextLayout.setError("Ingresa una palabra");
                    Toast.makeText(binding.getRoot().getContext(), "Intenta ingresando una palabra o frase", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public boolean PalindromoLibro(String pal) {
        PilaLineal pilaChar;
        char ch;
        boolean esPal = true;

        try {
            pilaChar = new PilaLineal();
            pal = pal.replace(" ", "");

            // se crea la pila con los caracteres de la palabra
            for (int i = 0; i < pal.length(); ) {
                Character c;
                c = new Character(pal.charAt(i++));
                pilaChar.insertar(c);
            }

            // se comprueba si es palíndromo
            for (int j = 0; esPal && !pilaChar.pilaVacia(); ) {
                Character c;
                c = (Character) pilaChar.quitar();
                esPal = pal.charAt(j++) == c.charValue();
            }

            pilaChar.limpiarPila();


        } catch (Exception er) {
            System.err.println("Excepcion: " + er);
        }

        return esPal;
    }

}