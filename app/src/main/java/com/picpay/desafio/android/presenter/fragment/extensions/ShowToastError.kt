package com.picpay.desafio.android.presenter.fragment.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.picpay.desafio.android.R

fun Fragment.showError() {
    Toast.makeText(
        context,
        R.string.error,
        Toast.LENGTH_LONG
    ).show()
}