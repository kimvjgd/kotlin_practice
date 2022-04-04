package com.dongpakka.recyclerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_main_data.*

private const val ARG_MAIN_DATA = "MAIN_DATA"

class MainDataFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var mainData: MainData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mainData = it.getParcelable(ARG_MAIN_DATA)
        }
//        btnBack.setOnClickListener {
//            finish()
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtTitle.text = mainData!!.title
        txtContent.text = mainData!!.content
        Glide.with(this).load(mainData!!.image).into(imageViewDetail)
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(mainData: MainData) =
            MainDataFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_MAIN_DATA, mainData)
                }
            }
    }


}