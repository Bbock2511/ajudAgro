package com.example.ajudagro.fragments.cadastrar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.ajudagro.database.models.AnaliseGeral
import com.example.ajudagro.databinding.FragmentCadastrarColetaPrimeiraParteBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CadastrarColetaPrimeiraParteFragment : Fragment() {

    private var _binding:FragmentCadastrarColetaPrimeiraParteBinding? = null
    private val binding get() = _binding!!

    private val data: LocalDate = LocalDate.now()
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    private val dataAtual: String = data.format(formatter)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCadastrarColetaPrimeiraParteBinding.inflate(layoutInflater, container, false)

        binding.dataAnaliseInput.setText(dataAtual)

        binding.estadoInput.setOnEditorActionListener { _, _, _ -> false }

        binding.buttonAvancar.setOnClickListener {
            enviarDadosParaSegundaParte()
        }
        
        return binding.root
    }

     private fun enviarDadosParaSegundaParte(){
        val nome = binding.nomeDaColetaInput.text.toString()
        val latitude = binding.latitudeInput.text.toString()
        val longitude = binding.longitudeInput.text.toString()
        val dimensoesArea = binding.areaInput.text.toString()
        val maquinario = binding.maquinarioInput.text.toString()
        val dataAnalise = binding.dataAnaliseInput.text.toString()
        val cidade = binding.cidadeInput.text.toString()
        val estado = binding.estadoInput.text.toString()

        if (stringsNaoSaoVazias(
                nome,
                latitude,
                longitude,
                dimensoesArea,
                maquinario,
                dataAnalise,
                cidade,
                estado
            )) {
            val analiseGeral = AnaliseGeral(nome, latitude, longitude, dimensoesArea.toInt(), maquinario, dataAnalise, cidade, estado)

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