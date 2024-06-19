import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tophair.R
import com.example.tophair.app.utils.CustomButton
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
            val digits = newText.text.filter { it.isDigit() }
            if (newText.text.length <= textState.text.length) {
                textState = textState.copy(
                    text = digits,
                    selection = TextRange(digits.length)
                )
                onValueChange(digits)
            } else if (digits.length <= 11) {
                val formattedText = formatPhoneNumber(digits)
                textState = textState.copy(
                    text = formattedText,
                    selection = TextRange(formattedText.length)
                )
                onValueChange(digits)
            }
        },
        label = { Text(stringResource(id = R.string.txt_telefone)) },
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

fun formatPhoneNumber(digits: String): String {
    val formatted = StringBuilder()
    for (i in digits.indices) {
        formatted.append(digits[i])
        if (i == 1) formatted.append(") ")
        if (i == 6) formatted.append("-")
    }
    return if (digits.isNotEmpty()) "(${formatted.toString()}" else formatted.toString()
}

fun removePhoneNumberMask(input: String): String {
    return input.filter { it.isDigit() }
}

@Preview(showBackground = true)
@Composable
fun FormattedPhoneNumberTextFieldPreview() {
    TopHairTheme {
        val phone = remember { mutableStateOf("") }

        FormattedPhoneNumberTextField(
            initialValue = phone.value,
            onValueChange = { phone.value = removePhoneNumberMask(it) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}