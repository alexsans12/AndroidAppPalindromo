package com.projecto.apppalindromo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.projecto.apppalindromo.databinding.FragmentInicioBinding;
import com.projecto.apppalindromo.databinding.FragmentRespuestaBinding;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InicioFragment extends Fragment {

    private FragmentInicioBinding binding;

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

                String texto = binding.editTextInicio.getText().toString();
                boolean esPalindromo = PalindromoLibro(texto);
                Bundle bundle = new Bundle();

                bundle.putBoolean("esPalindromo", esPalindromo);
                bundle.putString("text", texto);

                NavHostFragment.findNavController(InicioFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
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