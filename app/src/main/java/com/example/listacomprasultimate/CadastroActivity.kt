package com.example.listacomprasultimate

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_main.*

class CadastroActivity : AppCompatActivity() {
    val COD_IMAGE = 101
    var imageBitMap: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)


        btn_inserir.setOnClickListener {

            val produto = txt_produto.text.toString()
            val qtd = txt_qtd.text.toString()
            val valor = txt_valor.text.toString()


            if (produto.isNotEmpty() && qtd.isNotEmpty() && valor.isNotEmpty()) {
                val prod = Produto(produto, qtd.toInt(), valor.toDouble(),imageBitMap)

                produtosGlobal.add(prod)

                txt_produto.text.clear()
                txt_qtd.text.clear()
                txt_valor.text.clear()
            } else {
                if (txt_produto.text.isEmpty()) {
                    txt_produto.error = "Preencha o nome do produto"
                } else {
                    txt_produto.error = null
                }
                if (txt_qtd.text.isEmpty()) {
                    txt_qtd.error = "Preencha a quantidade"
                } else {
                    txt_qtd.error = null
                }
                if (txt_valor.text.isEmpty()) {
                    txt_valor.error = "Preencha o valor"
                } else {
                    txt_valor.error = null
                }

            }
        }

        img_foto_produto.setOnClickListener {
            abrirGaleria()
        }

    }

        fun abrirGaleria() {

            val intent = Intent(Intent.ACTION_GET_CONTENT)

            intent.type = "image/*"

         startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), COD_IMAGE)
        }


            override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
                super.onActivityResult(requestCode, resultCode, data)
                    if (requestCode == COD_IMAGE && resultCode == AppCompatActivity.RESULT_OK) {
                      if (data != null) {

                        val inputStream = data.getData()?.let { contentResolver.openInputStream(it) }

                         imageBitMap = BitmapFactory.decodeStream(inputStream)
                    
                          img_foto_produto.setImageBitmap(imageBitMap)
                      }
                    }
            }
}
