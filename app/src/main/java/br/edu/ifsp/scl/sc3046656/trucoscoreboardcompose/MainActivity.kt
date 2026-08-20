package br.edu.ifsp.scl.sc3046656.trucoscoreboardcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import br.edu.ifsp.scl.sc3046656.trucoscoreboardcompose.ui.theme.TrucoScoreBoardComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrucoScoreBoardComposeTheme {
                TelaComDoisContadores(modifier = Modifier)
            }
        }
    }
}

@Composable
fun TelaComDoisContadores(modifier: Modifier = Modifier) {
    var counterA by remember {mutableIntStateOf(0)}
    var counterB by remember {mutableIntStateOf(0)}

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        if(counterA < 12 && counterB < 12){
            ContadorTimeA(
                counterA = counterA,
                onIncrementarUm = {
                    counterA = (counterA + 1).coerceAtMost(12)
                },
                onIncrementarTres = {
                    counterA = (counterA + 3).coerceAtMost(12)
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {counterA=0;counterB=0}
            ) {
                Text("Reiniciar pontos")
            }


            ContadorTimeB(
                counterB = counterB,
                onIncrementarUm = {
                    counterB = (counterB + 1).coerceAtMost(12)
                },
                onIncrementarTres = {
                    counterB = (counterB + 3).coerceAtMost(12)
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )

        }

        if(counterA == 12){
            AlertDialog(
                onDismissRequest = {},
                confirmButton = {
                    Button(onClick = {counterA = 0; counterB = 0}){
                    Text("Nova partida")
                    }
                },
                title = {Text("Fim de Jogo")},
                text ={Text("Time A Venceu")})
        }

        if(counterB == 12){
            AlertDialog(
                onDismissRequest = {},
                confirmButton = {
                    Button(onClick = {counterA = 0; counterB = 0}){
                        Text("Nova partida")
                    }
                },
                title = {Text("Fim de Jogo")},
                text ={Text("Time B Venceu")})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaComDoisContadoresPreview(){
    TrucoScoreBoardComposeTheme {
        TelaComDoisContadores(modifier = Modifier)
    }
}

@Composable
fun ContadorTimeA(counterA : Int,
                  onIncrementarUm : () -> Unit,
                  onIncrementarTres : () -> Unit,
                  modifier: Modifier = Modifier){
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){

        if(counterA != 11){
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onIncrementarTres
            ){
                Text(text = "Mais três")
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onIncrementarUm
        ){
            Text(text = "Mais um")
        }





        Spacer(modifier = Modifier.weight(1f))


        Text(
            text = counterA.toString(),
            fontSize =48.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        if(counterA == 11){
            Text(text = "Time A na Mão de Onze")
        }


    }
}

@Composable
fun ContadorTimeB(counterB: Int,
                  onIncrementarUm : () -> Unit,
                  onIncrementarTres : () -> Unit,
                  modifier: Modifier = Modifier){
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        if(counterB == 11){
            Text(text = "Time B na Mão de Onze")
        }

        Text(
            text = counterB.toString(),
            fontSize = 48.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))


        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onIncrementarUm)
        {
            Text(text = "Mais um")
        }

        if(counterB != 11){
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onIncrementarTres
            ){
                Text(text = "Mais três")
            }
        }

    }
}

