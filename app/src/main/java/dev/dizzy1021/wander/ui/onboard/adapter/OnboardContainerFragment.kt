package dev.dizzy1021.wander.ui.onboard.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import dev.dizzy1021.core.utils.ARGS_SECTION_NUMBER
import dev.dizzy1021.wander.R
import dev.dizzy1021.wander.databinding.ItemContainerOnboardBinding

@AndroidEntryPoint
class OnboardContainerFragment: Fragment() {

    private var _binding: ItemContainerOnboardBinding? = null
    private val binding get() = _binding as ItemContainerOnboardBinding

    companion object {
        @JvmStatic
        fun newInstance(index: Int) =
            OnboardContainerFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARGS_SECTION_NUMBER, index)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val position = arguments?.getInt(ARGS_SECTION_NUMBER)
        _binding = ItemContainerOnboardBinding.inflate(inflater, container, false)

        when (position) {
            1 -> {

                binding.onboardImage.setImageResource(R.drawable.undraw_destination)
                binding.onboardTitle.text = getString(R.string.onboard_title_one)
                binding.onboardSubtitle.text = getString(R.string.onboard_subtitle_one)

            }
            2 -> {

                binding.onboardImage.setImageResource(R.drawable.undraw_moments)
                binding.onboardTitle.text = getString(R.string.onboard_title_two)
                binding.onboardSubtitle.text = getString(R.string.onboard_subtitle_two)

            }
            else -> {

                binding.onboardImage.setImageResource(R.drawable.undraw_search)
                binding.onboardTitle.text = getString(R.string.onboard_title_three)
                binding.onboardSubtitle.text = getString(R.string.onboard_subtitle_three)
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

}