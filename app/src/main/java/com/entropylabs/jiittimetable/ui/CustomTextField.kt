package com.entropylabs.jiittimetable.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.entropylabs.jiittimetable.ui.theme.GilroyMedium

@Composable
fun OutlinedTextFieldBackground(
    color: Color,
    content: @Composable () -> Unit
) {
    // This box just wraps the background and the OutlinedTextField
    Box {
        // This box works as background
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(top = 8.dp) // adding some space to the label
                .background(
                    color,
                    // rounded corner to match with the OutlinedTextField
                    shape = RoundedCornerShape(10.dp)
                )
        )
        // OutlineTextField will be the content...
        content()
    }
}
@Composable
fun CustomTextField(value: String, label:String , onValueChange: (String) -> Unit) {
    OutlinedTextFieldBackground(Color(0xFFFFD5B4)) {

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label, fontFamily = GilroyMedium) },
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = Color.Black,
                focusedBorderColor = Color(0xFFFFBC86),
                unfocusedBorderColor = Color(0xFFFFBC86),
                focusedLabelColor = Color.Black,
                //focusedIndicatorColor = Color.Transparent,
                //unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle.Default.copy(
                fontFamily = GilroyMedium,
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            )

    }
}