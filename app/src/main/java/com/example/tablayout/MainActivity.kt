package com.example.tablayout

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.tablayout.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {

    //uso de view binding para enlazar la vista
    lateinit var binding: ActivityMainBinding
    //declaramos el adapter
    lateinit var viewPagerAdapter:ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)

        setUpViewPager()
    }

    fun setUpViewPager(){
        //cargamos las paginas con sus respectivos contenidos(fragments)
        viewPagerAdapter.addFragment(Opcion1Fragment(), "Opcion 1")
        viewPagerAdapter.addFragment(Opcion2Fragment(), "Opcion 2")
        viewPagerAdapter.addFragment(Opcion3Fragment(), "Opcion 3")
        val viewPager = binding.viewPager
        viewPager.adapter = viewPagerAdapter
        //con el codigo anterior, funciona como un view pager sin tabs/pestañas

        //agregamos las tabs
        val tablayout = binding.tablayout
        tablayout.setupWithViewPager(viewPager) //asociamos el tablayout con el view pager

        //listener para las tabs para agregar mas personalizacion
        val imagen = binding.imagen
        tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        imagen.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                        imagen.drawable.setTint(Color.GRAY)
                        tablayout.setSelectedTabIndicatorColor(Color.GRAY)
                        Log.d("TAG1", "Posicion: " + tablayout.selectedTabPosition.toString() + " Titulo: ${viewPagerAdapter.getPageTitle(tablayout.selectedTabPosition)}")
                    }
                    1 -> {
                        imagen.setImageResource(R.drawable.ic_baseline_pause_24)
                        imagen.drawable.setTint(Color.RED)
                        tablayout.setSelectedTabIndicatorColor(Color.RED)
                        Log.d("TAG1", "Posicion: " + tablayout.selectedTabPosition.toString() + " Titulo: " + viewPagerAdapter.getPageTitle(tablayout.selectedTabPosition))
                    }
                    2 -> {
                        imagen.setImageResource(R.drawable.ic_baseline_skip_next_24)
                        imagen.drawable.setTint(Color.GREEN)
                        tablayout.setSelectedTabIndicatorColor(Color.GREEN)
                        Log.d("TAG1", "Posicion: ${tablayout.selectedTabPosition} Titulo: ${viewPagerAdapter.getPageTitle(tablayout.selectedTabPosition)}")
                    }
                    else -> {}
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}