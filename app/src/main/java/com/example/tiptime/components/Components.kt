package com.example.tiptime.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputTextField(
    modifier:Modifier=Modifier,
    valueState:MutableState<String>,
    labelId:String,
    enabled:Boolean,
    isSingleLine:Boolean,
    keyboardType:KeyboardType=KeyboardType.Number,
    imeActions:ImeAction=ImeAction.Next,
    onAction:KeyboardActions=KeyboardActions.Default
    ){
    OutlinedTextField(
        modifier=modifier.padding(10.dp),
        value = valueState.value, onValueChange =
    {
        valueState.value=it
    },
    label = { Text(text = labelId.toString())},
    leadingIcon = {Icon(imageVector = Icons.Rounded.AttachMoney, contentDescription = "Money Icon")},
        singleLine = isSingleLine,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.onBackground) ,
        enabled=enabled,
        keyboardActions = onAction,
        keyboardOptions = KeyboardOptions(keyboardType=keyboardType, imeAction = imeActions)

        )

}