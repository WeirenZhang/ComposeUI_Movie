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
import com.weiren.zhang.movie_composeui.model.theaterlist.TheaterInfoModel

@Preview
@Composable
fun PreviewTheaterListItemWidget() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Text(
                text = "基隆秀泰影城",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "基隆市中正區信一路177號",
                color = Color.Gray,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 5.dp)
            )
            Text(
                text = "(02)2421-2388",
                color = Color.Gray,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }
}

@Composable
fun TheaterListItemWidget(
    modifier: Modifier = Modifier,
    onClick: (model: TheaterInfoModel) -> Unit,
    item: TheaterInfoModel,
) {
    Row(
        modifier = Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            role = Role.Button,
            onClick = { onClick(item) },
        ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = modifier
                .weight(1f)
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Text(
                text = item.name,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = item.adds,
                color = Color.Gray,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 5.dp)
            )
            Text(
                text = item.tel,
                color = Color.Gray,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }
}