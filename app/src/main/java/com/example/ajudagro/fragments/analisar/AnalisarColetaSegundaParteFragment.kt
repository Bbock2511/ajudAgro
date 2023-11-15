package com.example.ajudagro.fragments.analisar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ajudagro.R
import com.example.ajudagro.databinding.FragmentAnalisarColetaSegundaParteBinding
import kotlin.math.roundToInt

class AnalisarColetaSegundaParteFragment : Fragment() {

    private val args by navArgs<AnalisarColetaSegundaParteFragmentArgs>()

    private var _binding: FragmentAnalisarColetaSegundaParteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnalisarColetaSegundaParteBinding.inflate(inflater, container, false)

        val perdasPlataforma = args.perdasPlataformaKg
        val perdasSistemaIndustrial = args.perdasSistemaIndustrialKg

        var perdasPlataformaSacas = perdasPlataforma / 60
        var perdasSistemaIndustrialSacas = perdasSistemaIndustrial / 60

        val perdasTotaisKg = perdasPlataforma + perdasSistemaIndustrial
        binding.perdasTotaisResultTextView.text = perdasTotaisKg.toString()

        val perdasTotaisSacas = perdasTotaisKg/60
        binding.perdasTotaisResultSacasTextView.text = perdasTotaisSacas.toString()

        binding.graficoPerdasSistemaIndustrial.layoutParams.height = perdasSistemaIndustrial.roundToInt()*2
        binding.graficoPerdasPlataforma.layoutParams.height = perdasPlataforma.roundToInt()*2

        if (perdasPlataformaSacas < 1){
            perdasPlataformaSacas *= 5
        }
        if (perdasSistemaIndustrialSacas < 1){
            perdasSistemaIndustrialSacas *= 5
        }

        binding.graficoPerdasSistemaIndustrialSacas.layoutParams.height = perdasSistemaIndustrialSacas.roundToInt()*30
        binding.graficoPerdasPlataformaSacas.layoutParams.height = perdasPlataformaSacas.roundToInt()*30

        binding.buttonPronto.setOnClickListener {
            findNavController().navigate(R.id.action_analisarColetaSegundaParteFragment_to_homeFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}