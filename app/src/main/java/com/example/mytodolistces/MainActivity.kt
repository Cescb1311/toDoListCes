package com.example.mytodolistces

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adaptadorDeTarea:TareaAdaptador
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adaptadorDeTarea= TareaAdaptador(mutableListOf())
//No termino de entender bien esto
        rvTareas.adapter=adaptadorDeTarea
        //No entendí para qué es esto exactamente
        rvTareas.layoutManager=LinearLayoutManager(this)
        btnAgregarTarea.setOnClickListener {
            val tituloDeTarea=etTituloDeTarea.text.toString()
            if (tituloDeTarea.isNotEmpty()){
                val tarea=Tarea(tituloDeTarea)
                adaptadorDeTarea.agregarTarea(tarea)
                etTituloDeTarea.text.clear()
            }
        }
        btnEliminarTarea.setOnClickListener {
            adaptadorDeTarea.eliminarTareas()
        }
    }
}
