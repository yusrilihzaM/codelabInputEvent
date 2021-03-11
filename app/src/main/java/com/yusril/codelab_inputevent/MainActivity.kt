package com.yusril.codelab_inputevent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var edtWidth:EditText
    private lateinit var edtLength:EditText
    private lateinit var edtHeight:EditText
    private lateinit var btnCalculate:Button
    private lateinit var tvResult: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtHeight=findViewById(R.id.edt_height)
        edtWidth=findViewById(R.id.edt_width)
        edtLength=findViewById(R.id.edt_length)
        btnCalculate=findViewById(R.id.btn_calculate)
        tvResult=findViewById(R.id.tv_result)
        btnCalculate.setOnClickListener(this)

        if(savedInstanceState!=null){
            val result=savedInstanceState.getString(STATE_RESULT)
            tvResult.text=result
        }
    }
    // tempat menyimpan data
    companion object {
        private const val STATE_RESULT = "state_result"
    }
    // mentimpan data ketika rotzte
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString(STATE_RESULT,tvResult.text.toString())// masukan hasil kedalam states
    }
    override fun onClick(v: View?) {
        if (v != null) {
            if(v.id==R.id.btn_calculate){
                val inputLength = edtLength.text.toString().trim()
                val inputWidth = edtWidth.text.toString().trim()
                val inputHeight = edtHeight.text.toString().trim()

                var isEmptyFields = false

                when{
                    inputLength.isEmpty()->{
                        isEmptyFields = true
                        edtLength.error = "Field ini tidak boleh kosong"
                    }
                    inputWidth.isEmpty() -> {
                    isEmptyFields = true
                    edtWidth.error = "Field ini tidak boleh kosong"
                }
                    inputHeight.isEmpty() -> {
                        isEmptyFields = true
                        edtHeight.error = "Field ini tidak boleh kosong"
                    }
                }

                if(!isEmptyFields){
                    val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
                    tvResult.text = volume.toString()
                }
            }
        }
    }
}