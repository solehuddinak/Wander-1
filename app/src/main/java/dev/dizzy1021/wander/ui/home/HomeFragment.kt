package dev.dizzy1021.wander.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.dizzy1021.core.adapter.PlaceAdapter
import dev.dizzy1021.core.adapter.event.OnItemClickCallback
import dev.dizzy1021.core.domain.model.Place
import dev.dizzy1021.core.utils.ResourceState
import dev.dizzy1021.core.utils.SharedPreferenceUtil
import dev.dizzy1021.core.utils.isNetworkAvailable
import dev.dizzy1021.wander.R
import dev.dizzy1021.wander.databinding.FragmentHomeBinding
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var pref: SharedPreferenceUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val actionBar = requireActivity().findViewById<Toolbar>(R.id.main_toolbar)
        actionBar.title = "Home"
        actionBar.isVisible = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PlaceAdapter()

        binding.rvHome.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvHome.adapter = adapter
        binding.rvHome.setHasFixedSize(true)

        adapter.setOnItemClickCallback(object : OnItemClickCallback<Place> {
            override fun onItemClicked(data: Place) {
                navigateToDetailPlace(data.id)
            }
        })

        val user = pref.getUser()
        val page = 1

        if (isNetworkAvailable(requireActivity())) {
            user?.let {
                viewModel.places(page, it).observe(viewLifecycleOwner, { place ->
                    if (place != null) {
                        when (place.state) {
                            ResourceState.PENDING -> {
                                binding.shimmerContainer.startShimmer()
                                binding.shimmerContainer.isVisible = true
                                binding.networkError.isGone = true
                                binding.rvHome.isGone = true
                            }
                            ResourceState.SUCCESS -> {
                                binding.shimmerContainer.stopShimmer()
                                binding.shimmerContainer.isGone = true
                                binding.networkError.isGone = true
                                binding.rvHome.isVisible = true

                                place.data?.let { list ->
                                    adapter.submitList(list)
                                }

                                Log.d("RESPONSE HOME", "Data Size : ${place.data?.size}")
                                // If Data is Empty
                            }
                            ResourceState.FAILURE -> {
                                binding.shimmerContainer.stopShimmer()
                                binding.shimmerContainer.isGone = true
                                binding.networkError.isVisible = true
                                binding.rvHome.isGone = true
                            }
                        }
                    }
                })
            }
        } else {
            binding.shimmerContainer.stopShimmer()
            binding.shimmerContainer.isGone = true
            binding.networkError.isVisible = true
            binding.rvHome.isGone = true
        }
    }

    private fun navigateToDetailPlace(id: Int) {
        val toDetail =
            HomeFragmentDirections.actionHomeFragmentToPlaceFragment(id)
        findNavController().navigate(toDetail)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}