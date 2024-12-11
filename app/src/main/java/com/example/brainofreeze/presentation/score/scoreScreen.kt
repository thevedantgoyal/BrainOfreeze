@file:Suppress("DEPRECATION")

package com.example.brainofreeze.presentation.score

import android.icu.text.DecimalFormat
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.brainofreeze.R
import com.example.brainofreeze.presentation.navGraph.Routes
import com.example.brainofreeze.presentation.utils.Dimens


//@Preview
//@Composable
//fun prev(){
//    scoreScreen(noOfQuestions = 10, noOfCorrectAns = 5, navController = NavController())
//}

@Composable
fun scoreScreen(
    noOfQuestions : Int,
    noOfCorrectAns : Int,
    navController: NavController
) {

    BackHandler {
        gotohome(navController)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.mediumPadding),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = {
                //
                gotohome(navController = navController)

            }) {
                Icon(
                    painter = painterResource(id = R.drawable.close_line_icon),
                    contentDescription = "close",
                    tint = colorResource(
                        id = R.color.blue_grey
                    )
                )
            }

        }
        Spacer(modifier = Modifier.height(Dimens.smallSpaceHeight))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .clip(RoundedCornerShape(Dimens.mediumCornerRadius))
                .background(colorResource(id = R.color.blue_grey)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = Dimens.mediumPadding, vertical = Dimens.mediumPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.congrats))
                val annotatedString = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append("You Attempted ")
                    }
                    withStyle(style = SpanStyle(color = Color.Blue)) {
                        append("$noOfQuestions questions")
                    }
                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append(" and from that")
                    }
                    withStyle(style = SpanStyle(color = colorResource(id = R.color.green))) {
                        append(" $noOfCorrectAns answers")
                    }
                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append(" are correct.")
                    }

                }
                val scorePercentage = calculatePercentage(noOfCorrectAns, noOfQuestions)
                LottieAnimation(
                    modifier = Modifier.size(Dimens.largeLottieAimSize),
                    composition = composition,
                    iterations = 100
                )
                Spacer(modifier = Modifier.height(Dimens.smallSpaceHeight))

                Text(
                    text = "Congrats!",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimens.mediumTextSize,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(Dimens.mediumSpaceHeight))
                Text(
                    text = "$scorePercentage% Score",
                    color = colorResource(id = R.color.green),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimens.largeTextSize,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(Dimens.mediumSpaceHeight))
                Text(
                    text = "Quiz Completed Successfully.",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimens.smallTextSize,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(Dimens.mediumSpaceHeight))
                Text(
                    text = annotatedString,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = Dimens.smallTextSize,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(Dimens.largeSpacerHeight))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Share With Us : ",
                        color = Color.Black,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = Dimens.smallTextSize
                    )
                    Spacer(modifier = Modifier.height(Dimens.smallspacerWidth))

                    Icon(
                        painter = painterResource(id = R.drawable.black_instagram_icon),
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(Dimens.smallspacerWidth))
                    Icon(
                        painter = painterResource(id = R.drawable.meta_black_icon),
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(Dimens.smallspacerWidth))
                    Icon(
                        painter = painterResource(id = R.drawable.whatsapp_icon),
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                    )

                }
            }
        }
    }

}

fun gotohome(navController: NavController){
    navController.navigate(Routes.HomeScreen.route){
        popUpTo(Routes.HomeScreen.route){
            inclusive = true
        }
    }
}

fun calculatePercentage(k: Int, n: Int): Double {
    require(k >= 0 && n > 0){"Invalid Input : k must be non - negative and n must be positive "}
    val percentage = (k.toDouble() / n.toDouble()) * 100.0
    return DecimalFormat("#.##").format(percentage).toDouble()
}

