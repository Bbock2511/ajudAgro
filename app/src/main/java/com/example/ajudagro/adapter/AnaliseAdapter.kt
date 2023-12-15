package com.example.ajudagro.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ajudagro.database.models.Analise
import com.example.ajudagro.database.viewmodel.AnaliseViewModel
import com.example.ajudagro.databinding.AnaliseAdapterBinding
import com.example.ajudagro.fragments.selecionar.SelecionarAnaliseFragmentArgs
import com.example.ajudagro.fragments.selecionar.SelecionarAnaliseFragmentDirections

class AnaliseAdapter(private val analiseViewModel: AnaliseViewModel, private val args: SelecionarAnaliseFragmentArgs) : RecyclerView.Adapter<AnaliseAdapter.MyViewHolder>() {

    private var analiseList = emptyList<Analise>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemAnalise = AnaliseAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemAnalise)
    }

    override fun getItemCount() = analiseList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val analiseAtual = analiseList[position]
        holder.nomeAnalise.text = analiseAtual.analiseGeral.nome

        holder.deletarAnaliseButton.setOnClickListener {
            deletarAnalise(analiseAtual, holder.itemView.context)
        }
        if (args.estado == "editar"){
            holder.itemView.rootView.setOnClickListener {
                val action = SelecionarAnaliseFragmentDirections.actionSelecionarAnaliseFragmentToEditarColetaFragment(analise = analiseAtual)
                holder.itemView.findNavController().navigate(action)
            }
        }
        if (args.estado == "analisar"){
            holder.deletarAnaliseButton.visibility = View.INVISIBLE
            holder.itemView.rootView.setOnClickListener {
                val action = SelecionarAnaliseFragmentDirections.actionSelecionarAnaliseFragmentToAnalisarColetaFragment(analise = analiseAtual)
                holder.itemView.findNavController().navigate(action)
            }
        }
        if (args.estado == "gerar relatorio"){
            holder.deletarAnaliseButton.visibility = View.INVISIBLE
            holder.itemView.rootView.setOnClickListener {
                val action = SelecionarAnaliseFragmentDirections.actionSelecionarAnaliseFragmentToGerarRelatorioFragment(analise = analiseAtual)
                holder.itemView.findNavController().navigate(action)
            }
        }

    }

    class MyViewHolder(binding: AnaliseAdapterBinding) : RecyclerView.ViewHolder(binding.root){
        val nomeAnalise = binding.nomeColetaAtual
        val deletarAnaliseButton = binding.deletarAnaliseButton
    }

    private fun deletarAnalise(analise: Analise, context: Context) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder
            .setMessage("Você deseja realmente excluir?\nTodas as informações serão excluídas.")
            .setTitle("Excluir")
            .setPositiveButton("Sim") { dialog, _ ->
                analiseViewModel.deletarAnalise(analise)
                dialog.dismiss()
            }
            .setNegativeButton("Não") { dialog, _ ->
                dialog.dismiss()
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setarDados(analise: List<Analise>) {
        this.analiseList = analise
        notifyDataSetChanged()
    }
}
