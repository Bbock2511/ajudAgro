package com.example.ajudagro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.ajudagro.database.models.AnaliseGeral
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
            enviarDadosParaSegundaParte()
        }
        
        return binding.root
    }

    private fun enviarDadosParaSegundaParte(){
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

            val action = CadastrarColetaPrimeiraParteFragmentDirections.actionCadastrarColetaPrimeiraParteFragmentToCadastrarColetaSegundaParteFragment(analiseGeral)
            findNavController().navigate(action)
        } else {
            Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_LONG).show()
        }

    }

    private fun stringsNaoSaoVazias(vararg inputs: String): Boolean {
        return inputs.all { it.isNotBlank() }
    }
}