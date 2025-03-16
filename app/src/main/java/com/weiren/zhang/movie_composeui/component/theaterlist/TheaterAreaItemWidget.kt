package com.weiren.zhang.movie_composeui.component.theaterlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weiren.zhang.movie_composeui.model.theaterlist.TheaterAreaModel;
import com.weiren.zhang.movie_composeui.model.theaterlist.TheaterInfoModel

@Preview
@Composable
fun PreviewTheaterAreaItemWidget() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 10.dp, vertical = 10.dp)
        ) {
            Text(
                text = "基隆",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun TheaterAreaItemWidget(
    modifier: Modifier = Modifier,
    onClick: (name: String, model: List<TheaterInfoModel>) -> Unit,
    item: TheaterAreaModel,
) {
    Row(
        modifier = modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            role = Role.Button,
            onClick = { onClick(item.theater_top, item.theater_list) },
        ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = modifier
                .weight(1f)
                .padding(horizontal = 10.dp, vertical = 10.dp)
        ) {
            Text(
                text = item.theater_top,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}