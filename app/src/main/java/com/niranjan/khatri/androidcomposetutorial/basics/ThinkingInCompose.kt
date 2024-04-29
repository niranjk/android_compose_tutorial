package com.niranjan.khatri.androidcomposetutorial.basics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel


var selected : Boolean = false
@Composable
fun RadioButtonOption(isSelected: Boolean, text: String, onSelected: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        RadioButton(
            selected = isSelected,
            onClick = {
                selected = !selected // simple steps ...
                onSelected(selected)
            }
        )
        Text(text = text, modifier = Modifier.padding(top = 16.dp))
    }
}

@Composable
fun SurveyQuestion(questionText: String, options: List<String>, onAnswerSelected: (String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = questionText, style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(8.dp))
        options.forEach { option ->
            RadioButtonOption(isSelected = selected, text = option) { isSelected ->
                if (isSelected) {
                    onAnswerSelected(option)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SurveyScreen(viewModel: SurveyViewModel = SurveyViewModel()) {
    val question = viewModel.currentQuestion
    val options = viewModel.currentQuestionOptions
    var selectedAnswer by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        SurveyQuestion(questionText = question, options = options) { answer ->
            selectedAnswer = answer
        }
        Button(
            onClick = { viewModel.onAnswerSubmitted(selectedAnswer) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Submit")
        }
    }
}


class SurveyViewModel : ViewModel() {
    private val _questions = listOf(
        "What is your favorite color?",
        "What is your favorite animal?"
    )
    private val _options = mapOf(
        _questions[0] to listOf("Red", "Green", "Blue"),
        _questions[1] to listOf("Cat", "Dog", "Bird")
    )
    private var _currentIndex = 0

    val currentQuestion: String
        get() = _questions[_currentIndex]

    val currentQuestionOptions: List<String>
        get() = _options[currentQuestion]!!

    fun onAnswerSubmitted(answer: String) {
        // Handle answer submission (e.g., navigate to next question or results)
        _currentIndex++
    }
}
