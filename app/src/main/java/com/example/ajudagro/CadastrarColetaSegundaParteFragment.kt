package com.example.ajudagro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ajudagro.database.models.Analise
import com.example.ajudagro.database.viewmodel.AnaliseViewModel
import com.example.ajudagro.databinding.FragmentCadastrarColetaSegundaParteBinding

class CadastrarColetaSegundaParteFragment : Fragment() {

    private val args by navArgs<CadastrarColetaSegundaParteFragmentArgs>()

    private var _binding: FragmentCadastrarColetaSegundaParteBinding? = null
    private val binding get() = _binding!!

    private lateinit var mAnaliseViewModel: AnaliseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCadastrarColetaSegundaParteBinding.inflate(layoutInflater, container, false)

        mAnaliseViewModel = ViewModelProvider(this)[AnaliseViewModel::class.java]

        binding.buttonSalvar.setOnClickListener {
            inserirAnaliseBancoDeDados()
        }

        return binding.root
    }

    private fun inserirAnaliseBancoDeDados() {
        val diametroPeneira = binding.diametroPeneiraInput.text.toString()
        val areaPeneira = binding.areaPeneiraInput.text.toString()
        val areaTodasPeneiras = binding.areaTodasPeneiraInput.text.toString()
        val perdaPeneiraUm = binding.peneiraUmInput.text.toString()
        val perdaPeneiraDois = binding.peneiraDoisInput.text.toString()
        val perdaPeneiraTres = binding.peneiraTresInput.text.toString()
        val perdaPeneiraQuatro = binding.peneiraQuatroInput.text.toString()
        val perdaEmbaixoPeneiraUm = binding.embaixoPeneiraUmInput.text.toString()
        val perdaEmbaixoPeneiraDois = binding.embaixoPeneiraDoisInput.text.toString()
        val perdaEmbaixoPeneiraTres = binding.embaixoPeneiraTresInput.text.toString()
        val perdaEmbaixoPeneiraQuatro = binding.embaixoPeneiraQuatroInput.text.toString()

        if (verificacaoDeEntrada(
                diametroPeneira,
                areaPeneira,
                areaTodasPeneiras,
                perdaPeneiraUm,
                perdaPeneiraDois,
                perdaPeneiraTres,
                perdaPeneiraQuatro,
                perdaEmbaixoPeneiraUm,
                perdaEmbaixoPeneiraDois,
                perdaEmbaixoPeneiraTres,
                perdaEmbaixoPeneiraQuatro
            )) {
            val analise = Analise(0, args.analiseGeral, diametroPeneira.toFloat(), areaPeneira.toFloat(),
                areaTodasPeneiras.toFloat(), perdaPeneiraUm.toFloat(), perdaPeneiraDois.toFloat(), perdaPeneiraTres.toFloat(),
                perdaPeneiraQuatro.toFloat(), perdaEmbaixoPeneiraUm.toFloat(), perdaEmbaixoPeneiraDois.toFloat(),
                perdaEmbaixoPeneiraTres.toFloat(), perdaEmbaixoPeneiraQuatro.toFloat()
            )

            mAnaliseViewModel.inserirAnalise(analise)
            Toast.makeText(context, "Análise cadastrada!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_cadastrarColetaSegundaParteFragment_to_homeFragment)
        } else {
            Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_LONG).show()
        }
    }

    private fun verificacaoDeEntrada(
        diametroPeneira: String,
        areaPeneira: String,
        areaTodasPeneiras: String,
        perdaPeneiraUm: String,
        perdaPeneiraDois: String,
        perdaPeneiraTres: String,
        perdaPeneiraQuatro: String,
        perdaEmbaixoPeneiraUm: String,
        perdaEmbaixoPeneiraDois: String,
        perdaEmbaixoPeneiraTres: String,
        perdaEmbaixoPeneiraQuatro: String
    ): Boolean {
        return (
                diametroPeneira.isNotEmpty() ||
                        areaPeneira.isNotEmpty() ||
                        areaTodasPeneiras.isNotEmpty() ||
                        perdaPeneiraUm.isNotEmpty() ||
                        perdaPeneiraDois.isNotEmpty() ||
                        perdaPeneiraTres.isNotEmpty() ||
                        perdaPeneiraQuatro.isNotEmpty() ||
                        perdaEmbaixoPeneiraUm.isNotEmpty() ||
                        perdaEmbaixoPeneiraDois.isNotEmpty() ||
                        perdaEmbaixoPeneiraTres.isNotEmpty() ||
                        perdaEmbaixoPeneiraQuatro.isNotEmpty()
                )
    }

}