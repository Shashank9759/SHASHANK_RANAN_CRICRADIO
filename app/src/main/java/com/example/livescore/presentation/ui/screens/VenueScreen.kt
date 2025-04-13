package com.example.livescore.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.compose.rememberAsyncImagePainter
import com.example.livescore.data.model.venue.*
import com.example.livescore.R

@Preview(showBackground = true)
@Composable
fun VenueScreenPreview() {

    VenueScreen(Data = Venue())
}

@Composable
fun VenueScreen(
    Data: Venue = Venue()
) {
    val matchData = Data.responseData?.result
    if (matchData == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.White)
        }
        return
    }

    // Extract necessary fields
    val venuePhotoUrl = matchData.venueDetails.photo
    val venueName = matchData.venueDetails.knownAs
    val matchTitle = matchData.related_name
    val seriesName = matchData.season.name
    val startDateStr = matchData.start_date.str
    val tossInfo = matchData.toss.str
    val firstUmpire = matchData.firstUmpire
    val secondUmpire = matchData.secoundUmpire
    val thirdUmpire = matchData.thirdUmpire
    val matchReferee = matchData.matchReferee
    val weatherTemp = matchData.weather.temp_c
    val weatherText = matchData.weather.condition.text
    val weatherIconUrl = matchData.weather.condition.icon
    val lastUpdated = matchData.weather.last_updated
    val venueStats = matchData.venueStats


    Column(
        modifier = Modifier
            .fillMaxSize()

            .background(Color.Black)
            .verticalScroll(rememberScrollState())
    ) {

        Box(modifier = Modifier.height(250.dp).padding(3.dp)) {
            HeaderImage(venuePhotoUrl)

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.7f)
                            ),
                            startY = 50f,
                            endY = 650f
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(20.dp)
            ) {

                Text(
                    text = venueName,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color(0xFF64B5F6),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$matchTitle $seriesName",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White,
                        fontSize = 14.sp
                    )
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = startDateStr,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color.White,
                        fontSize = 12.sp
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1C1C1C))
            ) {

                Text(
                    text = buildAnnotatedString {
                        if (tossInfo.contains("opt to bat", ignoreCase = true)) {
                            val parts = tossInfo.split("opt to bat", ignoreCase = true)
                            append(parts[0])
                            withStyle(style = SpanStyle(color = Color(0xFFFFC107))) {
                                append("opt to bat")
                            }
                            if (parts.size > 1) {
                                append(parts[1])
                            }
                        } else {
                            append(tossInfo)
                        }
                    },
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White,
                        fontSize = 14.sp
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        UmpiresCard(
            firstUmpire = firstUmpire,
            secondUmpire = secondUmpire,
            thirdUmpire = thirdUmpire,
            matchReferee = matchReferee
        )

        Spacer(modifier = Modifier.height(16.dp))


        WeatherCard(
            weatherTemp = weatherTemp,
            weatherText = weatherText,
            weatherIconUrl = weatherIconUrl,
            lastUpdated = lastUpdated,
            locationName = venueName
        )

        Spacer(modifier = Modifier.height(16.dp))


        venueStats?.let {
            VenueStatsCard(venueStats = it)
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}


@Composable
fun HeaderImage(venuePhotoUrl: String?) {
    if (venuePhotoUrl != null) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(venuePhotoUrl)
                .crossfade(true)
                .placeholder(R.drawable.venue)
                .error(R.drawable.venue)

                .build(),
            contentDescription = "Venue Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize().padding(start=16.dp,end=16.dp)
        )
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        )
    }
}


@Composable
fun UmpiresCard(
    firstUmpire: String,
    secondUmpire: String,
    thirdUmpire: String,
    matchReferee: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1C1C1C))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Officials",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Umpire",
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                    )
                    Text(
                        text = firstUmpire,
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.White)
                    )
                }
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Umpire",
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                    )
                    Text(
                        text = secondUmpire,
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.White)
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Third/TV Umpire",
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                    )
                    Text(
                        text = thirdUmpire,
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.White)
                    )
                }
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Referee",
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                    )
                    Text(
                        text = matchReferee,
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.White)
                    )
                }
            }
        }
    }
}


@Composable
fun WeatherCard(
    weatherTemp: Double,
    weatherText: String,
    weatherIconUrl: String,
    lastUpdated: String,
    locationName: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(140.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1C1C1C))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = rememberAsyncImagePainter(model = weatherIconUrl),
                contentDescription = "Weather Icon",
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = locationName,
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                )
                Text(
                    text = "${weatherTemp}°C",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                )
                Text(
                    text = weatherText,
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.White)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "Last Updated",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
                Text(
                    text = lastUpdated,
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.White)
                )
            }
        }
    }
}


@Composable
fun VenueStatsCard(venueStats: VenueStats) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1C1C1C))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Venue Stats",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            // Basic Stats Rows
            StatsRow(label = "Matches Played", value = venueStats.matchesPlayed.toString())
            StatsRow(label = "Lowest Defended", value = venueStats.lowestDefended.toString())
            StatsRow(label = "Highest Chased", value = venueStats.highestChased.toString())
            StatsRow(label = "Win Bat First", value = venueStats.batFirstWins.toString())
            StatsRow(label = "Win Ball First", value = venueStats.ballFirstWins.toString())
            Spacer(modifier = Modifier.height(12.dp))
            Divider(color = Color.Gray, thickness = 0.5.dp)
            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.weight(1f)) {}
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "1st Inn",
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                    )
                }
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "2nd Inn",
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            StatsRowTwoColumn(
                label = "Avg Score",
                firstValue = venueStats.battingFirst.averageScore.toString(),
                secondValue = venueStats.battingSecond.averageScore.toString()
            )
            StatsRowTwoColumn(
                label = "Highest Score",
                firstValue = venueStats.battingFirst.highestScore.toString(),
                secondValue = venueStats.battingSecond.highestScore.toString()
            )
            StatsRowTwoColumn(
                label = "Lowest Score",
                firstValue = venueStats.battingFirst.lowestScore.toString(),
                secondValue = venueStats.battingSecond.lowestScore.toString()
            )
            StatsRowTwoColumn(
                label = "Pace Wickets",
                firstValue = venueStats.battingFirst.paceWickets.toString(),
                secondValue = venueStats.battingSecond.paceWickets.toString()
            )
            StatsRowTwoColumn(
                label = "Spin Wickets",
                firstValue = venueStats.battingFirst.spinWickets.toString(),
                secondValue = venueStats.battingSecond.spinWickets.toString()
            )
        }
    }
}


@Composable
fun StatsRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = label,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
        )
        Text(
            text = value,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodySmall.copy(color = Color.White)
        )
    }
}

@Composable
fun StatsRowTwoColumn(label: String, firstValue: String, secondValue: String) {

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall.copy(color = Color.White)
            )
        }
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = firstValue,
                style = MaterialTheme.typography.bodySmall.copy(color = Color.White)
            )
        }
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = secondValue,
                style = MaterialTheme.typography.bodySmall.copy(color = Color.White)
            )
        }
    }
}
