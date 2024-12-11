package com.example.brainofreeze.presentation.quiz.compnent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.brainofreeze.R
import com.example.brainofreeze.presentation.quiz.QuizState
import com.example.brainofreeze.presentation.utils.Dimens


//@Preview
//@Composable
//fun prev() {
//    quizInterface(onOptionSelect = {}, qNumber = 1)
//}


@Composable
fun quizInterface(
    onOptionSelect: (Int) -> Unit, qNumber: Int, quizState: QuizState, modifier: Modifier = Modifier
) {

    val questions = quizState.quiz?.question!!.replace(oldValue = "&quot", newValue = "\"")
        .replace(oldValue = "&#039", newValue = "\'")
    Box(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.wrapContentHeight()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "$qNumber.",
                    color = colorResource(id = R.color.blue_grey),
                    fontSize = Dimens.smallTextSize
                )
                Text(
                    text = questions,
                    modifier = Modifier.weight(9f),
                    color = colorResource(id = R.color.blue_grey),
                    fontSize = Dimens.smallTextSize
                )

            }

            Spacer(modifier = Modifier.height(Dimens.largeSpacerHeight))


            Column(modifier = Modifier.padding(horizontal = 15.dp)) {

                val option = if (quizState.shuffledOptions.size >= 4) {
                    listOf(
                        "A" to quizState.shuffledOptions[0]
                            .replace(oldValue = "&quot", newValue = "\"").replace(oldValue = "&#039", newValue = "\'"),
                        "B" to quizState.shuffledOptions[1]
                            .replace(oldValue = "&quot", newValue = "\"").replace(oldValue = "&#039", newValue = "\'"),
                        "C" to quizState.shuffledOptions[2]
                            .replace(oldValue = "&quot", newValue = "\"").replace(oldValue = "&#039", newValue = "\'"),
                        "D" to quizState.shuffledOptions[3]
                            .replace(oldValue = "&quot", newValue = "\"").replace(oldValue = "&#039", newValue = "\'"),
                    )
                } else {
                    listOf(
                        "A" to quizState.shuffledOptions[0]
                            .replace(oldValue = "&quot", newValue = "\"").replace(oldValue = "&#039", newValue = "\'"),
                        "B" to quizState.shuffledOptions[1]
                            .replace(oldValue = "&quot", newValue = "\"").replace(oldValue = "&#039", newValue = "\'"),
                    )
                }

                Column {
                    option.forEachIndexed { index, (optionNumber, optionText) ->
                        if (optionText.isNotEmpty()) {
                            quizOption(optionNumber = optionNumber,
                                options = optionText,
                                onOptionClick = { onOptionSelect(index) },
                                selected = quizState.selectedOption == index,
                                onUnselectOption = { onOptionSelect(-1) })
                        }

                        Spacer(modifier = Modifier.height(Dimens.smallSpaceHeight))
                    }
                }

                Spacer(modifier = Modifier.height(Dimens.extraLargeSpaceHeight))

            }
        }
    }
}