package com.sachinshah.practical.ui.screens

import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.sachinshah.practical.ui.theme.ColorWhite

@Composable
fun ReelScreen() {

    val reelsVideos = listOf(
        "https://samplelib.com/lib/preview/mp4/sample-5s.mp4",

        "https://www.w3schools.com/html/mov_bbb.mp4",

        "https://media.w3.org/2010/05/sintel/trailer.mp4",

        "https://samplelib.com/lib/preview/mp4/sample-5s.mp4",

        "https://www.w3schools.com/html/mov_bbb.mp4",

        "https://media.w3.org/2010/05/sintel/trailer.mp4",   "https://samplelib.com/lib/preview/mp4/sample-5s.mp4",

        "https://www.w3schools.com/html/mov_bbb.mp4",

        "https://media.w3.org/2010/05/sintel/trailer.mp4",   "https://samplelib.com/lib/preview/mp4/sample-5s.mp4",

        "https://www.w3schools.com/html/mov_bbb.mp4",

        "https://media.w3.org/2010/05/sintel/trailer.mp4",   "https://samplelib.com/lib/preview/mp4/sample-5s.mp4",

        "https://www.w3schools.com/html/mov_bbb.mp4",

        "https://media.w3.org/2010/05/sintel/trailer.mp4",   "https://samplelib.com/lib/preview/mp4/sample-5s.mp4",

        "https://www.w3schools.com/html/mov_bbb.mp4",

        "https://media.w3.org/2010/05/sintel/trailer.mp4"

    )

    val pagerState = rememberPagerState(pageCount = { reelsVideos.size })
    VerticalPager(
       // key = {reelsVideos[it]},
        state = pagerState,
        beyondViewportPageCount = 1,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        ReelItem(videoUrl = reelsVideos[page], isPlaying = pagerState.targetPage == page )

    }
}


@OptIn(UnstableApi::class)
@Composable
fun rememberReelsPlayer(videoUrl: String): ExoPlayer {

    val context = LocalContext.current
    val dataSourceFactory =
        DefaultHttpDataSource.Factory().setUserAgent("Android")
    val mediaSource =
        ProgressiveMediaSource.Factory(
            dataSourceFactory
        ).createMediaSource(
            MediaItem.fromUri(videoUrl)
        )
    return remember {

        ExoPlayer.Builder(context).build().apply {
            repeatMode = ExoPlayer.REPEAT_MODE_ONE
            setMediaSource(
                mediaSource
            )
            prepare()
            pause()
        }

    }
}

@OptIn(UnstableApi::class)
@Composable
fun ReelItem(videoUrl: String, isPlaying: Boolean) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val isForeground  = remember { mutableStateOf(true) }

    val exoPlayer =  rememberReelsPlayer(videoUrl)

    LaunchedEffect(
        isPlaying,
        isForeground
    ) {

        if (
            isPlaying &&
            isForeground.value
        ) {

            exoPlayer.play()

        } else {

            exoPlayer.pause()
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {


        AndroidView(
            factory = {
                PlayerView(context).apply {
                    player = exoPlayer
                    useController = false
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                }
            },
            modifier = Modifier.fillMaxSize()
        )





        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.2f))
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text(text = "@Sachin", color = ColorWhite, style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(5.dp))
            Text(text = videoUrl, color = ColorWhite, style = MaterialTheme.typography.bodyMedium)
        }


        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 80.dp, end = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector =
                    Icons.Default.FavoriteBorder,

                contentDescription =
                    null,

                tint =
                    Color.White,

                modifier =
                    Modifier.size(
                        34.dp
                    )
            )

            Spacer(
                Modifier.height(
                    20.dp
                )
            )

            Icon(
                imageVector =
                    Icons.Default.MailOutline,

                contentDescription =
                    null,

                tint =
                    Color.White,

                modifier =
                    Modifier.size(
                        34.dp
                    )
            )

            Spacer(
                Modifier.height(
                    20.dp
                )
            )

            Icon(
                imageVector =
                    Icons.Default.Share,

                contentDescription =
                    null,

                tint =
                    Color.White,

                modifier =
                    Modifier.size(
                        34.dp
                    )
            )

        }

    }

    DisposableEffect(lifecycleOwner) {

        val observer = LifecycleEventObserver{
            _,event ->
            when(event){
                Lifecycle.Event.ON_RESUME->{

                    isForeground.value=true
                }
                Lifecycle.Event.ON_PAUSE->{
                    isForeground.value=false
                        exoPlayer.pause()
                }
                Lifecycle.Event.ON_STOP -> {

                    isForeground.value   = false

                    exoPlayer.pause()
                }

                else -> {}
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            exoPlayer.release()
        }
    }
}