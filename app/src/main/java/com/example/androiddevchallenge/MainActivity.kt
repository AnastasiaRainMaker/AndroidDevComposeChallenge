/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.typography

@ExperimentalAnimationApi
class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp(this)
            }
        }
    }
}

// Start building your app here!
@ExperimentalAnimationApi
@Composable
fun MyApp(mainActivity: MainActivity) {
    val viewModel = MainViewModel()

    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier.fillMaxHeight().fillMaxWidth()
        ) {
            PuppyList(
                viewModel.getPuppies(),
                Modifier.weight(1f),
                mainActivity.supportFragmentManager
            )
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun PuppyList(
    puppies: List<Puppy>,
    modifier: Modifier,
    fragmentManager: androidx.fragment.app.FragmentManager
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(items = puppies) {
            PuppyRow(it, fragmentManager)
        }
    }
}

@Composable
fun PuppyRow(puppy: Puppy, fragmentManager: androidx.fragment.app.FragmentManager) {

    var bitmap: Bitmap? by remember { mutableStateOf(null) }

    Glide.with(LocalContext.current)
        .asBitmap()
        .load(puppy.link)
        .into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) {}
            override fun onResourceReady(
                resource: Bitmap,
                transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
            ) {
                bitmap = resource
            }
        })

    Row(
        modifier = Modifier
            .padding(start = 8.dp, top = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .clickable(enabled = true) {
                PuppyDetailsBottomSheet(puppy).show(fragmentManager, "$puppy")
            }
    ) {
        bitmap?.let { loadedBitmap ->
            Image(
                bitmap = loadedBitmap.asImageBitmap(),
                contentDescription = "Puppy image",
                modifier = Modifier
                    .height(110.dp)
                    .width(110.dp)
                    .clip(shape = CircleShape),
                contentScale = ContentScale.Crop
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 8.dp)
                .fillMaxWidth(),
        ) {
            Text(
                style = typography.h5,
                text = puppy.name,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                style = typography.body1,
                text = puppy.description
            )
            Spacer(modifier = Modifier.height(16.dp))
            Divider(
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.height(1.dp)
            )
        }
    }
}

@ExperimentalAnimationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp(MainActivity())
    }
}

@ExperimentalAnimationApi
@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp(MainActivity())
    }
}
