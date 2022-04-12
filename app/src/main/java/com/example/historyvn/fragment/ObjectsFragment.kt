package com.example.historyvn.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.historyvn.R
import com.example.historyvn.User
import com.example.historyvn.databinding.FragmentObjectsBinding
import com.example.historyvn.databinding.FragmentTestsBinding
import com.example.historyvn.fragment.adapters.ObjectsAdapter
import com.example.historyvn.viewmodels.ObjectsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.RuntimeException


class ObjectsFragment : Fragment() {

    private var _binding: FragmentObjectsBinding? = null
    private val binding: FragmentObjectsBinding
        get() = _binding ?: throw RuntimeException()

    val args by navArgs<ObjectsFragmentArgs>()
    val viewModel by viewModel<ObjectsViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentObjectsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fioText.text = "${User.info.firstName} ${User.info.lastName[0]}. ${User.info.midlleName[0]}."
        binding.ratingText.text = User.info.rating.toString()

        binding.objects.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launchWhenCreated {
            binding.objects.adapter = ObjectsAdapter(
                viewModel.loadObjects(args.categoryId)
            )
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}