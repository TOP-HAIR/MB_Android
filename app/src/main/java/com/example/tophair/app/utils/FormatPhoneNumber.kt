import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tophair.R
import com.example.tophair.app.data.entities.enum.FilterServicoEnum
import com.example.tophair.app.data.entities.enum.NavMenuEnum
import com.example.tophair.app.data.entities.enum.TextType
import com.example.tophair.app.data.entities.enum.TitleType
import com.example.tophair.app.screen.menu.tabs.formatarDataHoraParaPadraoBrasileiro
import com.example.tophair.app.utils.CardComponent
import com.example.tophair.app.utils.CustomButton
import com.example.tophair.app.utils.CustomIconButton
import com.example.tophair.app.utils.fonts.TextComposable
import com.example.tophair.app.utils.fonts.TitleComposable
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
    val scrollState = rememberScrollState()
    Column(modifier = Modifier
        .fillMaxSize()) {

    }
    

}