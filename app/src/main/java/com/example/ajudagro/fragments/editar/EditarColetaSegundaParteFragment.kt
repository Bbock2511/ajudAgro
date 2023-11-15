package com.example.ajudagro.fragments.editar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ajudagro.R
import com.example.ajudagro.database.models.Analise
import com.example.ajudagro.database.viewmodel.AnaliseViewModel
import com.example.ajudagro.databinding.FragmentEditarColetaSegundaParteBinding

class EditarColetaSegundaParteFragment : Fragment() {

    private lateinit var mAnaliseViewModel: AnaliseViewModel

    private val args by navArgs<EditarColetaSegundaParteFragmentArgs>()

    private var _binding: FragmentEditarColetaSegundaParteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditarColetaSegundaParteBinding.inflate(inflater, container, false)

        mAnaliseViewModel = ViewModelProvider(this)[AnaliseViewModel::class.java]

        binding.diametroPeneiraInput.setText(args.analiseTodosDados.diametroPeneira.toString())
        binding.areaPeneiraInput.setText(args.analiseTodosDados.areaUmaPeneira.toString())
        binding.areaTodasPeneiraInput.setText(args.analiseTodosDados.areaTodasPeneiras.toString())
        binding.peneiraUmInput.setText(args.analiseTodosDados.emCimaPeneiraUm.toString())
        binding.peneiraDoisInput.setText(args.analiseTodosDados.emCimaPeneiraDois.toString())
        binding.peneiraTresInput.setText(args.analiseTodosDados.emCimaPeneiraTres.toString())
        binding.peneiraQuatroInput.setText(args.analiseTodosDados.emCimaPeneiraQuatro.toString())
        binding.embaixoPeneiraUmInput.setText(args.analiseTodosDados.embaixoPeneiraUm.toString())
        binding.embaixoPeneiraDoisInput.setText(args.analiseTodosDados.embaixoPeneiraDois.toString())
        binding.embaixoPeneiraTresInput.setText(args.analiseTodosDados.embaixoPeneiraTres.toString())
        binding.embaixoPeneiraQuatroInput.setText(args.analiseTodosDados.embaixoPeneiraQuatro.toString())

        binding.buttonAtualizar.setOnClickListener {
            atualizarAnalise()
        }

        return binding.root
    }

    private fun atualizarAnalise() {
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

        if (stringsNaoSaoVazias(
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
            val analise = Analise(args.analiseTodosDados.id, args.analiseTodosDados.analiseGeral, diametroPeneira.toFloat(), areaPeneira.toFloat(),
                areaTodasPeneiras.toFloat(), perdaPeneiraUm.toFloat(), perdaPeneiraDois.toFloat(), perdaPeneiraTres.toFloat(),
                perdaPeneiraQuatro.toFloat(), perdaEmbaixoPeneiraUm.toFloat(), perdaEmbaixoPeneiraDois.toFloat(),
                perdaEmbaixoPeneiraTres.toFloat(), perdaEmbaixoPeneiraQuatro.toFloat()
            )

            mAnaliseViewModel.atualizarAnalise(analise)
            Toast.makeText(context, "Análise atualizada!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_editarColetaSegundaParteFragment_to_homeFragment)
        } else {
            Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_LONG).show()
        }
    }

    private fun stringsNaoSaoVazias(vararg inputs: String): Boolean {
        return inputs.all { it.isNotBlank() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}