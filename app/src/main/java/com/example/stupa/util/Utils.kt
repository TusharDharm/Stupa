package com.example.stupa.util

import android.content.Context
import android.widget.Toast

fun Context.showToast(context: Context, msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}