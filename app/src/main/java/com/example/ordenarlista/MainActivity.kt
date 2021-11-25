package com.example.ordenarlista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SwitchCompat

class MainActivity : AppCompatActivity() {
    private var lista = mutableListOf<Float>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun checarNum(): Boolean{
        val etNum = findViewById<EditText>(R.id.etNum)

        if (etNum.text.isEmpty()){
            Toast.makeText(this, getString(R.string.errorSinNum),Toast.LENGTH_LONG).show()
            return false
        }else if(etNum.text.contains('-')){
            if(!etNum.text.contains('.')){
                if(etNum.text.length == 7){
                    etNum.text = null
                    Toast.makeText(this, getString(R.string.errorLongitud),Toast.LENGTH_LONG).show()
                    return false
                }
            }
        } else{
            if(etNum.text.contains('.')){
                if(etNum.text.length > 6){
                    etNum.text = null
                    Toast.makeText(this, getString(R.string.errorLongitud),Toast.LENGTH_LONG).show()
                    return false
                }
            } else if(etNum.text.length > 5){
                etNum.text = null
                Toast.makeText(this, getString(R.string.errorLongitud),Toast.LENGTH_LONG).show()
                return false
            }
        }
        lista.add(etNum.text.toString().toFloat())
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.arriba -> ascendente()
            R.id.abajo -> descendente()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun ascendente(){
        val adaptador: ArrayAdapter<Float>
        val listNum = findViewById<ListView>(R.id.listNumeros)
        if(lista.isEmpty()){
            Toast.makeText(this, getString(R.string.errorListaVacia),Toast.LENGTH_LONG).show()
        }else{
            lista.sort()
            adaptador = ArrayAdapter(this,android.R.layout.simple_list_item_1,lista)
            listNum.adapter = adaptador
        }
    }

    private fun descendente(){
        val adaptador: ArrayAdapter<Float>
        val listNum = findViewById<ListView>(R.id.listNumeros)
        if(lista.isEmpty()){
            Toast.makeText(this, getString(R.string.errorListaVacia),Toast.LENGTH_LONG).show()
        }else{
            lista.sortDescending()
            adaptador = ArrayAdapter(this,android.R.layout.simple_list_item_1,lista)
            listNum.adapter = adaptador
        }
    }

    fun ingresarDato(view: View) {
        val adaptador: ArrayAdapter<Float>
        val etNum = findViewById<EditText>(R.id.etNum)
        val listNum = findViewById<ListView>(R.id.listNumeros)
        if(checarNum()){
            etNum.text = null
            adaptador = ArrayAdapter(this,android.R.layout.simple_list_item_1,lista)
            listNum.adapter = adaptador
        }
    }

    fun decimalOn(view: View) {
        val etNum = findViewById<EditText>(R.id.etNum)
        val swNegativo = findViewById<SwitchCompat>(R.id.swNegativo)
        val swDecimal = findViewById<SwitchCompat>(R.id.swDecimal)
        if(swDecimal.isChecked){
            etNum.inputType = 8194
            if(swNegativo.isChecked){
                etNum.inputType = 12290
            }
        }else{
            etNum.inputType = 2
            if(swNegativo.isChecked){
                etNum.inputType = 4098
            }
        }
    }
    fun negativoOn(view: View) {
        val etNum = findViewById<EditText>(R.id.etNum)
        val swNegativo = findViewById<SwitchCompat>(R.id.swNegativo)
        val swDecimal = findViewById<SwitchCompat>(R.id.swDecimal)
        if(swNegativo.isChecked){
            etNum.inputType = 4098
            if(swDecimal.isChecked){
                etNum.inputType = 12290
            }
        }else{
            etNum.inputType = 2
            if(swDecimal.isChecked){
                etNum.inputType = 8194
            }
        }
    }
}
























