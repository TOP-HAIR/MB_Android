package com.example.tophair.app.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tophair.ui.theme.TopHairTheme

fun FormattedCepTextField(
    initialValue: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
}

fun removeRegexCep(input: String) {

    return
}

@Preview(showBackground = true)
@Composable
fun FormattedCepTextFieldPreview() {
    TopHairTheme {
        val cep = remember { mutableStateOf("") }

        FormattedCpfTextField(
            initialValue = cep.value,
            onValueChange = { cep.value = it },
            modifier = Modifier.fillMaxWidth()
        )
    }
}