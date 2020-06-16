package com.github.dexluthor.diary.activities

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.github.dexluthor.diary.R
import com.github.dexluthor.diary.entities.Subject
import com.github.dexluthor.diary.viewModel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_edit_subject.*


class EditSubjectActivity : AppCompatActivity() {
    private val viewModel = ViewModelFactory.getSubjectViewModel()
    private lateinit var subject: Subject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_subject)

        fillFieldsIfHasExtras()
        webView.visibility = View.INVISIBLE
    }

    private fun fillFieldsIfHasExtras() {
        if (intent.extras == null) return

        subject = intent.extras!!.getSerializable("subject") as Subject

        subjectName.setText(subject.name)
        email.setText(subject.email)
        site.setText(subject.site)
    }

    fun saveSubject(view: View) {
        val subject = extractSubjectFromFields()
        if (subject != null) {
            if (viewModel.addSubject(subject)) {
                Snackbar.make(
                    view,
                    resources.getString(R.string.subject_was_added),
                    Snackbar.LENGTH_SHORT
                ).show()
                finish()
                return
            }
            Snackbar.make(view, resources.getString(R.string.subject_exists), Snackbar.LENGTH_SHORT)
                .show()
            return
        }
        Toast.makeText(this, resources.getString(R.string.fill_all_fields), Toast.LENGTH_SHORT)
            .show()
    }

    private fun extractSubjectFromFields(): Subject? {
        return if (subjectName.text.isEmpty()) null
        else Subject(
            subjectName.text.toString(),
            site.text.toString(),
            email.text.toString()
        )
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun onOpenInBrowserButtonClick(view: View) {
        if (intent.extras == null) return
        webView.webViewClient = WebViewClient()
        if (subject.site.isNotEmpty()) {
            webView.loadUrl(subject.site)
            if (webView.isVisible) {
                webView.visibility = View.INVISIBLE
            } else {
                webView.visibility = View.VISIBLE
            }
        } else {
            Toast.makeText(
                this,
                resources.getString(R.string.site_not_provided),
                Toast.LENGTH_SHORT
            ).show()
        }
        webView.settings.javaScriptEnabled = true
    }

    override fun onBackPressed() {
        if (webView.canGoBack())
            webView.goBack()
        else
            super.onBackPressed()
    }

    fun onSendMailButtonClick(view: View) {
        if (intent.extras == null) return

        //https://www.tutorialspoint.com/android/android_sending_email.htm
        if (subject.email.isEmpty()) {
            Toast.makeText(
                this,
                resources.getString(R.string.email_isnt_provided),
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.data = Uri.parse("mailto:")
        emailIntent.type = "text/plain"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(subject.email))

        try {
            startActivity(
                Intent.createChooser(
                    emailIntent,
                    resources.getString(R.string.send_email)
                )
            )
            finish()
        } catch (e: ActivityNotFoundException) {
            Log.w(this.toString(), e)
            Toast.makeText(
                this,
                resources.getString(R.string.no_email_client_installed),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
