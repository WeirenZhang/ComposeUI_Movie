package com.weiren.zhang.movie_composeui.component.movieinfomain

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.weiren.zhang.movie_composeui.R
import com.weiren.zhang.movie_composeui.model.movieinfomain.MovieInfoModel

@Preview
@Composable
fun PreviewMovieInfoItemWidget() {
    Column(
        modifier = Modifier
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.pm_fmen13186482_0001),
            contentDescription = stringResource(id = R.string.app_name),
            contentScale = ContentScale.Crop,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Black)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                text = "電影名稱",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            text = "獅子王：木法沙",
            color = Color.Gray,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            text = "Mufasa: The Lion King",
            color = Color.Gray,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Black)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                text = "電影分級",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ad_1),
            contentDescription = stringResource(id = R.string.app_name),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(10.dp)
                .size(50.dp, 50.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Black)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                text = "上映日期",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            text = "12/19/1824",
            color = Color.Gray,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Black)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                text = "電影長度",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            text = "片長：118分",
            color = Color.Gray,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Black)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                text = "導演",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            text = "貝瑞傑金斯 Barry Jenkins",
            color = Color.Gray,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Black)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                text = "演員",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            text = "亞倫皮埃爾 Aaron Pierre............木法沙/Mufasa (voice)\n" +
                    "凱爾文哈里遜 Kelvin Harrison Jr.............塔卡/Taka (voice)\n" +
                    "塞斯羅根 Seth Rogen............彭彭/Pumbaa (voice)\n" +
                    "比利埃西納 Billy Eichner............丁滿/Timon (voice)\n" +
                    "約翰卡尼 John Kani............拉飛奇/Rafiki (voice)\n" +
                    "麥斯米克森 Mads Mikkelsen............Kiros (voice)\n" +
                    "譚蒂紐頓 Thandiwe Newton............Eshe (voice)\n" +
                    "布露艾薇卡特 Blue Ivy Carter............琪亞拉/Kiara (voice)\n" +
                    "Keith David............Masego (voice)\n" +
                    "唐納葛洛佛 Donald Glover............Simba (voice)\n" +
                    "Folake Olowofoyeku............Amara (voice)\n" +
                    "阿妮卡諾尼羅斯 Anika Noni Rose............Afia (voice)\n" +
                    "碧昂絲 Beyonce............Nala (voice)\n" +
                    "藍尼詹姆士 Lennie James............Obasi (voice)\n" +
                    "康蒂凡尼布恩 Tiffany Boone............莎拉巴/Sarabi (voice)\n" +
                    "布拉林蘭金斯 Braelyn Rankins............Young Mufasa (voice)",
            color = Color.Gray,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun MovieInfoItemWidget(
    modifier: Modifier = Modifier,
    item: MovieInfoModel,
) {
    Column(
        modifier = modifier
    ) {
        Image(
            modifier = modifier.fillMaxWidth(),
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = item.movie_intro_foto)
                    .apply(block = fun ImageRequest.Builder.() {
                        size(Size.ORIGINAL)
                    }).build()
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(color = Color.Black)
        ) {
            Text(
                modifier = modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                text = "電影名稱",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            modifier = modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            text = item.h1,
            color = Color.Gray,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            text = item.h3,
            color = Color.Gray,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(color = Color.Black)
        ) {
            Text(
                modifier = modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                text = "電影分級",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Image(
            painter = rememberAsyncImagePainter(model = item.icon),
            contentDescription = null,
            modifier = modifier
                .padding(10.dp)
                .size(50.dp, 50.dp)
        )
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(color = Color.Black)
        ) {
            Text(
                modifier = modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                text = "上映日期",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            modifier = modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            text = item.release_movie_time,
            color = Color.Gray,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(color = Color.Black)
        ) {
            Text(
                modifier = modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                text = "電影長度",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            modifier = modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            text = item.length,
            color = Color.Gray,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(color = Color.Black)
        ) {
            Text(
                modifier = modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                text = "導演",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            modifier = modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            text = item.director,
            color = Color.Gray,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(color = Color.Black)
        ) {
            Text(
                modifier = modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                text = "演員",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            modifier = modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            text = item.actor,
            color = Color.Gray,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}