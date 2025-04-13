package com.example.livescore.presentation.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.livescore.R

import com.example.livescore.data.model.minicard.Minicard


@Composable
fun ScoreScreen(minicard: Minicard?) {

    val team1name=minicard?.responseData?.result?.teams?.a?.shortName
    val team1logo=minicard?.responseData?.result?.teams?.a?.logo
    val team1score=minicard?.responseData?.result?.teams?.a?.a_1_score?.runs ?: 0
    val team1wicket=minicard?.responseData?.result?.teams?.a?.a_1_score?.wickets
    val team1over=minicard?.responseData?.result?.teams?.a?.a_1_score?.overs
    val currentrunrate=minicard?.responseData?.result?.now?.run_rate

    val team2name=minicard?.responseData?.result?.teams?.b?.shortName
    val team2logo=minicard?.responseData?.result?.teams?.b?.logo
    val team2score=minicard?.responseData?.result?.teams?.b?.b_1_score?.runs ?:0
    val team2wicket=minicard?.responseData?.result?.teams?.b?.b_1_score?.wickets
    val team2over=minicard?.responseData?.result?.teams?.b?.b_1_score?.overs
    val team2fullname=minicard?.responseData?.result?.teams?.b?.name


    val commentery=minicard?.responseData?.result?.lastCommentary?.primaryText.toString()
     val runneeds = team1score  - team2score  +1


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF0A1D38)) // dark background
            .padding(top=14.dp,start =14.dp,end=14.dp,bottom=14.dp)

    ) {

        Text(
            text = commentery,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.align(Alignment.Center)

        )
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    SubcomposeAsyncImage(
                        model =   ImageRequest.Builder(LocalContext.current)
                            .data(team1logo)
                            .placeholder(R.drawable.southafricaflag)
                            .error(R.drawable.southafricaflag)
                            // Mimic a browser's headers
                            .addHeader(
                                "User-Agent",
                                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
                            )
                            .addHeader("Accept", "image/webp,image/apng,image/*,*/*;q=0.8")
                            .addHeader("Referer", "https://www.espncricinfo.com/")
                            .listener(
                                onError = { _, throwable ->
                                    Log.e("Coil@@@", "Image load failed: ${throwable.throwable.message}")
                                    Log.e("Coil@@@", "Image load failed: ${throwable.throwable.localizedMessage}")
                                }
                                ,
                                onSuccess = {i,e->
                                    Log.e("Coil@@@", "sucess")
                                }
                            )
                            .build(),
                        contentDescription = "team 1 Image",

                        modifier = Modifier.size(24.dp),
                        loading = {
//                            CircularProgressIndicator(
//                                color = Color.Gray,
//                                modifier = Modifier.size(20.dp)
//                            )
                        }
                    )


                    Spacer(modifier = Modifier.width(6.dp))
                    Text(team1name.toString(), color = Color.White, fontSize = 18.sp)
                    Spacer(modifier = Modifier.width(6.dp))
                    if(team2score==null){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bat), // Your bat icon
                            contentDescription = "Bat",
                            tint = Color.Yellow,
                            modifier = Modifier.size(20.dp),

                            )
                    }
                }



                Row(verticalAlignment = Alignment.CenterVertically) {
                    Log.d("flaggggg",team2logo.toString())
                    SubcomposeAsyncImage(
                        model =   ImageRequest.Builder(LocalContext.current)
                            .data(team2logo)
                            .placeholder(R.drawable.srilankaflag)
                            .error(R.drawable.srilankaflag)
                            // Mimic a browser's headers
                            .addHeader(
                                "User-Agent",
                                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
                            )
                            .addHeader("Accept", "image/webp,image/apng,image/*,*/*;q=0.8")
                            .addHeader("Referer", "https://www.espncricinfo.com/") // Critical for ESPN
                            .listener(
                                onError = { _, throwable ->
                                    Log.e("Coil@@@", "Image load failed: ${throwable.throwable.message}")
                                    Log.e("Coil@@@", "Image load failed: ${throwable.throwable.localizedMessage}")
                                }
                                ,
                                onSuccess = {i,e->
                                    Log.e("Coil@@@", "sucess")
                                }
                            )
                            .build(),
                        contentDescription = "team 2 Image",

                        modifier = Modifier.size(24.dp),
                        loading = {
//                            CircularProgressIndicator(
//                                color = Color.Gray,
//                                modifier = Modifier.size(20.dp)
//                            )
                        }
                    )

                    Spacer(modifier = Modifier.width(6.dp))
                    Text(team2name.toString(), color = Color.White, fontSize = 18.sp)
                    Spacer(modifier = Modifier.width(6.dp))

                    if(team2score!=null){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bat), // Your bat icon
                            contentDescription = "Bat",
                            tint = Color.Yellow,
                            modifier = Modifier.size(20.dp),

                            )
                    }

                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "${team1score}/${team1wicket}",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold

                )
                if(team2score!=null){
                    Text(
                        text =    "${team2score}/${team2wicket}",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold

                    )
                }

            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = team1over.toString(),
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 16.sp
                )
                if(team2score!=null){
                    Text(
                        text = team2over.toString(),
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 16.sp
                    )
                }

            }

            Spacer(modifier = Modifier.height(5.dp))
            Divider(color = Color.White, thickness = 0.5.dp)
            Spacer(modifier = Modifier.height(5.dp))


            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "CRR :",
                    color = Color.White.copy(alpha = 0.6f),
                    fontSize = 14.sp
                )
                Text(
                    text = currentrunrate.toString(),
                    color = Color(0xFF00BFFF),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "${team2fullname} need ${runneeds} runs ",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 14.sp
                )
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun previrescreen(){
    val minicard= Minicard()
    ScoreScreen(minicard)
}