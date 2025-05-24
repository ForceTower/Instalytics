package dev.forcetower.instalytics.android.ui.components.pin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.forcetower.instalytics.android.ui.components.text.InstalyticsTextField
import dev.forcetower.instalytics.android.ui.theme.InstalyticsTheme
import kotlinx.coroutines.launch

@Composable
fun PinEntryView(
    modifier: Modifier = Modifier,
    pinLength: Int = 4,
    onPinEntered: (String) -> Unit
) {
    var pinValue by remember { mutableStateOf("") }
    val focusRequesters = remember { List(pinLength) { FocusRequester() } }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) { // Request focus on the first field initially
        focusRequesters.firstOrNull()?.requestFocus()
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until pinLength) {
            InstalyticsTextField(
                value = pinValue.getOrNull(i)?.toString() ?: "",
                onValueChange = { newValue ->
                    if (newValue.length <= 1) { // Allow only single digit or empty
                        // val oldChar = pinValue.getOrNull(i)?.toString() ?: ""
                        val newPin = pinValue.toMutableList()
                        if (newPin.size > i) {
                            newPin[i] = newValue.firstOrNull() ?: ' ' // Use space for empty to maintain length
                        } else if (newValue.isNotEmpty()) {
                            newPin.add(newValue.first())
                        }

                        // Reconstruct pinValue ensuring it doesn't exceed pinLength
                        // and handles backspace correctly
                        var tempPin = ""
                        newPin.forEachIndexed { index, char ->
                            if (index < i && char != ' ') tempPin += char
                            else if (index == i && newValue.isNotEmpty()) tempPin += newValue.first()
                            else if (index > i && char != ' ') tempPin += char
                        }
                        pinValue = tempPin.replace(" ", "").take(pinLength)


                        if (newValue.isNotEmpty() && i < pinLength - 1) {
                            // Move focus to next field
                            coroutineScope.launch {
                                focusRequesters.getOrNull(i + 1)?.requestFocus()
                            }
                        }
                        if (pinValue.length == pinLength) {
                            onPinEntered(pinValue)
                        }
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f) // Makes it a square
                    .padding(horizontal = 4.dp)
                    .focusRequester(focusRequesters[i])
                    .onKeyEvent { event ->
                        if (event.type == KeyEventType.KeyUp) {
                            when (event.key) {
                                Key.Backspace -> {
                                    if (pinValue.getOrNull(i)?.toString()?.isEmpty() != false && i > 0) {
                                        // If current is empty and backspace is pressed,
                                        // move focus to previous and clear it
                                        pinValue = pinValue.dropLast(1) // Or more sophisticated clearing
                                        coroutineScope.launch {
                                            focusRequesters[i - 1].requestFocus()
                                        }
                                        return@onKeyEvent true
                                    } else if (pinValue.getOrNull(i)?.toString()?.isNotEmpty() == true) {
                                        // If current has value, backspace will clear it (handled by onValueChange)
                                        // but we might want to stay in this field or move to previous
                                        // depending on desired behavior.
                                        // For this setup, onValueChange handles clearing and focus moves forward.
                                        // To move back on clearing, adjust onValueChange logic.
                                    }
                                    return@onKeyEvent false // Let onValueChange handle the state
                                }
                            }
                        }
                        false
                    },
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp // Adjust font size as needed
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword, // Or Number for visible digits
                    imeAction = if (i == pinLength - 1) ImeAction.Done else ImeAction.Next
                ),
//                shape = MaterialTheme.shapes.medium, // Rounded corners
//                colors = OutlinedTextFieldDefaults.colors() // Customize colors if needed
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PinEntryViewPreview() {
    InstalyticsTheme {
        var enteredPin by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Enter Your PIN", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(16.dp))
            PinEntryView(pinLength = 4) { pin ->
                enteredPin = pin
                // Handle PIN submission, e.g., show a message or navigate
            }
            Spacer(Modifier.height(16.dp))
            if (enteredPin.isNotEmpty()) {
                Text("Entered PIN: $enteredPin")
            }
        }
    }
}