package com.example.ajudagro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ajudagro.adapter.AnaliseAdapter
import com.example.ajudagro.adapter.EmptyObserver
import com.example.ajudagro.database.viewmodel.AnaliseViewModel
import com.example.ajudagro.databinding.FragmentSelecionarAnaliseBinding

class SelecionarAnaliseFragment : Fragment() {

    private lateinit var mAnaliseViewModel: AnaliseViewModel

    private val args by navArgs<SelecionarAnaliseFragmentArgs>()

    private var _binding: FragmentSelecionarAnaliseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSelecionarAnaliseBinding.inflate(layoutInflater, container, false)
        mAnaliseViewModel = ViewModelProvider(this)[AnaliseViewModel::class.java]

        val adapter = AnaliseAdapter(mAnaliseViewModel, args)
        val recyclerView = binding.recyclerViewAnalises
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val emptyList = binding.emptyList

        val emptyObserver = EmptyObserver(recyclerView, emptyList)
        adapter.registerAdapterDataObserver(emptyObserver)

        mAnaliseViewModel.lerAnalises.observe(viewLifecycleOwner) { analise ->
            adapter.setarDados(analise)
        }

        return binding.root
    }

}