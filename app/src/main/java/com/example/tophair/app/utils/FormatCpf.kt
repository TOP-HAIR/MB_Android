package com.example.tophair.app.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tophair.ui.theme.TopHairTheme

@Composable
fun FormattedCpfTextField(
    initialValue: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
}

fun removeRegexCpf(input: String) {

    return
}

@Preview(showBackground = true)
@Composable
fun FormattedCpfTextFieldPreview() {
    TopHairTheme {
        val cpf = remember { mutableStateOf("") }

        FormattedCpfTextField(
            initialValue = cpf.value,
            onValueChange = { cpf.value = it },
            modifier = Modifier.fillMaxWidth()
        )
    }
}