package org.oppia.app.option

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import org.oppia.app.R
import org.oppia.app.databinding.LanguageItemsBinding

// TODO(#216): Make use of generic data-binding-enabled RecyclerView adapter.
/** Adapter to bind languages to [RecyclerView] inside [AppLanguageActivityPresenter] and [DefaultAudioActivityPresenter]. */
class LanguageSelectionAdapter(private val prefKey: String) :
  RecyclerView.Adapter<LanguageSelectionAdapter.LanguageViewHolder>() {

  private var prefSummaryValue: String? = null
  private var languageList: List<String> = ArrayList()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
    val languageListItemBinding = DataBindingUtil.inflate<LanguageItemsBinding>(
      LayoutInflater.from(parent.context),
      R.layout.language_items, parent,
      /* attachToRoot= */ false
    )
    return LanguageViewHolder(languageListItemBinding)
  }

  override fun onBindViewHolder(languageViewHolder: LanguageViewHolder, i: Int) {
    languageViewHolder.bind(languageList[i], i)
  }

  override fun getItemCount(): Int {
    return languageList.size
  }

  fun setlanguageList(languageList: List<String>) {
    this.languageList = languageList
    notifyDataSetChanged()
  }

  fun setDefaultlanguageSelected(prefSummaryValue: String?) {
    this.prefSummaryValue = prefSummaryValue
  }

  inner class LanguageViewHolder(val binding: LanguageItemsBinding) : RecyclerView.ViewHolder(binding.root) {
    internal fun bind(language: String, position: Int) {
      binding.setVariable(BR.languageString, language)
      val indexOfPreviouslySelectedValue: Int = languageList.indexOf(prefSummaryValue)
      binding.languageRadioButton.isChecked = position == indexOfPreviouslySelectedValue

      binding.radioContainer.setOnClickListener {
        if (prefKey.equals(binding.radioContainer.context.getString(R.string.key_app_language))) {
          val message = language
          val intent = Intent()
          intent.putExtra("MESSAGE", message)
          (binding.radioContainer.context as AppLanguageActivity).setResult(2, intent)
          (binding.radioContainer.context as AppLanguageActivity).finish()//finishing activity
        } else {
          val message = language
          val intent = Intent()
          intent.putExtra("MESSAGE", message)
          (binding.radioContainer.context as DefaultAudioActivity).setResult(3, intent)
          (binding.radioContainer.context as DefaultAudioActivity).finish()//finishing activity
        }
      }
    }
  }
}
