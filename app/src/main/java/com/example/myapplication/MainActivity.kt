package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var dbReference: DatabaseReference
    private lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtxtname = findViewById<EditText>(R.id.edtxtNombre)
        val laboratorio1 = findViewById<EditText>(R.id.edtxtlab1)
        val laboratorio2 = findViewById<EditText>(R.id.edtxtlaboratorio2)
        val edtxtParcial = findViewById<EditText>(R.id.edtxtparcial)
        val btn = findViewById<Button>(R.id.btn_guardar)

        btn.setOnClickListener{
            val name = edtxtname.text.toString()
            val Lab1 = (laboratorio1.text.toString()).toDouble()
            val Lab2 = (laboratorio2.text.toString()).toDouble()
            val Parcial = (edtxtParcial.text.toString()).toDouble()

            var globallab1 = Lab1 * 0.2
            var globallab2 = Lab2 * 0.2
            var totallabs = globallab1 + globallab2
            var totalparcial = Parcial * 0.6
            var Total = totallabs + totalparcial

            guardar(name, Lab1, Lab2, Parcial, Total)

        }
    }

    fun guardar(nombre:String, lab1:Double, lab2:Double, parcial:Double, total:Double){
        val student = HashMap<String, String>()
        student["Nombre del alumno"] = nombre
        student["Laboratorio 1"] = lab1.toString()
        student["laboratorio 2"] = lab2.toString()
        student["parcial"] = parcial.toString()
        student["total"] = total.toString()
        val rootRef = FirebaseDatabase.getInstance().reference
        val taskRef = rootRef.child("Alumnos").push()
        taskRef.setValue(student)

    }
}

