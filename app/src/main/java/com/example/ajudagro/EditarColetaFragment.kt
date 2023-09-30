package com.example.ajudagro

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.ajudagro.database.models.Analise
import com.example.ajudagro.database.models.AnaliseGeral
import com.example.ajudagro.databinding.FragmentEditarColetaBinding

class EditarColetaFragment : Fragment() {

    private val args by navArgs<EditarColetaFragmentArgs>()

    private var _binding : FragmentEditarColetaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditarColetaBinding.inflate(inflater, container, false)


        binding.nomeDaColetaInput.setText(args.analise.analiseGeral.nome)
        binding.coordenadasInput.setText(args.analise.analiseGeral.coordenadas)
        binding.areaInput.setText(args.analise.analiseGeral.area.toString())
        binding.maquinarioInput.setText(args.analise.analiseGeral.maquinario)
        binding.dataAnaliseInput.setText(args.analise.analiseGeral.data)
        binding.cidadeInput.setText(args.analise.analiseGeral.cidade)
        binding.estadoInput.setText(args.analise.analiseGeral.estado)

        binding.buttonAvancar.setOnClickListener {
            val nome = binding.nomeDaColetaInput.text.toString()
            val coordenadas = binding.coordenadasInput.text.toString()
            val dimensoesArea = binding.areaInput.text.toString()
            val maquinario = binding.maquinarioInput.text.toString()
            val dataAnalise = binding.dataAnaliseInput.text.toString()
            val cidade = binding.cidadeInput.text.toString()
            val estado = binding.estadoInput.text.toString()

            if (stringsNaoSaoVazias(
                    nome,
                    coordenadas,
                    dimensoesArea,
                    maquinario,
                    dataAnalise,
                    cidade,
                    estado
                )) {
                val analiseGeral = AnaliseGeral(nome, coordenadas, dimensoesArea.toInt(), maquinario, dataAnalise, cidade, estado)

                val todosOsDados = Analise(args.analise.id, analiseGeral, args.analise.diametroPeneira, args.analise.areaUmaPeneira,
                    args.analise.areaTodasPeneiras, args.analise.emCimaPeneiraUm, args.analise.emCimaPeneiraDois, args.analise.emCimaPeneiraTres,
                    args.analise.emCimaPeneiraQuatro, args.analise.embaixoPeneiraUm, args.analise.embaixoPeneiraDois,
                    args.analise.embaixoPeneiraTres, args.analise.embaixoPeneiraQuatro)

                val action = EditarColetaFragmentDirections.actionEditarColetaFragmentToEditarColetaSegundaParteFragment(analiseTodosDados = todosOsDados)
                findNavController().navigate(action)
            } else{
                Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }

    private fun stringsNaoSaoVazias(vararg inputs: String): Boolean {
        return inputs.all { it.isNotBlank() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}