package com.example.mytodolistces

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.titulo_tarea.view.*

//Tendrá un atributo que será del tipo Lista con elementos del tipo Tarea
class TareaAdaptador (private val tareas:MutableList<Tarea>):RecyclerView.Adapter<TareaAdaptador.TituloDeTarea>(){
    //Esto no lo entendí muy bien
    class TituloDeTarea(vistaDeTarea: View):RecyclerView.ViewHolder(vistaDeTarea)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TituloDeTarea {
        return TituloDeTarea(
            LayoutInflater.from(parent.context).inflate(R.layout.titulo_tarea,parent,false)
        )
    }

    fun agregarTarea(tarea:Tarea){
        tareas.add(tarea)
        notifyItemInserted(tareas.size-1)
    }

    fun eliminarTareas(){
        tareas.removeAll { tarea ->
            tarea.isChecked
        }
        notifyDataSetChanged()
    }

    private fun alternarTachado(tvTituloDeTarea: TextView,isChecked:Boolean) {
        if (isChecked){
            tvTituloDeTarea.paintFlags=tvTituloDeTarea.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            tvTituloDeTarea.paintFlags=tvTituloDeTarea.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TituloDeTarea, position: Int) {
        val tareaActual=tareas[position]
        holder.itemView.apply {
            tvTituloTarea.text=tareaActual.title
            cbCheck.isChecked=tareaActual.isChecked
            alternarTachado(tvTituloTarea,tareaActual.isChecked)
            cbCheck.setOnCheckedChangeListener { _, isChecked ->
                alternarTachado(tvTituloTarea,isChecked)
                tareaActual.isChecked=!tareaActual.isChecked
            }
        }

    }

    override fun getItemCount(): Int {
        return tareas.size

    }
}

