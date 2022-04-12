package com.example.historyvn.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.historyvn.User
import com.example.historyvn.databinding.FragmentTestsBinding
import com.example.historyvn.fragment.adapters.CategoryAdapter
import com.example.historyvn.viewmodels.CategoriesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.RuntimeException

class FragmentTests : Fragment() {

    private var _binding: FragmentTestsBinding? = null
    private val binding: FragmentTestsBinding
        get() = _binding ?: throw RuntimeException()

    private val categoriesViewModel by viewModel<CategoriesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTestsBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fioText.text = "${User.info.firstName} ${User.info.lastName[0]}. ${User.info.midlleName[0]}."
        binding.ratignText.text = User.info.rating.toString()

        binding.categories.layoutManager = LinearLayoutManager(requireContext())
        lifecycleScope.launchWhenCreated {
            binding.categories.adapter = CategoryAdapter(
                categoriesViewModel.loadCategories().map { it to {} }
            )
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}