package com.example.tiptime

import android.graphics.Paint.Style
import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.tiptime.components.CustomButton
import com.example.tiptime.components.InputTextField
import com.example.tiptime.components.CustomButton
import com.example.tiptime.ui.theme.TipTimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                Myapp()
                // A surface container using the 'background' color from the theme

            }
        }
    }

}
//
//@Composable
//fun TipBackground(content: @Composable ()->Unit) {
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colors.background
//    ){
//        TopHeader()
//    }
//}

@Preview(showBackground = true)
@Composable
fun Myapp(){
    TopHeader()
    var amount= remember {
        mutableStateOf("")
    }
    var personCounter= remember {
        mutableStateOf(1)
    }
    var tipPercentage= remember {
        mutableStateOf(0f)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ){

        Column(Modifier.fillMaxWidth()) {


            TopHeader(getTotalAmount(amount.value,personCounter.value,tipPercentage.value))

//            Spacer(modifier = Modifier.height(120.dp))
            MainContent(
                amount = amount.value,
                amountChange = {
                    amount.value=it
                },
                personCounter = personCounter.value,
                onCounterChange ={
                    if(it<0){
                        if(personCounter.value < 2){
                            personCounter.value=1
                        }
                        else{
                            personCounter.value--
                        }

                    }
                    else{
                        personCounter.value++
                    }
                } ,
                tipPercentage = tipPercentage.value,
                tipPercentageChange ={
                    tipPercentage.value=it
                }
            )
            
        }
    }
}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainContent(
    amount:String="",
    amountChange:(String)->Unit,
    personCounter:Int=1,
    onCounterChange:(Int)->Unit,
    tipPercentage:Float=0.0f,
    tipPercentageChange:(Float)->Unit
){

//    var tipPercentage:Float
//    var billAmount:String

//    val validState= remember(billAmount) {
//        billAmount.trim().isNotEmpty()
//    }
    val keyboard=LocalSoftwareKeyboardController.current

    Surface(
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
    shape = RoundedCornerShape(8.dp),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Column {

            OutlinedTextField(

                value = amount,
                onValueChange = { amountChange.invoke(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                label = {
                        Text(text = "Enter bill")
                },
                placeholder = { Text(text = "Enter Your Amount") },
                keyboardOptions = KeyboardOptions(
                    autoCorrect = true,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    keyboard?.hide()
                })
            )
//            InputTextField(valueState = billAmount,
//                labelId = "Enter Bill", enabled = true,
//                isSingleLine = true,
//                onAction = KeyboardActions{
//                    if(!validState) return@KeyboardActions
//                    keboard?.hide()
//                }
//            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
               verticalAlignment = Alignment.CenterVertically

                        ) {
                Text(text = "Split" ,
                    style=MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.fillMaxWidth(.50f))
//
                CustomButton(imageVector = Icons.Rounded.Add) {

                    onCounterChange.invoke(1)
                }
                Text(text = "$personCounter")
                CustomButton(imageVector = Icons.Rounded.Remove) {

                    onCounterChange.invoke(-1)
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(text = "Tip" ,
                    style=MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.fillMaxWidth(.60f))
                Text(text = "Rs ${getTipAmount(amount,tipPercentage)}",
                style = MaterialTheme.typography.body1)
//
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
            horizontalAlignment = Alignment.CenterHorizontally
                ) {
                Text(text = "$tipPercentage" ,)
                Spacer(modifier = Modifier.height(5.dp))
                Slider(value =tipPercentage , onValueChange ={
                    tipPercentageChange.invoke(it)
                },
                    valueRange = 0f..100f, steps = 5,
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth())
            }

        }

    }
}





@Preview(showBackground = true)
@Composable
fun TopHeader(total: String="0.0"){
    Column(
        Modifier
            .fillMaxWidth()
            ) {
        Surface(
            Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(10.dp)
                .clip(RoundedCornerShape(corner = CornerSize(18.dp))),
            color= Color(0xFFE9D7F7)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Text(text = "Total Per Person",
                    style = MaterialTheme.typography.h4)
                Text(text = "Rs $total",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold
                )

            }

        }
        Spacer(modifier = Modifier.size(20.dp))

        
    }

}
fun getTipAmount(UserAmount:String,tipPercentage:Float):String{
    var amount:Float=0f
    if(!UserAmount.isEmpty()){
        amount=UserAmount.toFloat()
    }
   return (amount*tipPercentage.div(100)).toString()
}


fun getTotalAmount(amount: String,personCounter: Int,tipPercentage: Float):String{
    return when{
        amount.isEmpty()->{
            "0"
        }
        else->{
            val userAmount=amount.toFloat();
            val tipAmount=userAmount * tipPercentage.div(100)
            val perHeadAmount=(userAmount+tipAmount).div(personCounter)
            perHeadAmount.toString()
        }
    }
}

