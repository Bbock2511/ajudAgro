package com.example.ajudagro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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

        val perdasPlataformaSacas = perdasPlataforma / 60
        val perdasSistemaIndustrialSacas = perdasSistemaIndustrial / 60

        val perdasTotaisKg = perdasPlataforma + perdasSistemaIndustrial
        binding.perdasTotaisResultTextView.text = perdasTotaisKg.toString()

        val perdasTotaisSacas = perdasTotaisKg/60
        binding.perdasTotaisResultSacasTextView.text = perdasTotaisSacas.toString()

        binding.graficoPerdasSistemaIndustrial.layoutParams.height = perdasSistemaIndustrial.roundToInt()*2
        binding.graficoPerdasPlataforma.layoutParams.height = perdasPlataforma.roundToInt()*2

        binding.graficoPerdasSistemaIndustrialSacas.layoutParams.height = perdasSistemaIndustrialSacas.roundToInt()*20
        binding.graficoPerdasPlataformaSacas.layoutParams.height = perdasPlataformaSacas.roundToInt()*20

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