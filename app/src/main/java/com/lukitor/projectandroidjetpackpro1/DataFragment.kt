package com.lukitor.projectandroidjetpackpro1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lukitor.projectandroidjetpackpro1.databinding.FragmentDataBinding
import com.lukitor.projectandroidjetpackpro1.viewmodel.ViewModelFactory
import com.lukitor.projectandroidjetpackpro1.vo.Status

class DataFragment : Fragment() {
    private var tipe: String= ""
    private  var fragmentDataBinding: FragmentDataBinding? = null
    private val binding get() = fragmentDataBinding
    private lateinit var viewModel: DataViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {tipe = it.getString("tipe").toString()}
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity!=null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[DataViewModel::class.java]
            binding?.rvFollowing?.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = DataAdapter()
            }
            if (tipe == "All"){
                viewModel.getAllData().observe(this, { courses ->
                    if (courses != null) {
                        when (courses.status) {
                            Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                binding?.progressBar?.visibility = View.GONE
                                binding?.rvFollowing?.adapter?.let { adapter ->
                                    when(adapter){
                                        is DataAdapter -> {
                                            adapter.submitList(courses.data)
                                            adapter.notifyDataSetChanged()
                                        }
                                    }
                                }
                            }
                            Status.ERROR -> {
                                binding?.progressBar?.visibility = View.GONE
                                Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
            else if (tipe == "Movie"){
                viewModel.getMovie().observe(this, { courses ->
                    if (courses != null) {
                        when (courses.status) {
                            Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                binding?.progressBar?.visibility = View.GONE
                                binding?.rvFollowing?.adapter?.let { adapter ->
                                    when(adapter){
                                        is DataAdapter -> {
                                            adapter.submitList(courses.data)
                                            adapter.notifyDataSetChanged()
                                        }
                                    }
                                }
                            }
                            Status.ERROR -> {
                                binding?.progressBar?.visibility = View.GONE
                                Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
            else{
                viewModel.getTV().observe(this, { courses ->
                    if (courses != null) {
                        when (courses.status) {
                            Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                binding?.progressBar?.visibility = View.GONE
                                binding?.rvFollowing?.adapter?.let { adapter ->
                                    when(adapter){
                                        is DataAdapter -> {
                                            adapter.submitList(courses.data)
                                            adapter.notifyDataSetChanged()
                                        }
                                    }
                                }
                            }
                            Status.ERROR -> {
                                binding?.progressBar?.visibility = View.GONE
                                Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        fragmentDataBinding = FragmentDataBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }
    override fun onDestroy() {
        super.onDestroy()
        fragmentDataBinding = null
    }
    companion object {
        @JvmStatic
        fun newInstance(tipe: String) = DataFragment().apply {arguments = Bundle().apply {putString("tipe", tipe)}}
    }
}