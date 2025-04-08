package com.example.saudeja12302120

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Criando um mapa para os medicamentos
        var estoqueMedicamentos = mapOf(
            "paracetamol" to true,
            "ibuprofeno" to true,
            "amoxicilina" to true,
            "dipirona" to true,
            "aziromicina" to false,
            "aspirina" to false
        )

            val consulta = findViewById<EditText>(R.id.txt_input)
            val enviar = findViewById<Button>(R.id.btnconsulta)
            val tvResultado = findViewById<TextView>(R.id.tvresultado)

            enviar.setOnClickListener {
                val medicamento = consulta.text.toString().trim().lowercase() // Pega o texto do EditText e remove espaços
                if (medicamento.isNotEmpty()) {
                    // Verifica se o medicamento está no estoque
                    val disponibilidade = estoqueMedicamentos[medicamento]
                    if (disponibilidade != null) {
                        // Exibe o resultado
                        val resultado = if (disponibilidade) {
                            "${medicamento.replaceFirstChar { it.uppercase() }} está disponível."
                        } else {
                            "${medicamento.replaceFirstChar { it.uppercase() }} não está disponível."
                        }
                        tvResultado.text = resultado
                    } else {
                        tvResultado.text = "Medicamento não encontrado."
                    }
                } else {
                    tvResultado.text = "Por favor, digite o nome de um medicamento."
                }

    }
}}

