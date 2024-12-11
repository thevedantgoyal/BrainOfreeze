package com.example.brainofreeze.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.navigation.NavController
import com.example.brainofreeze.presentation.common.AppDropDownMenu
import com.example.brainofreeze.presentation.common.ButtonBox
import com.example.brainofreeze.presentation.navGraph.Routes
import com.example.brainofreeze.presentation.utils.Constants
import com.example.brainofreeze.presentation.utils.Dimens

//
//@Preview
//@Composable
//fun homeprev(){
//    HomeScreen(state = StateHomeScreen() ,
//        event = {})
//}
@Composable
fun HomeScreen(
    state: StateHomeScreen,
    event: (EventHomeScreen) -> Unit,
    navController: NavController
) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {

        HomeHeader()

        Spacer(modifier = Modifier.height(Dimens.mediumSpaceHeight))
        AppDropDownMenu(
            menuName = "Number of Questions",
            menuList = Constants.numberAsString,
            text = state.numberOfquizzes.toString(),
            onDropDownClick = { event(EventHomeScreen.SetNumberOfquizzes(it.toInt())) })

        Spacer(modifier = Modifier.height(Dimens.mediumSpaceHeight))
        AppDropDownMenu(
            menuName = "Select Category",
            menuList = Constants.categories,
            text = state.category,
            onDropDownClick = { event(EventHomeScreen.SetCategory(it)) })

        Spacer(modifier = Modifier.height(Dimens.mediumSpaceHeight))
        AppDropDownMenu(
            menuName = "Select Difficulty",
            menuList = Constants.difficulty,
            text = state.difficulty,
            onDropDownClick = { event(EventHomeScreen.SetDifficulty(it)) })

        Spacer(modifier = Modifier.height(Dimens.mediumSpaceHeight))
        AppDropDownMenu(
            menuName = "Select Type : ",
            menuList = Constants.type,
            text = state.type,
            onDropDownClick = { event(EventHomeScreen.SetType(it)) })

        Spacer(modifier = Modifier.height(Dimens.mediumSpaceHeight))

        ButtonBox(text = "Generate Quiz", padding = Dimens.mediumPadding) {
            navController.navigate(
                route = Routes.QuizScreen.passQuizParams(
                    state.numberOfquizzes,
                    state.category,
                    state.difficulty,
                    state.type
                )
            )
        }

    }
}

