import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.tophair.R
import com.example.tophair.ui.theme.TopHairTheme

@Composable
fun FormattedPhoneNumberTextField(
    initialValue: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var textState by remember { mutableStateOf(TextFieldValue(initialValue)) }
    val focusManager = LocalFocusManager.current

    TextField(
        value = textState,
        onValueChange = { newText ->
            val formattedText = formatPhoneNumber(newText.text)
            textState = textState.copy(text = formattedText)
            onValueChange(formattedText)
        },
        label = { Text(stringResource(R.string.txt_telefone)) },
        modifier = modifier,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Next)
                focusManager.clearFocus()
            }
        ),
        singleLine = true
    )
}

fun formatPhoneNumber(input: String): String {
    val digits = input.filter { it.isDigit() }
    if (digits.isEmpty()) return ""

    val formatted = buildString {
        append("(")
        for (i in digits.indices) {
            append(digits[i])
            if (i == 1) append(") ")
            if (i == 6) append("-")
            if (i == 10) break
        }
    }
    return formatted
}

fun removeRegexPhoneNumber(input: String) {

    return
}


@Preview(showBackground = true)
@Composable
fun FormattedPhoneNumberTextFieldPreview() {
    TopHairTheme {
        val telefone = remember { mutableStateOf("") }

        FormattedPhoneNumberTextField(
            initialValue = telefone.value,
            onValueChange = { telefone.value = it },
            modifier = Modifier.fillMaxWidth()
        )
    }
}