package com.example.brainofreeze.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.example.brainofreeze.R
import com.example.brainofreeze.presentation.utils.Dimens


@Preview
@Composable
fun PrevButton() {
    ButtonBox(text = "Generate Quiz"){}
}

@Composable
fun ButtonBox(
    modifier: Modifier = Modifier,
    text: String,
    padding: Dp = Dimens.smallPadding,
    borderColor : Color = colorResource(id = R.color.blue_grey),
    containerColor : Color = colorResource(id = R.color.blue_grey),
    textColor: Color = colorResource(id = R.color.black),
    fontSize : TextUnit = Dimens.mediumTextSize,
    fraction : Float = 1f,
    onBtnClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(padding)
            .border(Dimens.smallBorderWidth , borderColor , RoundedCornerShape(Dimens.largerCornerRadius))
            .fillMaxWidth(fraction)
            .clickable { onBtnClicked() }
            .height(Dimens.mediumBoxHeight)
            .clip(RoundedCornerShape(Dimens.largerCornerRadius))
            .background(containerColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = fontSize,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = textColor,
        )
    }

}