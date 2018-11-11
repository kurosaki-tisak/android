package devtee.com.photos.common.helper

import android.content.Context
import android.os.Build
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import java.util.Locale

class TSSHelper(private val context: Context) : UtteranceProgressListener(),
        TextToSpeech.OnInitListener, TextToSpeech.OnUtteranceCompletedListener {

    private var tts: TextToSpeech? = null
    private var locale = Locale.getDefault()
    private var enginePackageName: String? = null
    private var message: String? = null
    private var isRunning: Boolean = false
    private var speakCount: Int = 0

    companion object {
        fun getInstance(context: Context) = TSSHelper(context)
    }

    fun speak(message: String) {
        this.message = message

        if (tts == null || !isRunning) {
            speakCount = 0

            if (enginePackageName != null && !enginePackageName!!.isEmpty()) {
                tts = TextToSpeech(context, this, enginePackageName)
            } else {
                tts = TextToSpeech(context, this)
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                tts!!.setOnUtteranceProgressListener(this)
            } else {
                tts!!.setOnUtteranceCompletedListener(this)
            }

            isRunning = true
        } else {
            startSpeak()
        }
    }

    fun setEngine(packageName: String): TSSHelper {
        enginePackageName = packageName
        return this
    }

    fun setLocale(locale: Locale): TSSHelper {
        this.locale = locale
        return this
    }

    private fun startSpeak() {
        speakCount++

        if (locale != null) {
            tts!!.language = locale
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts!!.speak(message, TextToSpeech.QUEUE_FLUSH, null, "")
        } else {
            tts!!.speak(message, TextToSpeech.QUEUE_FLUSH, null)
        }
    }

    private fun clear() {
        speakCount--

        if (speakCount == 0) {
            tts!!.shutdown()
            isRunning = false
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            startSpeak()
        }
    }

    override fun onStart(utteranceId: String) {}

    override fun onDone(utteranceId: String) {
        clear()
    }

    override fun onError(utteranceId: String) {
        clear()
    }

    override fun onUtteranceCompleted(utteranceId: String) {
        clear()
    }
}