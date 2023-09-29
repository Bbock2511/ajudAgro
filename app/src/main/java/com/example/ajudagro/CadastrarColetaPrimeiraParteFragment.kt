package com.example.ajudagro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ajudagro.databinding.FragmentCadastrarColetaPrimeiraParteBinding

class CadastrarColetaPrimeiraParteFragment : Fragment() {

    private var _binding:FragmentCadastrarColetaPrimeiraParteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCadastrarColetaPrimeiraParteBinding.inflate(layoutInflater, container, false)

        binding.estadoInput.setOnEditorActionListener { _, _, _ -> false }

        binding.buttonAvancar.setOnClickListener {
            findNavController().navigate(R.id.action_cadastrarColetaPrimeiraParteFragment_to_cadastrarColetaSegundaParteFragment)
        }

        return binding.root
    }
}