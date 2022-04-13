package com.example.historyvn.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.historyvn.User
import com.example.historyvn.databinding.FragmentTestsListBinding
import com.example.historyvn.fragment.adapters.TestsAdapter
import com.example.historyvn.viewmodels.TestViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class TestsListFragment : Fragment() {


    private var _binding: FragmentTestsListBinding? = null
    private val binding: FragmentTestsListBinding
        get() = _binding ?: throw RuntimeException()

    val args by navArgs<TestsListFragmentArgs>()
    val viewModel by viewModel<TestViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTestsListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fioText.text =
            "${User.info.firstName} ${User.info.lastName[0]}. ${User.info.midlleName[0]}."
        binding.ratingText.text = User.info.rating.toString()

        binding.objects.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launchWhenCreated {
            binding.objects.adapter = TestsAdapter(
                viewModel.loadObjects(args.categoryId).map {
                    it to {
                        findNavController().navigate(
                            TestsListFragmentDirections.actionTestsListFragmentToQuestionsFragment(it.id)
                        )
                    }
                }
            )
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}