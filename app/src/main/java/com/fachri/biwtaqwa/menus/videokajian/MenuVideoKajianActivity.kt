package com.fachri.biwtaqwa.menus.videokajian

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.fachri.biwtaqwa.R
import com.fachri.biwtaqwa.databinding.ActivityMenuVideoKajianBinding
import com.fachri.biwtaqwa.menus.videokajian.adapter.VideoKajianListAdapter
import com.fachri.biwtaqwa.menus.videokajian.data.VideoKajianData
import com.fachri.biwtaqwa.menus.videokajian.model.VideoKajianModel

class MenuVideoKajianActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuVideoKajianBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuVideoKajianBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbarMenuVideoKajian)
        initNavMenu()
    }

    private fun initNavMenu() {
        val list: ArrayList<VideoKajianModel> = arrayListOf()
        binding.rvVideoKajian.setHasFixedSize(true)
        list.addAll(VideoKajianData.listData)
        binding.rvVideoKajian.layoutManager = LinearLayoutManager(this)
        val listVideoAdapter = VideoKajianListAdapter(list)
        binding.rvVideoKajian.adapter = listVideoAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}