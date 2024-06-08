package com.example.tophair.app.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.View
import android.view.ViewTreeObserver
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

@Composable
fun HideSystemBars() {
    val context = LocalContext.current
    val activity = (context as? Activity)
    if (activity != null) {
        val window = activity.window
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.apply {
            hide(WindowInsetsCompat.Type.statusBars())
            hide(WindowInsetsCompat.Type.navigationBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        val view = LocalView.current
        DisposableEffect(view) {
            val listener = ViewTreeObserver.OnGlobalLayoutListener {
                insetsController.hide(WindowInsetsCompat.Type.statusBars())
                insetsController.hide(WindowInsetsCompat.Type.navigationBars())
            }
            view.viewTreeObserver.addOnGlobalLayoutListener(listener)

            onDispose {
                view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
            }
        }
    }
}

fun Context.findActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    return null
}


