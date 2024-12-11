package com.example.brainofreeze.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.brainofreeze.R
import com.example.brainofreeze.presentation.utils.Dimens


@Preview
@Composable
fun HomeHeader(){
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.HomeTopBoxHeight)
            .background(
                color = colorResource(id = R.color.dark_state_value),
                shape = RoundedCornerShape(
                    bottomStart = Dimens.ExtraLargeCornerRadius,
                    bottomEnd = Dimens.ExtraLargeCornerRadius
                ),
            )
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimens.largePadding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ){
            Icon(painter = painterResource(id = R.drawable.menu_dots_square_svgrepo_com),
                contentDescription = "",
                modifier = Modifier
                    .weight(1f)
                    .size(Dimens.mediumIconSize),
                tint = colorResource(id = R.color.blue_grey)
            )
            Text(text = "BrainOfreeze",
                color = colorResource(id = R.color.blue_grey),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(3.5f),
                textAlign = TextAlign.Center,
                fontSize = 34.sp
            )
            
            Icon(painter = painterResource(id = R.drawable.profile_circle_svgrepo_com),
                contentDescription = "",
                modifier = Modifier
                    .weight(1f)
                    .size(Dimens.mediumIconSize),
                tint = colorResource(id = R.color.blue_grey)
                )
        }

    }
}