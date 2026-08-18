package br.edu.ifsp.scl.sc3046656.trucoscoreboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifsp.scl.sc3046656.trucoscoreboard.ui.theme.TrucoScoreBoardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrucoScoreBoardTheme {
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
        ContadorTimeA(
            counterA = counterA,
            counterB = counterB,
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
        HorizontalDivider(
            thickness = 2.dp,
            color = Color.Gray
        )
        ContadorTimeB(
            counterA = counterA,
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
    TrucoScoreBoardTheme {
        TelaComDoisContadores(modifier = Modifier)
    }
}

@Composable
fun ContadorTimeA(counterA : Int,
                  counterB: Int,
                  onIncrementarUm : () -> Unit,
                  onIncrementarTres : () -> Unit,
                  modifier: Modifier = Modifier){
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){

        if(counterB < 12 && counterA < 12){
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onIncrementarTres
            ){
                Text(text = "Mais três")
            }

            if(counterA != 11 && counterB != 11){
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onIncrementarUm
                ){
                    Text(text = "Mais um")
                }
            }
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
fun ContadorTimeB(counterA : Int,
                  counterB: Int,
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

        if (counterA < 12 && counterB < 12){
            if(counterA != 11 && counterB != 11){
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onIncrementarUm)
                {
                    Text(text = "Mais um")
                }
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onIncrementarTres
            ){
                Text(text = "Mais três")
            }
        }



    }
}

//@Preview(showBackground = true)
//@Composable
//fun ContadorTimeAPreview(){
//    TrucoScoreBoardTheme(){
//        ContadorTimeA(modifier = Modifier)
//    }
//}

