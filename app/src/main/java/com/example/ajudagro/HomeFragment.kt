package com.example.ajudagro

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ajudagro.databinding.FragmentHomeBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class HomeFragment : Fragment() {

    private var _binding:FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val bottomSheetBehavior = binding.bottomSheet
        BottomSheetBehavior.from(bottomSheetBehavior).apply {
            peekHeight = 100
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        binding.buttonCadastrarColeta.setOnClickListener {
            Log.e("Home to Cadastrar", "Ocorreu um erro aqui")
            findNavController().navigate(R.id.action_homeFragment_to_cadastrarColetaPrimeiraParteFragment)
        }

        binding.buttonEditarColeta.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_selecionarAnaliseFragment)
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}