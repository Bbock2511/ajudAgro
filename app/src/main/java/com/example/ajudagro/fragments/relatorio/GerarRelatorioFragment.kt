package com.example.ajudagro.fragments.relatorio

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.ajudagro.databinding.FragmentGerarRelatorioBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.itextpdf.text.Document
import com.itextpdf.text.Image
import com.itextpdf.text.PageSize
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import java.io.ByteArrayOutputStream
import java.io.FileOutputStream

class GerarRelatorioFragment : Fragment() {

    private val args by navArgs<GerarRelatorioFragmentArgs>()

    private var _binding: FragmentGerarRelatorioBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGerarRelatorioBinding.inflate(layoutInflater, container, false)

        val nomeColeta = args.analise.analiseGeral.nome

        val nomeColetaTextView = binding.nomeColeta
        val botaoGerarRelatorio = binding.botaoGerarRelatorio
        val pieChart = binding.pieChart

        nomeColetaTextView.text = nomeColeta

        val perdasPlataforma = calcularPerdasPlataforma(
            args.analise.emCimaPeneiraUm,
            args.analise.emCimaPeneiraDois,
            args.analise.emCimaPeneiraTres,
            args.analise.emCimaPeneiraQuatro,
            args.analise.areaTodasPeneiras
        )

        val perdasSistemaIndustrial = calcularPerdasSistemaIndustrial(
            args.analise.embaixoPeneiraUm,
            args.analise.embaixoPeneiraDois,
            args.analise.embaixoPeneiraTres,
            args.analise.embaixoPeneiraQuatro,
            args.analise.areaTodasPeneiras
        )

        botaoGerarRelatorio.setOnClickListener {

            val grafico = gerarGrafico(pieChart)
            gerarRelatorio(args.analise.analiseGeral.nome.toString(), perdasPlataforma, perdasPlataforma/60,
                perdasSistemaIndustrial, perdasSistemaIndustrial/60, grafico)
        }

        return binding.root
    }

    private fun gerarGrafico(chart: PieChart) : PieChart {
        val argumentosPerdas = args.analise

        val perdasPlataforma = calcularPerdasPlataforma(
            argumentosPerdas.emCimaPeneiraUm,
            argumentosPerdas.emCimaPeneiraDois,
            argumentosPerdas.emCimaPeneiraTres,
            argumentosPerdas.emCimaPeneiraQuatro,
            argumentosPerdas.areaTodasPeneiras
        )

        val perdasSistemaIndustrial = calcularPerdasSistemaIndustrial(
            argumentosPerdas.embaixoPeneiraUm,
            argumentosPerdas.embaixoPeneiraDois,
            argumentosPerdas.embaixoPeneiraTres,
            argumentosPerdas.embaixoPeneiraQuatro,
            argumentosPerdas.areaTodasPeneiras
        )

        val entradas = mutableListOf<PieEntry>()
        entradas.add(PieEntry(perdasPlataforma, "Perdas plataforma"))
        entradas.add(PieEntry(perdasSistemaIndustrial, "Perdas sistema industrial"))

        val dataSet = PieDataSet(entradas, "")
        dataSet.colors = mutableListOf(Color.CYAN, Color.RED)
        dataSet.valueTextSize = 12f

        val data = PieData(dataSet)
        chart.data = data

        val legend = chart.legend
        legend.isEnabled = true
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
        legend.setDrawInside(false)
        legend.xEntrySpace = 7f
        legend.yEntrySpace = 7f // Espaço entre as entradas da legenda
        legend.yOffset = 10f // Espaço entre a legenda e o gráfico

        chart.setDrawEntryLabels(false)

        chart.invalidate()

        return chart

    }

    private fun calcularPerdasPlataforma(
        peneiraUm: Float,
        peneiraDois: Float,
        peneiraTres: Float,
        peneiraQuatro: Float,
        areaTodasPeneiras: Float
    ): Float {
        return ((10000 * (peneiraUm + peneiraDois + peneiraTres + peneiraQuatro)) / areaTodasPeneiras) / 1000
    }

    private fun calcularPerdasSistemaIndustrial(
        embaixoPeneiraUm: Float,
        embaixoPeneiraDois: Float,
        embaixoPeneiraTres: Float,
        embaixoPeneiraQuatro: Float,
        areaTodasPeneiras: Float
    ): Float {
        return ((10000 * (embaixoPeneiraUm + embaixoPeneiraDois + embaixoPeneiraTres + embaixoPeneiraQuatro)) / areaTodasPeneiras) / 1000
    }

    private fun gerarRelatorio(nomeColeta: String, perdasPlataformaKgHa: Float, perdasPlataformaSacasHa: Float,
                               perdasSistemaIndustrialKgHa: Float, perdasSistemaIndustrialSacasHa: Float,
                               chart: PieChart) {
        val documento = Document(PageSize.A4)
        val directoryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString()
        val filePath = "$directoryPath/$nomeColeta.pdf"

        try {
            PdfWriter.getInstance(documento, FileOutputStream(filePath))
            documento.open()

            documento.addAuthor("Equipe ajudAgro")

            val table = PdfPTable(2)
            table.widthPercentage = 100f

            val headerCell = PdfPCell(Paragraph("Quantidade de Perdas"))
            headerCell.colspan = 2
            table.addCell(headerCell)

            val headers = arrayOf(
                "Perdas na plataforma (kg/ha)",
                "Perdas na plataforma (sacas/ha)",
                "Perdas no sistema industrial (kg/ha)",
                "Perdas no sistema industrial (sacas/ha)"
            )

            val values = arrayOf(perdasPlataformaKgHa.toString(), perdasPlataformaSacasHa.toString(),
                perdasSistemaIndustrialKgHa.toString(), perdasSistemaIndustrialSacasHa.toString())

            for (i in headers.indices) {
                table.addCell(headers[i])
                table.addCell(values[i])
            }

            documento.add(table)

            chart.visibility = View.VISIBLE

            chart.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    chart.viewTreeObserver.removeOnGlobalLayoutListener(this)

                    // Adiciona o gráfico de pizza ao PDF
                    val stream = ByteArrayOutputStream()

                    val bitmap = Bitmap.createBitmap(chart.width, chart.height, Bitmap.Config.ARGB_8888)

                    val canvas = Canvas(bitmap)
                    chart.draw(canvas)

                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)

                    val image = Image.getInstance(stream.toByteArray())

                    image.scaleToFit(PageSize.A4.width - 200, PageSize.A4.height - 200)
                    documento.add(image)

                    chart.visibility = View.GONE
                    documento.close()
                    Toast.makeText(context, "Relatório gerado", Toast.LENGTH_SHORT).show()
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Erro ao criar PDF", Toast.LENGTH_LONG).show()
            Log.e("Gerar PDF", "$e")
        }
    }
}