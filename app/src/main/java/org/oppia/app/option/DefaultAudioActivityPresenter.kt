package org.oppia.app.option

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.oppia.app.R
import org.oppia.app.activity.ActivityScope
import org.oppia.app.databinding.DefaultAudioActivityBinding
import javax.inject.Inject

/** The presenter for [DefaultAudioActivity]. */
@ActivityScope
class DefaultAudioActivityPresenter @Inject constructor(private val activity: AppCompatActivity) {

  private var prefSummaryValue: String? = null
  private lateinit var languageSelectionAdapter: LanguageSelectionAdapter
  fun handleOnCreate(prefKey: String, prefSummaryValue: String) {
    val binding = DataBindingUtil.setContentView<DefaultAudioActivityBinding>(activity, R.layout.default_audio_activity)

    this.prefSummaryValue = prefSummaryValue
    languageSelectionAdapter = LanguageSelectionAdapter(prefKey)
    binding.audioLanguageRecyclerView.apply {
      adapter = languageSelectionAdapter
    }
    binding.toolbar.setNavigationOnClickListener {
      val message = prefSummaryValue
      val intent = Intent()
      intent.putExtra("MESSAGE", message)
      (activity as DefaultAudioActivity).setResult(3, intent)
      (activity as DefaultAudioActivity).finish()//finishing activity
    }
    createAdapter()
  }

  private fun createAdapter() {
    val languageList = ArrayList<String>()//Creating an empty dummy arraylist
    languageList.add("No Audio")//Adding object in dummy arraylist
    languageList.add("English")
    languageList.add("French")
    languageList.add("Hindi")
    languageList.add("Chinese")
    languageSelectionAdapter.setlanguageList(languageList)

    languageSelectionAdapter.setDefaultlanguageSelected(prefSummaryValue = prefSummaryValue)
  }
}
