package dev.dizzy1021.wander.ui.onboard

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import dagger.hilt.android.AndroidEntryPoint
import dev.dizzy1021.wander.R
import dev.dizzy1021.wander.databinding.FragmentOnboardBinding
import dev.dizzy1021.wander.ui.onboard.adapter.OnboardSectionsAdapter


@AndroidEntryPoint
class OnboardFragment : Fragment() {

    private var _binding: FragmentOnboardBinding? = null
    private val binding get() = _binding as FragmentOnboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val actionBar = requireActivity().findViewById<Toolbar>(R.id.main_toolbar)
        actionBar.isGone = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardBinding.inflate(inflater, container, false)

        val pagerAdapter = OnboardSectionsAdapter(this)
        binding.onboardViewPager.adapter = pagerAdapter

        setOnboardIndicator(pagerAdapter)
        setCurrentOnboardIndicator(0, pagerAdapter)

        binding.onboardViewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentOnboardIndicator(position, pagerAdapter)
            }
        })

        binding.onboardButton.setOnClickListener {
            if (binding.onboardViewPager.currentItem + 1 < pagerAdapter.itemCount) {
                binding.onboardViewPager.currentItem = binding.onboardViewPager.currentItem + 1
            } else {
                findNavController().navigate(R.id.action_onboardFragment_to_homeFragment)
            }
        }

        return binding.root
    }

    private fun setOnboardIndicator(pagerAdapter: OnboardSectionsAdapter) {

        val indicators = arrayOfNulls<ImageView>(pagerAdapter.itemCount)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(requireContext())
            indicators[i]!!.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.onboard_indicator_inactive
                )
            )

            indicators[i]?.layoutParams = layoutParams
            binding.onboardIndicator.addView(indicators[i])
        }

    }

    private fun setCurrentOnboardIndicator(index: Int, pagerAdapter: OnboardSectionsAdapter) {
        val childCount: Int = binding.onboardIndicator.childCount
        for (i in 0 until childCount) {
            val imageView = binding.onboardIndicator.getChildAt(i) as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.onboard_indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.onboard_indicator_inactive
                    )
                )
            }
        }
        if (index == pagerAdapter.itemCount - 1) {
            binding.onboardButton.text = getString(R.string.explore_now)
        } else {
            binding.onboardButton.text = getString(R.string.next)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}