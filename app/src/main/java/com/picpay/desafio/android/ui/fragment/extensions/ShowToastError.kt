package com.picpay.desafio.android.ui.fragment.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.picpay.desafio.android.R

fun Fragment.showError() {
     val message = getString(R.string.error)
    Toast.makeText(
        context,
        message,
        Toast.LENGTH_LONG
    ).show()
}