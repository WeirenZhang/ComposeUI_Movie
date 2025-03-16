package com.weiren.zhang.movie_composeui.component.myfavourite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.weiren.zhang.movie_composeui.model.theaterlist.TheaterInfoModel
import com.weiren.zhang.movie_composeui.viewmodel.myfavourite.TheaterMyFavouriteViewModel

@Preview
@Composable
fun PreviewTheaterMyFavouriteItemWidget() {

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
                fontSize = 20.sp,
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
        IconButton(
            onClick = {}
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete"
            )
        }
    }
}

@Composable
fun TheaterMyFavouriteItemWidget(
    modifier: Modifier = Modifier,
    viewModel: TheaterMyFavouriteViewModel = hiltViewModel(),
    onClick: (model: TheaterInfoModel) -> Unit,
    item: TheaterInfoModel,
) {

    var showTheaterMyFavouriteDialog by remember { mutableStateOf(false) }

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
                fontSize = 20.sp,
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
        IconButton(
            onClick = {
                showTheaterMyFavouriteDialog = true
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete"
            )
        }

        if (showTheaterMyFavouriteDialog) {
            AlertDialog(
                title = {
                    Text(text = "提示")
                },
                text = {
                    Text(text = "您確定要刪除 " + item.name + " 嗎？")
                },
                onDismissRequest = {

                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            viewModel.removeTheaterMyFavourite(item.id)
                            showTheaterMyFavouriteDialog = false
                        }
                    ) {
                        Text("刪除")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showTheaterMyFavouriteDialog = false
                        }
                    ) {
                        Text("取消")
                    }
                }
            )
        } else {
            return
        }
    }
}