package com.example.tiptime.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
//fun RoundIconButton(
////    modifier:Modifier,
////    imageVector:ImageVector,
////    onClick:()->Unit,
//////    tint: Color=   Color.Black.copy(alpha = 0.8f),
//////    backgroundColor:Color=MaterialTheme.colors.background,
////    elevation: Dp =4.dp
////){
////    Card(modifier = modifier.size()
////        .padding(4.dp)
////        .clickable { onClick.invoke() }) {
////        Icon(imageVector = imageVector, contentDescription = null)
////    }
//}
fun CustomButton(imageVector: ImageVector, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(12.dp)
            .clickable {
                onClick.invoke()
            }, shape = CircleShape
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .padding(4.dp)
        )
    }
}
