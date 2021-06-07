package dev.dizzy1021.wander.ui.place.feedback

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.dizzy1021.core.adapter.ReviewAdapter
import dev.dizzy1021.core.adapter.ReviewLoadStateAdapter
import dev.dizzy1021.core.utils.isNetworkAvailable
import dev.dizzy1021.wander.R
import dev.dizzy1021.wander.databinding.FragmentPlaceReviewBinding
import dev.dizzy1021.wander.ui.place.PlaceFragmentArgs
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlaceReviewFragment : Fragment() {

    private var _binding: FragmentPlaceReviewBinding? = null
    private val binding get() = _binding as FragmentPlaceReviewBinding
    private val viewModel: FeedbackViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()

        val actionBar = requireActivity().findViewById<Toolbar>(R.id.main_toolbar)
        actionBar.title = "Reviews"
        actionBar.isVisible = true

        actionBar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        actionBar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaceReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ReviewAdapter()
        val idPlace = PlaceReviewFragmentArgs.fromBundle(arguments as Bundle).id

        binding.rvReview.layoutManager =
            LinearLayoutManager(context)

        binding.rvReview.adapter = adapter.withLoadStateHeaderAndFooter(
            header = ReviewLoadStateAdapter { adapter.refresh() },
            footer = ReviewLoadStateAdapter { adapter.retry() }
        )

        binding.rvReview.setHasFixedSize(true)

        if (isNetworkAvailable(requireActivity())) {

            lifecycleScope.launch {
                viewModel.reviews(idPlace).collectLatest { reviews ->
                    binding.networkError.isGone = true
                    binding.rvReview.isVisible = true
                    binding.shimmerContainer.isGone = true

                    adapter.submitData(reviews)
                }
            }

        } else {
            binding.shimmerContainer.isGone = true
            binding.networkError.isVisible = true
            binding.rvReview.isGone = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}