package com.example.historyvn.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.historyvn.R
import com.example.historyvn.User
import com.example.historyvn.databinding.ActivityMainBinding
import com.example.historyvn.databinding.FragmentTestsBinding
import com.example.historyvn.databinding.FragmentUserBinding
import com.example.historyvn.fragment.adapters.CategoryAdapter
import com.example.historyvn.fragment.adapters.ResultsAdapter
import com.example.historyvn.viewmodels.CategoriesViewModel
import com.example.historyvn.viewmodels.ResultsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentUser : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding: FragmentUserBinding
        get() = _binding ?: throw RuntimeException()

    private val viewModel by viewModel<ResultsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUserBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fioText.text =
            "${User.info.firstName} ${User.info.lastName[0]}. ${User.info.midlleName[0]}."
        binding.ratingText.text = User.info.rating.toString()

        binding.objects.layoutManager = LinearLayoutManager(requireContext())
        lifecycleScope.launchWhenCreated {
            binding.objects.adapter = ResultsAdapter(
                viewModel.loadResults()
            )
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}