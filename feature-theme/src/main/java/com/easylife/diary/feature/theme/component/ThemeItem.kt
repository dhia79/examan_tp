package com.easylife.diary.feature.theme.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.easylife.diary.feature.theme.util.DiaryTheme
import com.easylife.diary.feature.theme.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import kotlin.math.absoluteValue

/**
 * Created by erenalpaslan on 24.12.2022
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerScope.ThemeItem(
    diaryTheme: DiaryTheme,
    page: Int
) {
    Scaffold(
        containerColor = diaryTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxSize(0.9f)
            .graphicsLayer {
                // Calculate the absolute offset for the current page from the
                // scroll position. We use the absolute value which allows us to mirror
                // any effects for both directions
                val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                lerp(
                    start = 0.80f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                ).also { scale ->
                    //scaleX = scale
                    scaleY = scale
                }
            }
            .clip(MaterialTheme.shapes.large),
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.extraSmall)
                            .background(diaryTheme.colorScheme.onSurfaceVariant)
                            .fillMaxWidth(0.45f)
                            .height(12.dp)
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = diaryTheme.colorScheme.background,
                ),
            )
        },

        bottomBar = {

        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            ExampleDiaryItem("07 MAR", "Sunday", showBackground = true, diaryTheme = diaryTheme)
            Spacer(modifier = Modifier.height(8.dp))
            ExampleDiaryItem("08 MAR", "Monday", diaryTheme = diaryTheme)
            Spacer(modifier = Modifier.height(8.dp))
            ExampleDiaryItem("08 MAR", "Monday", showTitle = false, diaryTheme = diaryTheme)
        }
    }
}

@Composable
fun ExampleDiaryItem(
    dayOfMonth: String,
    day: String,
    showBackground: Boolean = false,
    showTitle: Boolean = true,
    diaryTheme: DiaryTheme
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(containerColor = diaryTheme.colorScheme.surfaceVariant)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        if (showBackground) {
            Image(
                painter = painterResource(id = R.drawable.bg_image_placeholder),
                contentDescription = "Background Placeholder",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(32.dp),
                contentScale = ContentScale.Inside
            )
        }
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = dayOfMonth,
                fontWeight = FontWeight.Bold,
                color = diaryTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelMedium
            )
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(diaryTheme.colorScheme.onSurfaceVariant)
                    .size(6.dp)
            )
            Text(
                text = day,
                color = diaryTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelSmall
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        if (showTitle) {
            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                Box(
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.extraSmall)
                        .background(diaryTheme.colorScheme.onSurfaceVariant)
                        .height(8.dp)
                        .fillMaxWidth(0.35f)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            Box(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.extraSmall)
                    .background(diaryTheme.colorScheme.onSurfaceVariant)
                    .fillMaxWidth(0.9f)
                    .height(4.dp)
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            Box(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.extraSmall)
                    .background(diaryTheme.colorScheme.onSurfaceVariant)
                    .fillMaxWidth(0.7f)
                    .height(4.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}