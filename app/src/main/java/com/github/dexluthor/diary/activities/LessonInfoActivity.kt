package com.github.dexluthor.diary.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.dexluthor.diary.R
import kotlinx.android.synthetic.main.activity_lesson_info.*


class LessonInfoActivity : AppCompatActivity() {
    // lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_info)
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun onOpenInBrowserButtonClick(view: View) {
//        if (subject_website.text.isBlank()) {
//            Toast.makeText(this, "Subject's website is not specified", Toast.LENGTH_SHORT).show()
//            return
//        }

        webView.webViewClient = WebViewClient()
        webView.loadUrl(/*subject_website.text.toString()*/"https://www.google.com")
        webView.settings.javaScriptEnabled = true
        //TODO implement
    }

    override fun onBackPressed() {
        if (webView.canGoBack())
            webView.goBack()
        else
            super.onBackPressed()
    }

    fun onSendMailButtonClick(view: View) {
        if (teachers_mail.text.isBlank()) {
            Toast.makeText(this, "Teacher's email is not specified", Toast.LENGTH_SHORT).show()
            return
        }

        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.data = Uri.parse("mailto:")
        emailIntent.type = "text/plain"

        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(teachers_mail.text))
        //emailIntent.putExtra(Intent.EXTRA_CC, CC)
        //emailIntent.putExtra(Intent.EXTRA_SUBJECT, "")
        //emailIntent.putExtra(Intent.EXTRA_TEXT, "")
        startActivity(Intent.createChooser(emailIntent, "Send mail..."))
        finish()
    }
}
