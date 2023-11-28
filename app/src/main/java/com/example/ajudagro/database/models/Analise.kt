package com.example.ajudagro.database.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity("analises")
data class Analise(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @Embedded
    val analiseGeral: AnaliseGeral,
    @ColumnInfo("Diametro Peneira")
    val diametroPeneira: Float,
    @ColumnInfo("Area unica peneira")
    val areaUmaPeneira: Float,
    @ColumnInfo("Area todas peneiras")
    val areaTodasPeneiras: Float,
    @ColumnInfo("Em cima Peneira 1")
    val emCimaPeneiraUm: Float,
    @ColumnInfo("Em cima Peneira 2")
    val emCimaPeneiraDois: Float,
    @ColumnInfo("Em cima Peneira 3")
    val emCimaPeneiraTres: Float,
    @ColumnInfo("Em cima Peneira 4")
    val emCimaPeneiraQuatro: Float,
    @ColumnInfo("Embaixo Peneira 1")
    val embaixoPeneiraUm: Float,
    @ColumnInfo("Embaixo Peneira 2")
    val embaixoPeneiraDois: Float,
    @ColumnInfo("Embaixo Peneira 3")
    val embaixoPeneiraTres: Float,
    @ColumnInfo("Embaixo Peneira 4")
    val embaixoPeneiraQuatro: Float,
): Parcelable

@Parcelize
data class AnaliseGeral(
    @ColumnInfo("Nome")
    val nome: String?,
    @ColumnInfo("Latitude")
    val latitude: String?,
    @ColumnInfo("Longitude")
    val longitude: String?,
    @ColumnInfo("Area")
    val area: Int,
    @ColumnInfo("Maquinario")
    val maquinario: String?,
    @ColumnInfo("Data")
    val data: String?,
    @ColumnInfo("Cidade")
    val cidade: String?,
    @ColumnInfo("Estado")
    val estado: String?
): Parcelable
