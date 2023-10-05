package com.example.ajudagro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.ajudagro.databinding.FragmentAnalisarColetaBinding

class AnalisarColetaFragment : Fragment() {

    private val args by navArgs<AnalisarColetaFragmentArgs>()

    private var _binding:FragmentAnalisarColetaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnalisarColetaBinding.inflate(inflater, container, false)

        binding.nomeAnalise.text = args.analise.analiseGeral.nome

        val peneiraUm = args.analise.emCimaPeneiraUm
        val peneiraDois = args.analise.emCimaPeneiraDois
        val peneiraTres = args.analise.emCimaPeneiraTres
        val peneiraQuatro = args.analise.emCimaPeneiraQuatro
        val embaixoPeneiraUm = args.analise.embaixoPeneiraUm
        val embaixoPeneiraDois = args.analise.embaixoPeneiraDois
        val embaixoPeneiraTres = args.analise.embaixoPeneiraTres
        val embaixoPeneiraQuatro = args.analise.embaixoPeneiraQuatro
        val areaTodasPeneiras = args.analise.areaTodasPeneiras

        val perdasPlataforma = calcularPerdasPlataforma(peneiraUm, peneiraDois, peneiraTres, peneiraQuatro, areaTodasPeneiras)
        binding.perdasPlataformaKgResultado.text = perdasPlataforma[0]
        binding.perdasPlataformaSacasResultado.text = perdasPlataforma[1]

        val perdaSistemaIndustrial = calcularPerdasSistemaIndustrial(embaixoPeneiraUm, embaixoPeneiraDois, embaixoPeneiraTres, embaixoPeneiraQuatro, areaTodasPeneiras)
        binding.perdasSistemaIndustrialKgResultado.text = perdaSistemaIndustrial[0]
        binding.perdasSistemaIndustrialSacasResultado.text = perdaSistemaIndustrial[1]

        binding.avancarButtonAnalisar

        return binding.root
    }

    private fun calcularPerdasPlataforma(peneiraUm:Float, peneiraDois:Float, peneiraTres:Float , peneiraQuatro:Float, areaTodasPeneiras:Float): Array<String> {
        val perdasPlataformaKg =
            ((10000 * (peneiraUm + peneiraDois + peneiraTres + peneiraQuatro)) / areaTodasPeneiras) / 1000
        val perdasPlataformaSacas = perdasPlataformaKg / 60

        return arrayOf(perdasPlataformaKg.toString(), perdasPlataformaSacas.toString())
    }

    private fun calcularPerdasSistemaIndustrial(embaixoPeneiraUm:Float, embaixoPeneiraDois:Float, embaixoPeneiraTres:Float , embaixoPeneiraQuatro:Float, areaTodasPeneiras:Float): Array<String> {
        val perdasSistemaIndustrialKg =
            ((10000 * (embaixoPeneiraUm + embaixoPeneiraDois + embaixoPeneiraTres + embaixoPeneiraQuatro)) / areaTodasPeneiras) / 1000
        val perdasSistemaIndustrialSacas = perdasSistemaIndustrialKg / 60

        return arrayOf(perdasSistemaIndustrialKg.toString(), perdasSistemaIndustrialSacas.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}