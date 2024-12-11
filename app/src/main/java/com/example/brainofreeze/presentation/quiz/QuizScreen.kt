package com.example.brainofreeze.presentation.quiz

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import com.example.brainofreeze.R
import com.example.brainofreeze.presentation.common.ButtonBox
import com.example.brainofreeze.presentation.common.QuizAppBar
import com.example.brainofreeze.presentation.navGraph.Routes
import com.example.brainofreeze.presentation.quiz.compnent.quizInterface
import com.example.brainofreeze.presentation.quiz.compnent.shimmerEffectquizInterface
import com.example.brainofreeze.presentation.utils.Constants
import com.example.brainofreeze.presentation.utils.Dimens
import kotlinx.coroutines.launch


//@Preview
//@Composable
//fun prevQuiz() {
//    QuizScreen(noOfQuizzes = 12, quizCategory = "GK", quizDifficulty = "Easy" , quizType = "true" , event = {} , state = StateQuizScreen() )
//}


@Composable
fun QuizScreen(
    noOfQuizzes: Int,
    quizCategory: String,
    quizDifficulty: String,
    quizType: String,
    event: (EventQuizScreen) -> Unit,
    state: StateQuizScreen,
    navController : NavController
) {

    BackHandler {
       navController.navigate(Routes.HomeScreen.route){
           popUpTo(Routes.HomeScreen.route){
               inclusive = true
           }
       }
    }
    val difficulty = when (quizDifficulty) {
        "Medium" -> "medium"
        "Hard" -> "hard"
        else -> "easy"
    }

    val type = when (quizType) {
        "Multiple Choice" -> "multiple"
        else -> "boolean"
    }
    LaunchedEffect(key1 = Unit) {
        event(
            EventQuizScreen.getQuiz(
                noOfQuizzes, Constants.categoriesMap[quizCategory]!!, difficulty, type
            )
        )
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        QuizAppBar(quizCategory = quizCategory) {
            navController.navigate(Routes.HomeScreen.route){
                popUpTo(Routes.HomeScreen.route){
                    inclusive = true
                }
            }
        }
        Column(
            modifier = Modifier
                .padding(Dimens.verysmallPadding)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(Dimens.largeSpacerHeight))

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Questions : ${noOfQuizzes}",
                    color = colorResource(id = R.color.blue_grey)
                )
                Text(
                    text = quizDifficulty, color = colorResource(id = R.color.blue_grey)
                )
            }
            Spacer(modifier = Modifier.height(Dimens.smallSpaceHeight))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.verySmallViewHeight)
                    .clip(RoundedCornerShape(Dimens.mediumCornerRadius))
                    .background(colorResource(id = R.color.blue_grey)),
            )

            Spacer(modifier = Modifier.height(Dimens.largeSpacerHeight))


            // quizInterface
            if (quizFetched(state)) {

                val pageState = rememberPagerState() { state.quizState.size }
                HorizontalPager(state = pageState) {index->
                    quizInterface(
                        onOptionSelect = {selectedIndex->
                            event(EventQuizScreen.setOptionSelected(index,selectedIndex))
                        },
                        qNumber = index + 1,
                        quizState = state.quizState[index],
                        modifier = Modifier.weight(1f)
                    )
                }
                val buttonText by remember {
                    derivedStateOf {
                        when(pageState.currentPage){
                            0 ->{
                                listOf("" , "Next")
                            }
                            state.quizState.size -1 ->{
                                listOf("Previous" , "Submit")
                            }
                            else ->{
                                listOf("Previous" , "Next")
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = Dimens.mediumPadding)
                        .navigationBarsPadding()
                ) {
                    val scope = rememberCoroutineScope()
                    if(buttonText[0].isNotEmpty()){
                        ButtonBox(
                            text = "Previous",
                            padding = Dimens.smallPadding,
                            fraction = 0.43f,
                            fontSize = Dimens.smallTextSize
                        ) {
                           scope.launch { pageState.animateScrollToPage(pageState.currentPage - 1)  }
                        }
                    }else{
                        ButtonBox(
                            text = "",
                            fraction = 0.43f,
                            borderColor = colorResource(id = R.color.mid_night_value),
                            containerColor = colorResource(id = R.color.mid_night_value),
                            fontSize = Dimens.smallTextSize
                            
                        ) {

                        }
                    }

                    ButtonBox(
                        text = buttonText[1],
                        padding = Dimens.smallPadding,
                        borderColor = colorResource(id = R.color.orange),
                        containerColor = if(pageState.currentPage == state.quizState.size - 1) colorResource(id = R.color.orange)
                                         else colorResource(id = R.color.dark_state_value),
                        fraction = 1f,
                        textColor = colorResource(id = R.color.white),
                        fontSize = Dimens.smallTextSize
                    ) {
                        if(pageState.currentPage == state.quizState.size - 1){

                            navController.navigate(Routes.ScoreScreen.passNumOfQuestionsAndCorrectAns(state.quizState.size , state.score))
                            Log.d("score" , state.score.toString())

                        }
                        else{
                            scope.launch { pageState.animateScrollToPage(pageState.currentPage + 1)  }
                        }

                    }

                }
            }


        }


    }

}

@Composable
fun quizFetched(state: StateQuizScreen): Boolean {

    return when {
        state.isLoading -> {
            shimmerEffectquizInterface()
            false
        }

        state.quizState?.isNotEmpty() == true -> {
            true
        }

        else -> {
            Text(text = state.error.toString(), color = colorResource(id = R.color.white))
            false
        }
    }
}
