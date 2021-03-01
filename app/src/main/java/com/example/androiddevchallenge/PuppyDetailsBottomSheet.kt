package com.example.androiddevchallenge

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle

import android.view.ViewGroup

import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.typography

import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PuppyDetailsBottomSheet constructor(private val puppy: Puppy) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View =
        ComposeView(requireContext()).apply {
            setContent {
                MyTheme {
                    PuppyDetailView(puppy)
                }
            }
        }

}

fun showMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Composable
fun PuppyDetailView(puppy: Puppy) {
    var bitmap: Bitmap? by remember { mutableStateOf(null) }

    val context = LocalContext.current

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

    Column(
        modifier = Modifier
            .padding(start = 16.dp, top = 16.dp, end = 8.dp)
            .height(700.dp)
            .fillMaxWidth(),
    ) {
        bitmap?.let { loadedBitmap ->
            Image(
                bitmap = loadedBitmap.asImageBitmap(),
                contentDescription = "Puppy image",
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(30.dp)),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            style = typography.h5,
            text = puppy.name,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            style = typography.body1,
            text = puppy.description,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                showMessage(context, message = "You have adopted ${puppy.name}")
            },
        ) {
            Text(
                "Adopt today",
                color = Color.White,
                style = typography.subtitle1
            )
        }
    }

}

