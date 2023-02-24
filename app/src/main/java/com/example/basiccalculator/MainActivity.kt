package com.example.basiccalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basiccalculator.ui.theme.BasicCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CalculatorApp()
                }
            }
        }
    }
}

@Composable
fun CalculatorApp() {
    var result by remember {
        mutableStateOf(0.0)
    }
    var value by remember {
        mutableStateOf("")
    }
    var notEqualClicks by remember {
        mutableStateOf(0)
    }
    var operator by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .padding(24.dp)
    ) {
        NumberDisplay(
            value = value,
            onValueChange = {value = it}
        )
        Text(text = result.toString())
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            OperationButton(
                onClicked = {
                    result = 0.0
                    value = ""
                    operator = ""
                    notEqualClicks = 0
                },
                modifier = Modifier.weight(0.5f),
                buttonValue = stringResource(id = R.string.reset_btn)
            )
            OperationButton(
                onClicked = {
                    result = calculateResult(
                        result, value.toDouble(), operator
                    )
                    value = ""
                    notEqualClicks = 0
                },
                modifier = Modifier.weight(0.5f),
                buttonValue = stringResource(id = R.string.equals_btn)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OperationButton(
                onClicked = {
                    when (notEqualClicks) {
                        0 -> {
                            result = value.toDouble()
                        }
                        else -> {
                            result = calculateResult(
                                result, value.toDouble(), operator
                            )
                        }
                    }
                    operator = "+"
                    value = ""
                    notEqualClicks++
                },
                buttonValue = stringResource(id = R.string.plus_btn)
            )
            OperationButton(
                onClicked = {
                    when (notEqualClicks) {
                        0 -> {
                            result = value.toDouble()
                        }
                        else -> {
                            result = calculateResult(
                                result, value.toDouble(), operator
                            )
                        }
                    }
                    operator = "-"
                    value = ""
                    notEqualClicks++
                },
                buttonValue = stringResource(id = R.string.minus_btn)
            )
            OperationButton(
                onClicked = {
                    when (notEqualClicks) {
                        0 -> {
                            result = value.toDouble()
                        }
                        else -> {
                            result = calculateResult(
                                result, value.toDouble(), operator
                            )
                        }
                    }
                    operator = "x"
                    value = ""
                    notEqualClicks++
                },
                buttonValue = stringResource(id = R.string.times_btn)
            )
            OperationButton(
                onClicked = {
                    when (notEqualClicks) {
                        0 -> {
                            result = value.toDouble()
                        }
                        else -> {
                            result = calculateResult(
                                result, value.toDouble(), operator
                            )
                        }
                    }
                    operator = ":"
                    value = ""
                    notEqualClicks++
                },
                buttonValue = stringResource(id = R.string.division_btn)
            )
        }
    }
}

@Composable
fun NumberDisplay(
    value : String,
    onValueChange : (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .border(1.dp, Color.Green)
            .background(Color.Black),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.None
        ),
        textStyle = TextStyle(
            textAlign = TextAlign.End,
        )
    )
}

@Composable
fun OperationButton(
    onClicked : () -> Unit,
    buttonValue : String,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClicked,
        modifier = modifier
    ) {
        Text(text = buttonValue)
    }
}

private fun calculateResult(
    num1 : Double,
    num2 : Double,
    operator : String
) : Double {
    return when (operator) {
        "+" -> num1 + num2
        "-" -> num1 - num2
        ":" -> num1 / num2
        else -> num1 * num2
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DefaultPreview() {
    BasicCalculatorTheme {
        CalculatorApp()
    }
}