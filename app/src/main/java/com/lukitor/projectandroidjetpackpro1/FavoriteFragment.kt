package com.lukitor.projectandroidjetpackpro1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lukitor.projectandroidjetpackpro1.databinding.FragmentFavoriteBinding
import com.lukitor.projectandroidjetpackpro1.viewmodel.ViewModelFactory

class FavoriteFragment : Fragment() {
    private var tipe: String= ""
    private var fragmentDataBinding: FragmentFavoriteBinding? = null
    private val binding get() = fragmentDataBinding
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var dataadapter: FavoriteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {tipe = it.getString("tipe").toString()}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataadapter = FavoriteAdapter()
        if (activity!=null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
            binding?.rvFollowingg?.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = FavoriteAdapter()
            }
            binding?.progressBar?.visibility = View.VISIBLE
            viewModel.getBookmarks(tipe).observe(viewLifecycleOwner, {
                if (it != null) {
                    binding?.rvFollowingg?.adapter?.let { adapters ->
                        when (adapters) {
                            is FavoriteAdapter -> {
                                binding?.progressBar?.visibility = View.GONE
                                adapters.submitList(it)
                                adapters.notifyDataSetChanged()
                            }
                        }
                    }
                }
            })
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        fragmentDataBinding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }
    override fun onDestroy() {
        super.onDestroy()
        fragmentDataBinding = null
    }
    companion object {
        @JvmStatic
        fun newInstance(tipe: String) = FavoriteFragment().apply {arguments = Bundle().apply {putString("tipe", tipe)}}
    }
}