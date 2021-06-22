package ca.example.rickandmorty

import android.app.AlertDialog
import android.content.Context

object Utils {
    fun showAlertDialog(context: Context, title: String, body: String) {
        AlertDialog.Builder(context).apply {
            setTitle(title)
            setMessage(body)
            setPositiveButton("Ok") { _, _ -> }
        }.create().show()
    }
}