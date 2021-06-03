package com.example.jogodavia

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.jogodavia.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val array = arrayOf(binding.b1, binding.b2, binding.b3, binding.b4, binding.b5, binding.b6, binding.b7, binding.b8, binding.b9)
        for(i in array){
            i.setOnClickListener {
                goplay(i)
                win(array)
            }
            binding.reset.setOnClickListener {
                reset(array)
            }
        }

    }
    //A variavel "player" define a vez de cada jogador, 1 para o primeiro jogador e 0 para o segundo
    var player = 1
    //A variavel "contador" conta o número de jogadas realizadas na partida
    var contador = 0

    //Função que define a vez de cada jogador
    fun goplay(selected: Button){
        //Esse "if" serve para evitar que um mesmo botão seja pressionado 2 vezes e o seu valor mude
        if(selected.text.equals("X") ||selected.text.equals("0")){
            Toast.makeText(this, "Já foi", Toast.LENGTH_SHORT).show()
            return
        }
        if(player == 1){
            selected.text = "X"
            selected.setBackgroundColor(Color.BLUE)
            player = 0
            contador++
        }else{
            selected.text = "0"
            selected.setBackgroundColor(Color.GREEN)
            player = 1
            contador++
        }

    }
    //Função que define o vencedor da partida
    fun win(teste: Array<Button>){
        //Aqui contém as 8 formas de vencer a partida
        if((teste[0].text.equals("X") && teste[1].text.equals(teste[0].text) && teste[0].text.equals(teste[2].text)) ||
            (teste[0].text.equals("X") && teste[3].text.equals(teste[0].text) && teste[0].text.equals(teste[6].text)) ||
            (teste[0].text.equals("X") && teste[4].text.equals(teste[0].text) && teste[0].text.equals(teste[8].text)) ||
            (teste[1].text.equals("X") && teste[4].text.equals(teste[1].text) && teste[1].text.equals(teste[7].text)) ||
            (teste[2].text.equals("X") && teste[5].text.equals(teste[2].text) && teste[2].text.equals(teste[8].text)) ||
            (teste[3].text.equals("X") && teste[4].text.equals(teste[3].text) && teste[3].text.equals(teste[5].text)) ||
            teste[6].text.equals("X") && teste[7].text.equals(teste[6].text) && teste[6].text.equals(teste[8].text) ||
            teste[2].text.equals("X") && teste[4].text.equals(teste[2].text) && teste[6].text.equals(teste[2].text)){
            Toast.makeText(this, "Jogador 1 venceu", Toast.LENGTH_SHORT).show()
            reset(teste)
            return
        }
        //Aqui contém as 8 formas de vencer a partida
        if((teste[0].text.equals("0") && teste[1].text.equals(teste[0].text) && teste[0].text.equals(teste[2].text)) ||
            (teste[0].text.equals("0") && teste[3].text.equals(teste[0].text) && teste[0].text.equals(teste[6].text)) ||
            (teste[0].text.equals("0") && teste[4].text.equals(teste[0].text) && teste[0].text.equals(teste[8].text)) ||
            (teste[1].text.equals("0") && teste[4].text.equals(teste[1].text) && teste[1].text.equals(teste[7].text)) ||
            (teste[2].text.equals("0") && teste[5].text.equals(teste[2].text) && teste[2].text.equals(teste[8].text)) ||
            (teste[3].text.equals("0") && teste[4].text.equals(teste[3].text) && teste[3].text.equals(teste[5].text)) ||
            teste[6].text.equals("0") && teste[7].text.equals(teste[6].text) && teste[6].text.equals(teste[8].text) ||
            teste[2].text.equals("0") && teste[4].text.equals(teste[2].text) && teste[6].text.equals(teste[2].text)){
            Toast.makeText(this, "Jogador 2 venceu", Toast.LENGTH_SHORT).show()
            reset(teste)
            return
        }
         //Verifica se a partida deu "velha"
        if (contador >= 9){
            Toast.makeText(this, "Deu velha", Toast.LENGTH_SHORT).show()
            reset(teste)
        }
    }
    //Função para resetar o jogo
    fun reset(array: Array<Button>){
        //Esse "count" vai servir para redefinir os valores dos botões para os valores iniciais{1,2,3,4,5,6,7,8,9}
        var count = "1"
        //O "k" vai servir como uma váriavel auxiliar para armazenar o conteúdo de "count" como um inteiro
        var k = 0
        //Laço que percorrerá todos os buttons definindo seus valores de texto para o inicial e mudando a cor do botão para a cor inicial
        for (i in array){
            i.text = count
            i.setBackgroundColor(Color.rgb(156,39,176))
            k = count.toInt()
            k++
            count = k.toString()
        }
        //Zerando o contador, pois se o jogo foi resetado então a quantidade de jogadas também deve ser resetada
        contador = 0
    }
}


