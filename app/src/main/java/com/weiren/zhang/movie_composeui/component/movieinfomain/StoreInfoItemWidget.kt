package com.weiren.zhang.movie_composeui.component.movieinfomain

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weiren.zhang.movie_composeui.model.movieinfomain.StoreInfoModel

@Preview
@Composable
fun StoreInfoItemWidget() {
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(20.dp)
        ) {
            Text(
                text = "劇情簡介\n" +
                        "本片由《衣袖紅鑲邊》吳代煥、《好搭檔》池承炫、《7號房的禮物》金正泰、《風願》李容奎主演，由黑道牧師、流氓僧人、刑警巫覡組成史上最強復仇者聯盟，向死對頭展開絕地大反攻！\n" +
                        "\n" +
                        "兩名黑道成員慶哲（吳代煥 飾）和泰勇在為老大慶生時，死對頭三岔路幫突然闖入並殺死了他們的老大，慶哲和泰勇後來便各自隱身在教會和寺廟。慶哲所待的教會原本因假牧師而差點被毀掉，慶哲竟陰錯陽差地成為新牧師，備受教徒敬重；泰勇趕走躲在寺廟中的罪犯後，和住持一起生活。兩人過著隱姓埋名的生活，等待復仇之日的到來。然而，三岔路幫繼續犯下放高利貸、人口買賣等罪行，這讓巫覡刑警道弼（池承炫 飾）非常憤怒，為了逮捕三岔路幫的老大，道弼找上了慶哲和泰勇，向他們倆提議要合作。牧師、僧人、巫覡展開前所未見的復仇行動！\n" +
                        "\n" +
                        "出道15年的池承炫近期以《好搭檔》中張娜拉的「渣男丈夫」一角備受關注，之前他曾透過《請輸入檢索詞WWW》、《娑婆訶》、《戀人》等作品展現多變演技，這次他在《第一聖拳》裡飾演突然被神靈附體的刑警一角，導演高勳表示：「池承炫真的是位很認真的演員，他為了要完美詮釋角色而十分努力，還不停地和我溝通。」導演表示雖然劇本上有寫著被神靈附體的過程，但池承炫說自己完全不會跳舞，很苦惱該如何表現出被附體時的肢體動作，導演說：「所以池承炫就自己練舞練超過一個月後，來到拍攝現場，和我討論了各式各樣的舞蹈動作，一起打造出適合電影中被神靈附體的動作，我看到他如此努力的模樣，不禁心想他真是位天生的演員！」\n" +
                        "\n" +
                        "而吳代煥在《第一聖拳》中所飾演的牧師角色的歌喉非常好，唱詩歌時有如新鶯出谷般令教徒們驚豔不已，吳代煥也為此做了許多準備，他表示：「收到電影中要唱的詩歌樂譜後，因為我太太在教會負責伴奏，我就請她協助我一起練習，但那詩歌的高音部分令我難以駕馭，所以我曾詢問是否能降Key，但導演希望還是能維持高音的部分，後來覺得這個設定真的很有趣，也努力地想以自己的聲音去飆高音！」",
                color = Color.Gray,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun StoreInfoItemWidget(
    modifier: Modifier = Modifier,
    item: StoreInfoModel,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = modifier
                .weight(1f)
                .padding(20.dp)
        ) {
            Text(
                text = item.storeInfo,
                color = Color.Gray,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}